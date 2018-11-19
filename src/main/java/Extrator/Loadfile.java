package Extrator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.common.io.Files;

import BasesDiarias.CanaisContactados;
import BasesDiarias.ClienteSantander;
import BasesDiarias.ManifestaoSantander;
import constant.ExtractorConstants;
import constant.QueryConstants;
import loadBd.LoadCsv;
import loadBd.ProcExecute;
import util.ReadLine;

public class Loadfile {

	public static void main(String[] args) throws Exception {

		
		Date data = new Date();
		String datafile = (new SimpleDateFormat("YYYY_MM_dd")).format(data);
		System.out.println("iniciando processamento");
		for (String a : System.getenv().keySet()) {
			// System.out.println(String.format("%s = %s", a, System.getenv(a)));
		}
		File file = new File(ExtractorConstants.PATCH_ARCHIVE_CSV +"\\");
		File[] fileNamesList = null;
		fileNamesList = file.listFiles();

		ArrayList<String> archiv = new ArrayList<String>();

		for (int i = 0; i < fileNamesList.length; i++) {
			
			
			boolean isCsv =  fileNamesList[i].getName().toString().endsWith(".csv");
			
			 if(isCsv==true) {

			
			     File criarDiretorio_linux = new File(ExtractorConstants.PATCH_BAU_WIN +"BAU");
				// File criarDiretorio_linux = new File(ExtractorConstants.PATCH_BAU_LINUX +"BAU");
				 
				 if (!criarDiretorio_linux.exists()) {
				
				 criarDiretorio_linux.mkdirs();
				
				 File source_linux = new File(
				 ExtractorConstants.PATCH_ARCHIVE_CSV +"\\"+
				 fileNamesList[i].getName().toString());
				 
				 File dest_linux = new File(ExtractorConstants.PATCH_BAU_WIN + "BAU\\" +
				 datafile + "_"
				 + fileNamesList[i].getName().toString());
				
				 try {
				
				 Files.copy(source_linux, dest_linux);
				
				 } catch (IOException e) {
				
				 e.printStackTrace();
				 }
				
				 } else {
				
				 File source_linux = new File(
				 ExtractorConstants.PATCH_ARCHIVE_CSV  +"\\"+
				 fileNamesList[i].getName().toString());
				 File dest_linux = new File(ExtractorConstants.PATCH_BAU_WIN + "BAU\\" +
				 datafile + "_"
				 + fileNamesList[i].getName().toString());
				
				 try {
				
				 Files.copy(source_linux, dest_linux);
				
				 } catch (IOException e) {
				
				 e.printStackTrace();
				 }
				
				 }

			  
			
				 
			 }else {
				 
				  System.out.println("não é csv");
			 }

			
			
			 archiv.add(fileNamesList[i].getName().toString());
		}

		String sql = null;

		LoadCsv f = new LoadCsv();
		ReadLine rl = new ReadLine();
		ProcExecute pe = new ProcExecute();

		for (int i = 0; i < archiv.size(); i++) {

			String ext_arq = archiv.get(i);

			try {

				if (ext_arq.split("-")[0].equals("STD_ETL_DIM_AREA_SOLUCIONADORA")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_AREA_SOLUCIONADORA);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_AREA_ORIENTADORA")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_AREA_ORIENTADORA);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_CANAL_OCORRENCIA")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_CANAL_OCORRENCIA);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_CANAL_ORIGEM")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_CANAL_ORIGEM);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_ODC")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_ODC);

				} else if (ext_arq.split("_")[1].equals("TRACKING")) {

					rl.readtoOracle(ext_arq, ext_arq.split("_")[1], QueryConstants.REL_TRACKING);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_FAMILIA")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_FAMILIA);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_LOCAIS")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_LOCAIS);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_LOCAL_SOLUCAO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_LOCAL_SOLUCAO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_ODC")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_ODC);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_PRAZO_SLA")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_PRAZO_SLA);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_PRODUTO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_PRODUTO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_SEGMENTO_CLIENTE")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_SEGMENTO_CLIENTE);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_SINTOMA")) {

					// System.out.println("passei aqui");

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_SINTOMA);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_STATUS")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_STATUS);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_SUPERVISOR")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_SUPERVISOR);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_TIPO_MANIFESTACAO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_TIPO_MANIFESTACAO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_TIPO_OBSERVACAO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_TIPO_OBSERVACAO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_DIM_USUARIO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_DIM_USUARIO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_CANAL_CONTACTADO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_CANAL_CONTACTADO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_CLIENTE")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_CLIENTE);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_CONTADOR")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_CONTADOR);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_DESCRICAO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_DESCRICAO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_ENDERECO_CLIENTE")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_ENDERECO_CLIENTE);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_FINANCEIRA")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_FINANCEIRA);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_MANIFESTACAO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_MANIFESTACAO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_OBSERVACAO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_OBSERVACAO);

				} else if (ext_arq.split("-")[0].equals("STD_ETL_FATO_RL_MANIFESTACAO")) {

					rl.readtoOracle(ext_arq, ext_arq.split("-")[0], QueryConstants.STD_ETL_FATO_RL_MANIFESTACAO);

				}

			} catch (Exception e) {

			}

		}

		System.out.println("Importação Concluida");

		System.out.println("Momento ETL");
		pe.ExecuteProc();
		Thread.sleep(6000);
		System.out.println("Finalizou ETL");

		System.out.println("mf");
		ManifestaoSantander mf = new ManifestaoSantander();
		mf.ConsultaManifestacao();
		Thread.sleep(6000);
		System.out.println("fim mf");
		System.out.println("CS");
		ClienteSantander cs = new ClienteSantander();
	    cs.BaseSantander();
		Thread.sleep(6000);
		System.out.println("fim CS");

		System.out.println("cc");
		CanaisContactados cc = new CanaisContactados();
		cc.ConsultaBase();
		Thread.sleep(6000);
		System.out.println("fim cc");
		
	
		System.out.println("processo finalizado");

	}

}
