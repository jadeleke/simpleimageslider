# simpleimageslider
This is simple way how to implement Image slider with swipes, Here we used a volley to get a JSON and load the URL's into the images from the internet.
It doesn't have customized description fonts.

Thanks to [AndroidImageSlider] , you can load images from url, drawable or file.
Kindly use the following link to use this library:
In build.gradle (Project)
```
allprojects {
  repositories {
		maven { url "https://jitpack.io" }
	}
}
```
And then add this below to your other gradle file
```
dependencies {
	implementation 'com.github.myinnos:ImageSliderWithSwipes:1.0.2'
}
```



###
[AndroidImageSlider]:https://github.com/daimajia/AndroidImageSlider
