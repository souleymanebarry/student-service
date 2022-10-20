package com.barry.student.unit.controller.impl;

import com.barry.student.controller.impl.HomeControllerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HomeControllerImplTest {

    @InjectMocks
    HomeControllerImpl homeController;

    @Test
    void redirectToSwaggerUi_shouldReturnARedirectStringToSwaggerUi() {
        assertEquals("redirect:/swagger-ui.html",homeController.redirectToSwaggerUi());
    }
}