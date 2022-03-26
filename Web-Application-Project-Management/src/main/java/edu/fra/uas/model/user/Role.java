package edu.fra.uas.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//Model to get ADMIN or USER Roles it contains only Id and name. Id is used for MANY TO MANY so we can set the USERID and ROLEID and set the name"Admin or USER"

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

//Default constructor
	public Role() {

	}

	// Constructor
	public Role(String name) {
		super();
		this.name = name;
	}

//GETTER SETTER
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
