package com.luca.condorapi.adapter.repository.responses.bson;


import com.luca.condorapi.domain.MatchCase;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class MatchCaseBson {

    @BsonProperty("MatchExact")
    private Boolean matchExact;
    @BsonProperty("Request")
    private String request;
    @BsonProperty("Response")
    private String response;

    @BsonCreator
    public MatchCaseBson(
            @BsonProperty("MatchExact") Boolean matchExact,
            @BsonProperty("Request") String request,
            @BsonProperty("Response") String response) {

        this.matchExact = matchExact;
        this.request = request;
        this.response = response;
    }


    @BsonCreator
    public MatchCaseBson(Builder builder) {
        this.matchExact = builder.matchExact;
        this.request = builder.request;
        this.response = builder.response;
    }


    public Boolean getMatchExact() {
        return matchExact;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    public static final class Builder {
        private Boolean matchExact;
        private String request;
        private String response;

        public Builder() {
        }

        public static Builder aMatchCaseBson() {
            return new Builder();
        }

        public Builder withMatchExact(Boolean matchExact) {
            this.matchExact = matchExact;
            return this;
        }

        public Builder withRequest(String request) {
            this.request = request;
            return this;
        }

        public Builder withResponse(String response) {
            this.response = response;
            return this;
        }

        public MatchCaseBson build() {
            return new MatchCaseBson(matchExact, request, response);
        }



    public static Builder fromMatchCase(MatchCase matchCase) {
        return new Builder()
                .withMatchExact(matchCase.ExactMatch)
                .withRequest(matchCase.Request)
                .withResponse(matchCase.Response);
    }
    }
}
