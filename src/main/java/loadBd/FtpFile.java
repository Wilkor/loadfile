package loadBd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FtpFile {

	private static final int BUFFER_SIZE = 4096;

	public void ftpfile() {

		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String host = "spuap232.santander.com.br";
		String user = "";
		String pass = "";
		String filePath = "/sistema/mis/";
		String savePath = "E:/filePeople/";

		ftpUrl = String.format(ftpUrl, user, pass, host, filePath);
		System.out.println("URL: " + ftpUrl);

		try {
			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			InputStream inputStream = conn.getInputStream();

			FileOutputStream outputStream = new FileOutputStream(savePath);

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			System.out.println("File downloaded");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
