package com.luca.condorapi.adapter.controller;

import com.luca.condorapi.domain.Log;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class LoggerControllerTest {

  List<Log> fakeRepo;
  private MockMvc mvc;

  @Before
  public void setUp(){
    fakeRepo = new ArrayList<>();
    mvc = standaloneSetup(new LoggerController()).build();
  }

  @Test
  public void shouldRetrieveLogs(){

  }

  @Test
  public void acceeptInfoPut() throws Exception {
    mvc.perform(put("/info")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{\"source\"  :\"source\"," +
                    "  \"message\" :\"message\"," +
                    "  \"time\"    :\"time\"" +
                    "}"))
            .andExpect(status().isAccepted());

    Log exp = new Log("source", "message", "info", "time");
    Log act = fakeRepo.get(0);
    assertThat(exp.source, is(act.source));
    assertThat(exp.message, is(act.message));
    assertThat(exp.level, is(act.level));
    assertThat(exp.time, is(act.time));

  }

  @Test
  public void acceeptWarnPut() throws Exception {
    mvc.perform(put("/warn")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{\"source\"  :\"source\"," +
                    "  \"message\" :\"message\"," +
                    "  \"time\"    :\"time\"" +
                    "}"))
            .andExpect(status().isAccepted());

    Log exp = new Log("source", "message", "warn", "time");
    Log act = fakeRepo.get(0);
    assertThat(exp.source, is(act.source));
    assertThat(exp.message, is(act.message));
    assertThat(exp.level, is(act.level));
    assertThat(exp.time, is(act.time));
  }


}