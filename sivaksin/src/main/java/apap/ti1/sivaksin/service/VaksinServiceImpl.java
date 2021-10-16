package apap.ti1.sivaksin.service;

import apap.ti1.sivaksin.model.FaskesModel;
import apap.ti1.sivaksin.model.VaksinModel;
import apap.ti1.sivaksin.repository.VaksinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VaksinServiceImpl implements VaksinService {
    @Autowired
    VaksinRepository vaksinRepository;

    @Override
    public List<VaksinModel> getListVaksin() {
        return vaksinRepository.findAll();
    }

    @Override
    public VaksinModel getVaksinByIdVaksin(Long idVaksin) {
        Optional<VaksinModel> vaksin = vaksinRepository.findVaksinByIdVaksin(idVaksin);
        if (vaksin.isPresent()) {
            return vaksin.get();
        }
        return null;
    }

    @Override
    public VaksinModel getVaksinByJenisVaksin(String jenisVaksin) {
        Optional<VaksinModel> vaksin = vaksinRepository.findVaksinByJenisVaksin(jenisVaksin);
        if (vaksin.isPresent()) {
            return vaksin.get();
        }
        return null;
    }
}