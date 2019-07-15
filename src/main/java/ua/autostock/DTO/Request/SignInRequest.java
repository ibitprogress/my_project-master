package ua.autostock.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.autostock.validation.CheckUnique;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SignInRequest {

    @NotNull
    @Size(min = 10, max = 100)
    @Pattern(regexp = "^[a-z0-9_-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", message = "bad format")
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    private String password;

}
