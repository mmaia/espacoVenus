package br.com.espacovenus.desenvolvimento.bancodados.carga;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.generator.ValueGenerator;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * Session Bean implementation class CargaDadosDesenvolvimento
 */
@Singleton
@Startup
public class CargaDadosDesenvolvimento {

	@Resource(name="java:/DataSources/EspacoVenusDS")
	private DataSource dataSource;
	
	public final static Logger log = Logger.getLogger(CargaDadosDesenvolvimento.class.getName());
	
	/**
	 * Limpa a base de dados
	 */
	public final static Operation DELETE_ALL = deleteAllFrom("alimento","grupoalimentar");
	
	/**
	 * Carrega tabela grupoalimentar
	 */
	public final static Operation CARREGA_GRUPO_ALIMENTAR = sequenceOf(
			insertInto("grupoalimentar")
			.withGeneratedValue("id", ValueGenerators.sequence().startingAt(1000L).incrementingBy(1))
			.columns("nomegrupo", "descricao")
			.values("Cereais e derivados", "Alimentos naturais com alto teor de fibras")
			.values("Verduras, hortaliças e derivados", "Alimentos vegetais naturais, ricos em vitaminas")
			.values("Frutas e derivados", "Alimentos normalmente ricos energia e vitaminas")
			.build()
			);
	
	
	
	/**
	 * Carrega tabela de alimentos
	 */
	public final static Operation CARREGA_ALIMENTOS = sequenceOf(
			insertInto("alimento")
			.withGeneratedValue("id", ValueGenerators.sequence().startingAt(1000L).incrementingBy(1))
			.columns("nome", "descricao", "liquido", "grupoalimentar_id")
			.values("Arroz, integral, cozido", "Rico em fibras e carboidratos", false, "1000")
			.values("Arroz, integral, cru", "Quem come arroz cru????", false, "1000")
			.values("Arroz, tipo 1, cozido", null, false, "1000")
			.values("Arroz, tipo 1, cru", null, false, "1000")
			.values("Arroz, tipo 2, cozido", null, false, "1000")
			.values("Arroz, tipo 2, cru", null, false, "1000")
			.values("Aveia, flocos, crua", null, false, "1000")
			.values("Biscoito, doce, maisena", null, false, "1000")
			.values("Biscoito, doce, recheado com chocolate", null, false, "1000")
			.values("Biscoito, doce, recheado com morango", null, false, "1000")
			.values("Biscoito, doce, wafer, recheado de chocolate", null, false, "1000")
			.values("Biscoito, doce, wafer, recheado de morango", null, false, "1000")
			.values("Biscoito, salgado, cream cracker", null, false, "1000")
			.values("Bolo, mistura para", null, false, "1000")
			.values("Bolo, pronto, aipim", null, false, "1000")
			.values("Bolo, pronto, chocolate", null, false, "1000")
			.values("Bolo, pronto, coco", null, false, "1000")
			.values("Abóbora, cabotian, cozida", null, false, "1001")
			.values("Batata, frita, tipo chips, industrializada", null, false, "1001")
			.values("Banana, prata, crua", null, false, "1002")
			.values("Maçã, Fuji, com casca, crua", null, false, "1002" )
			.build()
			);
	
	
	
	public CargaDadosDesenvolvimento() {
		log.info("\nContrutor CargaDadosDesenvolvimento executando!!!! WARNING!!! WARNING!!!! "
				+ " ==============================ESTE CODIGO SO DEVE EXECUTAR EM DESENVOLVIMENTO ===== DESATIVAR EM PRODUCAO=========\n\n");
	}

	@PostConstruct
	public void startup() {
		log.info("Executando CargaDadosDesenvolvimento.startup!");
		Operation operation = sequenceOf(
				CargaDadosDesenvolvimento.DELETE_ALL,
				CargaDadosDesenvolvimento.CARREGA_GRUPO_ALIMENTAR,
				CargaDadosDesenvolvimento.CARREGA_ALIMENTOS
				);
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
		dbSetup.launch();
		log.info("Executou CargaDadosDesenvolvimento.startup!");
	}

	@PreDestroy
	public void destroy() {
		log.info("Executando CargaDadosDesenvolvimento.destroy!");
		Operation operation = sequenceOf(
				CargaDadosDesenvolvimento.DELETE_ALL
				);
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
		dbSetup.launch();
		log.info("Executou CargaDadosDesenvolvimento.destroy!");
	}

}
