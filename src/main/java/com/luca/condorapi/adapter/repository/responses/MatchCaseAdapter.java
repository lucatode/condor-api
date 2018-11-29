package com.luca.condorapi.adapter.repository.responses;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.responses.bson.MatchCaseBson;
import org.bson.Document;

public class MatchCaseAdapter implements MongoAdapter.Adapter<MatchCaseBson> {

    @Override
    public MatchCaseBson adapt(Document doc) {
        return new MatchCaseBson.Builder()
                .withMatchExact((Boolean) doc.get("MatchExact"))
                .withRequest((String) doc.get("Request"))
                .withResponse((String) doc.get("Response"))
                .build();
    }

    @Override
    public Document reverseAdapt(MatchCaseBson logBson) {
        return new Document("MatchExact", logBson.getMatchExact())
                .append("Request", logBson.getRequest())
                .append("Response", logBson.getResponse());
    }


}
