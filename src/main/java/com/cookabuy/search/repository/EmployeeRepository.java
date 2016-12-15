package com.cookabuy.search.repository;

import com.cookabuy.search.document.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author yejinbiao
 * @create 2016-12-15-上午10:00
 */

public interface EmployeeRepository extends ElasticsearchRepository<Employee,Integer> {
    List<Employee> findByName(String name);
}
