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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.VideoViewModel;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainActivity extends DIFragmentActivity {

  @InjectView(R.id.grid_view) GridView gridView;

  @Inject RendererAdapter<VideoViewModel> adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_grid);
    ButterKnife.inject(this);
    initializeGridView();
  }

  /**
   * Initialize GridView with some injected data and configure OnItemClickListener.
   */
  private void initializeGridView() {
    gridView.setAdapter(adapter);
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> adapterView, View view, int position,
        long id) {

        Intent intent;
        switch (position) {
          case 0:
            intent = new Intent(getApplicationContext(), PlacesSampleActivity.class);
            break;
          case 1:
            intent = new Intent(getApplicationContext(), TvShowsActivity.class);
            break;
          case 2:
            intent = new Intent(getApplicationContext(), YoutubeSampleActivity.class);
            break;
          case 3:
            intent = new Intent(getApplicationContext(), VideoSampleActivity.class);
            break;
          case 4:
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
              Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_LONG).show();
              return;
            }
            intent = new Intent(getApplicationContext(), RtspSampleActivity.class);
            intent.putExtra(RtspSampleActivity.URL, "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov");
            intent.putExtra(RtspSampleActivity.TYPE, "Texture View (RTSP)");
            break;
          case 5:
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
              Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_LONG).show();
              return;
            }
            intent = new Intent(getApplicationContext(), RtspSampleActivity.class);
            intent.putExtra(RtspSampleActivity.URL, "http://techslides.com/demos/sample-videos/small.mp4");
            intent.putExtra(RtspSampleActivity.TYPE, "Texture View (MP4)");
            break;
          default:
            intent = new Intent(getApplicationContext(), VideoSampleActivity.class);
            break;
        }
        startActivity(intent);
      }
    });
  }
}
