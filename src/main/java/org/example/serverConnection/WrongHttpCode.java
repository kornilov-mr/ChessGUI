package org.example.serverConnection;

import java.util.Objects;

public class WrongHttpCode extends RuntimeException{
    private final int httpCode;

    public WrongHttpCode(int httpCode, String message) {
        super(message+"real Code:"+httpCode);
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WrongHttpCode that)) return false;
        return httpCode == that.httpCode;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(httpCode);
    }
}
