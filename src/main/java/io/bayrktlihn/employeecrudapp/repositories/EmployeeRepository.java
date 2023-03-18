package io.bayrktlihn.employeecrudapp.repositories;

import io.bayrktlihn.employeecrudapp.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Long>{


    void deleteByIds(List<Long> ids);

}
