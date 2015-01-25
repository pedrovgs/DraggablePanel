/*
 * Copyright (C) 2015 Pedro Paulo de Amorim.
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

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Surface;
import android.view.TextureView;

import android.widget.ImageView;
import com.github.pedrovgs.DraggableView;
import com.github.pedrovgs.sample.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.picasso.Picasso;

/**
 * Sample activity created to show a RTSP video (Real Time Streaming Player) and MP4 with a TextureView and
 * MediaPlayer to stream the video
 *
 * @author Pedro Paulo de Amorim
 */
@TargetApi(14)
public class RtspSampleActivity extends FragmentActivity implements TextureView.SurfaceTextureListener {

  public static final String URL_IMAGE = "http://upload.wikimedia.org/wikipedia/commons/c/c5/Big_buck_bunny_poster_big.jpg";

  public static final String URL = "url";
  public static final String TYPE = "type";

  @InjectView(R.id.background_image) ImageView backgroundImage;
  @InjectView(R.id.draggable_view) DraggableView draggableView;
  @InjectView(R.id.texture_view) TextureView textureView;

  private MediaPlayer mediaPlayer;
  private String fileName;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_texture_view_sample);
    ButterKnife.inject(this);
    initFileName();
    configActionBarName();
    initMediaPlayer();
    initTextureView();
    loadBackgroundImage();
  }

  @Override protected void onDestroy() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.release();
      mediaPlayer = null;
    }
    super.onDestroy();
  }

  private void initFileName() {
    fileName = getIntent().getExtras().getString(URL);
  }

  private void configActionBarName() {
    getActionBar().setTitle(getIntent().getExtras().getString(TYPE));
  }

  private void initMediaPlayer() {
    mediaPlayer = new MediaPlayer();
  }

  private void initTextureView() {
    textureView.setSurfaceTextureListener(this);
  }

  private void loadBackgroundImage() {
    Picasso.with(this).load(URL_IMAGE).into(backgroundImage);
  }

  @Override public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
    Surface surface = new Surface(surfaceTexture);
    try {
      mediaPlayer.setDataSource(fileName);
      mediaPlayer.setSurface(surface);
      mediaPlayer.prepareAsync();
      // Play video when the media source is ready for playback.
      mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
          mediaPlayer.start();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {

  }

  @Override public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
    return false;
  }

  @Override public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

  }
}
