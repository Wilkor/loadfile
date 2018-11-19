package util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import loadBd.LoadCsv;

public class ReadLine {

	public void readtoOracle(String arch, String nameArch, String query)
			throws SecurityException, IOException, Exception {

		LoadCsv f = new LoadCsv();
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(nameArch);
		int i = 0;
		boolean inseriu = false;
	
		f.getConnectionTeste(arch, query,nameArch);
	

	}

}
