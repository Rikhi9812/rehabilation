package com.example.rehabiliation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rehabiliation.mapper.PersonMapper;
import com.example.rehabiliation.model.Person;

@Service
public class PersonService {

	Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	PersonMapper personMapper;

	@Autowired
	DataSource dataSource;

	public List<Person> getAllPersons() {
		return personMapper.persons();
	}

	public void addPerson(Person person) throws SQLException {

		logger.info(person.getFirstName() + " " + person.getFirstName() + " " + person.getLastName() + " "
				+ person.getEmail() + " " + person.getAddress());
		Connection connection = dataSource.getConnection();
		try (connection) {
			connection.setAutoCommit(false);
			logger.info("start");
			personMapper.insertPerson(person);
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection.rollback();
		}
		connection.close();
		logger.info("close" + connection.getClass());
	}

	public void deletePersonById(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try (connection) {
			connection.setAutoCommit(false);
			logger.info("start");
			personMapper.deletePerson(id);
			;
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}

	public Person findById(int id) {
		return personMapper.findById(id);
	}

	public void updatePerson(Person person) throws SQLException {

		logger.info(person.getFirstName() + " " + person.getFirstName() + " " + person.getLastName() + " "
				+ person.getEmail() + " " + person.getAddress());
		Connection connection = dataSource.getConnection();
		try (connection) {
			connection.setAutoCommit(false);
			logger.info("start");
			personMapper.updatePerson(person);
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection.rollback();
		}
		connection.close();
		logger.info("close" + connection.getClass());
	}

}
