package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }


    public Message addMessage(Message message){
        Message checkMessage = messageDAO.getMessageById(message.getMessage_id());
        if(checkMessage != null){
            return null;
        }
        messageDAO.createMessage(message);
        return message;
    }
}
