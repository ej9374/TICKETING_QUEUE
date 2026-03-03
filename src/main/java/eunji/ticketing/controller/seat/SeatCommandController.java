package eunji.ticketing.controller.seat;

import eunji.ticketing.dto.request.SeatRequestDto;
import eunji.ticketing.dto.response.SeatSelectResponse;
import eunji.ticketing.facade.SeatFacade;
import eunji.ticketing.response.ApiResponse;
import eunji.ticketing.service.seat.SeatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 명령 API Controller (CQRS - Command)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat")
@Tag(name = "SeatController")
public class SeatCommandController {

    private final SeatFacade seatFacade;

    /**
     * [3.2] 좌석 선택 및 선점
     * FREE 좌석 중 결제를 위한 좌석을 선점합니다.
     *
     * @param userId  사용자 ID (Header)
     * @param request 좌석 선택 요청 정보 (concertId, seatId)
     * @return 선점 결과
     */
    @PostMapping("/select")
    public ResponseEntity<ApiResponse<SeatSelectResponse>> selectSeat(
            @RequestHeader("userId") Long userId,
            @Valid @RequestBody SeatRequestDto request
    ) {
        SeatSelectResponse response = seatFacade.selectSeat(userId, request);
        return ApiResponse.created(response);
    }

    /**
     * [3.3] 선점한 좌석 결제
     * 선점한 좌석에 대해 결제를 진행합니다.
     *
     * @param userId  사용자 ID (Header)
     * @param request 결제 요청 정보 (concertId, seatId)
     * @return 결제 결과
     */
    @PostMapping("/pay/{reservationId}")
    public ResponseEntity<ApiResponse<Void>> paySeat(
            @RequestHeader("userId") Long userId,
            @PathVariable("reservationId") Long reservationId,
            @Valid @RequestBody SeatRequestDto request
    ) {
        // TODO: 개발자가 직접 작성함
        seatFacade.paySeat(userId, reservationId, request);
        return ApiResponse.created();
    }
}