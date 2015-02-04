package br.com.espacovenus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.espacovenus.model.paciente.recordatario.Recordatario;

@NamedQueries({
		@NamedQuery(name = "User.find", query = "SELECT u FROM usert u WHERE u.username = :username AND u.password = :password"),
		@NamedQuery(name = "User.list", query = "SELECT u FROM usert u"),
		@NamedQuery(name = "user.find.social", query = "SELECT u FROM usert u WHERE u.username= :username") })
@Entity(name = "usert")
public class User implements Serializable {

	private static final long serialVersionUID = 7571729820113267748L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@Transient
	private String passwordConfirmation;

	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "UserRoles", joinColumns = { @JoinColumn(name = "userId") })
	@Column(name = "role")
	private List<Role> roles;
	
	@OneToMany
	private List<Recordatario> recordatarioList;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	/**
	 * helper para adicionar role para usuario
	 */
	public void setRole(Role role)
	{
		if(roles == null)
		{
			roles = new ArrayList<Role>();
		}
		getRoles().add(role);
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public List<Recordatario> getRecordatarioList() {
		return recordatarioList;
	}

	public void setRecordatarioList(List<Recordatario> recordatarioList) {
		this.recordatarioList = recordatarioList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}