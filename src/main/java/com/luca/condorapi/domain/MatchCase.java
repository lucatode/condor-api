package com.luca.condorapi.domain;

public class MatchCase {
    public Boolean ExactMatch;
    public String Request;
    public String Response;

    public MatchCase(Boolean exactMatch, String request, String response) {
        ExactMatch = exactMatch;
        Request = request;
        Response = response;
    }
}
