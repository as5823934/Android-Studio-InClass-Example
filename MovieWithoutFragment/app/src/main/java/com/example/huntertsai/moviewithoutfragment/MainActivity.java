package com.example.huntertsai.moviewithoutfragment;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements MovieFragment.setMovieTitle {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
           FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.movie_frag);

            if (fragment == null) {
                fragment = new MovieFragment();
                fm.beginTransaction().add(R.id.movie_frag, fragment).commit();
            }
        }
    }

    @Override
    public void onSendMovie(String title) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("title", title);
            startActivity(intent);

        }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            
            DetailFragment fragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.detail_container, fragment,null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }



    //    private void setImgContent() {
//        GridLayout gridLayout = findViewById(R.id.grid_movies);
//        for (int i = 0; i < gridLayout.getChildCount(); i++){
//            ImageButton imageButton = (ImageButton) gridLayout.getChildAt(i);
//            imageButton.setOnClickListener(this);
//        }
//    }


//    @Override
//    public void onClick(View view) {
//        String imageTag = view.getTag().toString();
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra("title", imageTag);
//        startActivity(intent);
//    }
//
//    private void setTxtContent() {
//        String title = getIntent().getStringExtra("title");
//
//        int img_res_id = getResources().getIdentifier(title, "drawable", getPackageName());
//        int txt_res_id = getResources().getIdentifier(title, "raw", getPackageName());
//
//        ImageView imageView;
//        TextView textView;
//        imageView = findViewById(R.id.detail_img);
//        imageView.setImageResource(img_res_id);
//
//        Scanner scan = new Scanner(getResources().openRawResource(txt_res_id));
//        String allText = "";
//        while (scan.hasNextLine()){
//            String line = scan.nextLine();
//            allText = line;
//        }
//
//        textView = findViewById(R.id.detail_text);
//        textView.setText(allText);
//        scan.close();
//    }
}
