package org.turbodi.menuapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuDto {

    private Long id;

    private Date date;

    private List<DishDto> dishes;
}
