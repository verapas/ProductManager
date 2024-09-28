package ch.csbe.productmanager.resources.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Schnittstelle zur Datenbank für CRUD-Operationen
 */
public interface ProductRepository extends JpaRepository<Product, Long> {


    /**
     *Methode zur Abfrage einer Kategorie anhand der ID.
     */
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product getById(@Param("id") Long id);

}