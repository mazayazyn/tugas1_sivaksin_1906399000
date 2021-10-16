package apap.ti1.sivaksin.service;

import apap.ti1.sivaksin.model.FaskesModel;
import apap.ti1.sivaksin.model.VaksinModel;
import java.util.List;

public interface VaksinService {
    List<VaksinModel> getListVaksin();
    VaksinModel getVaksinByIdVaksin(Long idVaksin);
    VaksinModel getVaksinByJenisVaksin(String jenisVaksin);
}