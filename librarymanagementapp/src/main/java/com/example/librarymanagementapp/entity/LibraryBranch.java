package com.example.librarymanagementapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;

/**
 * @author Jerry Chen
 * 
 */
@Entity
@Table(name = "Tables")
public class LibraryBranch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (name = "name")
	private String name;
	@Column (name = "location")
	private String location;
	
	public LibraryBranch() {
		// TODO Auto-generated constructor stub
	}

	public LibraryBranch(String name) {
		super();
		this.name = name;
	}

	public LibraryBranch(Long id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
