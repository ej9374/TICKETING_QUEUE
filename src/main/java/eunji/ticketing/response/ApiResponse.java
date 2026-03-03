package eunji.ticketing.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import eunji.ticketing.exception.SuccessCode;
import lombok.Builder;
import lombok.Getter;

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

    public static <T> ApiResponse<T> success(T data) {
        return of(SuccessCode.OK, data);
    }

    public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
        return of(successCode, data);
    }

    public static ApiResponse<Void> created() {
        return of(SuccessCode.CREATED, null);
    }

    public static <T> ApiResponse<T> created(T data) {
        return of(SuccessCode.CREATED, data);
    }

    public static <T> ApiResponse<T> of(SuccessCode successCode, T data) {
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