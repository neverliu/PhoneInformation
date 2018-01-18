package com.megafone.PhoneInformation.tools;

import com.megafone.PhoneInformation.BaseApplication;

import android.content.Context;
import android.content.res.Resources;


public class UIUtils {
	public static Context getContext(){
		return BaseApplication.getContext();
	}
	public static Resources getResource(){
		return getContext().getResources();
	}
	public static String getString(int resId){
		return getResource().getString(resId);
	}
	public static String[] getStringArr(int resId){
		return getResource().getStringArray(resId);
	}
	public static int getColor(int colorId) {
		return getResource().getColor(colorId);
	}
	public static String getPackageName() {
		return getContext().getPackageName();
	}
	
	
	public static void postTaskSafely(Runnable task){
		int currentThread = android.os.Process.myTid();
		if(currentThread == getMainThreadId()){
			task.run();
		}else {
			BaseApplication.getHandler().post(task);
		}
	}
	private static long getMainThreadId() {
		
		return BaseApplication.getTid();
	}
}
