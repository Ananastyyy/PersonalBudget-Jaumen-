package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;



public class Sleep implements Function {
    @Override
    public FunctionReply doFunction(ChatHistory user, String text) {
        FunctionReply reply = new FunctionReply();
        if (text != null && text.equals("/start")) {
            user.setStatus(Status.LOG_IN);
            return null;
        }
        reply.setText("(-, – )…zzzZZZ");
        return reply;
    }
}
