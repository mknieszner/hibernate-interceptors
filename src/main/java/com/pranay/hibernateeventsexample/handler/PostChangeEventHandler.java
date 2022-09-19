package com.pranay.hibernateeventsexample.handler;

public interface PostChangeEventHandler {
    void run(EntityChangeEvent event);
}
