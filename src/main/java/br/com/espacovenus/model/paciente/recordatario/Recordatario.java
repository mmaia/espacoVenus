package br.com.espacovenus.model.paciente.recordatario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.espacovenus.model.User;

@Entity(name = "recordatario")
public class Recordatario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne
	private User usuario;
	
	@OneToMany
	private List<Refeicao> refeicaoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public List<Refeicao> getRefeicaoList() {
		return refeicaoList;
	}

	public void setRefeicaoList(List<Refeicao> refeicaoList) {
		this.refeicaoList = refeicaoList;
	}
	
	
	
	
}
