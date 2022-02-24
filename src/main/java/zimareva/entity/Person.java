package zimareva.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "FIO should not be empty")
    private String fio;

    @OneToMany (
            mappedBy = "person",
            orphanRemoval = false
    )
    @JsonIgnoreProperties("person")
     private List<Person> contacts = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @JsonIgnoreProperties("contacts")
    private Person person;


    public Person() {
    }

    public Person(@NotEmpty(message = "FIO should not be empty") String fio, Person person) {
        this.fio = fio;
        this.person = person;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
    }

    @JsonIgnore
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void sortContacts() {
        //условие выхода из рекурсии
        if (this.contacts == null){
            return;
        }

        //сортируем List
        Collections.sort(this.contacts, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFio().compareTo(o2.getFio());
            }
        });

        //рекурсивно вызываем сортировку на след.уровне иерархии
        for (Person curr : this.getContacts()){
            curr.sortContacts();
        }
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
