/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.sample;

import android.app.Application;
import com.github.pedrovgs.sample.di.MainModule;
import dagger.ObjectGraph;

/**
 * Application implementation created to handle the dependency container implementation provided by
 * the ObjectGraph entity.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DraggablePanelApplication extends Application {

  private ObjectGraph objectGraph;

  /**
   * Override method used to initialize the dependency container graph with the MainModule.
   */
  @Override public void onCreate() {
    super.onCreate();
    MainModule mainModule = new MainModule(this);
    objectGraph = ObjectGraph.create(mainModule);
    objectGraph.inject(this);
    objectGraph.injectStatics();
  }

  /**
   * Inject an object to provide all the needed dependencies.
   */
  public void inject(Object object) {
    objectGraph.inject(object);
  }
}
