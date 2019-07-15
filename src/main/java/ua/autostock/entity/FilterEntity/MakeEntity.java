package ua.autostock.entity.FilterEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.autostock.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "make")
public class MakeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make", nullable = false, unique = true)
    private String make;

    @JsonIgnoreProperties("make")
    @OneToMany(mappedBy = "make")
    private List<ModelEntity> models;

}
