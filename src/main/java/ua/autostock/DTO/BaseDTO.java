package ua.autostock.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@ToString

public abstract class BaseDTO {

    private Long id;

}
