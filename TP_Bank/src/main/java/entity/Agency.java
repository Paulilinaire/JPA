package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table (name = "agency")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @ManyToOne
    private Customer customer;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers;

    public Agency() {
    }

    public Agency(String address) {
        this.address = address;
    }


}
