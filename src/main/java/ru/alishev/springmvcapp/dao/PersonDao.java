package ru.alishev.springmvcapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.*;
import ru.alishev.springmvcapp.entity.*;

import java.util.*;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL = """
            SELECT * FROM person;
            """;
    private static final String FIND_BY_ID = """
            SELECT * FROM person WHERE id=?;
            """;
    private static final String UPDATE = """
            UPDATE person SET name=?, age=?, email=? WHERE id=?;
            """;
    private static final String DELETE = """
            DELETE FROM person WHERE id=?;
            """;
    private static final String SAVE = """
            INSERT INTO person (id, name, age, email)
            VALUES(?, ?, ?, ?);
            """;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(int id) {
        return jdbcTemplate
                .query(FIND_BY_ID, new BeanPropertyRowMapper<>(Person.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update(SAVE, 1, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update(UPDATE, updatedPerson.getName(), updatedPerson.getAge(),
                updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }
}
