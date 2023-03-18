package io.bayrktlihn.employeecrudapp;

import io.bayrktlihn.employeecrudapp.configs.JpaConfig;
import io.bayrktlihn.employeecrudapp.entities.Employee;
import io.bayrktlihn.employeecrudapp.repositories.EmployeeRepository;
import io.bayrktlihn.employeecrudapp.repositories.EmployeeRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

public class JpaTest {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();


    @Test
    void test() {
        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();


        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNativeQuery("select * from person");

        List resultList = query.getResultList();

        for (Object o : resultList) {
            System.out.println(Arrays.toString((Object[]) o));
        }
    }

    @Test
    void getEmployees() {
        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Employee> employeeQuery = entityManager.createQuery("select e from Employee e", Employee.class);

        List<Employee> resultList = employeeQuery.getResultList();

        Assertions.assertEquals(resultList.size(), 9);
    }

    @Test
    void saveEmployee() {
        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = new Employee();
        employee.setName("alihan");
        employee.setPhone("5356993232");
        employee.setAddress("aliqweqwewq");
        employee.setEmail("bayrktlihn@bayrktlihn.io");

        employeeRepository.save(employee);

    }

}
