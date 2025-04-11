package puiiiokiq.collegelask.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // 20 янв

    private String photoId;      // ID фото
    private String photoName;    // Название фотки

    private String title; // Название новости

    private String day;   // день недели
    private LocalTime time; // время
    private String format; // очно / заочно

    @Column(length = 2000)
    private String description; // описание события

    private Long targetPageId; // куда ведет (ID страницы)
}
