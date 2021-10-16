package apap.ti1.sivaksin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "dokter_pasien")
public class DokterPasienModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "batch_id",nullable = false)
    private String batchId;

    @JsonFormat
    @NotNull
    @Column(name = "waktu_suntik",nullable = false)
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuSuntik;

    @NotNull
    @Size(max = 20)
    @Column(name = "id_faskes",nullable = false)
    private Long idFaskes;

    //Relasi dengan PasienModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pasien_id", referencedColumnName = "idPasien", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    //Relasi dengan DokterModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dokter_id", referencedColumnName = "idDokter", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;
}
