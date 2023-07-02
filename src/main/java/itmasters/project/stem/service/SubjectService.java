package itmasters.project.stem.service;

import com.google.gson.Gson;
import itmasters.project.stem.entity.subject.Subject;
import itmasters.project.stem.entity.TakenSubject;
import itmasters.project.stem.entity.Topic;
import itmasters.project.stem.entity.TopicProgress;
import itmasters.project.stem.entity.subject.SubjectAttachment;
import itmasters.project.stem.entity.subject.SubjectAttachmentContent;
import itmasters.project.stem.payload.subject.SubjectDTO;
import itmasters.project.stem.payload.subject.SubjectEn;
import itmasters.project.stem.payload.subject.SubjectResponse;
import itmasters.project.stem.payload.subject.SubjectUz;
import itmasters.project.stem.payload.takenSubject.IsCompletedResponse;
import itmasters.project.stem.repository.subject.SubjectAttachmentContentRepository;
import itmasters.project.stem.repository.subject.SubjectAttachmentRepository;
import itmasters.project.stem.repository.subject.SubjectRepository;
import itmasters.project.stem.repository.TakenSubjectRepository;
import itmasters.project.stem.repository.TopicProgressRepository;
import itmasters.project.stem.repository.TopicRepository;
import itmasters.project.stem.security.config.JwtService;
import itmasters.project.stem.security.user.User;
import itmasters.project.stem.security.user.UserRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final TopicProgressRepository topicProgressRepository;
    private final TopicRepository topicRepository;
    private final JwtService jwtService;
    private final TakenSubjectRepository takenSubjectRepository;
    private final SubjectAttachmentRepository subjectAttachmentRepository;
    private final SubjectAttachmentContentRepository subjectAttachmentContentRepository;

//    @PersistenceContext
//    private EntityManager entityManager;

    public SubjectService(
            SubjectRepository subjectRepository,
            UserRepository userRepository,
            TopicProgressRepository topicProgressRepository,
            TopicRepository topicRepository,
            JwtService jwtService,
            TakenSubjectRepository takenSubjectRepository,
            SubjectAttachmentRepository subjectAttachmentRepository,
            SubjectAttachmentContentRepository subjectAttachmentContentRepository
    ) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.topicProgressRepository = topicProgressRepository;
        this.topicRepository = topicRepository;
        this.jwtService = jwtService;
        this.takenSubjectRepository = takenSubjectRepository;
        this.subjectAttachmentRepository = subjectAttachmentRepository;
        this.subjectAttachmentContentRepository = subjectAttachmentContentRepository;
    }

    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    //    public Optional<Subject> getSubjectWithAttachmentAndContent(Integer id) {
    //        EntityGraph<?> entityGraph = entityManager.getEntityGraph("subject-with-attachment-and-content");
    //        return subjectRepository.findById(id, entityGraph);
    //    }

    public List<SubjectResponse> getAllSubject1() {
        List<Subject> subjectList = subjectRepository.findAll();
        List<SubjectResponse> subjectResponseList = new ArrayList<>();
        for (Subject subject : subjectList) {
            SubjectResponse subjectResponse =
                    SubjectResponse
                            .builder()
                            .subjectNameEn(subject.getSubjectNameEn())
                            .topicCount(subject.getTopics().size())
                            .subjectLogo(subject.getSubjectAttachment() == null ?
                                    null : subject.getSubjectAttachment().getSubjectAttachmentContent().getBytes())
                            .build();
            subjectResponseList.add(subjectResponse);
        }
        return subjectResponseList;
    }

    public Object getAllSubjectByLanguage1(String language) throws JSONException {

        List<Subject> subjectList = subjectRepository.findAll();


        JSONArray jsonArray = new JSONArray(subjectList);

        System.out.println("jsonArray = " + jsonArray);


        List<Object> uzSubjectList = new ArrayList<>();
        List<Object> enSubjectList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject subjectObject = jsonArray.getJSONObject(i);
            int id = subjectObject.getInt("id");
            String subjectNameUz = subjectObject.getString("subjectNameUz");
            double price = subjectObject.getDouble("price");
            int streak = subjectObject.getInt("streak");
            int coins = subjectObject.getInt("coins");
            String streakFirstDay = subjectObject.getString("streakFirstDay");

            JSONArray topics = subjectObject.getJSONArray("topics");
            List<Object> uzTopicList = new ArrayList<>();

            for (int j = 0; j < topics.length(); j++) {
                JSONObject topicObject = topics.getJSONObject(j);
                int topicId = topicObject.getInt("id");
                String topicNameUz = topicObject.getString("topicNameUz");
                String topicCoins = topicObject.getString("coins");

                // Create topic object
                JSONObject uzTopic = new JSONObject();
                uzTopic.put("id", topicId);
                uzTopic.put("topicNameUz", topicNameUz);
                uzTopic.put("coins", topicCoins);
                uzTopic.put("quiz", new ArrayList<>());

                uzTopicList.add(uzTopic);
            }

            // Create subject object
            JSONObject uzSubject = new JSONObject();
            uzSubject.put("id", id);
            uzSubject.put("subjectNameUz", subjectNameUz);
            uzSubject.put("price", price);
            uzSubject.put("streak", streak);
            uzSubject.put("coins", coins);
            uzSubject.put("streakFirstDay", streakFirstDay);
            uzSubject.put("topics", uzTopicList);

            uzSubjectList.add(uzSubject);
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject subjectObject = jsonArray.getJSONObject(i);
            int id = subjectObject.getInt("id");
            String subjectNameEn = subjectObject.getString("subjectNameEn");
            double price = subjectObject.getDouble("price");
            int streak = subjectObject.getInt("streak");
            int coins = subjectObject.getInt("coins");
            String streakFirstDay = subjectObject.getString("streakFirstDay");

            JSONArray topics = subjectObject.getJSONArray("topics");
            List<Object> enTopicList = new ArrayList<>();

            for (int j = 0; j < topics.length(); j++) {
                JSONObject topicObject = topics.getJSONObject(j);
                int topicId = topicObject.getInt("id");
                String topicNameEn = topicObject.getString("topicNameEn");
                String topicCoins = topicObject.getString("coins");

                // Create topic object
                JSONObject enTopic = new JSONObject();
                enTopic.put("id", topicId);
                enTopic.put("topicNameEn", topicNameEn);
                enTopic.put("coins", topicCoins);
                enTopic.put("quiz", new ArrayList<>());

                enTopicList.add(enTopic);
            }

            // Create subject object
            JSONObject enSubject = new JSONObject();
            enSubject.put("id", id);
            enSubject.put("subjectNameEn", subjectNameEn);
            enSubject.put("price", price);
            enSubject.put("streak", streak);
            enSubject.put("coins", coins);
            enSubject.put("streakFirstDay", streakFirstDay);
            enSubject.put("topics", enTopicList);

            enSubjectList.add(enSubject);
        }

        return language.equals("uz") ? uzSubjectList : language.equals("en") ? enSubjectList : null;
    }


    public Object getAllSubjectByLanguage(String language) throws JSONException {
        List<Subject> subjectList = subjectRepository.findAll();

        Gson gson = new Gson();
        String json1 = gson.toJson(subjectList.get(0));
        System.out.println("json1 = " + json1);
        String json3 = gson.toJson(subjectList.get(3));
        System.out.println("json3 = " + json3);
        String json5 = gson.toJson(subjectList.get(1));
        System.out.println("json5 = " + json5);
        String json4 = gson.toJson(subjectList.get(4));
        System.out.println("json4 = " + json4);
        String json2 = gson.toJson(subjectList.get(2));
        System.out.println("json2 = " + json2);

        for (Subject subject : subjectList) {
            String json = gson.toJson(subject);
            System.out.println("json = " + json);
        }

        for (int i = 0; i < subjectList.size(); i++) {
            JSONObject jsonObject = new JSONObject(subjectList.get(i).toString());
            System.out.println("jsonObject = " + jsonObject);
        }
        List<SubjectUz> uzSubjectList = new ArrayList<>();
        List<SubjectEn> enSubjectList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonObject.length(); i++) {
            JSONObject subjectObject = jsonObject.getJSONObject("i");
            int id = subjectObject.getInt("id");
            String subjectNameUz = subjectObject.getString("subjectNameUz");
            double price = subjectObject.getDouble("price");
            int streak = subjectObject.getInt("streak");
            int coins = subjectObject.getInt("coins");
            String streakFirstDay = subjectObject.getString("streakFirstDay");

            SubjectUz subjectUz = new SubjectUz();
            subjectUz.setId(id);
            subjectUz.setSubjectNameUz(subjectNameUz);
            subjectUz.setPrice(price);
            subjectUz.setStreak(streak);
            subjectUz.setCoins(coins);
            subjectUz.setStreakFirstDay(Date.valueOf(streakFirstDay));

//            jsonArray.optJSONObject(i).get("id");
//            jsonArray.optJSONObject(i).get("subjectNameUz");
//            jsonArray.optJSONObject(i).get("price");
//            jsonArray.optJSONObject(i).get("streak");
//            jsonArray.optJSONObject(i).get("coins");
//            jsonArray.optJSONObject(i).get("streakFirstDay");
//            JSONArray topics = jsonArray.getJSONObject(i).optJSONArray("topics");
//
//            topics.getJSONObject(i).get("id");
//            topics.getJSONObject(i).get("topicNameUz");
//            topics.getJSONObject(i).get("coins");
            uzSubjectList.add(subjectUz);
        }

//        for (int i = 0; i < jsonArray.length(); i++) {
//            jsonArray.getJSONObject(i).get("id");
//            jsonArray.getJSONObject(i).get("subjectNameEn");
//            jsonArray.getJSONObject(i).get("price");
//            jsonArray.getJSONObject(i).get("streak");
//            jsonArray.getJSONObject(i).get("coins");
//            jsonArray.getJSONObject(i).get("streakFirstDay");
//
//            jsonArray.getJSONObject(i).getJSONArray("topics").getJSONObject(i).get("id");
//            jsonArray.getJSONObject(i).getJSONArray("topics").getJSONObject(i).get("topicNameEn");
//            jsonArray.getJSONObject(i).getJSONArray("topics").getJSONObject(i).get("coins");
//            enSubjectList.add(jsonArray);
//        }
        return language.equals("uz") ? uzSubjectList : language.equals("en") ? enSubjectList : null;
    }

    public Subject getSubjectById(Integer subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }

    public Subject createSubject(SubjectDTO subjectDTO) throws IOException {

        Subject subject = new Subject();
        subject.setSubjectNameEn(subjectDTO.getSubjectNameEn());
        subject.setSubjectNameUz(subjectDTO.getSubjectNameUz());
        subject.setStreak(subjectDTO.getStreak());
        subject.setPrice(subjectDTO.getPrice());
        subject.setCoins(subjectDTO.getCoins());
        subject.setStreakFirstDay(subjectDTO.getStreakFirstDay());
        Subject savedSubject = subjectRepository.save(subject);

        SubjectAttachment subjectAttachment =
                SubjectAttachment
                        .builder()
                        .fileOriginalName(subjectDTO.getSubjectLogo().getOriginalFilename())
                        .size(subjectDTO.getSubjectLogo().getSize())
                        .contentType(subjectDTO.getSubjectLogo().getContentType())
                        .subject(savedSubject)
                        .build();
        SubjectAttachment savedSubjectAttachment =
                subjectAttachmentRepository.save(subjectAttachment);

        SubjectAttachmentContent subjectAttachmentContent =
                SubjectAttachmentContent
                        .builder()
                        .bytes(subjectDTO.getSubjectLogo().getBytes())
                        .subjectAttachment(savedSubjectAttachment)
                        .build();
        subjectAttachmentContentRepository.save(subjectAttachmentContent);

        return savedSubject;
    }

    public Subject updateSubject(Integer subjectId, SubjectDTO subjectDTO) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }
        Subject updatedSubject = optionalSubject.get();
        updatedSubject.setSubjectNameUz(subjectDTO.getSubjectNameUz());
        updatedSubject.setSubjectNameEn(subjectDTO.getSubjectNameEn());
        updatedSubject.setPrice(subjectDTO.getPrice());
        updatedSubject.setStreak(subjectDTO.getStreak());
        updatedSubject.setStreakFirstDay(subjectDTO.getStreakFirstDay());
        updatedSubject.setCoins(subjectDTO.getCoins());
        return subjectRepository.save(updatedSubject);
    }

    public String deleteSubject(Integer subjectId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }
        subjectRepository.deleteById(optionalSubject.get().getId());
        return "Subject deleted successfully";
    }

    public IsCompletedResponse isSubjectCompleted(String language, Integer subjectId, @NotNull HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        Integer userId = userRepository.findIdByUsername(username);
        User user = userRepository.findById(userId).orElseThrow();
        String subjectNameUz = subjectRepository.findSubjectNameUzBySubjectId(subjectId);
        String subjectNameEn = subjectRepository.findSubjectNameEnBySubjectId(subjectId);
        TakenSubject takenSubject = takenSubjectRepository.findByUserIdAndSubjectId(userId, subjectId).orElseThrow();
        if (takenSubject.toString().isEmpty()) {
            return null;
        }

        if (language.equals("uz")) {
            List<String> passedTopicUz = new ArrayList<>();
            List<String> failedTopicUz = new ArrayList<>();
            List<Topic> topicList = topicRepository.findAllTopicsBySubjectId(subjectId);
            for (int i = 0; i < topicList.size(); i++) {
                Integer topicId = topicList.get(i).getId();
                String topicNameUz = topicRepository.findTopicNameUzByTopicId(topicId);
                TopicProgress topicProgress = topicProgressRepository.findByTopicAndUser_Id(topicId, userId);
                if (topicProgress == null) {
                    takenSubject.setCompleted(false);
                    break;
                }
                log.info("topicProgress: {}", topicProgress);
                if (topicProgress.isCompleted()) {
                    passedTopicUz.add(topicNameUz);
                } else {
                    failedTopicUz.add(topicNameUz);
                }
            }

            if (topicList.size() != passedTopicUz.size() + failedTopicUz.size() || topicList.isEmpty()) {
                takenSubject.setCompleted(false);
                takenSubjectRepository.save(takenSubject);
                return IsCompletedResponse.builder()
                        .userName(user.getFirstName() + " " + user.getLastName())
                        .subjectName(subjectNameUz)
                        .isCompleted(false)
                        .build();
            }
            log.info("passedTopicUz: {}", passedTopicUz);
            if (failedTopicUz.isEmpty()) {
                takenSubject.setCompleted(true);
                takenSubjectRepository.save(takenSubject);
                return IsCompletedResponse.builder()
                        .userName(user.getFirstName() + " " + user.getLastName())
                        .subjectName(subjectNameUz)
                        .isCompleted(true)
                        .build();
            } else {
                takenSubject.setCompleted(false);
                takenSubjectRepository.save(takenSubject);
                return IsCompletedResponse.builder()
                        .userName(user.getFirstName() + " " + user.getLastName())
                        .subjectName(subjectNameUz)
                        .isCompleted(false)
                        .build();
            }
        } else if (language.equals("en")) {
            List<String> passedTopicEn = new ArrayList<>();
            List<String> failedTopicEn = new ArrayList<>();
            List<Topic> topicList = topicRepository.findAllTopicsBySubjectId(subjectId);
            for (int i = 0; i < topicList.size(); i++) {
                Integer topicId = topicList.get(i).getId();
                String topicNameEn = topicRepository.findTopicNameEnByTopicId(topicId);
                TopicProgress topicProgress = topicProgressRepository.findByTopicAndUser_Id(topicId, userId);
                if (topicProgress.isCompleted()) {
                    passedTopicEn.add(topicNameEn);
                } else {
                    failedTopicEn.add(topicNameEn);
                }
            }
            if (topicList.size() != passedTopicEn.size() + failedTopicEn.size() || topicList.isEmpty()) {
                takenSubject.setCompleted(false);
                takenSubjectRepository.save(takenSubject);
                return IsCompletedResponse.builder()
                        .userName(user.getFirstName() + " " + user.getLastName())
                        .subjectName(subjectNameEn)
                        .isCompleted(false)
                        .build();
            }
            log.info("passedTopicEn: {}", passedTopicEn);
            if (failedTopicEn.isEmpty()) {
                takenSubject.setCompleted(true);
                takenSubjectRepository.save(takenSubject);
                return IsCompletedResponse.builder()
                        .userName(user.getFirstName() + " " + user.getLastName())
                        .subjectName(subjectNameEn)
                        .isCompleted(true)
                        .build();
            } else {
                takenSubject.setCompleted(false);
                takenSubjectRepository.save(takenSubject);
                return IsCompletedResponse.builder()
                        .userName(user.getFirstName() + " " + user.getLastName())
                        .subjectName(subjectNameEn)
                        .isCompleted(false)
                        .build();
            }
        }

        return null;

    }

    public IsCompletedResponse isSubjectCompleted1(
            String language, Integer subjectId,
            @NotNull HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        Integer userId = userRepository.findIdByUsername(username);
        User user = userRepository.findById(userId).orElseThrow();

        TakenSubject takenSubject = takenSubjectRepository.findByUserIdAndSubjectId(userId, subjectId)
                .orElseThrow();

        if (takenSubject.toString().isEmpty()) {
            return null;
        }

        String subjectName = getSubjectNameByLanguageAndSubjectId(language, subjectId);
        List<String> passedTopics, failedTopics;
        if (language.equals("uz")) {
            passedTopics = new ArrayList<>();
            failedTopics = new ArrayList<>();
            checkTopics(language, userId, subjectId, passedTopics, failedTopics);
        } else {
            passedTopics = new ArrayList<>();
            failedTopics = new ArrayList<>();
            checkTopics(language, userId, subjectId, passedTopics, failedTopics);
        }

        boolean isCompleted = (failedTopics.isEmpty()) ? true : false;
        takenSubject.setCompleted(isCompleted);
        takenSubjectRepository.save(takenSubject);

        return IsCompletedResponse.builder()
                .userName(user.getFirstName() + " " + user.getLastName())
                .subjectName(subjectName)
                .isCompleted(isCompleted)
                .build();
    }

    private String getSubjectNameByLanguageAndSubjectId(String language, Integer subjectId) {
        return language.equals("uz") ? subjectRepository.findSubjectNameUzBySubjectId(subjectId)
                : language.equals("en") ? subjectRepository.findSubjectNameEnBySubjectId(subjectId) : null;
    }

    private void checkTopics(String language, Integer userId, Integer subjectId,
                             List<String> passedTopics, List<String> failedTopics) {
        List<Topic> topicList = topicRepository.findAllTopicsBySubjectId(subjectId);
        for (Topic topic : topicList) {
            Integer topicId = topic.getId();
            String topicName = language.equals("uz") ? topicRepository.findTopicNameUzByTopicId(topicId)
                    : language.equals("en") ? topicRepository.findTopicNameEnByTopicId(topicId) : null;

            TopicProgress topicProgress = topicProgressRepository.findByTopicAndUser_Id(topicId, userId);

            if (topicProgress.isCompleted()) {
                passedTopics.add(topicName);
            } else {
                failedTopics.add(topicName);
            }
        }
    }

}
