package com.luca.condorapi.adapter.controller;

import com.luca.condorapi.domain.Log;
import com.luca.condorapi.domain.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class LoggerController {

  private final LogRepository logRepository;

  @Autowired
  public LoggerController(LogRepository logRepository) {
    this.logRepository = logRepository;
  }

  @GetMapping("/getLogs")
  public String greeting() {return "";}

  @PutMapping(name="info", value = "/info", consumes=MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<Void> info(@RequestBody LogRepresentation logRepresentation){
    logRepository.storeLog(adapt(logRepresentation).get());
    return ResponseEntity.accepted().build();
  }

  @PutMapping("/warn")
  public ResponseEntity<Void> warn(@RequestBody LogRepresentation logRepresentation){
    logRepository.storeLog(adapt(logRepresentation).get());
    return ResponseEntity.accepted().build();
  }

  @PutMapping("/error")
  public ResponseEntity<Void> error(){ return null; }

  private static class LogRepresentation {
    public String source;
    public String message;
    public String level;
    public String time;

    public LogRepresentation() {
    }

  }

  private static Optional<Log> adapt(LogRepresentation logRepresentation){
    return Optional.of(new Log(logRepresentation.source, logRepresentation.message, logRepresentation.level, logRepresentation.time));
  }

}
