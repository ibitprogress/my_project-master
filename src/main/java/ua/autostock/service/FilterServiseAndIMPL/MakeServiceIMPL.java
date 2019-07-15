package ua.autostock.service.FilterServiseAndIMPL;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.FilterDTO.MakeDTO;
import ua.autostock.entity.FilterEntity.MakeEntity;
import ua.autostock.exceptions.AlreadyExistsException;
import ua.autostock.exceptions.NotFoundException;
import ua.autostock.repository.FilterRepo.MakeRepository;
import ua.autostock.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class MakeServiceIMPL implements MakeService {

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Autowired
    private MakeRepository repository;

    @Override
    public MakeDTO addNewMake(MakeDTO makeDTO)  {
        MakeEntity makeEntity = modelMapper.map(makeDTO, MakeEntity.class);
            repository.save(makeEntity);
            return makeDTO;

    }

    @Override
    public void removeMakeById(Long id) {

        repository.deleteById(id);
    }

    @Override
    public List<MakeDTO> getAllMakes() {
        List<MakeEntity> makeEntities = repository.findAll();
        List<MakeDTO> makeDTOS = modelMapper.mapAll(makeEntities, MakeDTO.class);
        return makeDTOS;
    }


}
