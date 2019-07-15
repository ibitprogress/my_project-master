package ua.autostock.controller.FilterController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.autostock.DTO.FilterDTO.MakeDTO;
import ua.autostock.entity.FilterEntity.MakeEntity;
import ua.autostock.exceptions.AlreadyExistsException;
import ua.autostock.service.FilterServiseAndIMPL.MakeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filters/make")
public class MakeController {

    @Autowired
    private MakeService makeService;

    @PostMapping
    public ResponseEntity<?> createNewMake(@Valid @RequestBody MakeDTO makeDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            makeService.addNewMake(makeDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllMakes(){
        List<MakeDTO> makeEntities = makeService.getAllMakes();
        return new ResponseEntity<>(makeEntities, HttpStatus.OK);
    }

    @DeleteMapping("{makeId}")
    public ResponseEntity<?> deleteMake(@PathVariable("makeId") Long id){
        makeService.removeMakeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
