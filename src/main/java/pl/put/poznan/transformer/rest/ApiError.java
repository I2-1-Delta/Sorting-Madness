package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import pl.put.poznan.transformer.sorting.NoSortingAlgorithmSelected;
import pl.put.poznan.transformer.sorting.NothingToSort;
import pl.put.poznan.transformer.sorting.ObjectDontHaveSortingProperty;

import java.time.LocalDateTime;

/**
 * The class that is used as a response when some error has happened
 * {@link NothingToSort}, {@link ObjectDontHaveSortingProperty}, {@link NoSortingAlgorithmSelected}
 */
public class ApiError {
    private final HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;
    private final String message;

    public ApiError(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    /**
     * HTTP status of the response
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Timestamp when error has occurred
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Message that explains to user what has happened
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
