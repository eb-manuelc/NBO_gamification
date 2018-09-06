package com.example.manuelc.nbo_gamification.game;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;


import java.io.IOException;
import java.security.Policy;

public class FindItActivity extends AppCompatActivity {

    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int  MY_PERMISSION_REQUEST_CAMERA  = 1;
    private String token = "";
    private String tokenanterior = "";
    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_it);
        cameraView = findViewById(R.id.camera_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initQR();
    }

    public void initQR(){

        //Crear el detector QR - BarcodeDetector permite reconocer còdigos de barra y còdigos QR
        final BarcodeDetector barcodeDetector= new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        //Crear la camara fuente - Camera Source permite obtener frames de la camara del dispositivo y posteriormente analizarlo
        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setRequestedPreviewSize(700, 500)
                .setAutoFocusEnabled(true)
                .build();


        //Listener del ciclo de vida de la camara
        this.cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //Verificar si el usuario dio permisos para la camara
                if(ActivityCompat.checkSelfPermission(FindItActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA));
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSION_REQUEST_CAMERA);
                    }
                    return;
                }else{
                    try{
                        cameraView.setRotation(180);
                        cameraSource.start(cameraView.getHolder());
                    }catch (IOException ie){
                        Log.e("CAMERA SOURCE", ie.getMessage());
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if(barcodes.size() != 0){

                    token = barcodes.valueAt(0).displayValue.toString();

                    if(!token.equals(tokenanterior)){

                        tokenanterior = token;
                        Log.i("Token", token);



                       if(token.equals("lollapalooza")){
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   Toast.makeText( getApplicationContext(),"Correct! +5 points!!  ",Toast.LENGTH_LONG).show();
                                   finish();
                               }
                           });


                        //if(URLUtil.isValidUrl(token)){
                           //Si es una URL valida abre el navegador
                          //  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(token));
                          //  startActivity(browserIntent);
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Ups, Not lollapalooza. Try again.",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                synchronized (this){
                                    wait(5000);
                                    //limpiar el token
                                    tokenanterior="";
                                }

                            }catch (InterruptedException e){
                                Log.e("Error", "Waiting didn't work!");
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }


        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_it, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_find_it, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
           return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
