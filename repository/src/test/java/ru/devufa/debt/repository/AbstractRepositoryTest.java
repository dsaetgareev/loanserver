package ru.devufa.debt.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.common.EntityWithId;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public abstract class AbstractRepositoryTest<T extends EntityWithId> {

    protected abstract T generateEntity();

    @Autowired
    protected JpaRepository<T, UUID> repository;

    @Test
    public void create() {
        T saved = repository.saveAndFlush(generateEntity());
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotNull(saved.getCreated());
        assertNotNull(saved.getModified());
    }

    @Test
    public void read() {
        T saved = repository.saveAndFlush(generateEntity());
        T one = repository.getOne(saved.getId());
        assertNotNull(one);
        assertEquals(one.getId(), saved.getId());
        assertEquals(one.getCreated(), saved.getCreated());
    }

    @Test
    public void update() {
        T saved = repository.saveAndFlush(generateEntity());
        Date date = new Date();
        saved.setCreated(date);
        repository.saveAndFlush(saved);
        T read = repository.getOne(saved.getId());
        assertEquals(date, read.getCreated());
    }
}
