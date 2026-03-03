package eunji.ticketing.dto.response;

/**
 * [2.1] 대기열 상태 조회 응답 DTO - CONNECTED 이벤트
 * GET /api/queue/{queueId} (SSE)
 * Event: CONNECTED
 */
public record QueueConnectedResponseDto(
        String status
) {
    public static QueueConnectedResponseDto of(String status) {
        return new QueueConnectedResponseDto(status);
    }

    public static QueueConnectedResponseDto connected() {
        return new QueueConnectedResponseDto("CONNECTED");
    }
}