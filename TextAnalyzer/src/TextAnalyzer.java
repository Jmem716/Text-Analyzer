/*
 * Author: Jaime Maldonado
 * Text Analyzer 
 * Program reads text file and prints out top 20 words with most frequency followed by count
 * 
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class TextAnalyzer {

	static void countEachWords() throws FileNotFoundException {
		
		Map<String, Integer> words = new HashMap<String, Integer>();
		ValueComparator compared = new ValueComparator(words);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(compared);
		Scanner file = new Scanner(new File("testfile.txt"));

		//Reads file and increments word frequency counter
		while(file.hasNext()) {
			String word = file.next();
			Integer count = words.get(word); //Returns NULL if string not in HashMap (First Iteration is NULL)

			if(count != null)
				count++;//String exists in HashMap, adding to frequency count
			else
				count = 1;
				words.put(word, count);//Adding string & count to HashMap
				
		}
		
		sorted_map.putAll(words); //Puts all key/value pairs in sorted TreeMap
		
		int i = 1;

		for(String key : sorted_map.keySet()) {//Prints key/value pair
			if (i > 20) break; //Show top 20
			String value = words.get(key).toString();  
			key = key.replaceAll("[ .]", " ").replaceAll("\"", "").replaceAll(",", "");//Removes unwanted characters
			System.out.println("(" + i + ") " + key + " - " + value);
			i++;
		}
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		countEachWords();

	}

}

class ValueComparator implements Comparator<String> {
    Map<String, Integer> words;

    public ValueComparator(Map<String, Integer> words) {
        this.words = words; 
    }

    // Received two (key/value) arguments and compares both
    public int compare(String a, String b) {
        if (words.get(a) >= words.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}