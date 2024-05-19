package messager.service;

import messager.mapper.*;
import messager.DTO.MessageDTO;
import messager.model.*;
import messager.repository.MessageRepository;
import messager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatServiceImpl chatServiceImpl;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, ChatServiceImpl chatServiceImpl) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatServiceImpl = chatServiceImpl;
    }

    @Override
    public Message addMessage(String text, long userId, long recipientId, long chatId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<User> recipient = userRepository.findById(recipientId);
        Message message = new Message(text, user.get(), recipient.get(), chatServiceImpl.getChatById(chatId));
        messageRepository.save(message);
        return message;
    }

    @Override
    public void addMessage(MessageDTO messageDTO) {
        Message message = MessageMapper.INSTANCE.messageDTOToMessage(messageDTO);
        messageRepository.save(message);
    }

    @Override
    public LinkedList<Message> getMessagesByChatId(long chatId) {
        return messageRepository.getMessagesByChatId(chatId);
    }

    @Override
    public LinkedList<MessageDTO> getMessageDTOsByChatId(long chatId) {
        LinkedList<Message> messageList = messageRepository.getMessagesByChatId(chatId);
        LinkedList<MessageDTO> messageDTOList = new LinkedList<>();
        for (Message m : messageList) {
            MessageDTO messageDTO = MessageMapper.INSTANCE.messageToMessageDTO(m);
            messageDTOList.add(messageDTO);
        }
        return messageDTOList;
    }
}