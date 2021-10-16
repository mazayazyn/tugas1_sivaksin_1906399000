package apap.ti1.sivaksin.controller;

import apap.ti1.sivaksin.model.DokterModel;
import apap.ti1.sivaksin.model.FaskesModel;
import apap.ti1.sivaksin.model.PasienModel;
import apap.ti1.sivaksin.model.VaksinModel;
import apap.ti1.sivaksin.service.DokterService;
import apap.ti1.sivaksin.service.VaksinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import apap.ti1.sivaksin.service.FaskesService;
import apap.ti1.sivaksin.service.PasienService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")
    @Autowired
    PasienService pasienService;

    @Qualifier("faskesServiceImpl")
    @Autowired
    FaskesService faskesService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    DokterService dokterService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    VaksinService vaksinService;

    @GetMapping("/pasien/tambah")
    public String addPasienForm(Model model) {
        model.addAttribute("pasien", new PasienModel());
        return "form-add-pasien";
    }

    @PostMapping("/pasien/tambah")
    public String addPasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ) {
        model.addAttribute("tempatLahir", pasien.getTempatLahir());
        int count = 0;
        for (int i = 0; i < pasien.getTempatLahir().length(); i++) {
            if (pasien.getTempatLahir().charAt(i) != ' ')
                count++;
        }
        if (count >= 2) {
            model.addAttribute("pasienModel", new PasienModel());
            model.addAttribute("idPasien", pasien.getIdPasien());
            model.addAttribute("namaPasien", pasien.getNamaPasien());
            pasienService.addPasien(pasien);
            return "add-pasien";
        }
        return "error-add-tempat-lahir-pasien";
    }

    @GetMapping("/pasien")
    public String viewAllPasien(
            Model model
    ) {
        model.addAttribute("listPasien", pasienService.getPasienList());
        return "viewall-pasien";
    }

    @GetMapping("/pasien/{idPasien}")
    private String viewPasien(
            @PathVariable Long idPasien,
            Model model
    ) {
        PasienModel pasienModel = pasienService.getPasienByIdPasien(idPasien);
        model.addAttribute("pasien", pasienModel);
        return "view-pasien";
    }

    @GetMapping("/pasien/ubah/{idPasien}")
    public String updatePasienForm(
            @PathVariable Long idPasien,
            Model model
    ) {
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);
        if (pasien == null) {
            return "error-notfound";
        }
        model.addAttribute("pasien", pasien);
        model.addAttribute("idPasien", pasien.getIdPasien());
        ;
        return "form-update-pasien";
    }

    @PostMapping("/pasien/ubah")
    public String updatePasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ) {
        pasienService.updatePasien(pasien);
        model.addAttribute("idPasien", pasien.getIdPasien());
        ;
        model.addAttribute("namaPasien", pasien.getNamaPasien());
        return "update-pasien";
    }


    @GetMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addPasienToFaskesForm(
            @PathVariable Long idFaskes,
            Model model
    ) {
        PasienModel pasien = new PasienModel();
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        List<FaskesModel> ListFaskes = pasien.getListFaskes();
        if (ListFaskes != null) {
            ListFaskes.add(faskes);
        } else {
            ListFaskes = new ArrayList<FaskesModel>(List.of(faskes));
        }
        pasien.setListFaskes(ListFaskes);
        model.addAttribute("pasien", pasien);
        model.addAttribute("faskes", faskes);
        model.addAttribute("listDokter", dokterService.getListDokter());
        model.addAttribute("listPasien", pasienService.getPasienList());
        return "form-add-pasien-faskes";
    }

    @PostMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addPasienToFaskesSubmit(
            @ModelAttribute PasienModel pasien, FaskesModel faskes,
            Model model
    ) {
        List<PasienModel> ListPasien = faskes.getListPasien();
        if (ListPasien != null) {
            ListPasien.add(pasien);
        } else {
            ListPasien = new ArrayList<PasienModel>(List.of(pasien));
        }
        faskes.setListPasien(ListPasien);
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        model.addAttribute("listDokter", dokterService.getListDokter());
        model.addAttribute("listPasien", pasienService.getPasienList());
        return "add-pasien-faskes";
    }

    @GetMapping("/cari/faskes")
    public String cariVaksin(
            Model model
    ) {
        List<VaksinModel> listVaksin = vaksinService.getListVaksin();
        List<FaskesModel> listFaskesResult = new ArrayList<>();
        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listFaskesResult", listFaskesResult);
        return "form-cari-vaksin";
    }

    @GetMapping(value = "/cari/faskes", params = {" "})
    public String cariFaskesByVaksin(
            @RequestParam(value = "jenisVaksin") String jenisVaksin,
            Model model
    ) {
        VaksinModel vaksin = vaksinService.getVaksinByJenisVaksin(jenisVaksin);
        List<FaskesModel> listFaskesResult = vaksin.getListFaskes();
        model.addAttribute("vaksin", vaksin);
        model.addAttribute("listFaskesResult", listFaskesResult);
        model.addAttribute("listVaksin", vaksinService.getListVaksin());
        model.addAttribute("jenisVaksin", vaksin.getJenisVaksin());
        model.addAttribute("idVaksin", vaksin.getIdVaksin());
        return "form-cari-vaksin";
    }

    @GetMapping("/cari/pasien")
    public String cariPasien(Model model) {
        List<VaksinModel> listVaksin = vaksinService.getListVaksin();
        List<FaskesModel> listFaskes = faskesService.getListFaskes();
        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("pasien", new PasienModel());
        return "form-cari-pasien";
    }

    @GetMapping("")
    public static String batchIdGenerator(int jenisKelamin, String namaPasien, DateTimeFormat tanggalLahir){
        StringBuilder ret = new StringBuilder();
        ret.append(jenisKelamin);
        ret.append(namaPasien.substring(namaPasien.length()-1).toUpperCase());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(tanggalLahir);
        ret.append(strDate.substring(8,10));
        ret.append(strDate.substring(5,7));
        ret.append(Integer.parseInt(strDate.substring(0,4)) / 10);

        Random r = new Random();
        char c1 = (char) (r.nextInt(26) + 'A');
        char c2 = (char) (r.nextInt(26) + 'A');
        ret.append(c1);
        ret.append(c2);

        return ret.toString();
    }
}