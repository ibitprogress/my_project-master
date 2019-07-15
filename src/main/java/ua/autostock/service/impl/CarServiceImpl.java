package ua.autostock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.CarDTO;
import ua.autostock.specification.CarSpecification;
import ua.autostock.entity.CarEntity;
import ua.autostock.repository.CarRepository;
import ua.autostock.service.CarService;
import ua.autostock.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void saveCar(CarDTO car) {
        CarEntity carEntity = modelMapper.map(car, CarEntity.class);
        carRepository.save(carEntity);
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<CarEntity> cars = carRepository.findAll();
        List<CarDTO> carDTOS = modelMapper.mapAll(cars, CarDTO.class);
        return carDTOS;
    }

    @Override
    public CarDTO getCarById(Long id) {
        boolean exists = carRepository.existsById(id);

        if(exists){
            CarEntity car = carRepository.findById(id).get();
            CarDTO carDTO = modelMapper.map(car, CarDTO.class);
            return carDTO;
        }else {
            return null;
        }
    }

    @Override
    public CarDTO updateCar(Long id, CarEntity carToUpdate) {
        boolean exists = carRepository.existsById(id);
        if (exists){
            CarEntity carFromDb = carRepository.findById(id).get();
                    carFromDb.setCategory(carToUpdate.getCategory());
                    carFromDb.setMake(carToUpdate.getMake());
                    carFromDb.setModel(carToUpdate.getModel());
                    carFromDb.setMileage(carToUpdate.getMileage());
                    carFromDb.setDateOfManufacture(carToUpdate.getDateOfManufacture());
                    carFromDb.setVin(carToUpdate.getVin());
                    carFromDb.setPrice(carToUpdate.getPrice());
                    carFromDb.setVolume(carToUpdate.getVolume());
                    carFromDb.setDrive(carToUpdate.getDrive());
                    carFromDb.setTransmission(carToUpdate.getTransmission());
                    carFromDb.setBody(carToUpdate.getBody());
                    carFromDb.setFuel(carToUpdate.getFuel());
                    carFromDb.setNumberOfDoors(carToUpdate.getNumberOfDoors());
                    carFromDb.setNumberOfSeats(carToUpdate.getNumberOfSeats());
                    carFromDb.setComfort(carToUpdate.getComfort());
                    carFromDb.setAdditionalEquipment(carToUpdate.getAdditionalEquipment());
                    carFromDb.setDescription(carToUpdate.getDescription());

                carRepository.save(carFromDb);
                CarDTO carDTO = modelMapper.map(carFromDb, CarDTO.class);
                return carDTO;
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteCarById(Long id) {

        boolean exists = carRepository.existsById(id);

        if(exists){
            carRepository.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public Page<CarEntity> getCarsByPageAndSearch(String make, String model, String vin, Pageable pageable) {

        Page<CarEntity> carEntities = carRepository.findAll(new CarSpecification(make,model,vin),pageable);

       // Page<CarEntity> carEntities = carRepository.findByMakeOrModelOrVin(make, model, vin, pageable);

        return carEntities;
    }


}
