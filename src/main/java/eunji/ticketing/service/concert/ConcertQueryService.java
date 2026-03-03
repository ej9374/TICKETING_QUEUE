package eunji.ticketing.service.concert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 콘서트 관련 Query 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class ConcertQueryService {

    /**
     * [1.1] 콘서트 상태 SSE 구독
     * 특정 콘서트의 예매 상태(오픈전/예매중/끝) 및 잔여 좌석 현황을 실시간으로 스트리밍합니다.
     *
     * @param concertId 콘서트 ID
     * @param userId    사용자 ID
     * @return SseEmitter
     */
    @Transactional(readOnly = true)
    public SseEmitter subscribeConcertStatus(Long concertId, Long userId) {
        // TODO: 개발자가 직접 작성함
        // 1. 콘서트 존재 여부 검증
        // 2. SseEmitter 생성 및 등록
        // 3. CONNECTED 이벤트 발송
        // 4. STATUS_UPDATE 이벤트 구독 설정
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
