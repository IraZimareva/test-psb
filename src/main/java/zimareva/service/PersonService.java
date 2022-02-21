package zimareva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimareva.entity.Person;
import zimareva.exception.PersonNotFoundException;
import zimareva.repository.PersonRepository;

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

    public List<Person> getPeople (){
        return StreamSupport
                .stream(personRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Person getPersonById (Long id){
        return personRepository.findById(id).orElseThrow(()->
                new PersonNotFoundException(id));
    }
}
