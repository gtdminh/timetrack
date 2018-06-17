package com.fxrialab.timetrack.config;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Minh T. on 6/15/2018.
 */
@Component
public class AppEventListeners {

    @EventListener
    public void handleContextRefreshEvent(ContextStartedEvent event)
    {

    }
}
