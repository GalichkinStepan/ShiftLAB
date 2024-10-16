package ru.cft.ShiftLAB.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.ShiftLAB.services.TestService;

@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public String getNameApplication() {
        return testService.getTest();
    }

}
