// Shobhit Gupta
// 11/06/2024
// Absurdle
/*
The Absurdle class is a game of absurdle which is similar to wordle. In absurdle the user
is prompted to make a guess and the game changes the list of words to those that match the
pattern. The game continues till the user guesses the word and the program cannot prune the
word dictionary any further.
*/

import java.util.*;
import java.io.*;

public class Absurdle  {
    public static final String GREEN = "🟩";
    public static final String YELLOW = "🟨";
    public static final String GRAY = "⬜";

    // [[ ALL OF MAIN PROVIDED ]]
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the game of Absurdle.");

        System.out.print("What dictionary would you like to use? ");
        String dictName = console.next();

        System.out.print("What length word would you like to guess? ");
        int wordLength = console.nextInt();

        List<String> contents = loadFile(new Scanner(new File(dictName)));
        Set<String> words = pruneDictionary(contents, wordLength);

        List<String> guessedPatterns = new ArrayList<>();
        while (!isFinished(guessedPatterns)) {
            System.out.print("> ");
            String guess = console.next();
            String pattern = recordGuess(guess, words, wordLength);
            guessedPatterns.add(pattern);
            System.out.println(": " + pattern);
            System.out.println();
        }
        System.out.println("Absurdle " + guessedPatterns.size() + "/∞");
        System.out.println();
        printPatterns(guessedPatterns);
    }

    // [[ PROVIDED ]]
    // Prints out the given list of patterns.
    // - List<String> patterns: list of patterns from the game
    public static void printPatterns(List<String> patterns) {
        for (String pattern : patterns) {
            System.out.println(pattern);
        }
    }

    // [[ PROVIDED ]]
    // Returns true if the game is finished, meaning the user guessed the word. Returns
    // false otherwise.
    // - List<String> patterns: list of patterns from the game
    public static boolean isFinished(List<String> patterns) {
        if (patterns.isEmpty()) {
            return false;
        }
        String lastPattern = patterns.get(patterns.size() - 1);
        return !lastPattern.contains("⬜") && !lastPattern.contains("🟨");
    }

    // [[ PROVIDED ]]
    // Loads the contents of a given file Scanner into a List<String> and returns it.
    // - Scanner dictScan: contains file contents
    public static List<String> loadFile(Scanner dictScan) {
        List<String> contents = new ArrayList<>();
        while (dictScan.hasNext()) {
            contents.add(dictScan.next());
        }
        return contents;
    }

    /*
    Behavior:
        This method adds all the possible words in a set that equals the word length from a
        given list which consists of words.
    Exeptions:
        This method uses an IllegalArgumentException which is fired when the word length is
        less than 1 character long.
    Returns:
        This method returns a set which consists of all the possible words sorted alphabetically
        that the user can guess.
    Parameter:
        This method takes a string array list which contains all the words from the text file.
        The method also takes an int wordLength as parameter which is the length of word that
        the user will guess for.
    */

    public static Set<String> pruneDictionary(List<String> contents, int wordLength) {

        if(wordLength < 1){
            throw new IllegalArgumentException();
        }

        Set<String> sortedWords = new TreeSet<>();
        for(int i = 0; i < contents.size(); i++){
            String word = contents.get(i);
            if(word.length() == wordLength){
                sortedWords.add(word);
            }
        }

        return sortedWords;
    }

    /*
    Behavior:
        This method compares and finds the pattern for each word in the set compared to the
        guess. Then for each unique pattern the method adds all the words with the same pattern
        into a map.
    Exeptions:
        This method uses an IllegalArgumentException, which is fired if the length of guess is
        not equal to the length of words in the set that is being comapred to. The expection is
        also fired if the words set is empty.
    Returns:
        This method returns a string, which is the pattern that contains the most amount of
        possible words.
    Parameter:
        This method takes string guess as paramater which is the string that will be compared.
        The method also takes a set of strings as parameter which consists of all the possible
        words. The method also takes int wordLength into parameter which tells the program for
        which word length the user wants to guess and to make sure that length of guess is valid.
    */

    public static String recordGuess(String guess, Set<String> words, int wordLength){

        if(!(guess.length() == wordLength) || words.isEmpty()){
            throw new IllegalArgumentException();
        }

        Map<String, Set<String>> recordedGuessMap = new TreeMap<>();
        Iterator<String> iter = words.iterator();
        while(iter.hasNext()){
            String word = iter.next();
            Set<String> guessSet = new HashSet<>();
            String pattern = patternFor(word, guess);
            if(!recordedGuessMap.containsKey(pattern)){
                guessSet.add(word);
                recordedGuessMap.put(pattern, guessSet);
            }else{
                recordedGuessMap.get(pattern).add(word);
            }
        }

        String patternSet = findTargetPattern(recordedGuessMap, words);

        return patternSet;
    }

    /*
    Behavior:
        This method traveres through a map to find which pattern consists the most amount of
        possible target words. The method then updates the words set to include the new set.
    Exeptions:
        There are no expections in this method.
    Returns:
        This method returns the string pattern which contains the largest possible set of
        target words.
    Parameter:
        This method takes a map of a key string and value of a set as parameter. This map
        is used to find the pattern. The method also takes into parameter a string set which
        is used to modify the set to contain the new set of target words.
    */

    public static String findTargetPattern(Map<String, Set<String>> recordedGuessMap,
                                                    Set<String> words){

        int patternSize = 0;
        String patternSet = "";
        for(String patternKey : recordedGuessMap.keySet()){
            int setSize = recordedGuessMap.get(patternKey).size();
            if(setSize > patternSize){
                patternSize = setSize;
                patternSet = patternKey;
            }
        }

        words.clear();
        words.addAll(recordedGuessMap.get(patternSet));

        return patternSet;
    }

    /*
    Behavior:
        This method compares the target word and guess word and creates the pattern
        of how similar the guess word is to the target word.
    Exeptions:
        There are no expections in this method.
    Returns:
        This method returns the String pattern which is the comparison of how
        similar guess word is to the target word.
    Parameter:
        This method take string word as parameter which is the target word and takes string
        guess which is the guess word that will be compared.
    */

    public static String patternFor(String word, String guess) {

        List<String> targetWordList = new ArrayList<>();
        Map<Character, Integer> countGuessMap = new TreeMap<>();
        for(int i = 0; i < guess.length(); i++){ //adds the char to list
            targetWordList.add(guess.charAt(i) + "");
        }

        for(int i = 0; i < guess.length(); i++){// adds to map with associated values and count
            int count = 0;
            for(int j = 0; j < guess.length(); j++){
                if(guess.charAt(i) == guess.charAt(j)){
                    count++;
                }
            }
            countGuessMap.put(guess.charAt(i), count);
        }

        for(int i = 0; i < guess.length(); i++){//updates list with green and changes map values
            if(guess.charAt(i) == word.charAt(i)){
                targetWordList.set(i, GREEN);
                int value = countGuessMap.get(guess.charAt(i)) - 1;
                countGuessMap.put(guess.charAt(i), value);
            }
        }

        for(int i = 0; i < word.length(); i++){//updates list with yellow and changes map values
            if(countGuessMap.containsKey(word.charAt(i)) && (countGuessMap.get(word.charAt(i))>0)
                             && (!targetWordList.get(i).equals(GREEN))){
                int value = countGuessMap.get(word.charAt(i)) - 1;
                countGuessMap.put(word.charAt(i), value);
                int index = targetWordList.indexOf(word.charAt(i) + "");
                targetWordList.set(index, YELLOW);
            }
        }

        for(int i = 0; i < targetWordList.size(); i++){
            String a = targetWordList.get(i);
            if(!a.equals(GREEN) && !a.equals(YELLOW)){
                targetWordList.set(i, GRAY);
            }
        }

        String output = "";
        for(int i = 0; i < targetWordList.size(); i++){
            output += targetWordList.get(i);
        }

        return output;
    }
}