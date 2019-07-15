package ua.autostock.service.FilterServiseAndIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.FilterDTO.ModelDTO;
import ua.autostock.entity.FilterEntity.ModelEntity;
import ua.autostock.repository.FilterRepo.ModelRepository;
import ua.autostock.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class ModelServiceIMPL implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void addNewModel(ModelDTO modelDTO) {
        modelRepository.save(modelMapper.map(modelDTO, ModelEntity.class));
    }

    @Override
    public List<ModelEntity> getAllModels() {
        List<ModelEntity> modelEntities = modelRepository.findAll();
        return modelEntities;
    }

    @Override
    public void removeModelById(Long id) {
        modelRepository.deleteById(id);
    }
}
