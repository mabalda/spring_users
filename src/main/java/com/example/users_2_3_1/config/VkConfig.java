package com.example.users_2_3_1.config;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VkConfig {
    @Value("${vk.group.token}")
    private String token;
    @Value("${vk.group.id}")
    private int groupId;

    @Bean
    public VkApiClient getVkApiClient() {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        return vk;
    }

    @Bean
    public GroupActor getGroupActor() throws ClientException, ApiException {
        GroupActor actor = new GroupActor(groupId, token);
        return actor;
    }
}
