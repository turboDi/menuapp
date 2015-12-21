package org.turbodi.menuapp.data.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy Borisov
 * @created 12/17/2015
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"votes", "votesCount"})
@ToString(exclude = "votes")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private boolean deleted;

    @OneToMany(mappedBy = "restaurant")
    private Set<Vote> votes = new HashSet<>(0);

    @Transient
    private long votesCount;

    public Restaurant(Restaurant r, long votesCount) {
        this.id = r.id;
        this.name = r.name;
        this.deleted = r.deleted;
        this.votes = r.votes;
        this.votesCount = votesCount;
    }
}
