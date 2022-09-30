package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.TransactionDao;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public class TransactionDaoImpl extends CrudDaoImpl<Transaction, Long> implements TransactionDao {

    private final TransactionRepository transactionRepository;

    protected TransactionDaoImpl(JpaRepository<Transaction, Long> repository, TransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<Transaction> findTransactionByStudent(Pageable pageable, Student student) {
        return transactionRepository.findTransactionByStudent(pageable,student);
    }

    @Override
    public Page<Transaction> findTransactionByTeacher(Pageable pageable, Teacher teacher) {
        return transactionRepository.findTransactionByTeacher(pageable, teacher);
    }
}
