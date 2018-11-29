package com.luca.condorapi.adapter.repository.responses;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.responses.bson.MatchCaseBson;
import com.luca.condorapi.domain.MatchCase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DefaultMatchCaseRepositoryTest {
    public static final String CONNECTION_STRING = "";
    public static final String DATABASE_NAME = "vauthbot";
    public static final String COLLECTION_NAME = "responses";

    @Test
    public void test(){
        DefaultMatchCaseRepository defaultMatchCaseRepository = new DefaultMatchCaseRepository(new MongoAdapter<MatchCaseBson>(new MatchCaseAdapter(), CONNECTION_STRING, DATABASE_NAME, COLLECTION_NAME));
        List<MatchCase> all = defaultMatchCaseRepository.getAll();
        assertTrue(all.size() > 0);
    }
}