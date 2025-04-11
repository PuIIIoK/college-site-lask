package puiiiokiq.collegelask.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String registrationCode;

    private boolean isRegistered;

    private String fullName;         // ФИО
    private String groupName;        // Группа
    private String curator;          // Куратор
    private String course;           // Курс
    private String specialty;        // Специальность
    private int enrolledYear;        // Год поступления
    private double avgGrade;         // Средняя оценка
    private int skips;               // Прогулы
    private int progress;            // Успеваемость (в процентах)
    private Integer rating;          // Рейтинг в колледже

    private boolean emailConfirmed;
    private boolean phoneConfirmed;
    @Column(nullable = false)
    private boolean telegramLinked = false;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
