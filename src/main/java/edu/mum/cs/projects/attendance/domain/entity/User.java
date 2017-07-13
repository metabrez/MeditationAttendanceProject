package edu.mum.cs.projects.attendance.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import edu.mum.cs.projects.attendance.domain.Identifiable;

@Entity
@Immutable
@Table(name="user")
public class User implements Identifiable<String> {
	@Id
	@Column(name="username", columnDefinition = "nvarchar(25)")
    private String userName;

	@Column(name="password", columnDefinition = "nvarchar(25)")
    private String password;
    
	@OneToOne
	@JoinColumn(name="studentId", columnDefinition = "nvarchar(50)")
	private Student student;
	
	@OneToOne
	@JoinColumn(name="facultyId", columnDefinition = "int")
	private Faculty faculty;
	
	@OneToOne
	@JoinColumn(name="roleId", columnDefinition = "int")
	private Role role;
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
