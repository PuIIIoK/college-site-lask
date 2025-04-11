package puiiiokiq.collegelask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class RegistrationCode {

    // Геттеры и сеттеры
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String code;
    private boolean isUsed;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
