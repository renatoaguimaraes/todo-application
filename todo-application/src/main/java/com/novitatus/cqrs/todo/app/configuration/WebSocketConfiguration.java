package com.novitatus.cqrs.todo.app.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer
{

    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
    }

    public boolean configureMessageConverters(List<MessageConverter> messageConverters)
    {
        return true;
    }

    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        registry.addEndpoint("/tasks").withSockJS();
    }

    public void configureWebSocketTransport(WebSocketTransportRegistration registry)
    {
    }

    public void configureClientInboundChannel(ChannelRegistration registration)
    {
    }

    public void configureClientOutboundChannel(ChannelRegistration registration)
    {
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
    }

    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers)
    {
    }
}
