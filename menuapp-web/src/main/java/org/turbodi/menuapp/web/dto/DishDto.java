package org.turbodi.menuapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DishDto {

    private Long id;

    private String name;

    private BigDecimal price;
}
