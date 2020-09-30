# Mark_My_Review


Reason: Sole purpose to create this was learning.

Benefits: How we give feedback/reviews in most places is by writing in their feedback book, which is neither a secure record (easily be manipulated), nor convinient 
Here in this app we can simply create our custom QR codes for each place and people can submit reviews as when they want to from wherever they want to, they are not worried of their anonymity.

How to Use:
1. Simply Scan any QRcode by pressing 'Scan' button, and that opens a feedback form created by the organization or company or wherever place your're visiting.
2. If you want to create a feedback form for yourself simply click on 'create' and login with your google account, as your're done with it just press back.
3. now click on 'create QR' button if you want to generate and download QR code for same form you created. 
(QR Image gets downloaded in directory 'Android/data/com.example.markmyreview' )

edit the manifest file as:
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.example.markmyreview">

    <uses-permission android:name="android.permission.INTERNET" /> <!-- to save generated QR Code -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".CreateQR"></activity>
        <activity android:name=".FillFeedback" />
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.journeyapps.barcodescanner.CaptureActivity"
            android:screenOrientation="fullSensor"
            tools:replace="screenOrientation" />
    </application>

</manifest>
```

build.gradle (app)
```
apply plugin: 'com.android.application'

android {
    compileSdkVersion project.androidTargetSdk

    defaultConfig {
        minSdkVersion 24
        targetSdkVersion project.androidTargetSdk
        versionCode 410
        versionName "4.1.0"
    }

    def validConfig
    def keystoreFile
    def keystorePassword
    def keystoreAlias

    try {
        Properties properties = new Properties()
        properties.load(project.rootProject.file('local.properties').newDataInputStream())
        keystoreFile = properties.getProperty('keystore.file')
        keystorePassword = properties.getProperty('keystore.password')
        keystoreAlias = properties.getProperty('keystore.alias')
        validConfig = keystoreFile != null && keystorePassword != null && keystoreAlias != null
    } catch (error) {
        validConfig = false
    }

    if (validConfig) {
        System.out.println("Release signing configured with " + keystoreFile)
        signingConfigs {
            release {
                storeFile project.rootProject.file(keystoreFile)
                storePassword keystorePassword
                keyAlias keystoreAlias
                keyPassword keystorePassword
            }
        }
    } else {
        System.out.println("Specify keystore.file, keystore.alias and keystore.password in local.properties to enable release signing.")
    }

    buildTypes {
        release {
            if (validConfig) {
                signingConfig signingConfigs.release
            }

            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.txt'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}


dependencies {
    // If you use this from an external project, use the following instead:
    //   implementation 'com.journeyapps:zxing-android-embedded:<version>'
    implementation project(':zxing-android-embedded')
    implementation 'androidmads.library.qrgenearator:QRGenearator:1.0.4'
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.legacy:legacy-support-v13:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.1'

    // leakcanary is for development purposes only
    // https://github.com/square/leakcanary
    debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.5'
    releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'

    // AboutLibraries
    implementation "com.mikepenz:aboutlibraries:6.2.3"
}
```

build.gradle (project)
```
buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:4.0.1'
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4'
    }
}

subprojects {
    repositories {
        google()
        jcenter()
        mavenLocal()
    }

    version = '4.1.0'
    group = 'com.journeyapps'

    ext.androidTargetSdk = 28
    ext.zxingCore = 'com.google.zxing:core:3.4.0'
}
```
