package eunji.ticketing.entity;

import eunji.ticketing.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(name = "pg_transaction_id")
    private String pgTransactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    /**
     * 결제 완료 처리
     */
    public void complete(String pgTransactionId) {
        // TODO: 개발자가 직접 작성함 (결제 검증 로직 등)
        this.pgTransactionId = pgTransactionId;
        this.paymentStatus = PaymentStatus.COMPLETED;
    }

    /**
     * 결제 실패 처리
     */
    public void fail() {
        // TODO: 개발자가 직접 작성함 (실패 사유 기록 등)
        this.paymentStatus = PaymentStatus.FAILED;
    }

    /**
     * 결제 취소 처리
     */
    public void cancel() {
        // TODO: 개발자가 직접 작성함 (PG사 취소 연동 로직 등)
        this.paymentStatus = PaymentStatus.CANCELED;
    }

    /**
     * 결제 완료 상태인지 확인
     */
    public boolean isCompleted() {
        return this.paymentStatus == PaymentStatus.COMPLETED;
    }

    /**
     * 결제 대기 상태인지 확인
     */
    public boolean isPending() {
        return this.paymentStatus == PaymentStatus.PENDING;
    }
}