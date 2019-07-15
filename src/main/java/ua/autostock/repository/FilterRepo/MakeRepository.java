package ua.autostock.repository.FilterRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;
import ua.autostock.entity.FilterEntity.MakeEntity;
import ua.autostock.entity.FilterEntity.ModelEntity;

import java.util.List;

@Repository
public interface MakeRepository extends JpaRepository<MakeEntity, Long> {

    void deleteByMake(MakeEntity makeEntity);

    boolean existsByMake(MakeEntity makeEntity);

}
