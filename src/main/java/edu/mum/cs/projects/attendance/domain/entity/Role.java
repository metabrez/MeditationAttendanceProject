package edu.mum.cs.projects.attendance.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import edu.mum.cs.projects.attendance.domain.Identifiable;

@Entity
@Immutable
@Table(name="role")
public class Role{
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="role_id")
	  private int id;

	  @Column(name="role")
	  private String role;
	}
