package com.example.huntertsai.firebaseinclassdemo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huntertsai on 2018-02-26.
 */

public class ArtistArrayAdapter extends ArrayAdapter<Artist> {

    private Context context;
    private List<Artist> martists;

    public ArtistArrayAdapter(@NonNull Context context, @NonNull List<Artist> objects) {
        super(context, R.layout.artist_list_layout, objects);
        this.context = context;
        this.martists = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Artist artist = martists.get(position);
        String artist_name = artist.getName();
        String artist_genre = artist.getGenre();

        LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
        View listView = layoutInflater.inflate(R.layout.artist_list_layout, null);
        TextView tv_name = listView.findViewById(R.id.artist_name_list);
        TextView tv_genre = listView.findViewById(R.id.artist_genre_list);

        tv_name.setText(artist_name);
        tv_genre.setText(artist_genre);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Track_activity.class);
                String id = artist.getId();
                String name = artist.getName();
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                getContext().startActivity(intent);

            }
        });


        listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit information");

                final EditText editText = new EditText(getContext());
                final Spinner spinner = new Spinner(getContext());
                List<String> list = Arrays.asList(getContext().getResources().getStringArray(R.array.genres));
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);
//
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(editText);
                linearLayout.addView(spinner);

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String modfied_input = editText.getText().toString().trim();
                        String spinner_input = spinner.getSelectedItem().toString();

                        FirebaseDatabase.getInstance().getReference("artists").child(artist.getId()).child("name").setValue(modfied_input);
                        FirebaseDatabase.getInstance().getReference("artists").child(artist.getId()).child("genre").setValue(spinner_input);

                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference("artists").child(artist.getId()).setValue(null);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setView(linearLayout);
                builder.show();


                return true;
            }
        });

        return listView;

    }
}
