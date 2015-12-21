package org.turbodi.menuapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantDto {

    private Long id;

    private String name;

    private boolean deleted;

    private int votes;
}
