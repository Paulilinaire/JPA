package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = défini l'auto incrément
    private Long id;

    private String prenom;

    @Column(name= "name")
    private String nom;

    @Transient
    private int age;

    public Person() {
    }

    public Person(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }
}
