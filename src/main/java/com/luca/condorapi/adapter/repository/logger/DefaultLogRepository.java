package com.luca.condorapi.adapter.repository.logger;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.logger.bson.LogBson;
import com.luca.condorapi.domain.Log;
import com.luca.condorapi.domain.repository.LogRepository;
import org.springframework.stereotype.Component;

public class DefaultLogRepository implements LogRepository {
    private final MongoAdapter<LogBson> mongodb;

    public DefaultLogRepository(MongoAdapter<LogBson> mongodb) {
        this.mongodb = mongodb;
    }


    @Override
    public void storeLog(Log log) {
        mongodb.insertDocument(LogBson.Builder.fromLog(log).build());
    }

    public static final class DefaultLogRepositoryBuilder {
        private MongoAdapter<LogBson> mongodb;

        private DefaultLogRepositoryBuilder() {
        }

        public static DefaultLogRepositoryBuilder aDefaultLogRepository() {
            return new DefaultLogRepositoryBuilder();
        }

        public DefaultLogRepositoryBuilder withMongodb(MongoAdapter<LogBson> mongodb) {
            this.mongodb = mongodb;
            return this;
        }

        public DefaultLogRepository build() {
            return new DefaultLogRepository(mongodb);
        }
    }
}
