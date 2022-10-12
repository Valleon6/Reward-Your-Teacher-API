package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;

import java.util.Optional;

public interface WalletDao extends CrudDAO<Wallet, Long>{
    Wallet findWalletByStudent(Student student);

    Optional<Wallet> findWalletByTeacher(Teacher teacher);
}
