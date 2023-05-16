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
        return messageDAO.getMessageById(message_id);
    }

    // public List<Message> getAllMessagesByUser(int posted_by){
    //     if(message.postedby == null){
    //         return null;
    //     }
    //     return messageDAO.getAllMessagesByUser(posted_by);
    // }

    public Message addMessage(Message message){
        Message newMessage = messageDAO.createMessage(message);
        return newMessage;
    }

    public Message updateMessage(int message_id, Message message) {
        Message oldMessage = messageDAO.getMessageById(message_id);
        // if (oldMessage == null){
        //     return null;
        // }
        // if (oldMessage.message_text == ""){
        //     return null;
        // }
        oldMessage.setMessage_text(message.getMessage_text());
        messageDAO.updateMessage(message_id, oldMessage);
        return oldMessage;
    }

    // public Message deleteMessage(int message_id) {
        
    //     if(message.message_id == null){
    //         return null;
    //     }
    //     return messageDAO.deleteMessage(message_id);
    // }
}
