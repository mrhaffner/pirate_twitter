package com.piritter.service;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PirateService {

    Map<String, String> translateTable;   

    public PirateService()  {
        try { // or should we throw it?
            File file = ResourceUtils.getFile("classpath:translations.json");
            translateTable  = new ObjectMapper().readValue(file, 
                                                    new TypeReference<HashMap<String, String>>(){});
        } catch (Exception e) {
            System.out.println("Error loading JSON");
        }
    }
    
    public String translate(String englishPhrase) {
        // lower case comparison then readjust capital
        // or capitalize first letter of every sentence
        // how to handle periods/commas?
        List<String> splitPhrase = Arrays.asList(englishPhrase.split(" "));
        splitPhrase = splitPhrase.stream()
                                 .map(word -> word.toLowerCase())
                                 .map(word -> word.replace("ing", "in'"))
                                 .map(word -> translateTable.containsKey(word) ? 
                                                translateTable.get(word) :
                                                word)
                                 .toList();
        String translatedPhrase = String.join(" ", splitPhrase);
        // check length, if short enough add begginning/ending phrase
        // max length for tweet???
        return translatedPhrase;
    }
}

// ing: 'in'
// add "arr" randomly or when text allows
//   blimey
// HANDSOMELY
// ho
// SHIVER ME TIMBERS!
// scurvy dog
// dear
// treasure
// booty
// buccaneer
// landlubber
// blimey
// end with savvy?
// yo ho ho
// scallywag
// maiden instead of wench
// beer
