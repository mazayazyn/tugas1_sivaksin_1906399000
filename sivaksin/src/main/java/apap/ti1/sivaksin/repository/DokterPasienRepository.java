package apap.ti1.sivaksin.repository;
import apap.ti1.sivaksin.model.DokterPasienModel;
import apap.ti1.sivaksin.model.VaksinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DokterPasienRepository extends JpaRepository<DokterPasienModel, Long> {
//    Optional<DokterPasienModel> findDokterByNamaDokter(String namaDokter);
}
