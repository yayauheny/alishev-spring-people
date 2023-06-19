package ru.alishev.springmvcapp.dao;

import org.springframework.stereotype.*;
import ru.alishev.springmvcapp.entity.*;

import java.util.*;

@Component
public class PersonDao {

    private List<Person> persons;
    private static Long personCounter = 0L;

    {
        persons = new ArrayList<>();

        persons.add(new Person(++personCounter, "Pavel", 24, "pavel@mail.ru"));
        persons.add(new Person(++personCounter, "Artem", 37, "tema@gmail.com"));
        persons.add(new Person(++personCounter, "Masha", 14, "mariasel@mail.ru"));
        persons.add(new Person(++personCounter, "Andrew", 19, "sandrew@mail.ru"));
        persons.add(new Person(++personCounter, "Margaret", 53, "mkeyle@gmail.com"));
    }

    public PersonDao() {
    }

    public List<Person> findAll() {
        return persons;
    }

    public Person findById(Long id) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getId(), id))
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++personCounter);
        persons.add(person);
    }

    public void update(Long id, Person updatedPerson){
        Person personToUpdate = findById(id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setAge(updatedPerson.getAge());
        personToUpdate.setEmail(updatedPerson.getEmail());
    }

    public void delete(Long id){
        persons.removeIf(person -> person.getId().equals(id));
    }
}
