package com.piritter.api.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PirateServiceTests {

    @Autowired
    private PirateService pirateService;

    @Test
    public void testTranslate() {
        String translatedWord = pirateService.translate("yo ho ho");
        assertNotEquals(0, translatedWord.length());
    }
}
