package com.luca.condorapi.adapter.controller;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.logger.DefaultLogRepository;
import com.luca.condorapi.adapter.repository.logger.LogAdapter;
import com.luca.condorapi.adapter.repository.logger.bson.LogBson;
import com.luca.condorapi.domain.Log;
import com.luca.condorapi.domain.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Optional;

@Controller
public class LoggerController {

  private static final String INFO = "INFO";
  private static final String WARN = "WARN";
  private static final String ERROR = "ERROR";
  private final LogRepository logRepository;
  
  public LoggerController() {
    String connectionString = System.getenv("CONNECTION_STRING"); //"";
    String databaseName = System.getenv("DATABASE_NAME"); //"";
    String collectionName = System.getenv("COLLECTION_NAME"); //"";
    this.logRepository = new DefaultLogRepository(new MongoAdapter<LogBson>(new LogAdapter(), connectionString, databaseName, collectionName));
  }

  @GetMapping("/getLogs")
  public @ResponseBody
  ResponseEntity<String> get() {
    return new ResponseEntity<String>("", HttpStatus.OK);
  }

  @PutMapping(name="info", value = "/info", consumes=MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<Void> info(@RequestBody LogRepresentation logRepresentation){
    logRepository.storeLog(adapt(logRepresentation, INFO).get());
    return ResponseEntity.accepted().build();
  }

  @PutMapping("/warn")
  public ResponseEntity<Void> warn(@RequestBody LogRepresentation logRepresentation){
    logRepository.storeLog(adapt(logRepresentation, WARN).get());
    return ResponseEntity.accepted().build();
  }

  @PutMapping("/error")
  public ResponseEntity<Void> error(@RequestBody LogRepresentation logRepresentation){
    logRepository.storeLog(adapt(logRepresentation, ERROR).get());
    return ResponseEntity.accepted().build();
  }

  private static class LogRepresentation {
    public String source;
    public String message;

    public LogRepresentation() {
    }

  }

  private static Optional<Log> adapt(LogRepresentation logRepresentation, String level){
    return Optional.of(new Log(logRepresentation.source, logRepresentation.message, level, new Date().toString()));
  }

}
