package com.pranay.hibernateeventsexample.handler;

import java.io.Serializable;

public class EntityChangeEvent {
    private final Serializable id;
    private final Object entity;
    private final String type;

    public EntityChangeEvent(Serializable id, Object entity, String type) {
        this.id = id;
        this.entity = entity;
        this.type = type;
    }

    public Serializable getId() {
        return id;
    }

    public Object getEntity() {
        return entity;
    }

    public String getType() {
        return type;
    }
}
