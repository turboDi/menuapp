package org.turbodi.menuapp.data.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dmitriy Borisov
 * @created 12/17/2015
 */
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Vote vote;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public static enum Role {
        USER, ADMIN
    }
}
