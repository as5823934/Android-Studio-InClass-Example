package com.example.huntertsai.dictionary_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huntertsai on 2018-01-19.
 */

public class Sense {
    @SerializedName("crossReferenceMarkers")
    private String[] crossReferenceMarkers = null;

//    @SerializedName("crossReferences")
//    private CrossReferencesList crossReferences = null;

    @SerializedName("definitions")
    private String[] definitions = null;

    @SerializedName("domains")
    private String[] domains = null;

    @SerializedName("examples")
    private ExamplesList examples = null;

    @SerializedName("id")
    private String id = null;

    @SerializedName("notes")
    private CategorizedTextList notes = null;

    @SerializedName("pronunciations")
    private PronunciationsList pronunciations = null;

    @SerializedName("regions")
    private String[] regions = null;

    @SerializedName("registers")
    private String[] registers = null;

    @SerializedName("subsenses")
    private List<Sense> subsenses = new ArrayList<Sense>();

    @SerializedName("translations")
    private TranslationsList translations = null;

    @SerializedName("variantForms")
    private VariantFormsList variantForms = null;

    public Sense crossReferenceMarkers(String[] crossReferenceMarkers) {
        this.crossReferenceMarkers = crossReferenceMarkers;
        return this;
    }

    public String[] getDefinitions() {
        return definitions;
    }
}
