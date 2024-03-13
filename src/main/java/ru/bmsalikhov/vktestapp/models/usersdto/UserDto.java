package ru.bmsalikhov.vktestapp.models.usersdto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String username;
    private String email;
    private AddressDto address;
    private String phone;
    private String website;
    private CompanyDto company;

}
