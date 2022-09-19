package com.pranay.hibernateeventsexample.handler;

import org.springframework.stereotype.Component;

@Component
public class LoggingSnapshotChangeEventHandler implements PostChangeEventHandler {
    @Override
    public void run(EntityChangeEvent event) {
        System.out.println(event.getType() + " : " + event.getEntity().getClass().getSimpleName() + " with ID " + event.getId());
    }
}
