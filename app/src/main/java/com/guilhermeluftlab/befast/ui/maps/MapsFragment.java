package com.guilhermeluftlab.befast.ui.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.guilhermeluftlab.befast.LoginActivity;
import com.guilhermeluftlab.befast.R;
import com.guilhermeluftlab.befast.async.ServicosMaps;
import com.guilhermeluftlab.befast.models.Endereco;
import com.guilhermeluftlab.befast.models.Usuario;
import com.guilhermeluftlab.befast.ui.servUsuerMap.ServicoUserMapFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MapsFragment extends Fragment {

    Location currentLocation;
    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;
    List<Usuario> usuarios;
    ViewGroup group;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        group = container;
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);


        client = LocationServices.getFusedLocationProviderClient(getContext());

        //Check permission
        getCurrentLocation();




    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                LatLng latLng;
                                MarkerOptions options;

                                latLng = new LatLng(location.getLatitude(),
                                        location.getLongitude());
                                //Marker
                                options = new MarkerOptions().position(latLng)
                                        .title("Meu Local");



                                //Zoom
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                                new ServicosMaps(MapsFragment.this).execute();

                                //Add on map
                                googleMap.addMarker(options);


                                //Teste
                                latLng = new LatLng(-4.965891, -39.034298);
                                options = new MarkerOptions().position(latLng).title("Serviço Teste");
                                googleMap.addMarker(options);


                                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    @Override
                                    public boolean onMarkerClick(Marker marker) {
                                        ServicoUserMapFragment servicoUserMapFragment = new ServicoUserMapFragment();
                                        servicoUserMapFragment.show(getActivity().getSupportFragmentManager(), "Servicos Map");

                                       return false;
                                    }
                                });


                            }
                        });
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    public void addServicosMapa() {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        for(Usuario user : usuarios) {

          try{
              List<Address> addresses = geocoder.getFromLocationName(user.getEndereco().getLogradouro(), 1);
              if(addresses.size() > 0){
                  Address address = addresses.get(0);
                  LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                  MarkerOptions options = new MarkerOptions().position(latLng)
                          .title("Servicos");
              }


          } catch (IOException ioException) {
              ioException.printStackTrace();
          }


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarios = new ArrayList<Usuario>();
    }

    public void addUsuario(Usuario usuario){
        usuarios.add(usuario);
    }


}

