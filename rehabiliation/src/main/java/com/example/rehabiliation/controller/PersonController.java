	package com.example.rehabiliation.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.rehabiliation.model.Person;
import com.example.rehabiliation.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/")
	public String getAllPersons(Model model) {

		model.addAttribute("persons", personService.getAllPersons());

		return "person";
	}

	@GetMapping("/addPerson")
	public String addPerson(Model model) {

		Person person = new Person();

		model.addAttribute("person", person);

		return "addPerson";
	}

	
	
	@PostMapping("/addPerson")
	public String addPerson(@ModelAttribute("person") Person person, @RequestParam("imagePer") MultipartFile file)
			throws IOException {

		Person newPerson = new Person();

		newPerson.setFirstName(person.getFirstName());
		newPerson.setLastName(person.getLastName());
		newPerson.setEmail(person.getEmail());
		newPerson.setAddress(person.getAddress());

		String imgPerson = null;

		if (!file.isEmpty()) {

			imgPerson = Base64.getEncoder().encodeToString(file.getBytes());

		}

		newPerson.setImagePerson(imgPerson);

		try {
			personService.addPerson(newPerson);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") int id) throws SQLException {

		personService.deletePersonById(id);

		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String addPerson(@PathVariable("id") int id, Model model) {

		Person person = personService.findById(id);

		model.addAttribute("person", person);

		return "updatePerson";
	}

	@PostMapping("/updatePerson")
	public String updatePerson(@ModelAttribute("person") Person person, @RequestParam("imagePer") MultipartFile file)
			throws IOException {

		Person persongt = personService.findById(person.getId());

		Person newPerson = new Person();

		newPerson.setId(person.getId());
		newPerson.setFirstName(person.getFirstName());
		newPerson.setLastName(person.getLastName());
		newPerson.setEmail(person.getEmail());
		newPerson.setAddress(person.getAddress());

		String imgPerson = null;

		if (!file.isEmpty()) {

			imgPerson = Base64.getEncoder().encodeToString(file.getBytes());

			newPerson.setImagePerson(imgPerson);
		} else {

			newPerson.setImagePerson(persongt.getImagePerson());
		}

		try {
			personService.updatePerson(newPerson);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

}
