package apap.ti1.sivaksin.repository;
import apap.ti1.sivaksin.model.FaskesModel;
import apap.ti1.sivaksin.model.VaksinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface VaksinRepository extends JpaRepository<VaksinModel, Long> {
    Optional<VaksinModel> findVaksinByIdVaksin(Long idVaksin);
    Optional<VaksinModel> findVaksinByJenisVaksin(String jenisVaksin);
//    Optional<VaksinModel> findFaskesByJenisVaksin(String jenisVaksin);
}
