package com.pranay.hibernateeventsexample.listener;

import com.pranay.hibernateeventsexample.handler.EntityChangeEvent;
import com.pranay.hibernateeventsexample.handler.PostChangeEventHandler;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ChangeListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

    private final List<PostChangeEventHandler> handlers;

    @Autowired
    public ChangeListener(List<PostChangeEventHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        for (PostChangeEventHandler han : handlers) {
            han.run(new EntityChangeEvent(postInsertEvent.getId(), postInsertEvent.getEntity(), "INSERT"));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        for (PostChangeEventHandler han : handlers) {
            han.run(new EntityChangeEvent(postUpdateEvent.getId(), postUpdateEvent.getEntity(), "UPDATE"));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        for (PostChangeEventHandler han : handlers) {
            han.run(new EntityChangeEvent(postDeleteEvent.getId(), postDeleteEvent.getEntity(), "DELETE"));
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }

}
