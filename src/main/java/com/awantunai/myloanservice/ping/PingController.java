package com.awantunai.myloanservice.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by arief@awantunai.com on 2019-10-08
 */
@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok("OK");
    }
}
