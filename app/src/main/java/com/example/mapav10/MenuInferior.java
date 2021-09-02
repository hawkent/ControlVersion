package com.example.mapav10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuInferior#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuInferior extends BottomSheetDialogFragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView nombreRest, direci, tel, webBtn;
    private static String restaurante, direc, telf, url;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MenuInferior() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuInferior.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuInferior newInstance(String param1, String param2) {
        MenuInferior fragment = new MenuInferior();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment

        //MapsActivity activity = (MapsActivity) getActivity();
        //String myDataFromActivity = activity.getMyData();
        //Log.v("nombreRes", myDataFromActivity);

        View view = inflater.inflate(R.layout.fragment_menu_inferior, container, false);


        nombreRest = view.findViewById(R.id.nombreRest);
        direci = view.findViewById(R.id.direc);
        webBtn = view.findViewById(R.id.url);
        tel = view.findViewById(R.id.telef);

        nombreRest.setText(restaurante);
        direci.setText(direc);

        tel.setText(telf);

        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://google.es");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        return view;
    }
    public  static void getMyData(String res, String telfs, String urls, String direcc){
        restaurante = res;
        direc = direcc;
        url = urls;
        telf = telfs;
    }



}