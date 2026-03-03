package eunji.ticketing.dto.response;

/**
 * [1.2] 티켓팅 진입 응답 DTO
 * POST /api/concerts/{concertId}/enter
 */
public record QueueEnterResponseDto(
        String queueId
) {
    public static QueueEnterResponseDto of(String queueId) {
        return new QueueEnterResponseDto(queueId);
    }
}