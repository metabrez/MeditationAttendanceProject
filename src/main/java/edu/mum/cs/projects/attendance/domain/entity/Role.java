package edu.mum.cs.projects.attendance.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import edu.mum.cs.projects.attendance.domain.Identifiable;

@Entity
@Immutable
@Table(name="role")
public class Role implements Identifiable<Integer> {
	@Id
	@Column(name="id")
    private int id;
	
	@Column(name="name", columnDefinition = "nvarchar(25)")
    private String name;

	@Override
	public Integer getId() {
		return id;
	}
	
	public Role() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
}
