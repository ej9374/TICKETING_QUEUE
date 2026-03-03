package eunji.ticketing.controller.queue;

import eunji.ticketing.service.queue.QueueQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 대기열 조회 API Controller (CQRS - Query)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/queue")
@Tag(name = "QueueController")
public class QueueQueryController {

    private final QueueQueryService queueQueryService;

    /**
     * [2.1] 대기열 상태 조회 (SSE)
     * 클라이언트의 대기열 상태(남은 대기 인원, 예상 대기 시간 등)를 실시간으로 스트리밍합니다.
     *
     * @param queueId 대기열 ID
     * @param userId  사용자 ID (Header)
     * @return SseEmitter
     */
    @GetMapping(value = "/{queueId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getQueueStatus(
            @PathVariable String queueId,
            @RequestHeader("userId") Long userId
    ) {
        return queueQueryService.subscribeQueueStatus(queueId, userId);
    }
}