package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.resources.category.Category;
import jakarta.persistence.*;

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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
