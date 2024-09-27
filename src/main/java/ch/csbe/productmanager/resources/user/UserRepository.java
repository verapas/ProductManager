package ch.csbe.productmanager.resources.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    // Benutzerdefinierte Methode zur Abfrage eines Users nach ID
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getById(@Param("id") Long id);

    // Eventuell noch mehr benutzerdefinierte Methode hinzufügen?
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
}
