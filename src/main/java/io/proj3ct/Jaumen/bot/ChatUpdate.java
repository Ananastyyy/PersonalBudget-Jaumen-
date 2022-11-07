package io.proj3ct.Jaumen.bot;

public class ChatUpdate {
    private Long userId;
    private String firstName;
    private String secondName;
    private String text;
    public ChatUpdate(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
