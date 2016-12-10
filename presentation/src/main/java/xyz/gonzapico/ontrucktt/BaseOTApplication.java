package xyz.gonzapico.ontrucktt;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by gfernandez on 8/12/16.
 */

public class BaseOTApplication extends Application{

  FirebaseDatabase mFirebaseDatabase = null;

  @Override public void onCreate() {
    super.onCreate();

    mFirebaseDatabase = FirebaseDatabase.getInstance();
  }

  public FirebaseDatabase getFirebaseDatabase(){
    return mFirebaseDatabase;
  }
}
