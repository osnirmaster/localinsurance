package br.com.insurance.command.bus;

import br.com.insurance.command.bus.api.Query;

public interface QueryHandler <R, C extends Query<R>>{
    R handle(C command);
}
