package eunji.ticketing.response;

import eunji.ticketing.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private final boolean success;
    private final int status;
    private final String message;
    private final String code;
    private final LocalDateTime timestamp;

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode) {
        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode, String message) {
        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(errorCode.getHttpStatus().value())
                .message(message)
                .code(errorCode.getCode())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }
}