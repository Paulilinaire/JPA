package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@Table(name="todo")
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean status;

    public Todo() {

    }

    public Todo(String title, boolean status) {
        this.title = title;
        this.status = status;
    }
}
