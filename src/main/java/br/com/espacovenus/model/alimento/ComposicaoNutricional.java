package br.com.espacovenus.model.alimento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class ComposicaoNutricional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;	
	
	@ManyToOne
	private Alimento alimento;
	
	@OneToOne
	private Quantidade quantidade;
	private boolean tabelaTaco;
	
	
	private int valorCalorico;
	private int carboidratos;
	private int proteina;
	private int gordurasTotais;
	private int gordurasSaturadas;
	private int gorduraTrans;
	private int fibra;
	private int acucar;
	private int sodio;
	private int calcio;
	private int ferro;
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
	public boolean isTabelaTaco() {
		return tabelaTaco;
	}
	public void setTabelaTaco(boolean tabelaTaco) {
		this.tabelaTaco = tabelaTaco;
	}
	public int getValorCalorico() {
		return valorCalorico;
	}
	public void setValorCalorico(int valorCalorico) {
		this.valorCalorico = valorCalorico;
	}
	public int getCarboidratos() {
		return carboidratos;
	}
	public void setCarboidratos(int carboidratos) {
		this.carboidratos = carboidratos;
	}
	public int getProteina() {
		return proteina;
	}
	public void setProteina(int proteina) {
		this.proteina = proteina;
	}
	public int getGordurasTotais() {
		return gordurasTotais;
	}
	public void setGordurasTotais(int gordurasTotais) {
		this.gordurasTotais = gordurasTotais;
	}
	public int getGordurasSaturadas() {
		return gordurasSaturadas;
	}
	public void setGordurasSaturadas(int gordurasSaturadas) {
		this.gordurasSaturadas = gordurasSaturadas;
	}
	public int getGorduraTrans() {
		return gorduraTrans;
	}
	public void setGorduraTrans(int gorduraTrans) {
		this.gorduraTrans = gorduraTrans;
	}
	public int getFibra() {
		return fibra;
	}
	public void setFibra(int fibra) {
		this.fibra = fibra;
	}
	public int getAcucar() {
		return acucar;
	}
	public void setAcucar(int acucar) {
		this.acucar = acucar;
	}
	public int getSodio() {
		return sodio;
	}
	public void setSodio(int sodio) {
		this.sodio = sodio;
	}
	public int getCalcio() {
		return calcio;
	}
	public void setCalcio(int calcio) {
		this.calcio = calcio;
	}
	public int getFerro() {
		return ferro;
	}
	public void setFerro(int ferro) {
		this.ferro = ferro;
	}
	
}
