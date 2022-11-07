package io.proj3ct.Jaumen.bot;

public class ChatUpdate {
    private Long userId;
    private Long chatId;
    private String text;

    public ChatUpdate(Long userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
