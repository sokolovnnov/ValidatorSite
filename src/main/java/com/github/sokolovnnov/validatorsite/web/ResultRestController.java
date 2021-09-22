package com.github.sokolovnnov.validatorsite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.sokolovnnov.validatorsite.model.SimpleWay;
import com.github.sokolovnnov.validatorsite.repo.ResultRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResultRestController {

    @Autowired
    private ResultRepository repository;

    @GetMapping
    public List<SimpleWay> getAll() {
        return repository.getAll();
    }
}
