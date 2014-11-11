package com.haopu.JSGame;

import android.app.Application;

/**
 * Created by afwang on 13-9-17.
 */
public class CmgameApplication extends Application {
  public void onCreate() {
    System.loadLibrary("megjb");
  }
}
