//Written by boudr055

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DictionaryTest {
    private Dictionary dictionary;

    @Before
    public void setUp() {
        dictionary = new Dictionary();
    }

    @Test
    public void testAdd() {
        dictionary.add("word", "definition");
        String[] result = dictionary.getEntry("word").getData();
        assertEquals("word", result[0]);
        assertEquals("definition", result[1]);
    }

    @Test
    public void testDelete() {
        dictionary.add("word", "definition");
        dictionary.delete("word");
        DictionaryEntry result = dictionary.getEntry("word");
        assertNull(result);
    }

    @Test
    public void testSearch() {
        dictionary.add("word", "definition");
        String result = dictionary.search("word");
        assertEquals("word : definition", result);
    }
}