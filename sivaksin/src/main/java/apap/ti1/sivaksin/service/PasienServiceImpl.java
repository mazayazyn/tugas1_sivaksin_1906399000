package apap.ti1.sivaksin.service;

import apap.ti1.sivaksin.model.PasienModel;
import apap.ti1.sivaksin.repository.PasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {

    @Autowired
    PasienRepository pasienRepository;

    @Override
    public void addPasien(PasienModel pasien) {
        pasienRepository.save(pasien);
    }

    @Override
    public void updatePasien(PasienModel pasien) {
        pasienRepository.save(pasien);
    }

    @Override
    public PasienModel getPasienByIdPasien(Long idPasien) {
        Optional<PasienModel> pasien = pasienRepository.findPasienByIdPasien(idPasien);
        if (pasien.isPresent()) {
            return pasien.get();
        }
        return null;
    }

    @Override
    public List<PasienModel> getPasienList() {
        return pasienRepository.findAll();
    }
}
