package ua.autostock.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity{

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model",nullable = false)
    private String model;

    @Column(name = "mileage", nullable = false)
    private int mileage;

    @Column(name = "date_of_manufacture", nullable = false, columnDefinition = "date")
    private LocalDate dateOfManufacture;

    @Column(name = "vin", unique = true)
    private String vin;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "volume", nullable = false)
    private int volume;

    @Column(name = "drive", nullable = false)
    private String drive;

    @Column(name = "transmission", nullable = false)
    private String transmission;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "fuel", nullable = false)
    private String fuel;

    @Column(name = "number_of_doors", nullable = false)
    private int numberOfDoors;

    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

    @Column(name = "comfort", columnDefinition = "text")
    private String comfort;

    @Column(name = "additional_equipment", columnDefinition = "text")
    private String additionalEquipment;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "addTime", columnDefinition = "date", nullable = false)
    private LocalDate addTime;

    @Column(name = "color", nullable = false)
    private String color;

}
