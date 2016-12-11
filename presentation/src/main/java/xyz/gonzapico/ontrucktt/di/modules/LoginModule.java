package xyz.gonzapico.ontrucktt.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import xyz.gonzapico.ontrucktt.di.PerActivity;

/**
 * Created by gfernandez on 10/12/16.
 */

@Module public class LoginModule {

  public LoginModule(){

  }

  @Provides @PerActivity FirebaseAuth provideFirebaseAuth() {
    return FirebaseAuth.getInstance();
  }
}
