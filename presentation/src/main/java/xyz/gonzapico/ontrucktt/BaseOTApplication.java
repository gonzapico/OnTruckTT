package xyz.gonzapico.ontrucktt;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;
import xyz.gonzapico.ontrucktt.di.components.ApplicationComponent;
import xyz.gonzapico.ontrucktt.di.components.DaggerApplicationComponent;
import xyz.gonzapico.ontrucktt.di.modules.ApplicationModule;

/**
 * Created by gfernandez on 8/12/16.
 */

public class BaseOTApplication extends Application{

  private FirebaseDatabase mFirebaseDatabase = null;
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    this.initializeInjector();
    mFirebaseDatabase = FirebaseDatabase.getInstance();
  }

  public FirebaseDatabase getFirebaseDatabase(){
    return mFirebaseDatabase;
  }

  private void initializeInjector() {
    this.applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

}
