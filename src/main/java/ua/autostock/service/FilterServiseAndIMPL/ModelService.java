package ua.autostock.service.FilterServiseAndIMPL;

import ua.autostock.DTO.FilterDTO.ModelDTO;
import ua.autostock.entity.FilterEntity.ModelEntity;

import java.util.List;

public interface ModelService {

    void addNewModel(ModelDTO modelDTO);

    List<ModelEntity> getAllModels();

    void removeModelById(Long id);
}
