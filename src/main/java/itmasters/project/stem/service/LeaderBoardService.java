package itmasters.project.stem.service;

import itmasters.project.stem.payload.LeaderBoardDTO;
import itmasters.project.stem.security.config.JwtService;
import itmasters.project.stem.security.user.User;
import itmasters.project.stem.security.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LeaderBoardService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LeaderBoardService(
            UserRepository userRepository,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public List<LeaderBoardDTO> getAllUsers() {
        return userRepository.getAllUsersAndOrderByCoins();
    }

    public User getUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer", "");
        String username = jwtService.extractUsername(token);
        return userRepository.findByUsername(username).orElseThrow();
    }

}
