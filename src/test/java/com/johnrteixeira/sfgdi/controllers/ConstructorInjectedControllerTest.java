package com.johnrteixeira.sfgdi.controllers;

import com.johnrteixeira.sfgdi.services.ConstructorGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConstructorInjectedControllerTest {

    ConstructorController controller;

    @BeforeEach
    void setUp() {
        controller = new ConstructorController(new ConstructorGreetingService());
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}