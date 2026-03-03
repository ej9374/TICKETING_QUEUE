package eunji.ticketing.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "concerts")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Concert extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "concert_start_time", nullable = false)
    private LocalDateTime concertStartTime;

    @Column(name = "ticket_open_date_time", nullable = false)
    private LocalDateTime ticketOpenTime;

    // 생성 메서드
    /**
     * 공연 정보 등록
     */
    public static Concert createConcert(String title, LocalDateTime concertStartTime, LocalDateTime ticketOpenTime) {
        // ticketOpenDateTime은 ConcertStartTime보다 빨라야됨
        return new Concert();
    }

    // 비즈니스 메서드
    /**
     * 공연 정보 수정
     */
    public void updateConcertInfo(String title, LocalDateTime concertStartTime, LocalDateTime ticketOpenDateTime) {
        this.title = title;
        this.concertStartTime = concertStartTime;
        this.ticketOpenTime = ticketOpenDateTime;
    }

    /**
     * 티켓 오픈 시간 변경
     */
    public void changeTicketOpenDateTime(LocalDateTime ticketOpenDateTime) {
        this.ticketOpenTime = ticketOpenDateTime;
    }

    /**
     * 티켓 예매가 오픈되었는지 확인
     */
    public boolean isTicketOpen() {
        return LocalDateTime.now().isAfter(this.ticketOpenTime);
    }
}