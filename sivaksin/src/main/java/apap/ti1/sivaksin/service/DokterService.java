package apap.ti1.sivaksin.service;

import apap.ti1.sivaksin.model.DokterModel;

import java.util.List;

public interface DokterService {
    List<DokterModel> getListDokter();
    DokterModel getDokterByNamaDokter(String namaDokter);
}
