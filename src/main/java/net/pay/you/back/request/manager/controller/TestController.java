package net.pay.you.back.request.manager.controller;

import net.pay.you.back.request.manager.comm.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth0", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @GetMapping(value = "/test-endpoint")
    public Message publicEndpoint() {
        return new Message("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private/test-endpoint")
    public Message privateEndpoint() {
        return new Message("All good. You can see this because you are Authenticated.");
    }

//    @GetMapping(value = "/private/test-endpoint-scoped")
//    public Message privateScopedEndpoint() {
//        return new Message("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
//    }
}
