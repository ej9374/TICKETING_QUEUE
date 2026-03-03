package eunji.ticketing.response;

import eunji.ticketing.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private final boolean success;
    private final int status;
    private final String message;
    private final String code;
    private final LocalDateTime timestamp;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .success(false)
                .status(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .success(false)
                .status(errorCode.getHttpStatus().value())
                .message(message)
                .code(errorCode.getCode())
                .timestamp(LocalDateTime.now())
                .build();
    }
}