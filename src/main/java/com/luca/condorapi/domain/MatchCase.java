package com.luca.condorapi.domain;

public class MatchCase {
    public String _Id;
    public Boolean ExactMatch;
    public String Request;
    public String Response;

    public MatchCase() {
    }

    public MatchCase(String _id, Boolean exactMatch, String request, String response) {
        _Id = _id;
        ExactMatch = exactMatch;
        Request = request;
        Response = response;
    }
}
