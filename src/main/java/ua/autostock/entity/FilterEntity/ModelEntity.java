package ua.autostock.entity.FilterEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.autostock.DTO.FilterDTO.MakeDTO;
import ua.autostock.entity.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "model")
public class ModelEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "models", nullable = false)
    private String model;

    @JsonIgnoreProperties("models")
    @ManyToOne
    @JoinColumn(name = "make_id", nullable = false)
    private MakeEntity make;

}
