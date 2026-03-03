package eunji.ticketing.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * [3.2] 좌석 선택 및 선점 요청 DTO
 * POST /api/seat/select
 */
public record SeatSelectRequestDto(
        @NotNull(message = "콘서트 ID는 필수입니다.")
        @Positive(message = "콘서트 ID는 양수여야 합니다.")
        Long concertId,

        @NotNull(message = "좌석 ID는 필수입니다.")
        @Positive(message = "좌석 ID는 양수여야 합니다.")
        Long seatId
) {
}