package br.com.yaman.utilsgenerator.main;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.yaman.utilsgenerator.generator.CnpjGenerator;
import br.com.yaman.utilsgenerator.generator.CpfGenerator;
import br.com.yaman.utilsgenerator.write.*;

/**
 * Hello world!
 *
 */
public class App {

	private static String TYPE = "";
	private static String QTD = "";
	private static String PONTUACAO = "";
	private static final Logger LOGGER = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		Instant start = Instant.now();
		boolean pontuacao;
		
		TYPE = args[0];
		QTD = args[1];
		PONTUACAO = args[2];
		
		// DEBUG MODE
//		TYPE = "CPF";
//		QTD = "60";
//		PONTUACAO = "true";
		
		
		try {

			if (PONTUACAO.equals("") || PONTUACAO.equalsIgnoreCase("false")) {
				pontuacao = false;
			} else {
				pontuacao = true;
			}
			ArrayList<String> csv = new ArrayList<>(Integer.parseInt(QTD));
			int qtdInt = Integer.parseInt(QTD);

			if (TYPE.equalsIgnoreCase("CPF")) {
				CpfGenerator cpf = new CpfGenerator();
				for (int i = 0; i < qtdInt; i++) {
					csv.add(cpf.geraCPF(pontuacao));
				}
			}
			if (TYPE.equalsIgnoreCase("CNPJ")) {
				CnpjGenerator cnpj = new CnpjGenerator();
				for (int i = 0; i < qtdInt; i++) {
					csv.add(cnpj.geraCNPJ(pontuacao));
				}
			}

			WriteCsvFile.mkdir();
			WriteCsvFile.writeCSV(csv, TYPE, qtdInt);

			Instant finish = Instant.now();
			LOGGER.info("Processing time {} milissegundos", Duration.between(start, finish).toMillis());
		} catch (Exception e) {
			LOGGER.error("Falha ao realizar processamento");
		}
	}
}
