package hr.kmilos21.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "crypto")
public class CryptoCoin extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="currency", nullable = false, unique = true)
    private String currency;

    @Column(name="current_val", nullable = false)
    private Double currentVal;

    @Column(name = "min_val", nullable = true)
    private Double minVal;

    @Column(name = "max_val", nullable = false)
    private Double maxVal;

    public CryptoCoin() {}

    public CryptoCoin(String currency,
                      Double currentVal,
                      Double minVal,
                      Double maxVal) {

        this.currency = currency;
        this.currentVal = currentVal;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    public void setValues(Double newVal) {
        this.currentVal = newVal;

        if (this.minVal == null || newVal <= this.minVal) {
            this.minVal = newVal;
        }

        if (this.maxVal == null || newVal >= this.maxVal) {
            this.maxVal = newVal;
        }
    }

}
