package io.bayrktlihn.employeecrudapp.mappers;

import io.bayrktlihn.employeecrudapp.entities.Employee;
import io.bayrktlihn.employeecrudapp.models.SaveEmployeeRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee saveEmployeeRequestDTOToEmployee(SaveEmployeeRequestDTO saveEmployeeRequestDTO);
}
