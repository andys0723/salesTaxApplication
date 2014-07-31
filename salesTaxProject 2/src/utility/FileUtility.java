package utility;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  FileUtility is a utility to help read line from input file and store back output file
 *  Method in FileUtility class is declared as static so that no object is require.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileUtility {

	public static List<String> read(File file) throws IOException {
		List<String> resultList = new ArrayList<String>();
		FileInputStream fileInputStream =null;
		BufferedReader bufferReader=null;
		try {
			fileInputStream = fileInputStream = new FileInputStream(file);
		    bufferReader = bufferReader = new BufferedReader(new InputStreamReader(fileInputStream));

			String line = bufferReader.readLine();
			while (line != null) {
				resultList.add(line);
				line = bufferReader.readLine();
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				bufferReader.close();
				fileInputStream.close();
			} catch (IOException ex) {
				Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		return resultList;
	}

	public static void write(File file, List<String> resultList)
			throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bufferReader = new BufferedWriter(fw);
		for (String content : resultList) {
			bufferReader.write(content);
		}
		try {
			bufferReader.close();

		} catch (IOException ex) {
			Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}
}
