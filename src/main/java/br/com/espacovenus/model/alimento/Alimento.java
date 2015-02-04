package br.com.espacovenus.model.alimento;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
@NamedQueries({
	@NamedQuery(name = "Alimento.findByNomeAlimento", query = "SELECT a FROM alimento a WHERE a.nome = :nome"),
	@NamedQuery(name = "Alimento.list", query = "SELECT a FROM alimento a"),
	 })
@Entity(name="alimento")
public class Alimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;	
	private String nome;
	private String descricao;
	
	@OneToMany
	private List<ComposicaoNutricional> composicaoNutricional;
	
	private boolean liquido;
	
	@ManyToOne(optional = false)
	private GrupoAlimentar grupoAlimentar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<ComposicaoNutricional> getComposicaoNutricional() {
		return composicaoNutricional;
	}
	public void setComposicaoNutricional(
			List<ComposicaoNutricional> composicaoNutricional) {
		this.composicaoNutricional = composicaoNutricional;
	}
	public boolean isLiquido() {
		return liquido;
	}
	public void setLiquido(boolean liquido) {
		this.liquido = liquido;
	}
	public GrupoAlimentar getGrupoAlimentar() {
		return grupoAlimentar;
	}
	public void setGrupoAlimentar(GrupoAlimentar grupoAlimentar) {
		this.grupoAlimentar = grupoAlimentar;
	}
	
	
	
}
