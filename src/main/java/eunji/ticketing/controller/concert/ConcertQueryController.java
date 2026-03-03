package eunji.ticketing.controller.concert;

import eunji.ticketing.service.concert.ConcertQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 콘서트 조회 API Controller (CQRS - Query)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concerts")
@Tag(name = "ConcertController")
public class ConcertQueryController {

    private final ConcertQueryService concertQueryService;

    /**
     * [1.1] 티켓팅 상태 조회 (SSE)
     * 특정 콘서트의 예매 상태(오픈전/예매중/끝) 및 잔여 좌석 현황을 실시간(SSE)으로 스트리밍합니다.
     *
     * @param concertId 콘서트 ID
     * @param userId    사용자 ID (Header)
     * @return SseEmitter
     */
    @GetMapping(value = "/{concertId}/status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getConcertStatus(
            @PathVariable Long concertId,
            @RequestHeader("userId") Long userId
    ) {
        // TODO: 개발자가 직접 작성함
        return concertQueryService.subscribeConcertStatus(concertId, userId);
    }
}