package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.User;

public class WaitingCommand implements Function{

    @Override
    public FunctionReply doFunction(User user, String text) {
        FunctionReply reply = new FunctionReply();
        reply.setText("Wait command");
        return reply;
    }
}
