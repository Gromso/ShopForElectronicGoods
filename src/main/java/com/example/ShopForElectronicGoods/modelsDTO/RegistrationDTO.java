package com.example.ShopForElectronicGoods.modelsDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationDTO {

    @NotBlank
    @Email(message = "invalid Email or password")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message ="invalid Email or password")
    @Size(max = 64)
    private String password;
    @NotBlank
    @Size(min = 2, max = 64)
    private String forename;
    @NotBlank
    @Size(min = 2, max = 64)
    private String surname;
    @NotBlank
    @Size(min = 2, max = 64)
    private String phone_number;
    @NotBlank
    @Size(min = 5, max = 200)
    private String postal_address;

    public RegistrationDTO(){}


    public RegistrationDTO(String email, String password, String forename, String surname, String phone_number, String postal_address) {
       super();
        this.email = email;
        this.password = password;
        this.forename = forename;
        this.surname = surname;
        this.phone_number = phone_number;
        this.postal_address = postal_address;
    }
    public RegistrationDTO(String password, String forename, String surname, String phone_number, String postal_address) {
        super();
        this.password = password;
        this.forename = forename;
        this.surname = surname;
        this.phone_number = phone_number;
        this.postal_address = postal_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }
}
