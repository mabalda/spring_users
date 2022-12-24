package com.example.users_2_3_1.config;

import com.example.users_2_3_1.listener.EventListener;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.gateway.intent.IntentSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DiscordBotConfig {
    @Value("${token}")
    private String token;

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(final List<EventListener<T>> eventListeners) {
//        final GatewayDiscordClient client = DiscordClientBuilder.create(token)
//                .build()
//                .login()
//                .block();

        final GatewayDiscordClient client = DiscordClient.create(token)
                .gateway()
                .setEnabledIntents(IntentSet.all())
                .login()
                .block();

        for(EventListener<T> listener : eventListeners) {
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }

        return client;
    }
}
