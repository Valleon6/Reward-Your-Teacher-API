package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.WalletDao;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.WalletRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletDaoImpl extends CrudDaoImpl<Wallet, Long> implements WalletDao {

    public final WalletRepository walletRepository;

    protected WalletDaoImpl(JpaRepository<Wallet, Long> repository, WalletRepository walletRepository) {
        super(repository);
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet findWalletByStudent(Student student) {
        return walletRepository.findWalletByStudent(student);
    }

    @Override
    public Optional<Wallet> findWalletByTeacher(Teacher teacher) {
        return walletRepository.findWalletByTeacher(teacher);
    }
}
