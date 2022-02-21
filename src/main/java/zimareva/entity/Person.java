package zimareva.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "FIO should not be empty")
    private String fio;

    //todo: подумать насчет каскадного типа
    /*@OneToMany (
            mappedBy = "contacts",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )*/
//    @JsonIgnoreProperties("contacts")
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id")
//    private List<Person> contacts = new ArrayList<>();

    //todo: проверить реализацию рекурсии сущностей
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
//    @JsonIgnoreProperties("person")
    private Person person;


    public Person() {
    }

    public Person(@NotEmpty(message = "FIO should not be empty") String fio, Person person) {
        this.fio = fio;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Person getContacts() {
        return person;
    }

    public void setContacts(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", person=" + person +
                '}';
    }
}
