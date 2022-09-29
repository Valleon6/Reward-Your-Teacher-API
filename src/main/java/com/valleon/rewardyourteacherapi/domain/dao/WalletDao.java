package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;

import java.util.Optional;

public interface WalletDao extends CrudDAO<Wallet, Long>{
    Optional<Wallet> findWalletByStudent(Student student);

    Optional<Wallet> findWalletByTeacher(Long teacher);
}
