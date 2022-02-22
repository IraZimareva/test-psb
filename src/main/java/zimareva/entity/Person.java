package zimareva.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import zimareva.service.PersonService;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "FIO should not be empty")
    private String fio;


//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id")
    //todo: подумать насчет каскадного типа
    @OneToMany (
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("person")
     private List<Person> contacts = new ArrayList<>();


    //todo: проверить реализацию рекурсии сущностей
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //public Stream<Person> streamContacts() {
    public void streamContacts() {
        StreamSupport.stream(this.contacts.spliterator(), false)
                .sorted(new PersonComparator())
                .forEach(p -> streamContacts());

        /*return Stream.concat(
                Stream.of(this),
                this.contacts.stream().flatMap(Person::streamContacts));

        List<Person> sortedPeople1Lvl = StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .sorted(new PersonComparator());*/
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", person=" + person +
                '}';
    }

    class PersonComparator implements Comparator<Person> {
        public int compare(Person a, Person b){
            return a.getFio().compareTo(b.getFio());
        }
    }
}
