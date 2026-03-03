package eunji.ticketing.facade;

import eunji.ticketing.dto.response.QueueEnterResponseDto;
import eunji.ticketing.service.concert.ConcertService;
import eunji.ticketing.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

    private final ConcertService concertService;
    private final UserService userService;

    /**
     * [1.2] 대기열 진입
     * 공연 예매를 위해 대기열 진입을 시도합니다.
     *
     * @param concertId 콘서트 ID
     * @param userId    사용자 ID
     * @return 대기열 진입 결과 (queueId)
     */
    public QueueEnterResponseDto enterQueue(Long concertId, Long userId) {
        // todo Redis 락 비동기 뭐 메세지큐 이런거 설정 추가
        return concertService.enterQueue(concertId, userId);
    }
}
