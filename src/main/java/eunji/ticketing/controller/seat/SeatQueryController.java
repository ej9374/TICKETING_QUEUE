package eunji.ticketing.controller.seat;

import eunji.ticketing.dto.response.SeatListResponseDto;
import eunji.ticketing.response.ApiResponse;
import eunji.ticketing.service.seat.SeatQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 조회 API Controller (CQRS - Query)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat")
@Tag(name = "SeatController")
public class SeatQueryController {

    private final SeatQueryService seatQueryService;

    /**
     * [3.1] 좌석 상태 조회
     * 예매창 진입 시 전체 좌석을 조회합니다.
     *
     * @param concertId 콘서트 ID
     * @param userId    사용자 ID (Header)
     * @return 좌석 목록 정보
     */
    @GetMapping("/{concertId}")
    public ResponseEntity<ApiResponse<SeatListResponseDto>> getSeats(
            @PathVariable Long concertId,
            @RequestHeader("userId") Long userId
    ) {
        // TODO: 개발자가 직접 작성함
        SeatListResponseDto response = seatQueryService.getSeatList(concertId, userId);
        return ApiResponse.success(response);
    }
}