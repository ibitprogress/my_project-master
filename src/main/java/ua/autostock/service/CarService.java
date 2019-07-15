package ua.autostock.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.CarDTO;
import ua.autostock.DTO.MessageDTO;
import ua.autostock.entity.CarEntity;
import ua.autostock.entity.MessageEntity;

import java.util.List;

public interface CarService {

    void saveCar(CarDTO car);

    List<CarDTO> getAllCars();

    CarDTO getCarById(Long id);

    CarDTO updateCar(Long id, CarEntity carToUpdate);

    boolean deleteCarById(Long id);

    Page<CarEntity> getCarsByPageAndSearch (String make,String model, String vin, Pageable pageable);


}
