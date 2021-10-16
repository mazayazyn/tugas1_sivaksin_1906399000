package apap.ti1.sivaksin.service;

import apap.ti1.sivaksin.model.DokterModel;
import apap.ti1.sivaksin.model.DokterPasienModel;
import apap.ti1.sivaksin.repository.DokterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {

    @Autowired
    DokterRepository dokterRepository;

    @Override
    public List<DokterModel> getListDokter() {
        return dokterRepository.findAll();
    }

    @Override
    public DokterModel getDokterByNamaDokter(String namaDokter) {
        Optional<DokterModel> dokter = dokterRepository.findDokterByNamaDokter(namaDokter);
        if (dokter.isPresent()) {
            return dokter.get();
        }
        return null;
    }
}