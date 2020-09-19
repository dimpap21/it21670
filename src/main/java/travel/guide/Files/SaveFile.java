package travel.guide.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class SaveFile {
	public static void saveToFile(String data, String filename) {
		try {
			final Path path = Paths.get(filename);
			Files.write(path, Arrays.asList(data), StandardCharsets.UTF_8,
					Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		} catch (final IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public static  HashMap<String, String> FromFileToHashMap() throws IOException {
	    String filePath = "citiesFile.txt";
	    HashMap<String, String> map = new HashMap<String, String>();
	    String line;
	    BufferedReader reader = new BufferedReader(new FileReader(filePath));
	    while ((line = reader.readLine()) != null)
	    {
	        String[] parts = line.split(":", 2);
	        if (parts.length >= 2)
	        {
	            String key = parts[0];
	            String value = parts[1];
	            map.put(key, value);
	        } else {
	            System.out.println("ignoring line: " + line);
	        }
	    }
	    for (String key : map.keySet())
	    {
	        System.out.println(key + ":" + map.get(key));
	    }
	    reader.close();
		return map;
	}
	public static<K, V extends Comparable<V> > Map.Entry<K, V>  GetSmallerDayCity() throws IOException {
		HashMap<String, String> map=SaveFile.FromFileToHashMap();
		// To store the result 
        Map.Entry<K, V> entryWithMaxValue = null; 
        // Iterate in the map to find the required entry 
        for (Entry<String, String> currentEntry : map.entrySet()) { 
  
            if ( 
                // If this is the first entry, set result as this 
                entryWithMaxValue == null
  
                // If this entry's value is more than the max value 
                // Set this entry as the max 
                || currentEntry.getValue() 
                           .compareTo((String) entryWithMaxValue.getValue()) 
                       > 0) { 
  
                entryWithMaxValue = (Entry<K, V>) currentEntry; 
            } 
        } 
  
        // Return the entry with highest value 
        return entryWithMaxValue; 

	}
	public static void clearFile() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("citiesFile.txt");
		System.out.println("aaa");
		pw.close();
	}
}
