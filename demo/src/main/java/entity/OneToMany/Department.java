package entity.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name="departement")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "dep_name")
    private String depName;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employeeList;

    public Department() {

    }


}
