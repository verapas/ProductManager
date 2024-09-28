package ch.csbe.productmanager.resources.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Schnittstelle zur Datenbank für CRUD-Operationen
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Findet einen Benutzer anhand seiner E-Mail-Adresse.
     */
    User findByEmail(String email);

    /**
     * Findet einen Benutzer anhand seiner ID.
     */
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getById(@Param("id") Long id);

    /**
     * Findet einen Benutzer anhand seines Benutzernamens.
     */
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
}
