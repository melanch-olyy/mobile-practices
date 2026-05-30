package com.mirea.Samsonova.osmmaps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import com.mirea.Samsonova.osmmaps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST = 100;

    private ActivityMainBinding binding;
    private MapView mapView;
    private MyLocationNewOverlay locationNewOverlay;

    private static final double[][] MARKER_COORDS = {
            {55.6697, 37.4821},
            {55.7539, 37.6208},
            {55.7288, 37.6006},
            {55.7415, 37.6208}
    };
    private static final String[] MARKER_TITLES = {
            "РТУ МИРЭА",
            "Красная площадь",
            "Парк Горького",
            "Третьяковская галерея"
    };
    private static final String[] MARKER_DESCRIPTIONS = {
            "Российский технологический университет МИРЭА",
            "Главная площадь России, объект Всемирного наследия ЮНЕСКО",
            "Центральный парк культуры и отдыха имени Горького",
            "Государственная Третьяковская галерея — коллекция русского искусства"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView = binding.mapView;
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(12.0);
        GeoPoint startPoint = new GeoPoint(55.6697, 37.4821);
        mapController.setCenter(startPoint);

        CompassOverlay compassOverlay = new CompassOverlay(getApplicationContext(),
                new InternalCompassOrientationProvider(getApplicationContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        final Context context = getApplicationContext();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mapView.getOverlays().add(scaleBarOverlay);

        addInterestMarkers();

        requestLocationPermission();
    }

    private void addInterestMarkers() {
        for (int i = 0; i < MARKER_TITLES.length; i++) {
            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(MARKER_COORDS[i][0], MARKER_COORDS[i][1]));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setTitle(MARKER_TITLES[i]);
            marker.setSnippet(MARKER_DESCRIPTIONS[i]);

            marker.setIcon(ResourcesCompat.getDrawable(
                    getResources(),
                    org.osmdroid.library.R.drawable.osm_ic_follow_me_on,
                    null));

            final String title = MARKER_TITLES[i];
            final String desc = MARKER_DESCRIPTIONS[i];
            marker.setOnMarkerClickListener((m, mv) -> {
                Toast.makeText(getApplicationContext(),
                        title + "\n" + desc,
                        Toast.LENGTH_SHORT).show();
                m.showInfoWindow();
                return true;
            });

            mapView.getOverlays().add(marker);
        }
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST);
        } else {
            enableMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation();
        }
    }

    private void enableMyLocation() {
        locationNewOverlay = new MyLocationNewOverlay(
                new GpsMyLocationProvider(getApplicationContext()), mapView);
        locationNewOverlay.enableMyLocation();
        mapView.getOverlays().add(locationNewOverlay);
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (mapView != null) {
            mapView.onPause();
        }
    }
}