package com.luca.condorapi.adapter.repository.logger.bson;


import com.luca.condorapi.domain.Log;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.math.BigDecimal;

public class LogBson {

    @BsonProperty("source") private  String source;
    @BsonProperty("message") private  String message;
    @BsonProperty("level") private  String level;
    @BsonProperty("time") private  String time;

    @BsonCreator
    public LogBson(
            @BsonProperty("source")  String source,
            @BsonProperty("message")  String message,
            @BsonProperty("level")  String level,
            @BsonProperty("time") String time) {
        this.source = source;
        this.message = message;
        this.level = level;
        this.time = time;
    }

    @BsonCreator
    public LogBson(Builder builder) {
        source = builder.source;
        message = builder.message;
        level = builder.level;
        time = builder.time;
    }


    @BsonCreator
    public LogBson() {
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public String getLevel() {
        return level;
    }

    public String getTime() {
        return time;
    }



    public static final class Builder {
        private  String source;
        private  String message;
        private String level;
        private  String time;

        public Builder() {
        }

        public static Builder aLogBson() {
            return new Builder();
        }

        public Builder withSource(String source) {
            this.source = source;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder withTime(String time) {
            this.time = time;
            return this;
        }

        public LogBson build() {
            LogBson logBson = new LogBson(source, message, level, time);
            return logBson;
        }

        public static Builder fromLog(Log aLog) {
            return new Builder()
                    .withSource(aLog.source)
                    .withMessage(aLog.message)
                    .withLevel(aLog.level)
                    .withTime(aLog.time);
        }
    }
}
