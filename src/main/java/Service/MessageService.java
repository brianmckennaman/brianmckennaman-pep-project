package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

import org.h2.value.ValueVarchar;

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
        return messageDAO.getMessageById(message_id);
    }

    public Message addMessage(Message message){
        Message newMessage = messageDAO.createMessage(message);
        System.out.println(newMessage);
        if(message.message_text == ""){
            return null;
        }
        // if(message.message_text > 255){
        //     return null;
        // }
        return newMessage;
    }

    public Message updateMessage(int message_id, Message message) {
        Message oldMessage = messageDAO.getMessageById(message_id);
        if (oldMessage == null){
            return null;
        }
        else if (oldMessage.message_text == ""){
            return null;
        }
        oldMessage.setMessage_text(message.getMessage_text());
        messageDAO.updateMessage(message_id, oldMessage);
        return oldMessage;
    }

    // public Message deleteMessage(int message_id) {
        
    //     if(message_id == null){
    //         return null;
    //     }
    //     return messageDAO.deleteMessage(message_id);
    // }
}
