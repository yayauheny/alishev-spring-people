package ru.alishev.springmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springmvcapp.dao.PersonDao;
import ru.alishev.springmvcapp.entity.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;

    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("people", personDao.findAll());

        return "people/people";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.findById(id));

        return "people/person";
    }

    @GetMapping("/new")
    public String showNewForm(@ModelAttribute("person") Person person) {
        return "people/new_person";
    }

    @PostMapping()
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new_person";
        }
        personDao.save(person);

        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Person personToUpdate = personDao.findById(id);
        model.addAttribute("person", personToUpdate);

        return "people/edit_person";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person updatedPerson, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit_person";
        }
        personDao.update(updatedPerson.getId(), updatedPerson);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.delete(id);

        return "redirect:/people";
    }

}
