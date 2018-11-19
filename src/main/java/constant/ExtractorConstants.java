package constant;

public class ExtractorConstants {

	public static final String URL_DB = getEnv("MOU_URL_DB");
	public static final String DATA_USER = getEnv("MOU_DATA_USER");
	public static final String DATA_PSW = getEnv("MOU_DATA_PSW");
	public static final String JDBC_DRIVER = getEnv("MOU_JDBC_DRIVER");
	public static final String PATCH_ARCHIVE_CSV =getEnv("MOU_PATCH_ARCHIVE_CSV");//"\\\\180.233.224.106\\TabelasExportadas\\MIS";
	public static final String PATCH_BAU_WIN = "C:\\Users\\t697609\\Desktop\\";
	public static final String PATCH_SAIDA_WIN = "C:\\Users\\t697609\\Desktop\\";
	public static final String PATCH_BAU_LINUX = "/sistema/mis/arquivos/entrada";
	public static final String PATCH_SAIDA_LINUX = "/sistema/mis/arquivos/saida";

	private static String getEnv(String var) {
		try {
			String response = System.getenv(var).trim();
			System.out.println(String.format("%s = %s", var, response));
			return response;
		} catch (Exception e) {
			return "";
		}
	}
}
