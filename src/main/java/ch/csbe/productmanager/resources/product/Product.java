package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.resources.category.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String sku;

    @Column(columnDefinition = "tinyint", nullable = false)
    private boolean active;

    @Column(columnDefinition = "varchar(500)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(1000)", nullable = true)
    private String image;

    @Column(columnDefinition = "mediumtext", nullable = true)
    private String description;

    @Column(columnDefinition = "float", nullable = false)
    private float price;

    @Column(columnDefinition = "int", nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {
    }

    public Product(String sku, boolean active, String name, String image, String description, float price, int stock, Category category) {
        this.sku = sku;
        this.active = active;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }


}
