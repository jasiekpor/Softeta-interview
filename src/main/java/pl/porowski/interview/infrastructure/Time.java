package pl.porowski.interview.infrastructure;

import java.time.Instant;

public interface Time {

    default Instant currentInstant() {
        return Instant.now();
    }

    default long currentTimeMillis() {
        return currentInstant().toEpochMilli();
    }
}
