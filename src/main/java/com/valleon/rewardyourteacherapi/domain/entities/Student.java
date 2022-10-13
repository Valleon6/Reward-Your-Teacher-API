package com.valleon.rewardyourteacherapi.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(columnDefinition = "VARCHAR (250)")
    private String displayPicture;

    @Column(columnDefinition = "VARCHAR(100)")
    private String phoneNumber;

    @Column(nullable = false)
    private String title;

    @JsonBackReference
    @ManyToOne
    Teacher teacher;

    @ManyToOne
    @JsonBackReference
    private AppUser appUser;

    @ManyToOne
    @JoinColumn
    private School school;

    @JsonManagedReference
    @OneToOne (mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wallet wallet;

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionList = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notificationList = new ArrayList<>();


}
