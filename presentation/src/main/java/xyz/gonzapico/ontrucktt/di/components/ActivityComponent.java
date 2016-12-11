package xyz.gonzapico.ontrucktt.di.components;

import android.app.Activity;
import dagger.Component;
import xyz.gonzapico.ontrucktt.di.PerActivity;
import xyz.gonzapico.ontrucktt.di.modules.ActivityModule;

/**
 * Created by gfernandez on 10/12/16.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  //Exposed to sub-graphs.
  Activity activity();
}
