package io.bayrktlihn.employeecrudapp;

import java.util.regex.Pattern;

public class EmployeeCrudAppApplication {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^/[0-9]+$");

        boolean matches = pattern.matcher("/12321").matches();

        System.out.println(matches);
    }
}
