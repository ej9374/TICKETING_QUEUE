package eunji.ticketing.controller.concert;

import eunji.ticketing.dto.response.QueueEnterResponseDto;
import eunji.ticketing.facade.ConcertFacade;
import eunji.ticketing.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 콘서트 명령 API Controller (CQRS - Command)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concerts")
@Tag(name = "ConcertController")
public class ConcertCommandController {

    private final ConcertFacade concertFacade;

    /**
     * [1.2] 티켓팅 진입
     * 공연 예매를 위해 대기열 진입을 시도합니다.
     *
     * @param concertId 콘서트 ID
     * @param userId    사용자 ID (Header)
     * @return 대기열 진입 결과 (queueId)
     */
    @PostMapping("/{concertId}/enter")
    public ResponseEntity<ApiResponse<QueueEnterResponseDto>> enterTicketing(
            @PathVariable Long concertId,
            @RequestHeader("userId") Long userId
    ) {
        QueueEnterResponseDto response = concertFacade.enterQueue(concertId, userId);
        return ApiResponse.created(response);
    }
}