package com.example.huntertsai.dictionary_app;

import com.example.huntertsai.dictionary_app.model.Entry;
import com.example.huntertsai.dictionary_app.model.Sense;

/**
 * Created by huntertsai on 2018-01-19.
 */

public class Definition {
    private final String category;
    private final String word;
    private final String etymology;
    private final String definiton;

    public Definition(String category, String word, Entry entry, Sense s) {
        this.category = category;
        this.word = word;

        String [] etymologies =entry.getEtymologies();
        this.etymology = etymologies != null && etymologies.length > 0 ? etymologies[0] : null;

        String[] definitions = s.getDefinitions();
        this.definiton = definitions != null && definitions.length > 0 ? definitions[0] : null;
    }


    public String getCategory() {
        return category;
    }

    public String getWord() {
        return word;
    }

    public String getEtymology() {
        return etymology;
    }

    public String getDefiniton() {
        return definiton;
    }
}
