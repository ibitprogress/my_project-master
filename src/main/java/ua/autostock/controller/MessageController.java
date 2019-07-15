package ua.autostock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.autostock.DTO.MessageDTO;
import ua.autostock.entity.MessageEntity;
import ua.autostock.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")

public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageDTO message){

        messageService.saveMessage(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessage(){
            List<MessageDTO> messages = messageService.findAllMassage();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
