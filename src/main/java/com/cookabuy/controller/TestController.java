package com.cookabuy.controller;

import com.cookabuy.search.document.Employee;
import com.cookabuy.search.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2016/12/12
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @ResponseBody
    @RequestMapping("test")
    public Object test(){
        log.info("employeeRepository :{}",employeeRepository);
        return employeeRepository.findByName("smith");
    }
}
