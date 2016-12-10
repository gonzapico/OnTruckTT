package xyz.gonzapico.ontrucktt.di.modules;

/**
 * Created by gfernandez on 10/12/16.
 */

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import xyz.gonzapico.ontrucktt.BaseOTApplication;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module public class ApplicationModule {
  private final BaseOTApplication application;

  public ApplicationModule(BaseOTApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

}