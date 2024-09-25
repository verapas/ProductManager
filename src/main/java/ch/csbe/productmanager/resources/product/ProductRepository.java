package ch.csbe.productmanager.resources.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Benutzerdefinierte Methode zur Abfrage eines Produkts nach ID
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product getById(@Param("id") Long id);

    // Eventuell noch mehr benutzerdefinierte Methode hinzufügen?
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(@Param("name") String name);
}