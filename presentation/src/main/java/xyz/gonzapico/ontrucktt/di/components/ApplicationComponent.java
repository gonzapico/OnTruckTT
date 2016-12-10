package xyz.gonzapico.ontrucktt.di.components;

/**
 * Created by gfernandez on 10/12/16.
 */

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import xyz.gonzapico.ontrucktt.BaseOTActivity;
import xyz.gonzapico.ontrucktt.di.modules.ApplicationModule;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  void inject(BaseOTActivity baseOTActivity);

  //Exposed to sub-graphs.
  Context context();
}
