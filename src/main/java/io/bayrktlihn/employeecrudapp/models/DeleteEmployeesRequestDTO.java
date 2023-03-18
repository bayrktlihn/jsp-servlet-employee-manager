package io.bayrktlihn.employeecrudapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeleteEmployeesRequestDTO {

    private List<Long> toBeDeletedEmployeeIds;

}
