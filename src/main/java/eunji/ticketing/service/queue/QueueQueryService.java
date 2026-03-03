package eunji.ticketing.service.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 대기열 관련 Query 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class QueueQueryService {

    /**
     * [2.1] 대기열 상태 SSE 구독
     * 클라이언트의 대기열 상태(남은 대기 인원, 예상 대기 시간 등)를 실시간으로 스트리밍합니다.
     *
     * @param queueId 대기열 ID
     * @param userId  사용자 ID
     * @return SseEmitter
     */
    @Transactional(readOnly = true)
    public SseEmitter subscribeQueueStatus(String queueId, Long userId) {
        // TODO: 개발자가 직접 작성함
        // 1. 대기열 토큰 유효성 검증
        // 2. SseEmitter 생성 및 등록
        // 3. CONNECTED 이벤트 발송
        // 4. 주기적 QUEUE_UPDATE 이벤트 발송 (rank, totalWaiting, estimatedTimes)
        throw new UnsupportedOperationException("Not implemented yet");
    }
}