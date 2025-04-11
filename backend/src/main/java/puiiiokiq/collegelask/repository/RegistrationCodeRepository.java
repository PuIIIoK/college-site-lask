package puiiiokiq.collegelask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puiiiokiq.collegelask.model.RegistrationCode;

public interface RegistrationCodeRepository extends JpaRepository<RegistrationCode, Long> {
    RegistrationCode findByCode(String code);
}
