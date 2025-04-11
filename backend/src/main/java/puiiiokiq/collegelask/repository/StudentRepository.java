package puiiiokiq.collegelask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puiiiokiq.collegelask.model.Student;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username); // Возвращаем Optional
}
