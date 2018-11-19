package loadBd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import constant.ExtractorConstants;
import constant.QueryConstants;
import util.Getcreden;
import util.LoadFileLog;

public class LoadCsv extends LoadFileLog {

	private Connection con;
	private PreparedStatement ps = null;
	private Statement stmt = null;
	private static final String EMPTY = "";

	Date data = new Date();
	String defaultDir = (new File("")).getAbsolutePath();

	String datafile = (new SimpleDateFormat("YYYY_MM_dd")).format(data);

	public Connection getConnectionTeste(String Arquivo, String query, String namearch)
			throws SecurityException, IOException, SQLException {

		try {

			Class.forName(ExtractorConstants.JDBC_DRIVER);

			int batchSize = 0;
			if (namearch.equals("STD_ETL_FATO_DESCRICAO") || namearch.equals("STD_ETL_FATO_MANIFESTACAO")
					|| namearch.equals("STD_ETL_FATO_OBSERVACAO")) {
			
				batchSize = 1000;
			} else {
				batchSize = 6000;
			}

			int count = 0;
			int i = 1;
			String[] query2 = query.split("\\(");
			String[] tmp;

			Runtime runtime = Runtime.getRuntime();

			FileInputStream stream = new FileInputStream(ExtractorConstants.PATCH_ARCHIVE_CSV +"/"+ Arquivo);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader, 8 * 1024);

			boolean inseriu = false;
			String line;

			inseriu = false;
			boolean cabe = false;

			String gu = new Getcreden().getCredentials(ExtractorConstants.DATA_USER);
			String gp = new Getcreden().getCredentials(ExtractorConstants.DATA_PSW);

			try {

				Connection connection = DriverManager.getConnection(ExtractorConstants.URL_DB, gu, gp);

				PreparedStatement startment1 = connection.prepareStatement("DELETE from " + query2[0]);
				ResultSet rs1 = startment1.executeQuery();
				rs1.close();
				startment1.close();

				try {

					PreparedStatement statment = connection.prepareStatement(QueryConstants.INSERT + " " + query);

					while ((line = br.readLine()) != null) {

						if (cabe == false) {

							cabe = true;
							continue;

						}

						if (namearch.equals("STD_ETL_DIM_SINTOMA")) {

							String line3 = line.replace("\\r\\n", "").replaceAll("\\\",\\\"", "@@");
							tmp = line3.split("@@");

						} else {

							tmp = line.trim().replace("\\r\\n", "").split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

						}

						for (int ik = 1; ik <= tmp.length; ik++) {

							statment.setString(ik, tmp[ik - 1].replace("\"",""));

						}

						statment.addBatch();

						if (++count % batchSize == 0) {

							statment.executeBatch();
							statment.clearBatch();

						}

					}

					statment.executeBatch();

					br.close();
					line = null;
					statment.close();

					tmp = null;

					PreparedStatement startment2 = connection.prepareStatement("select count(*) from " + query2[0]);
					ResultSet rs12 = startment2.executeQuery();

					if (rs12.next()) {

						System.out.println("Volume: " + rs12.getInt(1));

					}

					rs12.close();
					startment2.close();
					runtime.gc();

				} catch (Exception e) {

					br.close();
//					 File arquivo = new File(ExtractorConstants.PATCH_ARCHIVE_CSV + Arquivo);
//					 File criarDiretorio = new File(
//					 "/sistema/mis/arquivo/entrada/Incorretos/" + datafile + "_" + Arquivo);

					File arquivo = new File(ExtractorConstants.PATCH_ARCHIVE_CSV + Arquivo);
					File criarDiretorio = new File(
							"C:\\Users\\t697609\\Desktop\\Incorretos\\" + datafile + "_" + Arquivo);

					try {
						

//						 if (!criarDiretorio.exists()) {
//						 criarDiretorio.mkdir();
//						 File diretorio = new File(
//						 "/sistema/mis/arquivo/entrada/Incorretos/" + datafile + "_" + Arquivo);
//						 arquivo.renameTo(diretorio);
//						
//						 } else {
//						
//						 File diretorio = new File(
//						 "/sistema/mis/arquivo/entrada/Incorretos/" + datafile + "_" + Arquivo);
//						 arquivo.renameTo(diretorio);
//						
//						 }

						if (!criarDiretorio.exists()) {
							criarDiretorio.mkdir();
							File diretorio = new File(
									"C:\\Users\\t697609\\Desktop\\Incorretos\\" + datafile + "_" + Arquivo);
							arquivo.renameTo(diretorio);
						} else {

							File diretorio = new File(
									"C:\\Users\\t697609\\Desktop\\Incorretos\\" + datafile + "_" + Arquivo);
							arquivo.renameTo(diretorio);

						}

					} catch (Exception ex) {
						System.out.println(ex.getMessage().toString());
					}

					System.out.println(e);
					System.out.println(e.getStackTrace());
					LoadFileLog.fileLog(e.getMessage().toString(), Level.SEVERE);

				}

			} catch (Exception e) {
				System.out.println("Problemas na conexao");
				LoadFileLog.fileLog(e.getMessage().toString(), Level.SEVERE);
			}

		} catch (ClassNotFoundException ex) {

			System.out.println("Driver não localizado.");
			LoadFileLog.fileLog(ex.getMessage().toString(), Level.SEVERE);
		}

		File arquivo = new File(ExtractorConstants.PATCH_ARCHIVE_CSV +"\\"+ Arquivo);

//		 File criarDiretorio = new File("/sistema/mis/arquivo/entrada/Corretos/");
//
//		 if (!criarDiretorio.exists()) {
//		
//		 criarDiretorio.mkdir();
//		
//		 File diretorio = new File("/sistema/mis/arquivo/entrada/Corretos/" + datafile
//		 + "_" + Arquivo);
//		 arquivo.renameTo(diretorio);
//		
//		 } else {
//		
//		 File diretorio = new File("/sistema/mis/arquivo/entrada/Corretos/" + datafile
//		 + "_" + Arquivo);
//		 arquivo.renameTo(diretorio);
//		
//	     }

		File criarDiretorio = new File("C:\\Users\\t697609\\Desktop\\Corretos\\");

		if (!criarDiretorio.exists()) {

			criarDiretorio.mkdir();
			File diretorio = new File("C:\\Users\\t697609\\Desktop\\Corretos\\" + datafile + "_" + Arquivo);
			arquivo.renameTo(diretorio);
			
		} else {
			
			File diretorio = new File("C:\\Users\\t697609\\Desktop\\Corretos\\" + datafile + "_" + Arquivo);
			arquivo.renameTo(diretorio);

		}

		return con;

	}

}
