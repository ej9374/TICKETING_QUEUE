package eunji.ticketing.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import eunji.ticketing.exception.SuccessCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean success;
    private final int status;
    private final String message;
    private final String code;
    private final T data;
    private final LocalDateTime timestamp;

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(build(SuccessCode.OK, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(SuccessCode successCode, T data) {
        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(build(successCode, data));
    }

    public static ResponseEntity<ApiResponse<Void>> created() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(build(SuccessCode.CREATED, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(build(SuccessCode.CREATED, data));
    }

    public static <T> ApiResponse<T> of(SuccessCode successCode, T data) {
        return build(successCode, data);
    }

    private static <T> ApiResponse<T> build(SuccessCode successCode, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(successCode.getHttpStatus().value())
                .message(successCode.getMessage())
                .code(successCode.getCode())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
}