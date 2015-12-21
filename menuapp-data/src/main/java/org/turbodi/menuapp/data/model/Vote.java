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
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
