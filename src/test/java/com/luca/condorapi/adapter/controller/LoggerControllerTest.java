package com.luca.condorapi.adapter.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoggerControllerTest {

  private MockMvc mvc;

  @Before
  public void setUp(){
  }

  @Test
  public void shouldRetrieveLogs(){

  }

  @Test
  public void acceeptInfoPut() throws Exception {
    mvc.perform(put("/info")
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isAccepted());

    //assertRepoContains(infoLog);
  }

  @Test
  public void acceeptWarnPut() throws Exception {
    mvc.perform(put("/warn")
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isAccepted());

    //assertRepoContains(infoLog);
  }


}