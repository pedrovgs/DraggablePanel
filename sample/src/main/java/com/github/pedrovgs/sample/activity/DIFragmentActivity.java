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
package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.github.pedrovgs.sample.DraggablePanelApplication;

/**
 * Base FragmentActivity created to support dependency injection in activities extending this
 * Activity. This Activity extends from SherlockFragmentActivity to use the support Action Bar. The
 * implementation uses the application class to inject dependencies.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DIFragmentActivity extends SherlockFragmentActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((DraggablePanelApplication) getApplication()).inject(this);
  }
}
