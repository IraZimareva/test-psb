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

        //Первый (верхний) уровень иерархии
        Person personAlAl = new Person("Алексеев Алексей", null);
        personRepository.save(personAlAl);
        Person personIvIv = new Person("Иванов Иван", null);
        personRepository.save(personIvIv);
        Person personDiDi = new Person("Дмитриев Дмитрий", null);
        personRepository.save(personDiDi);
        Person personSiSi = new Person("Сидоров Сидр", null);
        personRepository.save(personSiSi);

        //Второй уровень иерархии
        Person personPePe = new Person("Петров Петр", personSiSi);
        personRepository.save(personPePe);
        Person personBoBo = new Person("Борисов Борис", personSiSi);
        personRepository.save(personBoBo);
        Person personArAr = new Person("Артемов Артем", personAlAl);
        personRepository.save(personArAr);
        Person personYlYl = new Person("Юлиева Юлия", personIvIv);
        personRepository.save(personYlYl);

        //Третий уровень иерархии
        Person personNaNa = new Person("Наташкова Наталия", personBoBo);
        personRepository.save(personNaNa);
        Person personVaVa = new Person("Валерьева Валерия", personBoBo);
        personRepository.save(personVaVa);
        Person personMiMi = new Person("Михайлов Михаил", personBoBo);
        personRepository.save(personMiMi);
        Person personKlKl = new Person("Климов Клим", personYlYl);
        personRepository.save(personKlKl);
        Person personKlaKla = new Person("Кларова Клара", personYlYl);
        personRepository.save(personKlaKla);
        Person personKarKar = new Person("Карлов Карл", personYlYl);
        personRepository.save(personKarKar);
    }
}
