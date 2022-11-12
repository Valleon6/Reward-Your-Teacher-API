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
@Table(name = "teachers")
public class Teacher extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(250)")
    private String name;

    @Column( unique = true, columnDefinition = "VARCHAR(100)")
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(100)")
    private String nin;

    @Column(columnDefinition = "VARCHAR(250)")
    private String displayPicture;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(250)")
    private String yearsOfTeaching;

    @ManyToOne
    @JsonBackReference
    private AppUser appUser;

    @ManyToOne
    @JoinColumn
    private School school;

    @Column(columnDefinition = "VARCHAR(100)")
    private String subjectTaught;

    @Column(columnDefinition = "VARCHAR(250)")
    private String about;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "teacher_status")
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

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Student> studentList = new ArrayList<>();

}
