package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.configs.StatusHandlerConfig;
import org.springframework.stereotype.Component;

@Component
public class StatusHandler {
   private StatusHandlerConfig config;

   public StatusHandler(StatusHandlerConfig config) {
      this.config = config;
   }

   public Function getFunction(Status status) {
      return config.getNavigation().get(status);
   }
}
