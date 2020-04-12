package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "users")
@NamedQueries({
	@NamedQuery(
			name = "checkLoginCodeAndPassword",
			query = "SELECT u FROM User AS u WHERE u.name = :name AND u.password = :password"
			),
	@NamedQuery(
			name = "checkNameJuhuku",
			query = "SELECT COUNT(u) FROM User AS u WHERE u.name = :juhukuname"
			)
})
@Entity
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", length = 64, nullable = false)
	private String name;
	
	@Column(name = "password", length = 64, nullable = false)
	private String password;
	
	@Column(name = "age", nullable = false)
	private Integer age;
	
	@Column(name = "gender", nullable = false)
	private Integer gender;
	
	@Column(name = "registration", nullable = false)
	private Timestamp registration;
	
	@Column(name = "update_at", nullable = false)
	private Timestamp update_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Timestamp getRegistration() {
		return registration;
	}

	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}

	public Timestamp getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Timestamp update_at) {
		this.update_at = update_at;
	}

}
