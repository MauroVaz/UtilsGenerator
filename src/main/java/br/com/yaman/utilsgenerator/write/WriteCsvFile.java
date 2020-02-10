package br.com.yaman.utilsgenerator.write;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.yaman.utilsgenerator.generator.CnpjGenerator;



public class WriteCsvFile {
	private static final Logger LOGGER = LogManager.getLogger(WriteCsvFile.class);

	public static void mkdir() {
		 try {
			 	File dir = new File("Generator/");
	            if(dir.exists()) {
	            }else {
	            	dir.mkdir();
	            }
	        } catch (Exception ex) {
				LOGGER.error("Erro ao Gerar Pasta");
	        }
	}
	
	public static void writeCSV(List<String> generatedData, String type, int qtd){
		try {
			long millis = System.currentTimeMillis(); 
			File file = new File("Generator/"+ type + "_" + millis + ".csv");
			FileWriter writer = new FileWriter(file);
			try {
				for (int i = 0; i < qtd; i++) {
					if(i == (qtd-1)) {
					writer.write(generatedData.get(i));	
					}else {
					writer.write(generatedData.get(i) +",");	
					}
				}
			} finally {
				writer.close();
			}
		} catch (Exception e) {
			LOGGER.error("Erro ao Gerar CSV");
		}
	}

}
