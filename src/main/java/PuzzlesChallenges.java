import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
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
        //TODO: Add your code here
        List<String> result = null;

        return result;
    }

    /**
     * Puzzle 2
     * Select the longest words from the input list. That is, select the words
     * whose lengths are equal to the maximum word length.
     */
    public static List<String> selectLongestWords( List<String> input )
    {

        //TODO: Add your code here
        List<String> result = null;


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

        //TODO: Add your code here
        List<String> result = null;

        return result;
    }

     /**
      * Puzzle 4
      * Get the last word in the text file
      */
     public static String getLastWord( BufferedReader reader )
     {
         //TODO: Add your code here
         String result = null;

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

        //TODO: Ad your code here
        Map<String, Map<Integer, List<String>>> result = null;


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
        //TODO: Add your code here
        Map<Boolean, Integer> result = null;

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
        //TODO:: Add your code here
        List<String> result = new ArrayList<>();

                return result;
    }
}
