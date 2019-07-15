package ua.autostock.DTO.FilterDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class ModelDTO {

    private Long id;

    @NotNull
    private String model;

    @NotNull
    private MakeDTO make;


}
