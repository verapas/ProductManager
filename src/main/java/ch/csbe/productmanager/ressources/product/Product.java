package ch.csbe.productmanager.ressources.product;
import ch.csbe.productmanager.ressources.category.Category;
import ch.csbe.productmanager.ressources.user.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String name;

    @Column(columnDefinition = "decimal(10,2)", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories;



    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
