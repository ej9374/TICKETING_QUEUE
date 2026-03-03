package eunji.ticketing.dto.response;

/**
 * [3.2] 좌석 선점 응답 DTO
 * POST /api/seat/select
 */
public record SeatSelectResponse(
        Long reservationId
) {
}
