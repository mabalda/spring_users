package com.example.users_2_3_1.listener;

import com.example.users_2_3_1.model.RequestDTO;
import com.example.users_2_3_1.service.AdminService;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ButtonListener extends ListenerAdapter {
    @Autowired
    private AdminService adminService;

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String messageContent = event.getMessage().getContentRaw();
        String[] messageContentArray = messageContent.split(" ");
        Long userId = Long.parseLong(messageContentArray[messageContentArray.length - 1]);
        String username = messageContentArray[1];

        RequestDTO request = new RequestDTO();
        request.setId(userId);

        if (event.getComponentId().equals("confirm")) {
            request.setApplied(true);
            adminService.requestForApplication(request);
            event.reply("The request was accepted. User " + username + " had become an admin").queue();
        } else if (event.getComponentId().equals("dismiss")) {
            request.setApplied(false);
            adminService.requestForApplication(request);
            event.reply("The request was rejected").queue();
        }
    }
}
