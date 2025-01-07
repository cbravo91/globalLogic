package com.evualuation.globallogic.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class UserDTO {

    private String name;
    @NotBlank
    @Email(message = "El correo debe tener el siguente formato: correo@dominio.com")
    private String email;
    @NotBlank(message = "Coloque su contraseña")
    @Size(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres.")
    @Pattern(regexp = "^(?!.*[A-Z].*[A-Z]).*[A-Z].*$", message = "La contraseña debe contener exactamente una letra mayúscula.")
    @Pattern(regexp = "^(?!.*\\d.*\\d.*\\d).*\\d.*\\d.*$", message = "La contraseña debe contener exactamente dos números.")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "La contraseña solo puede contener caracteres alfanuméricos.")
    private String password;
    private List<PhoneDTO> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }
}
