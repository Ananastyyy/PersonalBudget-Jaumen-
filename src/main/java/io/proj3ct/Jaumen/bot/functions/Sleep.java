package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.User;



public class Sleep implements Function {
    @Override
    public FunctionReply doFunction(User user, String text) {
        FunctionReply reply = new FunctionReply();
        if (text.equals("/start")) {
            user.setBotStatus(Status.LOG_IN);
            return null;
        }
        reply.setText("(-, – )…zzzZZZ");
        return reply;
    }
}
