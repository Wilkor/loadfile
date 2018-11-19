package loadBd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import constant.ExtractorConstants;
import util.Getcreden;

public class ProcExecute {

	public void ExecuteProc() throws ClassNotFoundException {

		Class.forName(ExtractorConstants.JDBC_DRIVER);

		try {

			String gu = new Getcreden().getCredentials(ExtractorConstants.DATA_USER);
			String gp = new Getcreden().getCredentials(ExtractorConstants.DATA_PSW);

			Connection connection = DriverManager.getConnection(ExtractorConstants.URL_DB, gu, gp);

			try {

				PreparedStatement startment1 = connection
						.prepareStatement("{call MOUPROC.PKG_ETL_OUVIDORIA.PRC_EXECUTE_QUERY}");
				startment1.execute();
				startment1.close();

			} catch (Exception e) {

				System.out.println(e.getMessage().toString());

			}

		} catch (Exception e) {

		}

	}

}
