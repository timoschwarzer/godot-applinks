# godot-applinks

This is a simple Godot Android plugin that allows you to read the view intent URL of the main activity. Useful for e.g. opening deep links in your game.

## Installation & Usage

1. Enable (Android Custom Build)[https://docs.godotengine.org/en/stable/getting_started/workflow/export/android_custom_build.html] for your project
2. Grab and extract the latest binary and plugin config from the (releases tab)[https://github.com/timoschwarzer/godot-applinks/releases] into `res://android/plugins/`
3. In your Anndroid export settings, make sure the AppLinks plugin is enabled
4. Add a new Intent Filter to your `res://android/build/AndroidManifest.xml`, e.g.:
   ```xml
   <intent-filter android:autoVerify="true">
       <action android:name="android.intent.action.VIEW" />
       <category android:name="android.intent.category.DEFAULT" />
       <category android:name="android.intent.category.BROWSABLE" />

       <data android:scheme="https"
             android:host="play.doodlepost.net"
             android:pathPattern="/.*" />
   </intent-filter>
   ```

Now you can get the Intent URL using the `AppLinks` singleton when your game starts:

```gdscript
if Engine.has_singleton('AppLinks'):
	var applinks = Engine.get_singleton('AppLinks')
	var app_url = applinks.getUrl()
	# Do something with app_url...
```

Also make sure you re-check the Intent URL when your app is resumed. Deep links will not work when the app is already running in the background otherwise.

```gdscript
func _notification(what):
	if what == NOTIFICATION_APP_RESUMED:
		check_app_link()
```


## Compiling manually

1. Open this project in Android Studio
2. Grab the matching `godot-lib.<version>.<target>.aar` from the (Godot Engine downloads page)[https://godotengine.org/download]
3. Add the downloaded `godot-lib.<version>.<target>.aar` as an Android Library, (see here)[https://developer.android.com/studio/projects/android-library#AddDependency]
4. In Android Studio, click Build > Make Project
5. Once finished, you'll find the generated `aar` file in `./app/build/outputs/aar`
