package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;



public class Sleep implements Function {
    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String text) {
        FunctionReply reply = new FunctionReply();
        if (text != null && text.equals("/preprocess")) {
//            chatHistory.se(Status.LOG_IN);
            return null;
        }
        reply.setText("(-, – )…zzzZZZ");
        return reply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        return null;
    }

    @Override
    public void stop(ChatHistory chatHistory) {

    }
}
