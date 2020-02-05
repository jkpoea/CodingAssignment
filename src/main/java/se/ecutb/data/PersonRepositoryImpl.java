package se.ecutb.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {
    private List<Person> persons = new ArrayList<Person>();

    @Override
    public Optional<Person> findById(int personId) {
        return persons.stream()
                .filter(person -> personId == person.getPersonId())
                .findAny();
    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {
        for (Person person1 : persons) {
            if (person1.getEmail().equalsIgnoreCase(person.getEmail())) {
                throw new IllegalArgumentException();
            }
        }
        persons.add(person);
        return person;
    }
    @Override
    public Optional<Person> findByEmail(String email) {
        return persons.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Person> findByAddress(Address address) {
        return persons.stream()
                .filter(person -> person.getAddress() == address)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByCity(String city) {
        return persons.stream()
                .filter(person -> person.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return persons.stream()
                .filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        return persons.stream()
                .filter(person -> (person.getFirstName() + " " + person.getLastName()).equalsIgnoreCase(fullName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return persons;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
        boolean deleted = false;
            for (Person person: persons){
                if (person.getPersonId() == personId){
                    persons.remove(person);
                    return deleted = true;
                }
            }
            if (deleted == false){
                throw new IllegalArgumentException();
            }
            return deleted;
    }

    @Override
    public void clear() {
        persons.clear();
    }
}
