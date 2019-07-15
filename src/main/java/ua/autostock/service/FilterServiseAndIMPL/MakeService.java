package ua.autostock.service.FilterServiseAndIMPL;

import ua.autostock.DTO.FilterDTO.MakeDTO;
import ua.autostock.entity.FilterEntity.MakeEntity;

import java.util.List;

public interface MakeService {

    MakeDTO addNewMake(MakeDTO makeDTO);

    void removeMakeById(Long id);

    List<MakeDTO> getAllMakes();


}
