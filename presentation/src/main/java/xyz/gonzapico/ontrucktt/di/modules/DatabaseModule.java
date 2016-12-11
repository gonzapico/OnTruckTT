package xyz.gonzapico.ontrucktt.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;
import xyz.gonzapico.ontrucktt.di.PerActivity;

/**
 * Created by gfernandez on 11/12/16.
 */

@Module public class DatabaseModule {

  public DatabaseModule(){

  }

  @Provides @PerActivity FirebaseDatabase provideFirebaseDatabase() {
    return FirebaseDatabase.getInstance();
  }
}
