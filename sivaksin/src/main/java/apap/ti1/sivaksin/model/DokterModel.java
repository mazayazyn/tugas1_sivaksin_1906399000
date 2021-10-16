package apap.ti1.sivaksin.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "dokter")
public class DokterModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_dokter",nullable = false)
    private String namaDokter;

    @NotNull
    @Size(max = 18)
    @Column(name = "nip",nullable = false)
    private String nip;

    @NotNull
    @Size(max = 13)
    @Column(name = "nomor_telepon",nullable = false)
    private String nomorTelepon;

    //Relasi dengan DokterPasienModel
    @OneToMany(mappedBy ="dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DokterPasienModel> listDokterPasien;
}

