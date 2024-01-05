package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "agency")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    public Agency() {
    }
}
