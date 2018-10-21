package com.luca.condorapi.domain;

import java.util.Objects;

public class Log {
    public final String source;
    public final String message;
    public final String level;
    public final String time;

    public Log(String source, String message, String level, String time) {
        this.source = source;
        this.message = message;
        this.level = level;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(source, log.source) &&
                Objects.equals(message, log.message) &&
                Objects.equals(level, log.level) &&
                Objects.equals(time, log.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(source, message, level, time);
    }
}
