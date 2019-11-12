package com.luca.condorapi.adapter.controller;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.responses.DefaultMatchCaseRepository;
import com.luca.condorapi.adapter.repository.responses.MatchCaseAdapter;
import com.luca.condorapi.domain.MatchCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JpResponsesController
{

    String connectionString = System.getenv("CONNECTION_STRING_JP_RESPONSES"); //"";
    String databaseName = System.getenv("DATABASE_NAME_JP_RESPONSES"); //"";
    String collectionName = System.getenv("COLLECTION_NAME_JP_RESPONSES"); //"";
    private DefaultMatchCaseRepository defaultMatchCaseRepository;

    public JpResponsesController (){
        defaultMatchCaseRepository = new DefaultMatchCaseRepository(new MongoAdapter<>(new MatchCaseAdapter(),connectionString,databaseName,collectionName));
    }

    @CrossOrigin
    @GetMapping("/jp/getMatch")
    public @ResponseBody
    ResponseEntity<List<MatchCase>> get() {
        List<MatchCase> matchCases = defaultMatchCaseRepository.getAll();
        return new ResponseEntity<>(matchCases, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(name="info", value = "/jp/addMatch", consumes= MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Void> addMatch(@RequestBody MatchCase matchCase){
        try {
            defaultMatchCaseRepository.storeMatchCase(matchCase);
            return ResponseEntity.accepted().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @CrossOrigin
    @PostMapping(name="info", value = "/jp/editMatch", consumes= MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Void> edit(@RequestBody MatchCase matchCase){
        defaultMatchCaseRepository.editMatchCase(matchCase);
        return ResponseEntity.accepted().build();
    }

    @CrossOrigin
    @GetMapping(value = "/jp/deleteMatch/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable String id){
        defaultMatchCaseRepository.deleteMatchCase(id);
        return ResponseEntity.accepted().build();
    }
}
