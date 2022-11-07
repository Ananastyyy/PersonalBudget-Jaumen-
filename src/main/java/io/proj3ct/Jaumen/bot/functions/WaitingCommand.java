package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.User;

public class WaitingCommand implements Function{
    @Override
    public FunctionReply doFunction(User user, String text) {
        if (text != null && text.equals("/sleep")) {
            user.setStatus(Status.SLEEPING);
            user.setLogin(null);
            return null;
        }
        FunctionReply reply = new FunctionReply();
        reply.setText("Waiting command");
        return reply;
    }
}
