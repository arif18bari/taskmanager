package com.cardinity.taskmanager.model;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Transient
    @JsonIgnore
    @OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;
    public Role(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
}
