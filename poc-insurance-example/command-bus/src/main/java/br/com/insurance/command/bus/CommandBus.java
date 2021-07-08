package br.com.insurance.command.bus;

import br.com.insurance.command.bus.api.Command;
import br.com.insurance.command.bus.api.Query;

public interface CommandBus {

    <R, C extends Command<R>> R executeCommand(C command);

    <R, Q extends Query<R>> R executeQuery(Q query);
}
