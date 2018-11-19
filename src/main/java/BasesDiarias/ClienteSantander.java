package BasesDiarias;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import constant.ExtractorConstants;
import util.Getcreden;

public class ClienteSantander {

	public void BaseSantander() throws IOException {

		String gu = new Getcreden().getCredentials(ExtractorConstants.DATA_USER);
		String gp = new Getcreden().getCredentials(ExtractorConstants.DATA_PSW);

		Date data = new Date();

		String datafile = (new SimpleDateFormat("YYYY_MM_dd")).format(data);

		try {
			Connection con = null;

			Class.forName(ExtractorConstants.JDBC_DRIVER);
			con = DriverManager.getConnection(ExtractorConstants.URL_DB, gu, gp);
			Statement st = con.createStatement();
		

//			 File file = new File(
//			 ExtractorConstants.PATCH_SAIDA_LINUX + "/BASE_DIARIA_CLIENTE_SANTANDER_" +
//			 datafile + ".txt");

			File file = new File(
					ExtractorConstants.PATCH_SAIDA_WIN + "BASE_DIARIA_CLIENTE_SANTANDER_" + datafile + ".txt");

			PrintWriter pw = new PrintWriter(file);
			BufferedWriter out = new BufferedWriter(pw);
			ResultSet stt = st.executeQuery(
					"SELECT ID_MANIFESTACAO, DS_TIPO_CLIENTE, CD_CPF_CNPJ,DS_NOME_CLIENTE, DS_METODO_CONTATO_1, DS_CONTATO_1, DS_METODO_CONTATO_2, DS_CONTATO_2, CD_CONTA_CLIENTE, CD_NR_CARTAO FROM MOU.TB_MANIFESTACAO_DIAR");

			
			ResultSetMetaData md = stt.getMetaData();

			String texto = "";

			while (stt.next()) {

				for (int i = 1; i <= md.getColumnCount(); i++) {

					String columname = stt.getObject(md.getColumnLabel(i)) == null ? ""
							: stt.getObject(md.getColumnLabel(i)).toString().trim();

					texto += columname + ";";

				}

				out.write(texto + "\n");
				texto = "";

			}

			stt.close();
			out.flush();
			pw.close();
			out.close();

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
