package io.bayrktlihn.employeecrudapp.repositories;

import io.bayrktlihn.employeecrudapp.configs.JpaConfig;
import io.bayrktlihn.employeecrudapp.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> findAll() {
        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Employee> employeeQuery = entityManager.createQuery("select e from Employee e order by e.id", Employee.class);


        return employeeQuery.getResultList();
    }

    @Override
    public Employee findById(Long id) {

        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Employee> employeeQuery = entityManager.createQuery("select e from Employee e where e.id = :id", Employee.class);

        return employeeQuery.getSingleResult();

    }

    @Override
    public void deleteById(Long id) {
        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        entityManager.remove(entityManager.getReference(Employee.class, id));

        tx.commit();
    }

    @Override
    public Employee save(Employee employee) {

        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        Employee mergedEmployee = entityManager.merge(employee);

        tx.commit();

        return mergedEmployee;

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        EntityManagerFactory entityManagerFactory = JpaConfig.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        for (Long id : ids) {
            entityManager.remove(entityManager.getReference(Employee.class, id));
        }

        tx.commit();

    }
}
