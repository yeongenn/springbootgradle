package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MatchDao {

    @Autowired
    private MatchRepository matchRepository;

}
