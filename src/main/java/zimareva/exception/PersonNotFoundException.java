package zimareva.exception;

import java.text.MessageFormat;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id){
        super(MessageFormat.format("Could not find person with id: {0} ", id));
    }
}
