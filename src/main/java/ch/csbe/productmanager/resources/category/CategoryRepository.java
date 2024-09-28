package ch.csbe.productmanager.resources.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Schnittstelle zur Datenbank für CRUD-Operationen
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     *Methode zur Abfrage einer Kategorie anhand der ID.
     */
    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Category getById(@Param("id") Long id);

}
