package ua.autostock.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = "cars")

@Entity
@Table(name = "photos")
public class PhotoEntity extends BaseEntity{

    @Column(name = "content_type")
    private String contentType;

    @Type(type="org.hibernate.type.BinaryType")
    @Basic(fetch=FetchType.LAZY)
    @Column(name="image", nullable = false)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity cars;
}
