package apap.ti1.sivaksin.repository;

import apap.ti1.sivaksin.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DokterRepository extends JpaRepository<DokterModel, Long> {
    Optional<DokterModel> findDokterByNamaDokter(String namaDokter);
}
