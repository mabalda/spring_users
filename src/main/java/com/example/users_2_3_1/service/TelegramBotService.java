package com.example.users_2_3_1.service;

import com.example.users_2_3_1.model.TelegramCodeDTO;
import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.repository.UserRepository;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Service
public class TelegramBotService extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String token;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cache<String, String> cache;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();

            if (messageText.equals("/start")) {
                handleStartMessage(message);
            }
        }
    }

    public void handleStartMessage(Message message) {
        try {
            replyToStartMessage(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        if (message.getChat().isUserChat()) {
            long chatId = message.getChatId();
            String username = message.getFrom().getUserName();
            setChatIdToUser(chatId, username);
        }
    }

    public void setChatIdToUser(long chatId, String telegramUsername) {
        User user = userRepository.findByTelegramUsername(telegramUsername);
        user.setTelegramChatId(String.valueOf(chatId));
        userRepository.save(user);
    }

    public void replyToStartMessage(Message message) throws TelegramApiException {
        String firstName = message.getFrom().getFirstName();
        long chatId = message.getChatId();

        SendMessage reply = new SendMessage();
        reply.setChatId(String.valueOf(chatId));
        reply.setText("Привет, " + firstName + "!");

        execute(reply);
    }

    public boolean sendAuthorizationCode(String telegramUsername) throws TelegramApiException {
        User user = userRepository.findByTelegramUsername(telegramUsername);

        if (user != null) {
            String chatId = user.getTelegramChatId();
            String code = getAuthorizationCode(telegramUsername);

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(code);

            execute(message);

            return true;
        }

        return false;
    }

    public String getAuthorizationCode(String telegramUsername) {
        Random random = new Random();
        int intCode = random.ints(1000, 10000).findFirst().getAsInt();
        String code = String.valueOf(intCode);
        cache.put(telegramUsername, code);
        return code;
    }

    public boolean checkAuthorizationCodes(TelegramCodeDTO dto) {
        String codeFromForm = dto.getTelegramCode();
        String telegramUsername = dto.getTelegramUsername();
        String codeFromCache = cache.getIfPresent(telegramUsername);
        return codeFromCache.equals(codeFromForm);
    }
}
