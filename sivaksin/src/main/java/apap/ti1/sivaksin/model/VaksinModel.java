package apap.ti1.sivaksin.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vaksin")
@EqualsAndHashCode

public class VaksinModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVaksin;

    @NotNull
    @Column(name = "efikasi",nullable = false)
    private double efikasi;

    @NotNull
    @Column(name = "jenis_vaksin", nullable = false)
    @Size(max = 50)
    private String jenisVaksin;

    @NotNull
    @Column(name = "asal_negara",nullable = false)
    @Size(max = 50)
    private String asalNegara;

    //Relasi dengan FaskesModel
    @OneToMany(mappedBy = "vaksin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FaskesModel> listFaskes;
}
