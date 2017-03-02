import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class PuzzlesChallenges
{

    static final Pattern WORD_PATTERN = Pattern.compile( "[- .:,]+");

    /**
     * Puzzle 1
     * Select the list of words from the input list whose length is greater than
     * the word's position in the list (starting from zero) .
     */
    public static List<String> selectByLengthAndPosition( List<String> input )
    {
        
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < input.size(); ++i) {
        	if (input.get(i).length() > i) {
        		result.add(input.get(i));
        	}
        }

        return result;
    }

    /**
     * Puzzle 2
     * Select the longest words from the input list. That is, select the words
     * whose lengths are equal to the maximum word length.
     */
    public static List<String> selectLongestWords( List<String> input )
    {

        List<String> result = new ArrayList<String>();
        int currentLongestWordLength = 0;
        for (int i = 0; i < input.size(); ++i) {
        	// if longer than the previous longest word
        	if (input.get(i).length() > currentLongestWordLength) {
        		result = new ArrayList<String>(); // empty list
        		String newLongestWord = input.get(i);
        		result.add(newLongestWord);
        		currentLongestWordLength = newLongestWord.length();
        	} else if (input.get(i).length() == currentLongestWordLength) {
        		// otherwise if the word is as long as the previous longest word, add it to the list
        		result.add(input.get(i));
        	}
        }

        return result;
    }


    /**
     * Puzzle 3
     * Read words from the text file, and sort unique, lower-cased words by length,
     * then alphabetically within length, and place the result into an output list.
     *
     * @throws IOException
     */
    public static List<String> sortedLowerCaseDistinctByLengthThenAlphabetically( BufferedReader reader )
        throws IOException
    {
    	
        List<String> result = new ArrayList<String>();
        String line = "";
        
        // go through the text
        while ((line = reader.readLine()) != null) {
        	// collect a line of text and replace all unallowed characters with a space
        	line = line.replaceAll(WORD_PATTERN.pattern(), " ");
        	// split the line at all the spaces to get a list of words
        	List<String> lineWords = new ArrayList<String>(Arrays.asList(line.split(" ")));
        	
        	// go through the words in the line and check for duplicates
        	for (String word : lineWords) {
        		word = word.toLowerCase();
        		if (!word.isEmpty() && !result.contains(word)) {
        			result.add(word);
        		}
        	}
        }
        
        Collections.sort(result, (word1, word2) -> {
        	int lengthDifference = word1.length() - word2.length();
        	if (lengthDifference == 0) {
        		return word1.compareTo(word2);
        	} else {
        		return lengthDifference;
        	}
        });
        
        return result;
    }

     /**
      * Puzzle 4
      * Get the last word in the text file
      */
     public static String getLastWord( BufferedReader reader )
     {
         String result = null;
         
         try {
        	 String line = "";
        	 while ((line = reader.readLine()) != null) {
        		 line = line.replaceAll("[,.:]+", "");
        		 result = line.substring(line.lastIndexOf(" ") + 1);
			 }
		 } catch (IOException e) {
			 e.printStackTrace();
		 }

         return result;
     }

    /**
     * Puzzle 5
     * From the words in the text file, create nested maps, where the outer map is a
     * map from the first letter of the word to an inner map. (Use a string of length
     * one as the key.) The inner map, in turn, is a mapping from the length of the
     * word to a list of words with that length. Don't bother with any lowercasing
     * or uniquifying of the words.
     *
     * For example, given the words "foo bar baz bazz foo" the string
     * representation of the result would be:
     *     {b={3=[bar, baz], 4=[bazz]}, f={3=[foo, foo]}}
     *
     * @throws IOException
     */
    public static Map<String, Map<Integer, List<String>>> nestedMaps( BufferedReader reader )
    {

        Map<String, Map<Integer, List<String>>> result = new TreeMap<String, Map<Integer, List<String>>>();

        try {
        	String line = "";
        	while ((line = reader.readLine()) != null) {
        		line = line.replaceAll(WORD_PATTERN.pattern(), " ");
            	List<String> lineWords = new ArrayList<String>(Arrays.asList(line.split(" ")));
        		for (String word : lineWords) {
        			String initial = "" + word.charAt(0);
        			if (result.containsKey(initial)) {
        				if (!result.get(initial).containsKey(word.length())) {
        					result.get(initial).put(word.length(), new ArrayList<String>());
        				}
        			} else {
        				result.put(initial, new TreeMap<Integer, List<String>>());
        				result.get(initial).put(word.length(), new ArrayList<String>());
        			}
        			result.get(initial).get(word.length()).add(word);
        		}
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }

        return result;
    }

    /**
     * Puzzle 6
     * Given a stream of integers, compute separate sums of the even and odd values
     * in this stream. Since the input is a stream, this necessitates making a single
     * pass over the input.
     */
    public static Map<Boolean, Integer> separateOddEvenSums( IntStream input )
    {

        Map<Boolean, Integer> result = new HashMap<Boolean, Integer>();
        
        result.put(false, 0);
        result.put(true, 0);
        
        input.forEach(new IntConsumer() {
			
			@Override
			public void accept(int value) {
				double remainder = value % 2;
				if (remainder != 0) {
					result.put(true, result.get(true) + value);
				} else {
					result.put(false, result.get(false) + value);
				}
			}
		});

        return result;
    }

    /**
     * Puzzle 7
     * Denormalize this map. The input is a map whose keys are the number of legs of an animal
     * and whose values are lists of names of animals. Run through the map and generate a
     * "denormalized" list of strings describing the animal, with the animal's name separated
     * by a colon from the number of legs it has. The ordering in the output list is not
     * considered significant.
     *
     * Input is Map<Integer, List<String>>:
     *   { 4=["ibex", "hedgehog", "wombat"],
     *     6=["ant", "beetle", "cricket"],
     *     ...
     *   }
     *
     * Output should be a List<String>:
     *   [ "ibex:4",
     *     "hedgehog:4",
     *     "wombat:4",
     *     "ant:6",
     *     "beetle:6",
     *     "cricket:6",
     *     ...
     *   ]
     */
    public static List<String> denormilizeMap( Map<Integer, List<String>> input )
    {
        
        List<String> result = new ArrayList<>();
        
        for (Map.Entry<Integer, List<String>> entry : input.entrySet()) {
        	for (String animalName : entry.getValue()) {
        		result.add(animalName + ":" + entry.getKey());
        	}
        }

        return result;
    }
}
