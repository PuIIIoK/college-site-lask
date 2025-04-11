package puiiiokiq.collegelask.repository;

import puiiiokiq.collegelask.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByEmailAndPhone(String email, String phone);
}