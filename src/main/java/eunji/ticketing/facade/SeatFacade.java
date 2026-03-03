package eunji.ticketing.facade;

import eunji.ticketing.dto.request.SeatRequestDto;
import eunji.ticketing.dto.response.SeatSelectResponse;
import eunji.ticketing.service.seat.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SeatFacade {

    private final SeatService seatService;

    /**
     * [3.2] 좌석 선택 및 선점
     * FREE 좌석 중 결제를 위한 좌석을 선점합니다.
     *
     * @param userId  사용자 ID
     * @param request 좌석 선택 요청 정보
     */
    @Transactional
    public SeatSelectResponse selectSeat(Long userId, SeatRequestDto request) {
        // TODO: 개발자가 직접 작성함
        // 1. 좌석 존재 여부 검증
        // 2. 좌석 상태 검증 (FREE 상태인지)
        // 3. 분산 락 획득 (동시성 제어)
        // 4. 좌석 선점 처리 (상태 변경)
        Long reservationId = seatService.selectSeat(userId, request);
        return new SeatSelectResponse(reservationId);
    }

    /**
     * [3.3] 선점한 좌석 결제
     * 선점한 좌석에 대해 결제를 진행합니다.
     *
     * @param userId  사용자 ID
     * @param request 결제 요청 정보
     */
    @Transactional
    public void paySeat(Long userId, Long reservationId, SeatRequestDto request) {
        // TODO: 개발자가 직접 작성함
        // 1. 좌석 선점 상태 검증
        // 2. 선점자 일치 여부 검증
        // 3. 결제 처리 (외부 PG 연동)
        // 4. 좌석 상태 변경 (SOLD)
        // 5. 예매 정보 생성
    }
}
