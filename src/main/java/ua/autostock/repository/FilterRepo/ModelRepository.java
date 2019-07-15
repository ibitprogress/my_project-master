package ua.autostock.repository.FilterRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.autostock.DTO.FilterDTO.ModelDTO;
import ua.autostock.entity.FilterEntity.ModelEntity;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    void deleteByModel(ModelEntity modelEntity);

    List<ModelEntity> findAllByModel(ModelEntity modelEntity);
}
