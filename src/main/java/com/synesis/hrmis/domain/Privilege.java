package com.synesis.hrmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "privilege_actions",
            joinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "actions_id", referencedColumnName = "id"))
    private Collection<Action> actions;

}
