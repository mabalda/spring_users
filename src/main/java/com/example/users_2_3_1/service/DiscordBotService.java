package com.example.users_2_3_1.service;

import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.repository.UserRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordBotService {
    final static String ADMIN_ROLE_ID = "1056291509248471186";

    @Autowired
    private JDA client;
    @Autowired
    private UserRepository userRepository;

    public void sendMessageToChat(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        String username = user.getUsername();

        String messageContent = "User " + username + " wants to become admin. User's id is " + userId;

        Role role = client.getRoleById(ADMIN_ROLE_ID);
        Guild guild = role.getGuild();

        List<Member> members = guild.getMembersWithRoles(role);

        if (!members.isEmpty()) {
            members.forEach(member -> member
                    .getUser()
                    .openPrivateChannel().queue(channel -> channel.sendMessage(messageContent)
                            .addActionRow(
                                    Button.success("confirm", "Confirm"),
                                    Button.danger("dismiss", "Dismiss")
                            )
                            .queue()));
        }
    }
}
