import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PuzzlesChallengesTest
{
    private BufferedReader reader;

    @BeforeEach
    public void setUp() throws Exception
    {
        reader = Files.newBufferedReader( Paths.get( "src/test/resources/text_file.txt" ).toAbsolutePath(),
                                          StandardCharsets.UTF_8 );
    }


    @AfterEach
    public void closeBufferedReader() throws IOException
    {
        reader.close();
    }


    @Test
    void selectByLengthAndPosition()
    {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        List<String> result = PuzzlesChallenges.selectByLengthAndPosition( input );

        assertEquals(Arrays.asList("alfa", "bravo", "charlie", "delta", "foxtrot"), result);

    }

    @Test
    void selectLongestWords()
    {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        List<String> result = PuzzlesChallenges.selectLongestWords( input );

        assertEquals(Arrays.asList("charlie", "foxtrot"), result);
    }

    @Test
    void sortedLowerCaseDistinctByLengthThenAlphabetically() throws IOException
    {
        List<String> result = PuzzlesChallenges.sortedLowerCaseDistinctByLengthThenAlphabetically( reader );

        assertEquals(
            Arrays.asList(
                "a", "as", "be", "by", "in", "or", "to", "we",
                "and", "art", "bud", "but", "die", "due", "eat", "foe",
                "his", "now", "own", "the", "thy", "too", "bear", "else",
                "eyes", "from", "fuel", "heir", "lies", "only",
                "pity", "rose", "self", "that", "thee", "this", "thou",
                "time", "with", "churl", "cruel", "flame", "fresh", "gaudy",
                "grave", "might", "never", "riper", "sweet", "thine",
                "waste", "where", "world", "bright", "desire", "famine",
                "herald", "mak'st", "making", "memory", "should", "spring",
                "tender", "within", "buriest", "content", "decease",
                "fairest", "feed'st", "glutton", "light's", "thereby", "world's", "beauty's",
                "increase", "ornament", "abundance", "creatures", "contracted", "niggarding",
                "substantial"),
            result);
    }

    @Test
    void getLastWord()
    {
        String result = PuzzlesChallenges.getLastWord( reader );
        assertEquals("thee", result);
    }

    @Test
    void nestedMaps()
    {

        Map<String, Map<Integer, List<String>>> result = PuzzlesChallenges.nestedMaps( reader );

        assertEquals("[abundance]", result.get("a").get(9).toString());
        assertEquals("[by, be, by]", result.get("b").get(2).toString());
        assertEquals("[flame, fresh]", result.get("f").get(5).toString());
        assertEquals("[gaudy, grave]", result.get("g").get(5).toString());
        assertEquals("[should, spring]", result.get("s").get(6).toString());
        assertEquals("[substantial]", result.get("s").get(11).toString());
        assertEquals("[the, thy, thy, thy, too, the, the, thy, the, the, the]",
                     result.get("t").get(3).toString());
        assertEquals("[where, waste, world]", result.get("w").get(5).toString());
    }

    @Test
    void separateOddEvenSums()
    {
        IntStream input = new Random( 987523).ints( 20, 0, 100);

        Map<Boolean, Integer> result = PuzzlesChallenges.separateOddEvenSums( input );

        int sumEvens = result.get(false);
        int sumOdds  = result.get(true);

        assertEquals(516, sumEvens);
        assertEquals(614, sumOdds);
    }

    @Test
    void denormilizeMap()
    {
        Map<Integer, List<String>> input = new HashMap<>();
        input.put(4, Arrays.asList("ibex", "hedgehog", "wombat"));
        input.put(6, Arrays.asList("ant", "beetle", "cricket"));
        input.put(8, Arrays.asList("octopus", "spider", "squid"));
        input.put(10, Arrays.asList("crab", "lobster", "scorpion"));
        input.put(750, Arrays.asList("millipede"));

        List<String> result = PuzzlesChallenges.denormilizeMap( input );

        assertEquals(13, result.size());
        assertTrue(result.contains("ibex:4"));
        assertTrue(result.contains("hedgehog:4"));
        assertTrue(result.contains("wombat:4"));
        assertTrue(result.contains("ant:6"));
        assertTrue(result.contains("beetle:6"));
        assertTrue(result.contains("cricket:6"));
        assertTrue(result.contains("octopus:8"));
        assertTrue(result.contains("spider:8"));
        assertTrue(result.contains("squid:8"));
        assertTrue(result.contains("crab:10"));
        assertTrue(result.contains("lobster:10"));
        assertTrue(result.contains("scorpion:10"));
        assertTrue(result.contains("millipede:750"));
    }

}
