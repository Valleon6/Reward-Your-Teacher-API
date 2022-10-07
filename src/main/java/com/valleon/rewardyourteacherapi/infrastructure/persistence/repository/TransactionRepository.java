package com.valleon.rewardyourteacherapi.infrastructure.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findTransactionByStudent(Pageable pageable, Student student);

    Page<Transaction> findTransactionByTeacher(Pageable pageable, Teacher teacher);

}
