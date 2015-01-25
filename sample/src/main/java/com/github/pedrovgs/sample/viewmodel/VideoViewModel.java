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
package com.github.pedrovgs.sample.viewmodel;

/**
 * VideoViewModel implementation created to contain all the video information and to keep all
 * the representation state of a video item.
 *
 * @author Pedro Paulo de Amorim.
 */
public class VideoViewModel {

    private final int title;
    private final int image;

    public VideoViewModel(int title, int image) {
        this.title = title;
        this.image = image;
    }

    public int getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

}
