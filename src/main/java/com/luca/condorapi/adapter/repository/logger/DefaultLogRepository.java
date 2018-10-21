package com.luca.condorapi.adapter.repository.logger;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.logger.bson.LogBson;
import com.luca.condorapi.domain.Log;
import com.luca.condorapi.domain.repository.LogRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultLogRepository implements LogRepository {
    private final MongoAdapter<LogBson> mongodb;

    public DefaultLogRepository(MongoAdapter<LogBson> mongodb) {
        this.mongodb = mongodb;
    }

    @Override
    public void storeLog(Log log) {
        mongodb.insertDocument(LogBson.Builder.fromLog(log).build());
    }
}
