package apap.ti1.sivaksin.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.ejb.Local;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Setter @Getter
@Table(name = "pasien")
@EqualsAndHashCode

public class PasienModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasien;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_pasien",nullable = false)
    private String namaPasien;

    @NotNull
    @Size(max = 16)
    @Column(name = "nik",nullable = false)
    private String nik;

    @NotNull
    @Size(max = 13)
    @Column(name = "nomor_telepon",nullable = false)
    private String nomorTelepon;

    @NotNull
    @Column(name = "jenis_kelamin",nullable = false)
    private Integer jenisKelamin;

    @JsonFormat
    @NotNull
    @Column(name = "tanggal_lahir",nullable = false)
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalLahir;

    @NotNull
    @Column(name = "tempat_lahir",nullable = false)
    @Size(max = 255)
    private String tempatLahir;

    @NotNull
    @Column(name = "pekerjaan",nullable = false)
    @Size(max = 255)
    private String pekerjaan;

    //Relasi dengan FaskesModel
    @ManyToMany(mappedBy = "listPasien")
    List<FaskesModel> listFaskes;

    //Relasi dengan DokterPasienModel
    @OneToMany(mappedBy ="pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DokterPasienModel> listDokterPasien;

//    public List<FaskesModel> getFaskes() {
//        return faskes;
//    }
//
//    public void setListFaskes(faskes) {
//        this.faskes = faskes;
//    }
}


