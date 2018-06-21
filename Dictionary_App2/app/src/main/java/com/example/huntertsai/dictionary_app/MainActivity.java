package com.example.huntertsai.dictionary_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.huntertsai.dictionary_app.Api.DictionaryEntriesApi;
import com.example.huntertsai.dictionary_app.model.Entry;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView resultView;
    private EditText searchView;
    private DictionaryEntriesApi entriesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entriesApi = ((SampleApp) getApplication()).apiClient().get(DictionaryEntriesApi.class);

        searchView.findViewById(R.id.search);
        resultView.findViewById(R.id.list);
        resultView.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.search_Btn).setOnClickListener(v -> performSearch(searchView.getText().toString()));


    }

    private void performSearch(final String searchTerm) {
        entriesApi.getDictionaryEntries("en", searchTerm, BuildConfig.APP_ID, BuildConfig.APP_KEY)
                .doOnSubscribe(d -> hideKeyboard())
                .flatMap(re -> Observable.fromIterable(re.getResults()))
                .flatMap(he -> Observable.fromIterable(he.getLexicalEntries()))
                .flatMap(le -> Observable.fromIterable(le.getEntries()).map(e -> new CategorizedEntry(searchTerm, le.getLexicalCategory(), e)))
                .flatMap(ce -> Observable.fromIterable(ce.entry.getSenses()).map(s -> new Definition(ce.category, ce.word, ce.entry, s)))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::createAdapter)
                .subscribe(this::updateRecyclerView);

    }
    @NonNull
    private RVRendererAdapter<Definition> createAdapter(List<Definition> definitions) {
        RendererBuilder<Definition> builder = new RendererBuilder<Definition>()
                .bind(Definition.class, new DefinitionRenderer());
        ListAdapteeCollection<Definition> collection = new ListAdapteeCollection<>(definitions);
        return new RVRendererAdapter<>(builder, collection);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(resultView.getWindowToken(), 0);
    }

    private void updateRecyclerView(RVRendererAdapter<Definition> adapter) {
        if (resultView.getAdapter() != null) {
            resultView.swapAdapter(adapter, true);
        } else {
            resultView.setAdapter(adapter);
        }
    }

    private static class CategorizedEntry {
        final String word;
        final String category;
        final Entry entry;

        CategorizedEntry(String word, String category, Entry entry) {
            this.word = word;
            this.category = category;
            this.entry = entry;
        }
    }



}
