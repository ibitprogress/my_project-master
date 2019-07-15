package ua.autostock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.autostock.DTO.UserDTO;
import ua.autostock.service.FileStorageService;
import ua.autostock.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
                String message = bindingResult.getFieldErrors().get(0).getDefaultMessage();
                String field = bindingResult.getFieldErrors().get(0).getField();
                String error =  "field: " + field + " , message: " + message;
            System.out.println(error);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
            userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("{userId}")
    public ResponseEntity<?> getUserById(
            @PathVariable("userId") Long id){
        UserDTO user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@PathVariable ("userId") Long id){
            userService.deleteUserById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long id,
                                        @RequestBody UserDTO userDto){
        UserDTO user = userService.updateUserById(id, userDto);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("page")
    private ResponseEntity<?> GetUsersByPage(@PageableDefault Pageable pageable){
        return  new ResponseEntity<>(userService.getUsersByPage(pageable), HttpStatus.OK);
    }

    @PostMapping("image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("imageFile")MultipartFile file
            ){
        fileStorageService.storeFile(file);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("image")
    public ResponseEntity<?> getImage(
            @RequestParam("imageName") String name,
            HttpServletRequest servletRequest
    ){
        Resource resource = fileStorageService.loadFile(name);
        String contentType = "application/octet-stream";
        try {
            contentType = servletRequest.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
        if (contentType == null){
            contentType = "application/octet-stream";
        }
        return (ResponseEntity<?>) ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename = \"" + resource.getFilename() + "\"")
                .body(resource);
    }





}
