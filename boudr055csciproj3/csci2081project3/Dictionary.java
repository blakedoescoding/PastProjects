//Written by boudr055

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Dictionary{
    private ArrayList<DictionaryEntry> myMadeDictionary;
    String preparedWord;
    String preparedDefinition;

    public Dictionary() {
        this.myMadeDictionary = new ArrayList<>();
        
    }
    public void prepareAdd(String word, String definition) {
        this.preparedWord = word;
        this.preparedDefinition = definition;
    }
    
    public void prepareDelete(String word) {
        this.preparedWord = word;
    }
    
    public void prepareUpdate(String word, String newDefinition) {
        this.preparedWord = word;
        this.preparedDefinition = newDefinition;
    }

    public DictionaryEntry getEntry(String word) {
        for (DictionaryEntry entry : myMadeDictionary) {
            if (entry.getData()[0].equalsIgnoreCase(word)) {
                return entry;
            }
        }
        return null;
    }

    public void populateDictionary() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the filename:");
            String filename = scanner.nextLine();
            //for some reason there is ?? where there are apostrophes, so I looked it up but I don't know how to fix it
            //I checked the text file on windows and its UTF-8, so I don't know why it's not working
            //Could be just my terminal on VS code but i think vs code is UTF-8 by default
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("-");
                    if (parts.length >= 2) {
                        String word = parts[0].trim();
                        String definition = parts[1].trim();
                        updateEntry(word, definition);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error opening or reading the file. Please try again.");
                continue;
            }
            System.out.println("Do you have more files to enter? (yes/no)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
        myMadeDictionary.sort(null);
    }

    public void printEntries() {
        //prints out the entries in the dictionary
        for (DictionaryEntry entry : myMadeDictionary) {
            System.out.println(entry.getData()[0] + " : " + entry.getData()[1]);
        }
    }

    private void updateEntry(String word, String definition) {
        //updates the entry if it exists, otherwise adds it
        //so if there is duplicates like if you want to add Apple but Apple alread is there
        for (DictionaryEntry entry : myMadeDictionary) {
            if (entry.getData()[0].equals(word)) {
                entry.setData(new String[]{word, definition});
                return;
            }
        }
        myMadeDictionary.add(new DictionaryEntry(word, definition));
    }

    public void search(String input) {
        //searches for the word in the dictionary
        ArrayList<DictionaryEntry> matches = new ArrayList<>();
        for (DictionaryEntry entry : myMadeDictionary) {
            String word = entry.getData()[0];
            String definition = entry.getData()[1];
            if (word.equalsIgnoreCase(input)) {
                System.out.println(word + " : " + definition);
                return;
            } else if (word.toLowerCase().startsWith(input.toLowerCase())) {
                matches.add(entry);
            }
        }
        //if there are multiple matches, print them out
        if (!matches.isEmpty()) {
            for (DictionaryEntry match : matches) {
                System.out.println(match.getData()[0] + " : " + match.getData()[1]);
            }
        } else {
            System.out.println("No match was found.");
        }
    }

    // public String search(String input) {
    //     //searches for the word in the dictionary
    //     ArrayList<DictionaryEntry> matches = new ArrayList<>();
    //     for (DictionaryEntry entry : myMadeDictionary) {
    //         String word = entry.getData()[0];
    //         String definition = entry.getData()[1];
    //         if (word.equalsIgnoreCase(input)) {
    //             return word + " : " + definition;
    //         } else if (word.toLowerCase().startsWith(input.toLowerCase())) {
    //             matches.add(entry);
    //         }
    //     }
    //     if (!matches.isEmpty()) {
    //         StringBuilder result = new StringBuilder();
    //         for (DictionaryEntry match : matches) {
    //             result.append(match.getData()[0]).append(" : ").append(match.getData()[1]).append("\n");
    //         }
    //         return result.toString().trim();
    //     } else {
    //         return "No match was found.";
    //     }
    // }

    public void delete() {
        //deletes the word from the dictionary
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word or partial word you want to delete:");
        String input = scanner.nextLine();
        ArrayList<DictionaryEntry> matches = new ArrayList<>();
        for (DictionaryEntry entry : myMadeDictionary) {
            if (entry.getData()[0].toLowerCase().startsWith(input.toLowerCase())) {
                matches.add(entry);
            }
        }
        //if there are multiple matches, print them out and ask for the exact word
        if (matches.size() > 1) {
            System.out.println("There was more than one possible candidate for removal:");
            for (DictionaryEntry match : matches) {
                System.out.println(match.getData()[0] + " : " + match.getData()[1]);
            }
            //ask for the exact word
            System.out.println("Please enter the exact word you want to delete:");
            String word = scanner.nextLine();
            //if the word is in the list, remove it
            for (DictionaryEntry match : matches) {
                if (match.getData()[0].equalsIgnoreCase(word)) {
                    myMadeDictionary.remove(match);
                    System.out.println("The word has been deleted.");
                    return;
                }
            }
            System.out.println("The word is not in the list of possible candidates.");
        } else if (matches.size() == 1) {
            myMadeDictionary.remove(matches.get(0));
            System.out.println("The word has been deleted.");
        } else {
            System.out.println("No match was found.");
        }
    }

    // public void delete(String word) {
    //     //deletes the word from the dictionary
    //     for (DictionaryEntry entry : myMadeDictionary) {
    //         if (entry.getData()[0].equalsIgnoreCase(word)) {
    //             myMadeDictionary.remove(entry);
    //             System.out.println("The word has been deleted.");
    //             return;
    //         }
    //     }
    //     System.out.println("The word does not exist in the dictionary.");
    // }

    public void add() {
        //adds the word to the dictionary
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word you want to add:");
        String word = scanner.nextLine();
        for (DictionaryEntry entry : myMadeDictionary) {
            if (entry.getData()[0].equalsIgnoreCase(word)) {
                System.out.println("The word already exists in the dictionary.");
                return;
            }
        }
        System.out.println("Enter the definition:");
        String definition = scanner.nextLine();
        myMadeDictionary.add(new DictionaryEntry(word, definition));
        myMadeDictionary.sort(null);
        System.out.println("The word has been added.");
    }

    // public void add(String word, String definition) {
    //     //adds the word to the dictionary
    //     for (DictionaryEntry entry : myMadeDictionary) {
    //         if (entry.getData()[0].equalsIgnoreCase(word)) {
    //             System.out.println("The word already exists in the dictionary.");
    //             return;
    //         }
    //     }
    //     myMadeDictionary.add(new DictionaryEntry(word, definition));
    //     myMadeDictionary.sort(null);
    //     System.out.println("The word has been added.");
    // }

    public void update() {
        //updates the word in the dictionary
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word you want to update:");
        String word = scanner.nextLine();
        for (DictionaryEntry entry : myMadeDictionary) {
            if (entry.getData()[0].equalsIgnoreCase(word)) {
                System.out.println("Enter the new definition:");
                String definition = scanner.nextLine();
                entry.setData(new String[]{word, definition});
                System.out.println("The word has been updated.");
                return;
            }
        }
        System.out.println("The word does not exist in the dictionary.");
    }
    

    public void closeToFile() {
        //closes the dictionary to a file
        //basically its saving it, i had some issues before where it was like overwriting the file to nothing if i was adding words
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the filename to save the dictionary:");
        String filename = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
            for (DictionaryEntry entry : myMadeDictionary) {
                writer.println(entry.getData()[0] + " - " + entry.getData()[1]);
            }
            System.out.println("The dictionary has been saved.");
        } catch (IOException e) {
            System.out.println("Error writing to the file.");
        }
    }


    public void nextCommands(String nextCommandInput){
        //decided to switch case it because it looks cleaner
        switch (nextCommandInput) {
            case "search":
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the word you want to search for:");
                String input = scanner.nextLine();
                search(input);
                break;

            case "add":
                add();
                break;
            
            case "delete":
                //remove case
                delete();
                break;
            
            case "update":
                //update case
                update();
                break;

            case "close":
                //close case
                closeToFile();
                break;
        
            default:
                break;
        }
    }


    public static void main(String[] args) {
        //i have a print statement here for testing at the bottom but i think i'm going to comment it out
        Scanner mainScanner = new Scanner(System.in);
        Dictionary dict = new Dictionary();
        dict.populateDictionary();
        while(true){
            System.out.println("Enter in a command: search, add, delete, close, update, exit");
            String nextCommandInput = mainScanner.nextLine();
            if(nextCommandInput.equals("exit")){
                break;
            }
            
            
            dict.nextCommands(nextCommandInput);
        }
        //For testing 
        // dict.printEntries();
        
    }
}