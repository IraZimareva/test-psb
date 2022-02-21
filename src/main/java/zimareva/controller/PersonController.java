package zimareva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zimareva.entity.Person;
import zimareva.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPeople (){
        List<Person> people = personService.getPeople();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Person> getPersonById (@PathVariable(value = "id") Long personId){
        Person person = personService.getPersonById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
