package com.pranay.hibernateeventsexample;

import com.pranay.hibernateeventsexample.listener.ChangeListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class HibernateListenerConfigurer {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private final ChangeListener changeListener;

    @Autowired
    public HibernateListenerConfigurer(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(changeListener);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(changeListener);
        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(changeListener);
    }
}
