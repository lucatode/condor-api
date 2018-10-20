package com.luca.condorapi.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoggerController {

  @GetMapping("/getLogs")
  public String greeting() {return "";}

  @PutMapping("/info")
  public ResponseEntity<Void> info(){ return null; }

  @PutMapping("/warn")
  public ResponseEntity<Void> warn(){ return null; }

  @PutMapping("/error")
  public ResponseEntity<Void> error(){ return null; }

}
