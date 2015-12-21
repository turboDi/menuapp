package org.turbodi.menuapp.data.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Dmitriy Borisov
 * @created 12/17/2015
 */
@Data
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}
