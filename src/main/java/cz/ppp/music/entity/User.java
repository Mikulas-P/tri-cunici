package cz.ppp.music.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@javax.persistence.Entity(name="USERS")
@AllArgsConstructor @ToString
public class User {

    public User(){
        role = "AGENT";
    }

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "user_name", unique = true)
    @Getter @Setter
    @Size(min = 5, max = 20)
    public String username;

    @NotNull
    @Size(min = 5, max = 20)
    @Getter @Setter
    private String password;

    @NotNull
    @Getter @Setter
    private String role;


}
