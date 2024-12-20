package com.example.springsecurit.Controller;

import com.example.springsecurit.Entity.Hit;
import com.example.springsecurit.Entity.User;
import com.example.springsecurit.Repository.HitRepository;
import com.example.springsecurit.Repository.UserRepository;
import com.example.springsecurit.Service.AddHitService;
import com.example.springsecurit.Service.JwtService;
import com.example.springsecurit.dto.AddHitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final AddHitService addHitService;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello user");
    }

    @PostMapping("/add-hit")
    public ResponseEntity<Hit> addHit(@RequestHeader("Authorization") String token, @RequestBody AddHitRequest request){
        return ResponseEntity.ok(addHitService.addHit(token, request));
    }

    @PostMapping("/get-all")
    private ResponseEntity<ArrayList<Optional<Hit>>> getAll(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(addHitService.getAll(token));
    }
}
