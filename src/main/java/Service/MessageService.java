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

    public Message getMessageById(int message_id){
        Message message = messageDAO.getMessageById(message_id);
        return message;
    }

    public List<Message> getAllMessagesByUser(int posted_by){
        return messageDAO.getAllMessagesByUser(posted_by);
    }

    public Message addMessage(Message message){
        Message newMessage = messageDAO.createMessage(message);
        return newMessage;
    }

    public Message updateMessage(int message_id, Message message) {
        Message oldMessage = messageDAO.getMessageById(message_id);
        if (oldMessage == null){
            return null;
        }
        oldMessage.setMessage_text(message.getMessage_text());
        if (oldMessage.message_text == ""){
            return null;
        }
        return oldMessage;
    }

    // Service
    public Message deleteMessage(int message_id) {
        return messageDAO.deleteMessage(message_id);
    }
}
