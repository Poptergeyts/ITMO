package com.example.springsecurit.Service;

import com.example.springsecurit.Entity.Hit;
import com.example.springsecurit.dto.AddHitRequest;

import java.util.ArrayList;
import java.util.Optional;

public interface AddHitService {

    Hit addHit(String token, AddHitRequest addHitRequest);

    ArrayList<Optional<Hit>> getAll(String token);

}
