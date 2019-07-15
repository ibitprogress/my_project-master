package ua.autostock.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.autostock.validation.CheckUnique;
import ua.autostock.validation.CheckUniqueTelephone;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String firstName;

    @NotNull(message = "Surname can`t be NULL, you can use Ukr or Eng letters")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZА-ЩЬЮЯҐЄІЇа-щьюяґєії]+$")
    private String surName;

    @NotNull(message = "telephone mush have 12 symbols, begin for 380")
    @CheckUniqueTelephone(message = "this telephone number already exists by another user")
    @Size(min = 12, max = 12)
    @Pattern(regexp = "^[0-9]+$")
    private String telephone;

    @NotNull
    @CheckUnique(message = "email already exists")
    @Size(min = 10, max = 100)
    @Pattern(regexp = "^[a-z0-9_-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", message = "bad format")
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^.*(?=.{8,})([a-zA-Z0-9]).*$", message = "password must have length must be more than 8")
    private String password;

    @NotNull
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^.*(?=.{8,})([a-zA-Z0-9]).*$")
    private String passwordConfirm;

    @NotNull
    @Size
    private String gender;

    @Min(value = 12)
    @Max(value = 90)
    @NotNull(message = "age must be from 12 to 90")
    private int age;

    @NotNull
    private String registryDate;

}
