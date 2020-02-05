package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class ToDoRepositoryImpl implements TodoRepository {
    private List<Todo> tasks = new ArrayList<>();
    @Override
    public Todo persist(Todo todo) {
        tasks.add(todo);
        return todo;
    }

    @Override
    public Optional<Todo> findById(int todoId) {
        return tasks.stream()
                .filter(todo -> todo.getTodoId() == todoId)
                .findFirst();
    }

    @Override
    public List<Todo> findByTaskDescriptionContains(String taskDescription) {
        return tasks.stream()
                .filter(todo -> todo.getTaskDescription().toLowerCase().contains(taskDescription))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDeadLineBefore(LocalDate endDate) {
        return tasks.stream()
                .filter(todo -> todo.getDeadLine().isBefore(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDeadLineAfter(LocalDate startDate) {
        return tasks.stream()
                .filter(todo -> todo.getDeadLine().isAfter(startDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDeadLineBetween(LocalDate start, LocalDate end) {
        return tasks.stream()
                .filter(todo -> todo.getDeadLine().isBefore(end) && todo.getDeadLine().isAfter(start))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByAssigneeId(int personId) {
        return tasks.stream()
                .filter(todo -> (todo.getAssignee().getPersonId()) == personId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAllUnassignedTasks() {
        return tasks.stream()
                .filter(todo -> todo.getAssignee() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAllAssignedTasks() {
        return tasks.stream()
                .filter(todo -> todo.getAssignee() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDone(boolean isDone) {
        return tasks.stream()
                .filter(todo -> todo.isDone())
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAll() {
        return tasks;
    }

    @Override
    public boolean delete(int todoId) throws IllegalArgumentException {
        boolean erased = false;
        for (Todo chores : tasks) {
            if (chores.getTodoId() == todoId) {
                tasks.remove(chores);
                return erased = true;
            }
        }
        if (erased == false){
            throw new IllegalArgumentException();
        }
        return false;
    }

    @Override
    public void clear() {
        tasks.clear();
    }
}
