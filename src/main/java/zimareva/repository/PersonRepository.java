package zimareva.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zimareva.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository <Person,Long> {
    //List<Person> findAllByFIO(Pageable pageable);
}
