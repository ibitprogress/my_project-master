package ua.autostock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.MessageDTO;
import ua.autostock.entity.MessageEntity;
import ua.autostock.repository.MessageRepository;
import ua.autostock.service.MessageService;
import ua.autostock.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public MessageDTO findMassageById(Long id) {
        MessageEntity message = messageRepository.findById(id).get();
        MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
        return messageDTO;
    }

    @Override
    public List<MessageDTO> findAllMassage() {

        List<MessageEntity> messageEntity = messageRepository.findAll();
        List<MessageDTO> messageDTOS = modelMapper.mapAll(messageEntity, MessageDTO.class);

        return messageDTOS;
    }

    @Override
    public void saveMessage(MessageDTO message) {
        MessageEntity messageEntity = modelMapper.map(message, MessageEntity.class);
        messageRepository.save(messageEntity);
    }
}
