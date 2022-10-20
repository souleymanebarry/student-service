package com.barry.student.controller.impl;

import com.barry.student.controller.HomeController;
import org.springframework.stereotype.Controller;

/**
 * Default implementation of the {@link HomeController}
 */

@Controller
public class HomeControllerImpl implements HomeController {


    @Override
    public String redirectToSwaggerUi() {
        return "redirect:/swagger-ui.html";
    }
}
