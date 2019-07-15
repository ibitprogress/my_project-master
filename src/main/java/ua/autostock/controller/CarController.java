package ua.autostock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.autostock.DTO.CarDTO;
import ua.autostock.DTO.exception.ValidationError;
import ua.autostock.entity.CarEntity;
import ua.autostock.repository.CarRepository;
import ua.autostock.service.CarService;
import ua.autostock.service.FileStorageService;
import ua.autostock.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car/")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody CarDTO car, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String message = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            String field = bindingResult.getFieldErrors().get(0).getField();
            return new ResponseEntity<>(new ValidationError("validation " + field + " failed", message), HttpStatus.BAD_REQUEST);
        }
        car.getPhotos().forEach(System.out:: println);
        carService.saveCar(car);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{carId}")
    public ResponseEntity<?> getCarById(@PathVariable ("carId")Long id){
        CarDTO car = carService.getCarById(id);
        if (car != null)
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{carId}")
    public ResponseEntity<?> updateCarById(@PathVariable("carId") Long id,
                                           @RequestBody CarEntity carEntity){

        CarDTO car = carService.updateCar(id, carEntity);
        if (car != null)
            return new ResponseEntity<>(car, HttpStatus.OK);
        else
            return new ResponseEntity<>((HttpStatus.BAD_REQUEST));

    }

    @GetMapping("page")
    public ResponseEntity<?> GetCarsByPage(@PageableDefault Pageable pageable,
                                            @RequestParam(required = false) String make,
                                            @RequestParam(required = false) String model,
                                            @RequestParam(required = false) String vin){
        Page page = carService.getCarsByPageAndSearch(make, model, vin, pageable);
        System.out.println(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("image")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile")MultipartFile file){
        fileStorageService.storeFile(file);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("image")
    public ResponseEntity<?> showImage(@RequestParam("imageName") String name,
                                       HttpServletRequest servletRequest){

        Resource resource = fileStorageService.loadFile(name);
        String contentType = null;
        try{
            contentType = servletRequest.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"" )
                .body(resource);
    }

}
