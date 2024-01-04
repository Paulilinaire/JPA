package entity.oneToOne;

import javax.persistence.*;

@Entity
@Table(name="maison")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_maison")
    private Long id;

    private Integer taille;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToOne
    @JoinColumn(name="adresse_id", referencedColumnName = "id_adresse")
    private Adress adress;

    public House() {
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", taille=" + taille +
                ", type=" + type +
                ", adress=" + adress +
                '}';
    }
}
