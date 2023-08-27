package com.machnet.domain.contracts;

public interface Command<T> {

    T execute();
}
