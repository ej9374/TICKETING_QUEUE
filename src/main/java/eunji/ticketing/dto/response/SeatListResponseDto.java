package eunji.ticketing.dto.response;

import eunji.ticketing.entity.Seat;

import java.util.List;

/**
 * [3.1] 좌석 상태 조회 응답 DTO
 * GET /api/seat/{concertId}
 */
public record SeatListResponseDto(
        Long concertId,
        String zone,
        List<SeatInfoResponseDto> seats
) {
    public static SeatListResponseDto of(Long concertId, String zone, List<Seat> seats) {
        List<SeatInfoResponseDto> seatInfoList = seats.stream()
                .map(SeatInfoResponseDto::from)
                .toList();
        return new SeatListResponseDto(concertId, zone, seatInfoList);
    }
}