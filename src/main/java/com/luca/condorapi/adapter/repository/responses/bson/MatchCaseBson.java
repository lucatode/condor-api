package com.luca.condorapi.adapter.repository.responses.bson;


import com.luca.condorapi.domain.MatchCase;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class MatchCaseBson {

  @BsonProperty("_Id")
  private String _id;
  @BsonProperty("MatchExact")
  private Boolean matchExact;
  @BsonProperty("Request")
  private String request;
  @BsonProperty("Response")
  private String response;
  @BsonProperty("IgnoreCase")
  private Boolean ignoreCase;

  @BsonCreator
  public MatchCaseBson(
          @BsonProperty("_Id") String _id,
          @BsonProperty("MatchExact") Boolean matchExact,
          @BsonProperty("Request") String request,
          @BsonProperty("Response") String response,
          @BsonProperty("IgnoreCase") Boolean ignoreCase) {

    this._id = _id;
    this.matchExact = matchExact;
    this.request = request;
    this.response = response;
    this.ignoreCase = ignoreCase;
  }


  @BsonCreator
  public MatchCaseBson(Builder builder) {
    this._id = builder._id;
    this.matchExact = builder.matchExact;
    this.request = builder.request;
    this.response = builder.response;
    this.ignoreCase = builder.ignoreCase;
  }

  public String getId() {
    return _id;
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

  public Boolean getIgnoreCase() {
    return ignoreCase;
  }

  public static final class Builder {
    public String _id;
    private Boolean matchExact;
    private String request;
    private String response;
    private Boolean ignoreCase;

    public Builder() {
    }

    public static Builder aMatchCaseBson() {
      return new Builder();
    }

    public Builder withId(String id) {
      this._id = id;
      return this;
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

    public Builder withIgnoreCase(Boolean ignoreCase) {
      this.ignoreCase = ignoreCase;
      return this;
    }

    public MatchCaseBson build() {
      return new MatchCaseBson(_id, matchExact, request, response, ignoreCase);
    }


    public static Builder fromMatchCase(MatchCase matchCase) {
      return new Builder()
              .withId(matchCase._Id)
              .withMatchExact(matchCase.ExactMatch)
              .withRequest(matchCase.Request)
              .withResponse(matchCase.Response)
              .withIgnoreCase(matchCase.IgnoreCase);
    }
  }
}
