package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;

public class WaitingCommand implements Function{
    @Override
    public FunctionReply doFunction(ChatHistory history, String text) {
        if (text != null && text.equals("/sleep")) {
            history.setStatus(Status.SLEEP);
            history.setLogin(null);
            return null;
        } else if (text != null && text.equals("/create_category")) {
            history.setStatus(Status.CREATE_CATEGORY);
            return null;
        }
        FunctionReply reply = new FunctionReply();
        reply.setText("Waiting command");
        return reply;
    }
}
