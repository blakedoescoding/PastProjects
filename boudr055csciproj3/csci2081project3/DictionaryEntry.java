//Written by boudr055

import java.util.Comparator;
public class DictionaryEntry implements Comparable {

    // instance variables
    private String wordOrPhrase;
	private String definition;

    // constructors
    public DictionaryEntry() {}

    public DictionaryEntry(String wop, String def) {
        this.wordOrPhrase = wop;
		this.definition = def;
    }

	// You are free to implement other constructors as you see fit

    // You should implement at least the getters and setters below
	
	// returns a String array with the wordOrPhrase at location zero and definition 
	// at location 1
    public String [] getData() {
        String [] data = new String[2];
        data[0] = this.wordOrPhrase;
        data[1] = this.definition;
        return data;
    }

	// accepts a String array with the wordOrPhrase at location zero and the definition
	// of the wordOrPhrase at location 1 and sets the variables accordingly
    public void setData(String [] data) {
        if (data.length >= 2){
            this.wordOrPhrase = data[0];
            this.definition = data[1];  
        }
    }
	
	// Define a Comparator method that can be used to sort an ArrayList of Nodes in Lexically
	// Ascending order - that is, from A to Z, according to the wordOrPhrase
	// Note that you must keep the exact method signature provided here
	
	@Override
	public int compareTo(Object o) {
        if (o instanceof DictionaryEntry){
            DictionaryEntry other = (DictionaryEntry) o;
            return this.wordOrPhrase.compareTo(other.wordOrPhrase);
        }
        else{
            throw new IllegalArgumentException("Object is not a DictionaryEntry");
        }
    }
}