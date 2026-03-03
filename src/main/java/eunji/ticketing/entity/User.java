package eunji.ticketing.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // 생성 로직
    public static User createUser(String name, String username, String password) {
        return new User();
    }

    // 비즈니스 로직
    /**
     * 비밀번호 변경
     */
    public void changePassword(String newPassword) {
        // TODO: 개발자가 직접 작성함 (비밀번호 암호화 등)
        this.password = newPassword;
    }

    /**
     * 이름 변경
     */
    public void changeName(String newName) {
        this.name = newName;
    }
}