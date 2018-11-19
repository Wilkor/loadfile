package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadFileLog {

	public static void fileLog(String description, Level level) throws SecurityException, IOException {

		String defaultDir = (new File("")).getAbsolutePath();
		String datafile;
		String d;

		Date data = new Date();

		datafile = (new SimpleDateFormat("ddMMyyyy")).format(data);

		String path = "/produtos/bases_ouvidoria/log/log_etl_" + datafile + ".txt";
		FileHandler fh = new FileHandler(path);
		Logger logger = Logger.getLogger("log_etl_" + datafile);

		logger.setLevel(level);
		logger.addHandler(fh);
		logger.log(level, description);

	}

}
