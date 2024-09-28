package ch.csbe.productmanager.resources.user;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Repräsentiert ein Benutzer in der Datenbank
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String role;

    public User() {
    }

}
