import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class main {
	public static void main(String ...args) {
		StringBuilder bldr = new StringBuilder("");
		StringBuilder books = new StringBuilder("books = {");
		File file = new File("/Users/aasierra/Desktop/bible");
		if (file != null) {
	        // Reading directory contents
	        File[] files = file.listFiles();

	        for (int i = 0; i < files.length; i++) {
	        	books.append("\"" + files[i].getName() + "\", ");
	        	File[] subFiles = files[i].listFiles();
                bldr.append("verses[\"" + files[i].getName() + "\"] = {");
	            for (int j = 0; j < subFiles.length; j++) {
	            	BufferedReader reader = null;

	                try {
	                    reader = new BufferedReader(new FileReader(subFiles[j]));
	                    String line = null;
	                    while(true)
	                    {
	                        line = reader.readLine();
	                        if(line == null)
	                            break;
	                        
	                        line = line.replace("\"", "'");
	                        bldr.append("\"");
	                        bldr.append(subFiles[j].getName().replace(".txt", " "));
	                        bldr.append(line);
	                        bldr.append("\", ");
	                    }
	                }catch(Exception e) {
	                    e.printStackTrace();
	                }finally {
	                    if(reader != null)
	                    {
	                        try {
	                            reader.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            }

                String removeComma = bldr.toString();
                removeComma = removeComma.substring(0, removeComma.length()-2);
                bldr = new StringBuilder(removeComma);
        		bldr.append("}\n");
	        }
		}
        String removeComma = books.toString();
        removeComma = removeComma.substring(0, removeComma.length()-2);
        books = new StringBuilder(removeComma);
		books.append("}");
		PrintWriter writer;
		try {
			writer = new PrintWriter("/Users/aasierra/Desktop/output.txt", "UTF-8");
			writer.println("verses = {}");
			writer.println(books.toString());
			writer.println(bldr.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end static
}
