package eunji.ticketing.service.seat;

import eunji.ticketing.dto.response.SeatListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 좌석 관련 Query 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class SeatQueryService {

    /**
     * [3.1] 좌석 상태 조회
     * 예매창 진입 시 전체 좌석을 조회합니다.
     *
     * @param concertId 콘서트 ID
     * @param userId    사용자 ID
     * @return 좌석 목록 정보
     */
    @Transactional(readOnly = true)
    public SeatListResponseDto getSeatList(Long concertId, Long userId) {
        // TODO: 개발자가 직접 작성함
        // 1. 콘서트 존재 여부 검증
        // 2. 사용자 대기열 통과 여부 검증
        // 3. 전체 좌석 조회 (Fetch Join 적용)
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
