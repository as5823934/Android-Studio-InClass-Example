package com.example.huntertsai.fragmentsmovie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    ImageView image1_1, image1_2, image1_3,
            image2_1, image2_2, image2_3,
            image3_1, image3_2, image3_3;

    private static final int REQ_CODE_DEF = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ;
        //setTag();
        image1_1 = findViewById(R.id.oneOfone);
        image1_2 = findViewById(R.id.oneOftwo);
        image1_3 = findViewById(R.id.oneOfthree);

        image2_1 = findViewById(R.id.twoOfone);
        image2_2 = findViewById(R.id.twoOftwo);
        image2_3 = findViewById(R.id.twoOfthree);

        image3_1 = findViewById(R.id.threeOfone);
        image3_2 = findViewById(R.id.threeOftwo);
        image3_3 = findViewById(R.id.threeOfthree);
    }

    public void toDetail(View view) {
        if (view.getId() == image1_1.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.catch_me_if_you_can));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.catch_me_if_you_can);
            startActivityForResult(intent, REQ_CODE_DEF);

        } else if (view.getId() == image1_2.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.flight_club));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.flight_club);
            startActivityForResult(intent, REQ_CODE_DEF);

        } else if (view.getId() == image1_3.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.forrest_gump));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.forrest_gump);
            startActivityForResult(intent, REQ_CODE_DEF);

        } else if (view.getId() == image2_1.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.good_will_hunting));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.good_will_hunting);
            startActivityForResult(intent, REQ_CODE_DEF);
        } else if (view.getId() == image2_2.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.pulp_fiction));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.pulp_fiction);
            startActivityForResult(intent, REQ_CODE_DEF);
        } else if (view.getId() == image2_3.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.the_godfather));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.the_godfather);
            startActivityForResult(intent, REQ_CODE_DEF);

        } else if (view.getId() == image3_1.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.the_hangover));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.the_hangover);
            startActivityForResult(intent, REQ_CODE_DEF);

        } else if (view.getId() == image3_2.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.the_shaw_shank_redemption));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.the_shaw_shank_redemption);
            startActivityForResult(intent, REQ_CODE_DEF);
        } else if (view.getId() == image3_3.getId()) {
            String txtData = "";
            Scanner scan = new Scanner(getResources().openRawResource(R.raw.titanic));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                txtData = line;
            }
            scan.close();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("text", txtData);
            intent.putExtra("image", R.drawable.titanic);
            startActivityForResult(intent, REQ_CODE_DEF);
        }
    }

    private void readTxt() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.titanic));
        String txtData = "";
        while (scan.hasNext()) {
            String line = scan.nextLine();
            txtData = line;
            System.out.println(line);
        }

        scan.close();
    }

    private void setTag() {
//        image1_1 = findViewById(R.id.oneOfone);
//        image1_2 = findViewById(R.id.oneOftwo);
//        image1_3 = findViewById(R.id.oneOfthree);
//
//        image2_1 = findViewById(R.id.twoOfone);
//        image2_2 = findViewById(R.id.twoOftwo);
//        image2_3 = findViewById(R.id.twoOfthree);
//
//        image3_1 = findViewById(R.id.threeOfone);
//        image3_2 = findViewById(R.id.threeOftwo);
//        image3_3 = findViewById(R.id.threeOfthree);
//
//
//        image1_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.catch_me_if_you_can));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.catch_me_if_you_can);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//
//        image1_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.flight_club));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.flight_club);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//        image1_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.forrest_gump));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.forrest_gump);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//        image2_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.good_will_hunting));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.good_will_hunting);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//        image2_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.pulp_fiction));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.good_will_hunting);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//        image2_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.the_godfather));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.the_godfather);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//        image3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.the_hangover));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.the_hangover);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//        image3_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.the_shaw_shank_redemption));
//
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.the_shaw_shank_redemption);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//        image3_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txtData = "";
//                Scanner scan = new Scanner(getResources().openRawResource(R.raw.titanic));
//                System.out.println(scan);
//                while (scan.hasNext()){
//                    String line = scan.nextLine();
//                    txtData = line;
//                }
//                scan.close();
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("text", txtData);
//                intent.putExtra("image", R.drawable.titanic);
//                startActivityForResult(intent, REQ_CODE_DEF);
//            }
//        });
//    }
    }
}
