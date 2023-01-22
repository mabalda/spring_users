package com.example.users_2_3_1.service;

import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.repository.UserRepository;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VkBotService {
    @Autowired
    private GroupActor groupActor;
    @Autowired
    private VkApiClient client;
    @Autowired
    private UserRepository userRepository;
    @Value("${vk.group.id}")
    private int groupId;

    public void sendMessageToChat(Long id) throws ClientException, ApiException {
        User user = userRepository.findById(id).orElse(null);
        String username = user.getUsername();
        String messageContent = "User " + username + " wants to become admin. User's id is " + id;

        Random random = new Random();
        List<Integer> usersIds = client.groups().getMembers(groupActor).groupId(String.valueOf(groupId)).execute().getItems();

        for (Integer userId : usersIds) {
            client.messages().send(groupActor).userId(userId).message(messageContent).randomId(random.nextInt(10000)).execute();
        }
    }
}
