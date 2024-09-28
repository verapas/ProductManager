package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.resources.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Stellt eine Produktkategorie dar mit einer ID, Aktivitätsstatus und Namen.
 */
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "tinyint", nullable = false)
    private boolean active;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(boolean active, String name) {
        this.active = active;
        this.name = name;
    }


}
