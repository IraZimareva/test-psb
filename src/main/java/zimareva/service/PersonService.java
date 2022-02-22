package zimareva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimareva.entity.Person;
import zimareva.exception.PersonNotFoundException;
import zimareva.repository.PersonRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        List<Person> sortedPeople1Lvl = StreamSupport
                .stream(personRepository.findAll().spliterator(), false)
                .sorted(new PersonComparator())
                .collect(Collectors.toList());
        //sortedPeople1Lvl.forEach(Person::streamContacts);
        return sortedPeople1Lvl;
    }

    /*public List<Person> getPeople (){
        return StreamSupport
                .stream(personRepository.findAll().spliterator(),false)
                .sorted(new PersonComparator())
                .collect(Collectors.toList());
    }*/

    public Person getPersonById (Long id){
        return personRepository.findById(id).orElseThrow(()->
                new PersonNotFoundException(id));
    }


    class PersonComparator implements Comparator<Person> {
        public int compare(Person a, Person b){
            return a.getFio().compareTo(b.getFio());
        }
    }
}

