package zimareva.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        List<Person> people = StreamSupport
                .stream(personRepository.findAll().spliterator(), false)
                .sorted(new PersonComparator())
                .peek(p -> p.sortContacts())
                .collect(Collectors.toList());
        this.getJSONListResult(people);
        return people;
    }

    public Person getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() ->
                new PersonNotFoundException(id));
        person.sortContacts();
        return person;
    }

    private String getJSONListResult (List<Person> people){
        ObjectMapper mapper = new ObjectMapper();
        String regRespStr = null;
        try{
            regRespStr = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(people);
            System.out.println("Formatted JSON Response:" + regRespStr);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        return regRespStr;
    }


    class PersonComparator implements Comparator<Person> {
        public int compare(Person a, Person b){
            return a.getFio().compareTo(b.getFio());
        }
    }
}

