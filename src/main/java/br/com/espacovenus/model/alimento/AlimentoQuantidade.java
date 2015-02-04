package br.com.espacovenus.model.alimento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.espacovenus.model.paciente.recordatario.Refeicao;

@Entity
public class AlimentoQuantidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@OneToOne
	private Alimento alimento;
	
	@OneToOne
	private Quantidade quantidade;
	
	@ManyToOne
	private Refeicao refeicao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Quantidade getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Quantidade quantidade) {
		this.quantidade = quantidade;
	}
	
}
