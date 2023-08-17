package WebSocketSwipe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
// include this as per your project file structure needs
// common case: webcoket files placed in sub-directory,
// all controllers work except websockets
public class WebSocketConfig {
    //    establishing basic websocket configuration for project

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}


