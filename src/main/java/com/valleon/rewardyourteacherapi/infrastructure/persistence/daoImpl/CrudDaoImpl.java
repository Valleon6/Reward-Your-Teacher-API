package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.CrudDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudDaoImpl<T, ID> implements CrudDAO<T, ID> {

    private final JpaRepository<T, ID> repository;


    protected CrudDaoImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T getRecordById(ID id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found. Entity with Id: " + id));
    }

    @Override
    public T saveRecord(T record) {
        return repository.saveAndFlush(record);
    }

    @Override
    public List<T> saveAllRecord(List<T> records) {
        return repository.saveAll(records);
    }

    @Override
    public void deleteRecord(T record) {
        repository.delete(record);
    }

    @Override
    public List<T> getAllRecords() {
        return repository.findAll();
    }
}
