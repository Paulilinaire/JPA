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

    private boolean completed;

    public Todo() {

    }

    public Todo(String title) {
        this.title = title;
        this.completed = false;
    }

    public boolean getStatus() {
        return completed;
    }
    }
