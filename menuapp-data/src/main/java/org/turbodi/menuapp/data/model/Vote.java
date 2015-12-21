package org.turbodi.menuapp.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Dmitriy Borisov
 * @created 12/21/2015
 */
@Data
@NoArgsConstructor
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Restaurant restaurant;

    public Vote(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
