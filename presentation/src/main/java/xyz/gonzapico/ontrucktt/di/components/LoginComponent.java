package xyz.gonzapico.ontrucktt.di.components;

import dagger.Component;
import xyz.gonzapico.ontrucktt.LoginActivity;
import xyz.gonzapico.ontrucktt.di.PerActivity;
import xyz.gonzapico.ontrucktt.di.modules.ActivityModule;
import xyz.gonzapico.ontrucktt.di.modules.LoginModule;

/**
 * Created by gfernandez on 10/12/16.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, LoginModule.class
}) public interface LoginComponent {
  void inject(LoginActivity loginActivity);
}
