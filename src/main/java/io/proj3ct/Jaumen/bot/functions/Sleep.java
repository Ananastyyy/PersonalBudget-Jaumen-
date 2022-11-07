package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.User;

import static io.proj3ct.Jaumen.bot.functions.Status.LOG_IN;

public class Sleep implements Function {
    @Override
    public FunctionReply doFunction(User user, String text) {
        FunctionReply reply = new FunctionReply();
        if (text.equals("/start")) {
            user.setBotStatus(LOG_IN);
            return null;
        }
        reply.setText("(-, – )…zzzZZZ");
        return reply;
    }
}
