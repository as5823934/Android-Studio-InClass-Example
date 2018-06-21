package com.example.huntertsai.dictionary_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huntertsai on 2018-01-19.
 */
public class Entry {
    @SerializedName("etymologies")
    private String[] etymologies = null;

    @SerializedName("grammaticalFeatures")
    private GrammaticalFeaturesList grammaticalFeatures = null;

    @SerializedName("homographNumber")
    private String homographNumber = null;

    @SerializedName("notes")
    private CategorizedTextList notes = null;

    @SerializedName("pronunciations")
    private PronunciationsList pronunciations = null;

    @SerializedName("senses")
    private List<Sense> senses = new ArrayList<Sense>();

    @SerializedName("variantForms")
    private VariantFormsList variantForms = null;

    public Entry etymologies(String[] etymologies) {
        this.etymologies = etymologies;
        return this;
    }

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(List<Sense> senses) {
        this.senses = senses;
    }

    public String[] getEtymologies() {
        return etymologies;
    }


}
