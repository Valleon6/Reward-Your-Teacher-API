package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionDao extends CrudDAO<Transaction, Long> {
    Page<Transaction> fireTransactionByStudent(Pageable pageable, Student student);
    Page<Transaction> findTransactionByTeacher(Pageable pageable, Teacher teacher);

}
