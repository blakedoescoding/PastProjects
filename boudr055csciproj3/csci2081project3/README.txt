Written by boudr055



late turning this in because I had some issues with
the aposrophes so I dont know if its in an issue with the encoding or not.
I looked online and the text file is UTF-8 and VS Code terminal is UTF-8 by default so maybe its
my machine or something.

I added in the two UML files because the submission is closed on Canvas

NOTE-------------------------FPR TESTING JUNIT
There are 3 commented out functions,for Add, Delete, Search.
On Intellij I didnt want to change what i had already written so i changed them a bit for testing to work
AS WELL AS THIS: 
String preparedWord;
    String preparedDefinition;
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