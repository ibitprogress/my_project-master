package ua.autostock.DTO.FilterDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import ua.autostock.entity.FilterEntity.ModelEntity;
import ua.autostock.validation.CheckUnique;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MakeDTO {

    private Long id;

    @NotNull
    @CheckUnique(message = "make already exists")
    private String make;

    @JsonIgnoreProperties("make")
    private List<ModelDTO> models;

}
