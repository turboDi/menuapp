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
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private boolean deleted;
}
