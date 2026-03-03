package eunji.ticketing.entity;

import eunji.ticketing.entity.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id", nullable = false)
    private Concert concert;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false, unique = true)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private ReservationStatus reservationStatus = ReservationStatus.PENDING_PAYMENT;

    /**
     * 결제 완료 후 예매 확정
     */
    public void confirm() {
        // TODO: 개발자가 직접 작성함 (상태 검증 로직 등)
        this.reservationStatus = ReservationStatus.CONFIRMED;
    }

    /**
     * 예매 취소
     */
    public void cancel() {
        // TODO: 개발자가 직접 작성함 (상태 검증, 좌석 해제 로직 등)
        this.reservationStatus = ReservationStatus.CANCELED;
    }

    /**
     * 결제 대기 상태인지 확인
     */
    public boolean isPendingPayment() {
        return this.reservationStatus == ReservationStatus.PENDING_PAYMENT;
    }

    /**
     * 예매 확정 상태인지 확인
     */
    public boolean isConfirmed() {
        return this.reservationStatus == ReservationStatus.CONFIRMED;
    }

    /**
     * 취소된 예매인지 확인
     */
    public boolean isCancelled() {
        return this.reservationStatus == ReservationStatus.CANCELED;
    }
}