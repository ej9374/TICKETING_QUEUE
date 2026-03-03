package eunji.ticketing.service.seat;

import eunji.ticketing.dto.request.SeatRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 좌석 관련 Command 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class SeatService {

    public Long selectSeat(Long userId, SeatRequestDto request) {
        return 1L;
    }
}