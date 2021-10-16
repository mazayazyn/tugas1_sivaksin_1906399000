package apap.ti1.sivaksin.service;

import apap.ti1.sivaksin.model.FaskesModel;
import apap.ti1.sivaksin.model.VaksinModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FaskesService {
    void addFaskes(FaskesModel faskes);
    List<FaskesModel> getListFaskes();
    FaskesModel getFaskesByIdFaskes(Long idFaskes);
    FaskesModel getFaskesByNamaFaskes(String namaFaskes);
    void deleteFaskes(FaskesModel faskes);
    void updateFaskes(FaskesModel faskes);
    boolean getSedangTutup(FaskesModel faskes);
}