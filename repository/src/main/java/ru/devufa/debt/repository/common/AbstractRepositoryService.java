package ru.devufa.debt.repository.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.devufa.debt.entity.common.EntityWithId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractRepositoryService<T extends EntityWithId> implements RepositoryService<T>{

    @Autowired
    private JpaRepository<T, UUID> repository;

    @Override
    @Transactional
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T read(UUID uuid) {
        Optional<T> debt = repository.findById(uuid);
        return debt.orElse(null);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        T one = repository.getOne(uuid);
        repository.delete(one);
    }

    @Override
    @Transactional
    public List<T> list() {
        return repository.findAll();
    }
}
