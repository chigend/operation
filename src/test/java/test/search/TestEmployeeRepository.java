package test.search;

import com.cookabuy.search.document.Employee;
import com.cookabuy.search.repository.EmployeeRepository;
import lombok.ToString;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author yejinbiao
 * @create 2016-12-15-上午11:09
 */
public class TestEmployeeRepository extends AbstractElasticsearchTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    public void testFind(){
       List<Employee> employees =  employeeRepository.findByName("smith");
        Assert.notNull(employees);
    }
}
