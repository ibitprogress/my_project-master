package ua.autostock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.autostock.entity.CarEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor {

    Page<CarEntity> findByMakeOrModelOrVin(String make, String model, String vin, Pageable pageable);

    Page<CarEntity> findByMakeAndModelOrVin(String make, String model, String vin, Pageable pageable);

}
