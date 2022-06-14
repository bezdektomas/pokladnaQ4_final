package websocket;

import java.util.Set;

import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerApplicationConfig;
import jakarta.websocket.server.ServerEndpointConfig;

public class WebsocketConfig implements ServerApplicationConfig{

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> arg0) {
        System.out.println("----------------------");
		System.out.println("Number of configuration files loaded initially: "+arg0.size());
        return arg0;
    }

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
