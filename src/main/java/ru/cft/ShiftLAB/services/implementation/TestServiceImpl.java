package ru.cft.ShiftLAB.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.ShiftLAB.repositories.TestRepository;
import ru.cft.ShiftLAB.repositories.implementation.TestRepositoryImpl;
import ru.cft.ShiftLAB.services.TestService;

@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Override
    public String getTest() {
        return testRepository.getTest();
    }
}
