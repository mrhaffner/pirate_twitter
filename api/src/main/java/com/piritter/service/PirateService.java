package com.piritter.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PirateService {

    Map<String, String> translateTable;
    String[] prefixes = {"Yo ho ho.", "Arrrr.", "Blimey!", "Shiver me timbers."};
    String[] postfixes = {"Savvy?", "Ya scurvy dog!"};   

    public PirateService()  {
        try { // or should we throw it?
            File file = ResourceUtils.getFile("classpath:translations.json");
            translateTable  = new ObjectMapper().readValue(file, 
                                                    new TypeReference<HashMap<String, String>>(){});
        } catch (Exception e) {
            System.out.println("Error loading JSON");
        }
    }
    
    public String translate(String text) {
        String outputText;
        List<String> splitPhrase = splitText(text);
        outputText = splitPhrase.stream()
                                .map(word -> translateWord(word))
                                .reduce("", (word1, word2) -> combineWords(word1, word2));
        return addRandomSaying(outputText);
        // check length, if short enough add begginning/ending phrase
        // max length for tweet???
        // return translatedPhrase;
    }

    private String translateWord(String word) {
        String translatedWord = word.toLowerCase()
                                    .replace("ing", "in'");
        translatedWord = translateTable.containsKey(translatedWord) ? 
                                                translateTable.get(translatedWord) :
                                                translatedWord;
        return translatedWord;
    }

    private List<String> splitText(String text) {
        List<String> splitPhrase = new ArrayList<>();
        for (String word : Arrays.asList(text.split(" "))) {
            String lastChar = String.valueOf(word.charAt(word.length() - 1));
            if (word.length() <= 1) {
                splitPhrase.add(word);
            } else if (lastChar.contentEquals(".") || lastChar.contentEquals("?") || 
                        lastChar.contentEquals("!") || lastChar.contentEquals(",")) {
                splitPhrase.add(word.substring(0, word.length() - 1));
                splitPhrase.add(lastChar);
            } else {
                splitPhrase.add(word);
            }
        }
        return splitPhrase;
    }

    private String combineWords(String word1, String word2) {
        switch (word1) {
            case "":
                return capitalizeFirstChar(word2);
            case ".":
                return word1 + " " + capitalizeFirstChar(word2);
            case "!":
                return word1 + " " + capitalizeFirstChar(word2);
            case "?":
                return word1 + " " + capitalizeFirstChar(word2);
            default:
                break;
        }
        switch (word2) {
            case ",":
                return word1 + word2;
            case ".":
                return word1 + word2;
            case "!":
                return word1 + word2;
            case "?":
                return word1 + word2;
            default:
                return word1 + " " + word2;
        }
    }

    private String capitalizeFirstChar(String word) {
        char fistChar = word.charAt(0);
        return word.replaceFirst(String.valueOf(fistChar), String.valueOf(fistChar)
                                                                 .toUpperCase());
    }

    private String addRandomSaying(String text) {
        int addPhrase = ThreadLocalRandom.current().nextInt(0, 5);
        if (text.length() > 260 || addPhrase > 1) {
            return text;
        }
        int prefixOrPostfix = ThreadLocalRandom.current().nextInt(0, 2);
        if (prefixOrPostfix == 0) {
            int prefixIndex = ThreadLocalRandom.current().nextInt(0, prefixes.length);
            return prefixes[prefixIndex] + " " + text;
        } else {
            int postfixIndex = ThreadLocalRandom.current().nextInt(0, postfixes.length);
            return text + " " + postfixes[postfixIndex] ;
        }
    }
}

// add "arr" randomly or when text allows
//   blimey
// HANDSOMELY
// ho
// scurvy dog
// dear
// treasure
// booty
// buccaneer
// landlubber
// scallywag
// maiden instead of wench
// beer
