package ru.devufa.debt.repository.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.devufa.debt.entity.common.EntityWithId;
import ru.devufa.debt.repository.RepositoryServiceTestConfiguration;
import ru.devufa.debt.repository.common.RepositoryService;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryServiceTestConfiguration.class})
@ActiveProfiles({"test"})
@Transactional
public abstract class AbstractRepositoryTest<T extends EntityWithId> {

    protected abstract T generateEntity();

    @Autowired
    protected RepositoryService<T> repository;

    @Test
    public void create() {
        T saved = repository.create(generateEntity());
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotNull(saved.getCreated());
        assertNotNull(saved.getModified());
    }

    @Test
    public void read() {
        T saved = repository.create(generateEntity());
        T one = repository.read(saved.getId());
        assertNotNull(one);
        assertEquals(one.getId(), saved.getId());
        assertEquals(one.getCreated(), saved.getCreated());
    }

    @Test
    public void update() {
        T saved = repository.create(generateEntity());
        Date date = new Date();
        saved.setCreated(date);
        repository.update(saved);
        T read = repository.read(saved.getId());
        assertEquals(date, read.getCreated());
    }
}
