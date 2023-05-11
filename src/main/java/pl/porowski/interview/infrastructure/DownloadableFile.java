package pl.porowski.interview.infrastructure;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@RequiredArgsConstructor
public abstract class DownloadableFile<T> {

    protected final T objectToSerialize;

    public abstract InputStream serialize();
}
