package com.megafone.PhoneInformation;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class BaseApplication extends Application {

	private static Context mContext;
	private static Thread mThread;
	private static int mTid;
	private static Looper mLooper;
	private static Handler handler;
	
	
	public static Context getContext() {
		return mContext;
	}

	public static Thread getThread() {
		return mThread;
	}

	public static int getTid() {
		return mTid;
	}

	public static Looper getMainThreadLooper() {
		return mLooper;
	}

	public static Handler getHandler() {
		return handler;
	}


	@Override
	public void onCreate() {
		mContext = getApplicationContext();
		mThread = Thread.currentThread();
		mTid = android.os.Process.myTid();
		mLooper = getMainLooper();
		handler = new Handler();
		super.onCreate();
	}
}
