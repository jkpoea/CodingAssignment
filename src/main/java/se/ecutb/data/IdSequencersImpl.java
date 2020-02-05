package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

@Component
public class IdSequencersImpl implements IdSequencers {
    int personCounter;
    int toDoCounter;
    @Override
    public int nextPersonId() {
        return personCounter + 1;
    }

    @Override
    public int nextTodoId() {
        return toDoCounter + 1;
    }

    @Override
    public void clearPersonId() {
        personCounter = 0;
    }

    @Override
    public void clearTodoId() {
        toDoCounter = 0;
    }
}
