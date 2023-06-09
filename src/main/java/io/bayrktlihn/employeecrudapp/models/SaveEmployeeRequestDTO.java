package io.bayrktlihn.employeecrudapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveEmployeeRequestDTO {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
