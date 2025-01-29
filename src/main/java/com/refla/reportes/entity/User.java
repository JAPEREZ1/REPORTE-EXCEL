package com.refla.reportes.entity;
import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

}
