package apap.ti1.sivaksin.controller;

import apap.ti1.sivaksin.model.FaskesModel;
import apap.ti1.sivaksin.repository.FaskesRepository;
import apap.ti1.sivaksin.service.FaskesService;
import apap.ti1.sivaksin.service.PasienService;
import apap.ti1.sivaksin.service.VaksinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class FaskesController {
    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Autowired
    private VaksinService vaksinService;

    @Autowired
    private PasienService pasienService;


    @Autowired
    private FaskesRepository faskesRepository;

    @GetMapping("/faskes")
    public String getFaskesList(Model model) {
        List<FaskesModel> listFaskes = faskesService.getListFaskes();
        model.addAttribute("listFaskes", listFaskes);
        return "viewall-faskes";
    }

    @GetMapping("/faskes/tambah")
    public String addFaskesForm(Model model) {
        model.addAttribute("faskes", new FaskesModel());
        model.addAttribute("listVaksin", vaksinService.getListVaksin());
        return "form-add-faskes";
    }

    @PostMapping("/faskes/tambah")
    public String addFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ) {
        faskesService.addFaskes(faskes);
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        model.addAttribute("listVaksin", vaksinService.getListVaksin());
        return "add-faskes";
    }

    @GetMapping("/faskes/{idFaskes}")
    private String viewFaskes(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskesModel = faskesService.getFaskesByIdFaskes(idFaskes);
        model.addAttribute("faskes", faskesModel);
        model.addAttribute("sedangTutup", faskesService.getSedangTutup(faskesModel));
        model.addAttribute("listPasien", faskesModel.getListPasien());
        return "view-faskes";
    }

    @GetMapping ("/faskes/hapus/{idFaskes}")
    public String deleteFaskes(
            @PathVariable Long idFaskes,
            Model model
    ) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        if (faskesService.getSedangTutup(faskesService.getFaskesByIdFaskes(idFaskes))){
            model.addAttribute("idFaskes", idFaskes);
            model.addAttribute("namaFaskes", faskes.getNamaFaskes());
            faskesService.deleteFaskes(faskes);
            return "delete-faskes";
        }
        return "error-delete-faskes";
    }

    @GetMapping("/faskes/ubah/{idFaskes}")
    public String updateFaskesForm(
            @PathVariable Long idFaskes,
            Model model
    ) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        model.addAttribute("faskes", faskes);
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        model.addAttribute("listVaksin", vaksinService.getListVaksin());
        return "form-update-faskes";
    }

    @PostMapping("/faskes/ubah")
    public String updateFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ) {
        faskesService.updateFaskes(faskes);
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        model.addAttribute("listVaksin", vaksinService.getListVaksin());
        return "update-faskes";
    }

}