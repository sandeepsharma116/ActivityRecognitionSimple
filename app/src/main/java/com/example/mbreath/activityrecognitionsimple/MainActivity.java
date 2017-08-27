package com.example.mbreath.activityrecognitionsimple;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG="MainActivity";
    public GoogleApiClient mApiClient;
    public TextView T1,T2,T3,T4,T5,T6,T7,T8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T1 = (TextView) findViewById(R.id.IN_VEHICLE);
        T2 = (TextView) findViewById(R.id.ON_BICYCLE);
        T3 = (TextView) findViewById(R.id.ON_FOOT);
        T4 = (TextView) findViewById(R.id.RUNNING);
        T5 = (TextView) findViewById(R.id.STILL);
        T6 = (TextView) findViewById(R.id.TILTING);
        T7 = (TextView) findViewById(R.id.WALKING);
        T8 = (TextView) findViewById(R.id.UNKNOWN);

        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent(this, ActivityRecognizedService.class);
        PendingIntent pendingIntent =  PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mApiClient, 3000, pendingIntent);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
