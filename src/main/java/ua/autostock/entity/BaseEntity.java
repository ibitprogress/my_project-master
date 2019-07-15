package ua.autostock.entity;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@MappedSuperclass

public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
