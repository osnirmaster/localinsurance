package br.com.insurance.command.bus;

import br.com.insurance.command.bus.api.Command;

public interface CommandHandler <R, C extends Command<R>>{
    R handle(C command);
}
