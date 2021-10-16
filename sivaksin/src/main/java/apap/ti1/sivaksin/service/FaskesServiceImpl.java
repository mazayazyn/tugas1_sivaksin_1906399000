package apap.ti1.sivaksin.service;

import apap.ti1.sivaksin.model.FaskesModel;
import apap.ti1.sivaksin.model.PasienModel;
import apap.ti1.sivaksin.repository.FaskesRepository;
import apap.ti1.sivaksin.repository.PasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;

@Service
@Transactional
public class FaskesServiceImpl implements FaskesService {

    @Autowired
    FaskesRepository faskesRepository;

    @Override
    public void addFaskes(FaskesModel faskes) {
        faskesRepository.save(faskes);
    }

    @Override
    public List<FaskesModel> getListFaskes() {
        return faskesRepository.findAll();
    }

    @Override
    public FaskesModel getFaskesByIdFaskes(Long idFaskes) {
        Optional<FaskesModel> faskes = faskesRepository.findFaskesByIdFaskes(idFaskes);
        if (faskes.isPresent()) {
            return faskes.get();
        }
        return null;
    }

    @Override
    public FaskesModel getFaskesByNamaFaskes(String namaFaskes) {
        Optional<FaskesModel> faskes = faskesRepository.findFaskesByNamaFaskes(namaFaskes);
        if (faskes.isPresent()) {
            return faskes.get();
        }
        return null;
    }

    @Override
    public void deleteFaskes(FaskesModel faskes) {
        faskesRepository.delete(faskes);
    }

    @Override
    public void updateFaskes(FaskesModel faskes) {
        faskesRepository.save(faskes);
    }

    @Override
    public boolean getSedangTutup (FaskesModel faskes){
        LocalTime time = LocalTime.now();
        LocalTime a1 = faskes.getJamMulai();
        LocalTime a2 = faskes.getJamTutup();

        int isOpen = time.compareTo(a1);
        int isClosed = time.compareTo(a2);

        return ((isOpen <= 0 || isClosed >= 0) ? true : false);
    }
}