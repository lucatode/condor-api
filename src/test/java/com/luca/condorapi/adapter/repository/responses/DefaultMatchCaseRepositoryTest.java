package com.luca.condorapi.adapter.repository.responses;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.responses.bson.MatchCaseBson;
import com.luca.condorapi.domain.MatchCase;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DefaultMatchCaseRepositoryTest {
    public static final String CONNECTION_STRING = "mongodb://lucatode:Viaggiare99@ds119734.mlab.com:19734/vauthbot";
    public static final String DATABASE_NAME = "vauthbot";
    public static final String COLLECTION_NAME = "responses";

    @Test
    public void test(){
        DefaultMatchCaseRepository defaultMatchCaseRepository = new DefaultMatchCaseRepository(new MongoAdapter<MatchCaseBson>(new MatchCaseAdapter(), CONNECTION_STRING, DATABASE_NAME, COLLECTION_NAME));
        List<MatchCase> all = defaultMatchCaseRepository.getAll();
        int size = all.size();
        assertTrue(size > 0);

        //defaultMatchCaseRepository.storeMatchCase(new MatchCase("_abc", false, "aaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbbb"));
        List<MatchCase> allNew = defaultMatchCaseRepository.getAll();
        int size2 = allNew.size();
        assertTrue(size2 > size);
    }

    @Test
    public void test2(){
        DefaultMatchCaseRepository defaultMatchCaseRepository = new DefaultMatchCaseRepository(new MongoAdapter<MatchCaseBson>(new MatchCaseAdapter(), CONNECTION_STRING, DATABASE_NAME, COLLECTION_NAME));

        //defaultMatchCaseRepository.deleteMatchCase("5bffabf428aaac41c04a0283");
    }

}