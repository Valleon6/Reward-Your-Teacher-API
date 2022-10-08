package com.valleon.rewardyourteacherapi.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Position;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Status;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher extends AbstractEntity {
//    name = "TeacherDao Name",
    @Column( unique = true, nullable = false, columnDefinition = "VARCHAR(250)")
    private String name;

//    name = "Phone Number",
    @Column( unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private String phoneNumber;

//    name = "National Identity Number",
    @Column( unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private String nin;

//    name = "Display Picture",
    @Column( unique = true, nullable = false, columnDefinition = "VARCHAR(250)")
    private String displayPicture;

//    name = "Years Taught",
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(250)")
    private Integer yearsOfTeaching;

    @ManyToOne
    @JsonBackReference
    private AppUser appUser;

    @ManyToOne
    @JoinColumn
    private School school;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private String subjectTaught;

    @Column(columnDefinition = "VARCHAR(250)")
    private String about;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    private Position position;

    @Column(unique = true, columnDefinition = "VARCHAR(100)")
    private String title;

    @JsonManagedReference
    @OneToOne(mappedBy = "teacher", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Wallet wallet;

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Notification> notificationList = new ArrayList<>();

//    @JsonManagedReference
//    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
//    private List<Student> studentList = new ArrayList<>();

}
