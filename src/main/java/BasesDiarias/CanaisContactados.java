package BasesDiarias;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import constant.ExtractorConstants;
import util.Getcreden;

public class CanaisContactados {

	private static final String SQL = null;

	public void ConsultaBase() throws IOException {

		String gu = new Getcreden().getCredentials(ExtractorConstants.DATA_USER);
		String gp = new Getcreden().getCredentials(ExtractorConstants.DATA_PSW);

		Date data = new Date(0);

		String datafile = (new SimpleDateFormat("YYYY_MM_dd")).format(data);
		
		

		try {
			Connection con = null;

			Class.forName(ExtractorConstants.JDBC_DRIVER);
			con = DriverManager.getConnection(ExtractorConstants.URL_DB, gu, gp);
			Statement st = con.createStatement();
	
//			 File file = new File(
//			 ExtractorConstants.PATCH_SAIDA_LINUX+"/BASE_DIARIA_CANAIS_CONTACTADOS_"+datafile+".txt");

			File file = new File(
					ExtractorConstants.PATCH_SAIDA_WIN + "BASE_DIARIA_CANAIS_CONTACTADOS_" + datafile + ".txt");
			PrintWriter pw = new PrintWriter(file);
			BufferedWriter out = new BufferedWriter(pw);

			ResultSet stt = st.executeQuery(
					"SELECT DS_EMPRESA_SISTEMA_ORIGEM, DS_SISTEMA_ORIGEM, ID_MANIFESTACAO, DS_CANAL_CONTACTADO FROM MOU.TB_CANAL_CONTACTADO");

	
			ResultSetMetaData md = stt.getMetaData();

			String texto = "";

			System.out.println(md.getColumnCount());

			while (stt.next()) {

				for (int i = 1; i <= md.getColumnCount(); i++) {

					String columname = stt.getObject(md.getColumnLabel(i)) == null ? ""
							: stt.getObject(md.getColumnLabel(i)).toString().trim();

					texto += columname + ";";

				}

				out.write(texto + "\n");
				texto = "";

			}

			
			out.flush();
			pw.close();
			out.close();
			stt.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
