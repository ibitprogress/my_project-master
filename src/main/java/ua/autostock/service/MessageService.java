package ua.autostock.service;

import ua.autostock.DTO.MessageDTO;
import ua.autostock.entity.MessageEntity;

import java.util.List;

public interface MessageService {

    List<MessageDTO> findAllMassage();

    MessageDTO findMassageById(Long id);

    void saveMessage(MessageDTO message);
}
