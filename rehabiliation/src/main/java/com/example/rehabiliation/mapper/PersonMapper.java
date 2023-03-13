package com.example.rehabiliation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.rehabiliation.model.Person;

@Mapper
public interface PersonMapper {
	
	public List<Person> persons();
	
	public void insertPerson(Person person);
	
	public void deletePerson(int id);
	
	public Person findById(int id);
	
	void updatePerson(Person person);

}
