package ua.autostock.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"users", "cars"})

@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "text")
    private String message;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity cars;
}
