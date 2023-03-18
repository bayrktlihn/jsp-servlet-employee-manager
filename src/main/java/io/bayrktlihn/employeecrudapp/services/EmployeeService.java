package io.bayrktlihn.employeecrudapp.services;

import io.bayrktlihn.employeecrudapp.entities.Employee;
import io.bayrktlihn.employeecrudapp.mappers.EmployeeMapper;
import io.bayrktlihn.employeecrudapp.models.SaveEmployeeRequestDTO;
import io.bayrktlihn.employeecrudapp.repositories.EmployeeRepository;
import io.bayrktlihn.employeecrudapp.repositories.EmployeeRepositoryImpl;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    public Employee saveEmployee(SaveEmployeeRequestDTO saveEmployeeRequestDTO){

        Employee employee = employeeMapper.saveEmployeeRequestDTOToEmployee(saveEmployeeRequestDTO);

        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public void deleteByIds(List<Long> ids){
        employeeRepository.deleteByIds(ids);
    }

}
