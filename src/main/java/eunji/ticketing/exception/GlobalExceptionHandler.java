package eunji.ticketing.exception;

import eunji.ticketing.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 전역 예외 처리 핸들러
 * 애플리케이션에서 발생하는 모든 예외를 일관된 형식(ErrorResponse)으로 변환하여 반환한다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 비즈니스 예외 처리
     * 서비스 계층에서 발생하는 비즈니스 로직 관련 예외를 처리한다.
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<ErrorResponse> handleBizException(BizException e) {
        return ErrorResponse.of(e.getErrorCode(), e.getMessage());
    }

    /**
     * {@code @Valid} 유효성 검증 실패 예외 처리
     * DTO의 {@code @NotNull}, {@code @Size} 등 Bean Validation 어노테이션 검증 실패 시 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse(ErrorCode.INVALID_INPUT_VALUE.getMessage());

        return ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, message);
    }

    /**
     * 타입 불일치 예외 처리
     * PathVariable, RequestParam 등의 타입 변환 실패 시 발생한다. (예: Long 타입에 문자열 전달)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE);
    }

    /**
     * HTTP 메시지 파싱 실패 예외 처리
     * JSON 파싱 오류, 잘못된 Request Body 형식 등으로 요청 본문을 읽을 수 없을 때 발생한다.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
    }

    /**
     * 필수 요청 파라미터 누락 예외 처리
     * 필수 RequestParam이 전달되지 않았을 때 발생한다.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getParameterName() + " 파라미터가 필요합니다.");
    }

    /**
     * 지원하지 않는 HTTP 메서드 예외 처리
     * GET만 허용된 엔드포인트에 POST 요청 등 잘못된 HTTP 메서드로 요청 시 발생한다.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 핸들러 없음 예외 처리
     * 존재하지 않는 API 엔드포인트로 요청 시 발생한다. (404 Not Found)
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        return ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
    }

    /**
     * 기타 모든 예외 처리 (Fallback)
     * 위에서 처리되지 않은 모든 예외를 500 Internal Server Error로 처리한다.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}