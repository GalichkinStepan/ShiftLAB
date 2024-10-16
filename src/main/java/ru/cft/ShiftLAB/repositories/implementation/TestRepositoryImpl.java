package ru.cft.ShiftLAB.repositories.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.cft.ShiftLAB.repositories.TestRepository;

@Repository(value = "testRepository")
public class TestRepositoryImpl implements TestRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String getTest() {
        return "Hello World!";
    }
}
