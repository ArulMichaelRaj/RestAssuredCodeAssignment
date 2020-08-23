package com.assingment.api.operations.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeAppController {


    @RequestMapping(value = "/apitest", method = RequestMethod.GET)
    public String launchPage() {
        return "main";
    }
}

