package xyz.gonzapico.ontrucktt.di.components;

import dagger.Component;
import xyz.gonzapico.ontrucktt.BaseOTActivity;
import xyz.gonzapico.ontrucktt.ShowLoadsActivity;
import xyz.gonzapico.ontrucktt.ShowUsersActivity;
import xyz.gonzapico.ontrucktt.di.PerActivity;
import xyz.gonzapico.ontrucktt.di.modules.ActivityModule;
import xyz.gonzapico.ontrucktt.di.modules.DatabaseModule;

/**
 * Created by gfernandez on 11/12/16.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, DatabaseModule.class
}) public interface DatabaseComponent {
  void inject(ShowLoadsActivity showLoadsActivity);
  void inject(ShowUsersActivity showUsersActivity);
}
