package edu.dosw.rideci.application.events.listener;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@RequiredArgsConstructor
@Slf4j
public class UserRegisteredListener {

    public void handleUserRegistered() {
        
        log.info("UserRegisteredListener handled event");
    }
    
}
