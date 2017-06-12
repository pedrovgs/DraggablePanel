Draggable Panel [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pedrovgs/draggablepanel/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pedrovgs/draggablepanel)
===============

**DEPRECATED. This project is not maintained anymore.**

Draggable Panel is an Android library created to build a draggable user interface similar to the new YouTube draggable video component based on Fragments or Views.

This Android library offers you two main classes to use and create your own awesome user interfaces. If you want to use it with fragments add ``DraggablePanel`` to your layout. If you want to use it with views use ``DraggableView`` and put your views inside.

This new component has been created using some concepts described on [Flavien Laurent Blog][1] and [Denevell Blog][2].

To create this library I've used an Android component called [ViewDragHelper][3] and [ViewDragHelper.Calback][4]. This component doesn't have too much documentation and that's the reason why I've added some javadoc to my code in order to clarify the component usage.

This library works on Android 4.X or higher versions but not in lower versions because the scale effect is not going to work properly when the user try to drag the view. The clickable zone on a scaled view in Android 2.X is bigger than the real scaled zone.

Screenshots
-----------

![Demo Screenshot 1][5]
![Demo Screenshot 2][6]
![Demo Screenshot 4][7]
![Demo Screenshot 3][8]


Usage
-----

To use Draggable Panel library and get your new awesome UI working you can use two different Android widgets:

* 1. Add ``DraggablePanel`` widget to your layout. Configure the view customization elements using styleable attributes or programatically and configure your fragments to work as top or bottom fragment inside the ``DraggablePanel`` widget.

```xml
<com.github.pedrovgs.DraggablePanel
        android:id="@+id/draggable_panel"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"/>
```

```java
private void initializeDraggablePanel() throws Resources.NotFoundException {
      draggablePanel.setFragmentManager(getSupportFragmentManager());
      draggablePanel.setTopFragment(placeFragment);
      draggablePanel.setBottomFragment(mapFragment);
      draggablePanel.initializeView();
}
```

* 2. Add ``DraggableView`` widget to your layout. Configure the view to use two children views as the draggable view and the second view. Configure the customization elements using styleable attributes or programatically.

```xml
<com.github.pedrovgs.DraggableView
          xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:draggable_view="http://schemas.android.com/apk/res-auto"
          android:id="@+id/draggable_view"
          android:layout_width="fill_parent"
          android:layout_height="fill_parent"
          draggable_view:top_view_id="@+id/iv_fan_art"
          draggable_view:bottom_view_id="@+id/lv_episodes"
          draggable_view:top_view_x_scale_factor="@dimen/x_scale_factor"
          draggable_view:top_view_y_scale_factor="@dimen/y_scale_factor"
          draggable_view:top_view_margin_right="@dimen/top_fragment_margin"
          draggable_view:top_view_margin_bottom="@dimen/top_fragment_margin"
          android:background="@color/black">

      <!-- ListView Episodes -->

      <ListView
              android:id="@+id/lv_episodes"
              android:layout_width="fill_parent"
              android:layout_height="fill_parent"
              android:layout_below="@+id/iv_fan_art"
              style="@style/episodes_list_view"/>

      <!-- TvShow Fan Art -->

      <ImageView
              android:id="@+id/iv_fan_art"
              android:layout_width="fill_parent"
              android:layout_height="@dimen/tv_show_fan_art_height"
              android:layout_alignParentTop="true"
              style="@style/image_view"/>

</com.github.pedrovgs.DraggableView>
```

**If you are going to use ``DraggablePanel`` or ``DraggableView`` combined with a ``DrawerLayout`` review [Famous Places Sample Activity](https://github.com/pedrovgs/DraggablePanel/blob/develop/sample/src/main/java/com/github/pedrovgs/sample/activity/PlacesSampleActivity.java)

Import DraggablePanel dependency
--------------------------------

Download the project, compile it using maven or gradle and import ``draggablepanel-1.9.aar`` into your project.

Or declare it into your pom.xml. This library uses NineOldAndroid library and Android support library v4 version 19.1.0, you have to provide this dependencies from your local artifact repository or from maven central repository.

```xml
<dependency>
  <groupId>com.github.pedrovgs</groupId>
  <artifactId>draggablepanel</artifactId>
  <version>1.9</version>
  <type>aar</type>
</dependency>
```


Or into your build.gradle

```groovy
dependencies {
    compile 'com.github.pedrovgs:draggablepanel:1.9'
}
```


Customization
-------------

You can customize some of the view effects programatically or using xml styleable attributes. The elements to customize are:

* Draggable view / fragment height.
* Draggable view X scale factor.
* Draggable view Y scale factor.
* Draggable view margin right applied when the view is minimized.
* Draggable view margin bottom applied when the view is minimized.
* Enable or disable the horizontal alpha effect applied while the view is being horizontally dragged.
* Enable or disable touch on minimized/maximized view to minimize/maximize.

```xml
<com.github.pedrovgs.DraggableView
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:draggable_view="http://schemas.android.com/apk/res-auto"
        android:id="@+id/draggable_view"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        draggable_view:top_view_id="@+id/iv_fan_art"
        draggable_view:bottom_view_id="@+id/lv_episodes"
        draggable_view:top_view_x_scale_factor="@dimen/x_scale_factor"
        draggable_view:top_view_y_scale_factor="@dimen/y_scale_factor"
        draggable_view:top_view_margin_right="@dimen/top_fragment_margin"
        draggable_view:top_view_margin_bottom="@dimen/top_fragment_margin"
        draggable_view:enable_click_to_maximize_view="false"
        draggable_view:enable_click_to_minimize_view="true"
        android:background="@color/black">

        <!-- ....... -->

</com.github.pedrovgs.DraggableView>
```

```java
draggablePanel.setTopFragment(placeFragment);
draggablePanel.setBottomFragment(mapFragment);
draggablePanel.setXScaleFactor(xScaleFactor);
draggablePanel.setYScaleFactor(yScaleFactor);
draggablePanel.setTopViewHeight(topViewHeight);
draggablePanel.setTopFragmentMarginRight(topViewMarginRight);
draggablePanel.setTopFragmentMarginBottom(topViewMargnBottom);
draggablePanel.setClickToMaximizeEnabled(enableClickToMaximize);
draggablePanel.setClickToMinimizeEnabled(enableClickToMinimize);
```

Similar customizable attributes are available programatically or using styleable attributes in ``DraggableView``.

Do you want to resize the top view instead of scale it? Add ``dragable_view:top_view_resize`` attribute to your DraggableView:

```xml
<com.github.pedrovgs.DraggableView
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:draggable_view="http://schemas.android.com/apk/res-auto"
            android:id="@+id/draggable_view"
            draggable_view:top_view_resize="true">
```


Do you want to contribute? TODO
-------------------------------

* Support landscape mode when DraggableView is using ResizeTransformer.

Developed By
------------

* Pedro Vicente Gómez Sánchez - <pedrovicente.gomez@gmail.com>

<a href="https://twitter.com/pedro_g_s">
  <img alt="Follow me on Twitter" src="https://image.freepik.com/iconos-gratis/twitter-logo_318-40209.jpg" height="60" width="60"/>
</a>
<a href="https://es.linkedin.com/in/pedrovgs">
  <img alt="Add me to Linkedin" src="https://image.freepik.com/iconos-gratis/boton-del-logotipo-linkedin_318-84979.png" height="60" width="60"/>
</a>

*Does your app use DraggablePanel? If you want to be featured on this list tell me on [Twitter][10]

Libraries used on the sample project
------------------------------------

* [Renderers][11]
* [Dagger][12]
* [Butterknife][13]
* [NineOldAndroids][14]
* [Picasso][15]
* [ActionBarSherlock][16]
* [YouTube Player][17]


License
-------

    Copyright 2014 Pedro Vicente Gómez Sánchez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: http://flavienlaurent.com/blog/2013/08/28/each-navigation-drawer-hides-a-viewdraghelper/
[2]: http://blog.denevell.org/android-viewdraghelper-example-tutorial.html
[3]: http://developer.android.com/reference/android/support/v4/widget/ViewDragHelper.html
[4]: http://developer.android.com/reference/android/support/v4/widget/ViewDragHelper.Callback.html
[5]: ./art/screenshot1.gif
[6]: ./art/screenshot2.gif
[7]: ./art/screenshot3.gif
[8]: ./art/screenshot4.gif
[10]: https://twitter.com/pedro_g_s
[11]: https://github.com/pedrovgs/Renderers
[12]: https://github.com/square/dagger
[13]: https://github.com/JakeWharton/butterknife
[14]: https://github.com/JakeWharton/NineOldAndroids/
[15]: https://github.com/square/picasso
[16]: http://actionbarsherlock.com/
[17]: https://developers.google.com/youtube/android/player/
