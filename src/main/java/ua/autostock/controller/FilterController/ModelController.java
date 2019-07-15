package ua.autostock.controller.FilterController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.autostock.DTO.FilterDTO.ModelDTO;
import ua.autostock.entity.FilterEntity.ModelEntity;
import ua.autostock.service.FilterServiseAndIMPL.ModelService;

import java.util.List;

@RestController
@RequestMapping("/filters/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping
    public ResponseEntity<?> addNewModel(@RequestBody ModelDTO modelDTO){
        modelService.addNewModel(modelDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllModels(){
        List<ModelEntity> modelEntities = modelService.getAllModels();
        return new ResponseEntity<>(modelEntities, HttpStatus.OK);
    }

    @DeleteMapping("{modelId}")
    public ResponseEntity<?> deleteModel(@PathVariable("modelId") Long id){
        modelService.removeModelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
