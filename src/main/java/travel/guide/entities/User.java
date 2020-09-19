package travel.guide.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	@Column
	String role;
	@Column(unique=true)
	 private String username;
	@Column(length = 45, nullable = false,unique=true)
	private String email;
	private String password;
	@Column(columnDefinition = "boolean default true", nullable = false)
    private boolean enabled  = true;
	@Column(nullable = false)
	private String name;
	@Column(length = 45, nullable = false)
	private int age;
	public User(String username, String password, String email, String role,String name, int age ) {
		this.username=username;
		this.email=email;
		this.password=password;
        this.role=role;
        this.name = name;
		this.age = age;
    }
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", username=" + username + ", email=" + email + ", password="
				+ password + ", enabled=" + enabled + ", name=" + name + ", age=" + age + "]";
	}

}
