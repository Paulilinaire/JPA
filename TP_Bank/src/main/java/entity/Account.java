package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 27)
    private String IBAN;

    @Column(precision = 10, scale = 2)
    private double balance;

    @ManyToOne
    private Agency agency;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers;

}
