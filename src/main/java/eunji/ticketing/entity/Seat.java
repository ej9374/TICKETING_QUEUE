package eunji.ticketing.entity;

import eunji.ticketing.entity.enums.SeatGrade;
import eunji.ticketing.entity.enums.SeatStatus;
import eunji.ticketing.entity.enums.Zone;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "seats")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id", nullable = false)
    private Concert concert;

    @Enumerated(EnumType.STRING)
    @Column(name = "zone", nullable = false)
    private Zone zone;

    @Column(name = "seat_row", nullable = false)
    private Integer seatRow;

    @Column(name = "seat_column", nullable = false)
    private Integer seatColumn;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_grade", nullable = false)
    private SeatGrade seatGrade;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private SeatStatus seatStatus = SeatStatus.FREE;

    /**
     * 좌석을 판매 완료 상태로 변경
     */
    public void markAsSold() {
        this.seatStatus = SeatStatus.SOLD;
    }

    /**
     * 좌석을 다시 판매 가능 상태로 변경 (예매 취소 시)
     */
    public void release() {
        this.seatStatus = SeatStatus.FREE;
    }

    /**
     * 좌석이 예매 가능한 상태인지 확인
     */
    public boolean isAvailable() {
        return this.seatStatus == SeatStatus.FREE;
    }
}