package WebSocketSwipe;

import jakarta.websocket.server.ServerEndpoint;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@ServerEndpoint("/websocketswipe/{userid}")
@Component
public class WebSocketServer {


    private static List<String> likedCandidates = new ArrayList<String>();
    @MessageMapping("/swipe")
    @SendTo("/topic/swipe")
    public String handleSwipe(String message, @DestinationVariable String userid) throws Exception {
        // Process the message, e.g., save it to the database, etc.
        if (message.equals("like")) {
            likedCandidates.add(userid);
        }
        return message;
    }
}