package com.luca.condorapi.adapter.repository.responses;

import com.luca.condorapi.adapter.repository.MongoAdapter;
import com.luca.condorapi.adapter.repository.responses.bson.MatchCaseBson;
import com.luca.condorapi.domain.MatchCase;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultMatchCaseRepository  {
    private final MongoAdapter<MatchCaseBson> mongodb;

    public DefaultMatchCaseRepository(MongoAdapter<MatchCaseBson> mongodb) {
        this.mongodb = mongodb;
    }


    public List<MatchCase> getAll(){
        return this.mongodb.getAll().stream().map(x -> new MatchCase(x.getId(), x.getMatchExact(),x.getRequest(),x.getResponse(), x.getIgnoreCase())).collect(Collectors.toList());
    }

    public void storeMatchCase(MatchCase matchCase) {
        mongodb.insertDocument(MatchCaseBson.Builder.fromMatchCase(matchCase).build());
    }

    public void deleteMatchCase(String id){
        mongodb.deleteDocument(id);
    }

    public void editMatchCase(MatchCase matchCase){
        mongodb.updateDocument(matchCase._Id, MatchCaseBson.Builder.fromMatchCase(matchCase).build());
    }

    public static final class DefaultMatchCaseRepositoryBuilder {
        private MongoAdapter<MatchCaseBson> mongodb;

        private DefaultMatchCaseRepositoryBuilder() {
        }

        public static DefaultMatchCaseRepositoryBuilder aDefaultMatchCaseRepository() {
            return new DefaultMatchCaseRepositoryBuilder();
        }

        public DefaultMatchCaseRepositoryBuilder withMongodb(MongoAdapter<MatchCaseBson> mongodb) {
            this.mongodb = mongodb;
            return this;
        }

        public DefaultMatchCaseRepository build() {
            return new DefaultMatchCaseRepository(mongodb);
        }
    }
}
