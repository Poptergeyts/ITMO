package com.example.springsecurit.Repository;

import com.example.springsecurit.Entity.Hit;
import com.example.springsecurit.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface HitRepository extends JpaRepository<Hit, Long> {

    ArrayList<Optional<Hit>> findAllByUserOrderByTimeOfCreatedDesc(User user);
}
