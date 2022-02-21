package zimareva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import zimareva.entity.Person;
import zimareva.repository.PersonRepository;


@SpringBootApplication
public class TestPSBApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(TestPSBApplication.class, args);

        PersonRepository personRepository =
                configurableApplicationContext.getBean(PersonRepository.class);

        Person personAlAl = new Person("Алексеев Алексей", null);
        personRepository.save(personAlAl);
        Person personSiSi = new Person("Сидоров Сидр", null);
        personRepository.save(personSiSi);
        Person personPePe = new Person("Петров Петр", personSiSi);
        personRepository.save(personPePe);
    }
}
