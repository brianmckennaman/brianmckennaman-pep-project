package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import java.sql.SQLException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::accountRegistrationHandler);
        // app.post("/login", this:loginHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        // app.get("/messages/{posted_by}/messages", this::getAllMessagesByUserHandler);
        app.post("/messages", this::postMessageHandler);
        app.put("/messages/{message_id}", this::updateMessageHandler);
        // app.delete("/messages/{message_id}" this::deleteMessageHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void accountRegistrationHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account newAccount = accountService.registerAccount(account);
        if(newAccount!=null) {
            context.json(mapper.writeValueAsString(newAccount));
        } else {
            context.status(400);
        }
    }

    // private void loginHandler(Context context) throws JsonProcessingException {
    //     ObjectMapper mapper = new ObjectMapper();
    //     Account account = mapper.readValue(context.body(), Account.class);
    //     try {
    //         Account checkExistAccount = accountService.getAccountByUsername(account.username);
    //         if(checekExistAccount.password.equals(account.password)){
    //             context.json(checkExistAccount);
    //             context.status(200);
    //         } else{
    //             context.status(401);
    //         }
    //     } catch (SQLException e) {
    //         context.
    //     }
    // }
    
    private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException{
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageById(message_id);
        if(message == null){
            context.status(400);
        } else { 
            context.json(message);
        }
    }

    // private void getAllMessagesByUserHandler(Context context){
    //     int posted_by = Integer.parseInt(context.pathParam("posted_by"));
    //     List<Message> messages = messageService.getAllMessagesByUser(posted_by);
    //     context.json(messages);
    // }

    private void postMessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message postedMessage = messageService.addMessage(message);
        if(postedMessage!=null && postedMessage.message_text.length() < 254 && postedMessage.message_text != "") {
            context.json(mapper.writeValueAsString(postedMessage));
        } else{
            context.status(400);
        }
    }

    private void updateMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessage(message_id, message);
        System.out.println(updatedMessage);
        if(updatedMessage != null && updatedMessage.message_text.length() < 254 && updatedMessage.message_text != "") {
            
            context.json(mapper.writeValueAsString(updatedMessage));
        } else{
            context.status(400);
        }
    }

    private void deleteMessageHandler(Context context) throws JsonProcessingException {

    }


}