package apap.ti1.sivaksin.repository;

import apap.ti1.sivaksin.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface PasienRepository extends JpaRepository<PasienModel, Long> {
    Optional<PasienModel> findPasienByIdPasien(Long idPasien);
    Optional<PasienModel> findPasienByTempatLahir(String tempatLahir);
}
