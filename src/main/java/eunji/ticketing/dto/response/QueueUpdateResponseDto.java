package eunji.ticketing.dto.response;

/**
 * [2.1] 대기열 상태 조회 응답 DTO - QUEUE_UPDATE 이벤트
 * GET /api/queue/{queueId} (SSE)
 * Event: QUEUE_UPDATE
 */
public record QueueUpdateResponseDto(
        Integer rank,
        Integer totalWaiting,
        Integer estimatedTimes
) {
    public static QueueUpdateResponseDto of(Integer rank, Integer totalWaiting, Integer estimatedTimes) {
        return new QueueUpdateResponseDto(rank, totalWaiting, estimatedTimes);
    }
}