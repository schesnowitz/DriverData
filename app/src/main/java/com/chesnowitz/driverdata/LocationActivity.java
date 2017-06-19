package com.chesnowitz.driverdata;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class LocationActivity extends AppCompatActivity {

  LocationListener locationListener;
  LocationManager locationManager;
  Location lastKnownLocation;


  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//    check for location permission

    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//      if this is true this means we have permission

//
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              == PackageManager.PERMISSION_GRANTED)

      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
  }



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);

    locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    locationListener = new LocationListener() {
      @Override
      public void onLocationChanged(Location location) {
        Log.i("Location", location.toString());

      }

      @Override
      public void onStatusChanged(String s, int i, Bundle bundle) {

      }

      @Override
      public void onProviderEnabled(String s) {

      }

      @Override
      public void onProviderDisabled(String s) {

      }
    };

//    if version less than 23
    if (Build.VERSION.SDK_INT < 23) {
      //      we have permission
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    } else {

//    request permission

//    checks to see if we have permission
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              != PackageManager.PERMISSION_GRANTED) {

//    if we do not have permission we need to ask
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

//      above we now create method on request permission results

      } else {
//      we have permission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

//        lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//        if (lastKnownLocation != null) {
//          ParseObject location = new ParseObject("Location");
//          location.put("userEmail", ParseUser.getCurrentUser().getEmail());
//          ParseGeoPoint geoPoint = new ParseGeoPoint(lastKnownLocation.getLatitude(),
//                  lastKnownLocation.getLongitude());
//
//          location.put("coordinates", geoPoint);
//
//        }
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
//      case R.id.action_add:
//        if (user != null && auth != null) {
//          startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
//          finish(); // removes old activities if user goes back
//        }
//        break;
      case R.id.action_signout:
        if (ParseUser.getCurrentUser() != null ) {
          ParseUser.logOut();
          startActivity(new Intent(LocationActivity.this, MainActivity.class));
          finish(); // removes old activities if user goes back
        }
    }
    return super.onOptionsItemSelected(item);
  }
}