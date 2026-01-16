package ir.msob.jima.core.commons.util;

import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import jakarta.annotation.Nonnull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * A composite InputStream that reads from multiple underlying InputStreams sequentially.
 * <p>
 * This class allows you to create an InputStream that reads data from multiple InputStreams
 * in a sequential manner. It is useful when you need to concatenate multiple input streams
 * into a single stream.
 */
public class MultiInputStream extends InputStream {
    private final Deque<InputStream> streams;

    /**
     * Constructs a MultiInputStream from an array of InputStreams.
     *
     * @param streams An array of InputStreams to be read sequentially.
     */
    public MultiInputStream(InputStream... streams) {
        this.streams = new LinkedList<>();
        Collections.addAll(this.streams, streams);
    }

    /**
     * Constructs a MultiInputStream from a collection of InputStreams.
     *
     * @param inputStreams A collection of InputStreams to be read sequentially.
     */
    public MultiInputStream(Collection<InputStream> inputStreams) {
        this.streams = new LinkedList<>();
        Collections.addAll(this.streams, inputStreams.toArray(InputStream[]::new));
    }

    /**
     * Closes the current InputStream and moves to the next one.
     *
     * @throws IOException If an I/O error occurs when closing the current InputStream.
     */
    private void nextStream() throws IOException {
        streams.removeFirst().close();
    }

    /**
     * Reads the next byte of data from the input stream.
     *
     * @return The next byte of data, or -1 if the end of the stream is reached.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        int result = -1;
        while (!streams.isEmpty()
                && (result = streams.getFirst().read()) == -1) {
            nextStream();
        }
        return result;
    }

    /**
     * Reads up to len bytes of data from the input stream into an array of bytes.
     *
     * @param b   The buffer into which the data is read.
     * @param off The start offset in the data.
     * @param len The maximum number of bytes to read.
     * @return The total number of bytes read, or -1 if there is no more data.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public int read(@Nonnull byte[] b, int off, int len) throws IOException {
        int result = -1;
        while (!streams.isEmpty()
                && (result = streams.getFirst().read(b, off, len)) == -1) {
            nextStream();
        }
        return result;
    }

    /**
     * Skips over and discards n bytes of data from the input stream.
     *
     * @param n The number of bytes to skip.
     * @return The actual number of bytes skipped.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public long skip(long n) throws IOException {
        long skipped = 0L;
        while (skipped < n && !streams.isEmpty()) {
            int thisSkip = (int) streams.getFirst().skip(n - skipped);
            if (thisSkip > 0)
                skipped += thisSkip;
            else
                nextStream();
        }
        return skipped;
    }

    /**
     * Returns an estimate of the number of bytes that can be read (or skipped over)
     * from the current input stream without blocking.
     *
     * @return An estimate of the number of bytes that can be read or skipped.
     */
    @Override
    public int available() {
        return streams.isEmpty() ? 0 : streams
                .stream()
                .filter(Objects::nonNull)
                .map(s -> {
                    try {
                        return s.available();
                    } catch (IOException e) {
                        throw new CommonRuntimeException(e);
                    }
                }).reduce(0, Integer::sum);
    }

    /**
     * Closes all underlying InputStreams.
     *
     * @throws IOException If an I/O error occurs when closing any of the InputStreams.
     */
    @Override
    public void close() throws IOException {
        while (!streams.isEmpty())
            nextStream();
    }
}
