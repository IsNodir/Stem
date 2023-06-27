//package itmasters.project.stem.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "_user")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @NotNull
//    @Column(unique = true)
//    private String username;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String password;
//
//    private String phoneNumber;
//
////    @Enumerated(EnumType.STRING)
//    private UserRole role;
//
//}