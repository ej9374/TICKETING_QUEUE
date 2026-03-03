package eunji.ticketing.service.concert;

import eunji.ticketing.dto.response.QueueEnterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 콘서트 관련 Command 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class ConcertService {

    /**
     * [1.2] 대기열 진입
     * 공연 예매를 위해 대기열 진입을 시도합니다.
     *
     * @param concertId 콘서트 ID
     * @param userId    사용자 ID
     * @return 대기열 진입 결과 (queueId)
     */
    @Transactional
    public QueueEnterResponseDto enterQueue(Long concertId, Long userId) {
        // TODO: 개발자가 직접 작성함
        // 1. 콘서트 존재 여부 검증
        // 2. 티켓 오픈 시간 검증
        // 3. 대기열 토큰 생성
        // 4. 대기열에 사용자 등록
        throw new UnsupportedOperationException("Not implemented yet");
    }
}