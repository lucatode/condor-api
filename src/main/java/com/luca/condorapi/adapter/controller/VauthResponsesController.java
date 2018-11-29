package com.luca.condorapi.adapter.controller;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.responses.DefaultMatchCaseRepository;
import com.luca.condorapi.adapter.repository.responses.MatchCaseAdapter;
import com.luca.condorapi.domain.MatchCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VauthResponsesController {

    String connectionString = System.getenv("CONNECTION_STRING_VAUTH_RESPONSES"); //"";
    String databaseName = System.getenv("DATABASE_NAME_VAUTH_RESPONSES"); //"";
    String collectionName = System.getenv("COLLECTION_NAME_VAUTH_RESPONSES"); //"";
    private DefaultMatchCaseRepository defaultMatchCaseRepository;

    public VauthResponsesController(){
        defaultMatchCaseRepository = new DefaultMatchCaseRepository(new MongoAdapter<>(new MatchCaseAdapter(),connectionString,databaseName,collectionName));
    }

    @GetMapping("/getMatch")
    public @ResponseBody
    ResponseEntity<List<MatchCase>> get() {
        List<MatchCase> matchCases = defaultMatchCaseRepository.getAll();
        return new ResponseEntity<>(matchCases, HttpStatus.OK);
    }
}
