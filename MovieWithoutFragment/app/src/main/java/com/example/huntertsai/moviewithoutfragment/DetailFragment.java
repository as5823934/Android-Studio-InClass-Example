package com.example.huntertsai.moviewithoutfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Scanner;

public class DetailFragment extends Fragment{
    ImageView imageView;
    TextView textView;
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_detail, container, false);
        imageView = v.findViewById(R.id.detail_img);
        textView = v.findViewById(R.id.detail_text);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            setTxtContent();
        }
    }

    private void setTxtContent() {
        String title = getArguments().getString("title");
        int img_res_id = getResources().getIdentifier(title, "drawable", getActivity().getPackageName());
        int txt_res_id = getResources().getIdentifier(title, "raw", getActivity().getPackageName());

        imageView.setImageResource(img_res_id);

        Scanner scan = new Scanner(getResources().openRawResource(txt_res_id));
        String allText = "";
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            allText = line;
        }
        textView.setText(allText);
        scan.close();
    }

}
