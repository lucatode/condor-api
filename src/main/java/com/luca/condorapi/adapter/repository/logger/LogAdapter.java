package com.luca.condorapi.adapter.repository.logger;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.logger.bson.LogBson;
import org.bson.Document;

public class LogAdapter implements MongoAdapter.Adapter<LogBson> {
    @Override
    public LogBson adapt(Document doc) {
        return new LogBson.Builder()
                .withSource((String) doc.get("source"))
                .withMessage((String) doc.get("message"))
                .withLevel((String) doc.get("level"))
                .withTime((String) doc.get("time"))
                .build();
    }

    @Override
    public Document reverseAdapt(LogBson logBson) {
        return new Document("source", logBson.getSource())
                .append("message", logBson.getTime())
                .append("level", logBson.getMessage())
                .append("time", logBson.getLevel());
    }
}
