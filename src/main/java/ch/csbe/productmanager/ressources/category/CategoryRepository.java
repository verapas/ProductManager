package ch.csbe.productmanager.ressources.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Benutzerdefinierte Methode zur Abfrage einer Kategorie nach ID
    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Category getById(@Param("id") Long id);

    // Eventuell noch mehr benutzerdefinierte Methode hinzufügen?
    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findByName(@Param("name") String name);
}
