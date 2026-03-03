package eunji.ticketing.dto.response;

import eunji.ticketing.entity.Seat;

/**
 * [3.1] 좌석 상태 조회 - 개별 좌석 정보 DTO
 */
public record SeatInfoResponseDto(
        Long seatId,
        Integer seatRow,
        Integer seatColumn,
        String seatGrade,
        String status
) {
    public static SeatInfoResponseDto from(Seat seat) {
        return new SeatInfoResponseDto(
                seat.getSeatId(),
                seat.getSeatRow(),
                seat.getSeatColumn(),
                seat.getSeatGrade().name(),
                seat.getSeatStatus().name()
        );
    }
}