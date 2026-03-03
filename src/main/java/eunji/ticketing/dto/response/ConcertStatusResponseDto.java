package eunji.ticketing.dto.response;

/**
 * [1.1] 티켓팅 상태 조회 응답 DTO
 * GET /api/concerts/{concertId}/status (SSE)
 * Event: CONNECTED, STATUS_UPDATE
 */
public record ConcertStatusResponseDto(
        Long concertId,
        String status
) {
    public static ConcertStatusResponseDto of(Long concertId, String status) {
        return new ConcertStatusResponseDto(concertId, status);
    }
}
