package org.fhtech.yamaServer.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false, unique = true)
    String username;

    @NotNull
    @Column(length = 128)
    String password;

    boolean enabled;

    @ManyToMany()
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name= "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

}
