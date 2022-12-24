package com.example.users_2_3_1;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordBot {
    final static String CHANNEL_ID = "1056280383374766112";
    final static String ADMIN_ROLE_ID = "1056291509248471186";

    @Autowired
    private GatewayDiscordClient client;

    public void sendMessageToChat(String username, Long userId) {

        List<User> users = client.getUsers().collectList().block();

        Role role = client.getRoleById(Snowflake.of(CHANNEL_ID), Snowflake.of(ADMIN_ROLE_ID)).block();

        if (true) {

//            Guild guild = client.getGuildById(Snowflake.of(CHANNEL_ID)).block();

            List<Member> admins = client.getGuildMembers(Snowflake.of(CHANNEL_ID)).collectList().block();
//            List<Member> admins = client.requestMembers(Snowflake.of(CHANNEL_ID))
//                    .collectList().block();

            admins.forEach(member -> member.getPrivateChannel()
                    .flatMap(channel -> channel.createMessage("User " + username + " want to be ADMIN, userId = " + userId))
                    .subscribe());
        }
    }
}
