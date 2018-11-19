package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import constant.ExtractorConstants;

public class ExtratorUtil {

	public static Collection<String[]> get(String arch) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(ExtractorConstants.PATCH_ARCHIVE_CSV + arch));

		String line = br.readLine();
		Collection<String[]> map = new ArrayList<String[]>();

		while ((line = br.readLine()) != null) {

			String[] array = line.split(",\"");
			for (String value : array) {
				value = value.replaceAll("\"", "");
			}
			map.add(array);
		}

		br.close();

		return map;
	}
}
