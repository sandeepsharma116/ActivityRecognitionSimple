package com.example.mbreath.activityrecognitionsimple;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

/**
 * Created by mbreath on 26/08/17.
 */

class ActivityRecognizedService extends IntentService {

//    public TextView T1,T2,T3,T4,T5,T6,T7,T8;
    private static final String TAG = "ActivityRecognizedServi";
    public ActivityRecognizedService(){
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name){
        super(name);
    }

//    @Override
//    public void onCreate() {
//        T1.findViewById(R.id.IN_VEHICLE);
//        T2.findViewById(R.id.ON_BICYCLE);
//        T3.findViewById(R.id.ON_FOOT);
//        T4.findViewById(R.id.RUNNING);
//        T5.findViewById(R.id.STILL);
//        T6.findViewById(R.id.TILTING);
//        T7.findViewById(R.id.WALKING);
//        T8.findViewById(R.id.UNKNOWN);
//    }

    @Override
    protected void onHandleIntent( Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());

        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    Log.d(TAG, "In Vehicle: " + activity.getConfidence());
//                    T1.setText("In Vehicle: "+activity.getConfidence());
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.d(TAG, "On Bicycle: " + activity.getConfidence() );
//                    T2.setText("On Bicycle: "+ activity.getConfidence());
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    Log.e( "ActivityRecognition", "On Foot: " + activity.getConfidence() );
//                    T3.setText( "On Foot: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.RUNNING: {
                    Log.e( "ActivityRecognition", "Running: " + activity.getConfidence() );
//                    T4.setText( "Running: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e( "ActivityRecognition", "Still: " + activity.getConfidence() );
//                    T5.setText( "Still: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );
//                    T6.setText( "Tilting: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
//                    T7.setText(  "Walking: " + activity.getConfidence() );

                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
//                    T8.setText(  "Unknown: " + activity.getConfidence() );
                    break;
                }
            }
        }
    }
}