package br.com.espacovenus.model.alimento;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
@NamedQueries({
	@NamedQuery(name = "GrupoAlimentar.findByNomeGrupo", query = "SELECT ga FROM grupoAlimentar ga WHERE ga.nomeGrupo = :nomeGrupo"),
	@NamedQuery(name = "GrupoAlimentar.list", query = "SELECT ga FROM grupoAlimentar ga"),
	 })
@Entity(name = "grupoAlimentar")
public class GrupoAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@OneToMany
	private List<Alimento> alimentos;
	
	@Column(nullable = false, unique = true)
	private String nomeGrupo;
	
	@Column
	private String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Alimento> getAlimentos() {
		return alimentos;
	}
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
	public String getNomeGrupo() {
		return nomeGrupo;
	}
	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
