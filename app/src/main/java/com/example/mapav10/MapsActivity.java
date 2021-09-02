package com.example.mapav10;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapav10.databinding.ActivityMapsBinding;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    double id, id2;
    double latitud, latitud2;
    double longitud, longitud2;
    String url, url2;
    String nombre, nombre2, title;
    String telefono, telefono2;
    String direccion, direccion2;
    JSONArray coordenadasArray, coordenadasArray2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    try {
        CargarRestaurantes();
    } catch (IOException e) {

        e.printStackTrace();
    }

}

private void CargarRestaurantes() throws IOException {

    URL urlws = new URL("https://raw.githubusercontent.com/zodiacodam/food/main/MapInfo.json");
    URLConnection uc = urlws.openConnection();
    uc.connect();
    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
    String inputLine = "";
    String contenido = "";

    while ((inputLine = in.readLine()) != null) {
        contenido += inputLine + "\n";
    }

    in.close();

    Log.v("--Array entero de markers",contenido);

    //Enviar el String al JSON

    try {
        JSONObject jsonObject = new JSONObject(contenido);
         coordenadasArray = jsonObject.getJSONArray("coordinates");


        for(int i=0; i<coordenadasArray.length();i++){

            JSONArray restaurantesArray = coordenadasArray.getJSONArray(i);

             id = restaurantesArray.getDouble(0);
             latitud = restaurantesArray.getDouble(2);
             longitud = restaurantesArray.getDouble(3);
             url = restaurantesArray.getString(4);
             nombre = restaurantesArray.getString(1);
             telefono = restaurantesArray.getString(5);
             direccion = restaurantesArray.getString(6);

            Log.v("--Recorriendo array","latitude = "+latitud+", long = "+longitud);
            // marcador.remove();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(latitud,longitud)).title(nombre);
            mMap.addMarker(markerOptions);
        }

    } catch (JSONException e) {
        Log.v("--Error try catch",e.getMessage());
    }
    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
           title = marker.getTitle();
           try {
                for(int i=0; i<coordenadasArray.length();i++){

                    JSONArray restaurantesArray = coordenadasArray.getJSONArray(i);

                    id2 = restaurantesArray.getDouble(0);
                    latitud2 = restaurantesArray.getDouble(2);
                    longitud2 = restaurantesArray.getDouble(3);
                    url2 = restaurantesArray.getString(4);
                    nombre2 = restaurantesArray.getString(1);
                    telefono2 = restaurantesArray.getString(5);
                    direccion2 = restaurantesArray.getString(6);


                    Log.v("--ttttttttttttttttttttttttttt",nombre2);
                    Log.v("--ttttttttttttttttttttttttttt2",title);

                    if (nombre2.equals(title) ){
                        Log.v("--tttttttttttttttttttt3333333",nombre2);
                        MenuInferior.getMyData(nombre2,telefono2,url2,direccion2);
                        Log.v("--Marker pulsado", marker.getTitle());
                        break;
                    }

                    Log.v("--Recorriendo array para Menu","latitude = "+latitud2+", long = "+longitud2);
                    // marcador.remove();


                    Log.v("--ttttttttttttttttttttttttttt",nombre2);
                    Log.v("--ttttttttttttttttttttttttttt2",title);



                }
            } catch (JSONException e) {
                Log.v("--Error try catch",e.getMessage());
            }






            MenuInferior menuInferior = new MenuInferior();
            menuInferior.show(getSupportFragmentManager(), menuInferior.getTag());
           // MenuInferior.cambiarNombre(marker.getTitle().toString());

            return false;
        }
    });



}






}