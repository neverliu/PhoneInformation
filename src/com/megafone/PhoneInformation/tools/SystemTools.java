package com.megafone.PhoneInformation.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.megafone.PhoneInformation.adapter.BaseFragment;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

public class SystemTools {

	public  String getAvailMemory(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);

		return Formatter.formatFileSize(context, mi.availMem);
	}
	public  String getKernelVersion() {  
	    String kernelVersion = "";  
	    InputStream inputStream = null;  
	    try {  
	        inputStream = new FileInputStream("/proc/version");  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	        return kernelVersion;  
	    }  
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8 * 1024);  
	    String info = "";  
	    String line = "";  
	    try {  
	        while ((line = bufferedReader.readLine()) != null) {  
	            info += line;  
	        }  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        try {  
	            bufferedReader.close();  
	            inputStream.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    try {  
	        if (info != "") {  
	            final String keyword = "version ";  
	            int index = info.indexOf(keyword);  
	            line = info.substring(index + keyword.length());  
	            index = line.indexOf(" ");  
	            kernelVersion = line.substring(0, index);  
	        }  
	    } catch (IndexOutOfBoundsException e) {  
	        e.printStackTrace();  
	    }  
	  
	    return kernelVersion;  
	}
    private String getTotalSize(File path,Context context) {  
        StatFs stat = new StatFs(path.getPath());  
        long blockSize = stat.getBlockSize();  
        long blockCount = stat.getBlockCount();  
        long totalSize = blockCount * blockSize;  
        String totalStr = Formatter.formatFileSize(context, totalSize);  
        return totalStr;  
    }
    private String getAvailSize(File path,Context context) {  
        StatFs stat = new StatFs(path.getPath());  
        long blockSize = stat.getBlockSize();  
        long availableBlocks = stat.getAvailableBlocks();  
        long availSize = availableBlocks * blockSize;  
        String availStr = Formatter.formatFileSize(context, availSize);  
        return availStr;  
    }  
	public String getStorage(Context context, int a ){
        File externalStorage = Environment.getExternalStorageDirectory();  
        File internalStorage = Environment.getDataDirectory();  
        String externalTotalSize=getTotalSize(externalStorage,context);  
        String externalAvailSize=getAvailSize(externalStorage,context);  
        String internalTotalSize=getTotalSize(internalStorage,context);  
        String internalAvailSize=getAvailSize(internalStorage,context);
        
        if(a==1)return externalTotalSize;
        if(a==2)return externalAvailSize;
        if(a==3)return internalTotalSize;
        if(a==4)return internalAvailSize;
        return null;
	}
	public  String getTotalMemory(Context context) {
		String str1 = "/proc/meminfo";
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;

		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();

			arrayOfString = str2.split("\\s+");
			for (String num : arrayOfString) {
				Log.i(str2, num + "\t");
			}

			initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
			localBufferedReader.close();

		} catch (IOException e) {
		}
		return Formatter.formatFileSize(context, initial_memory);
	}

	public  String getInfo(Context context , int i) {  
	      
		TelephonyManager mTm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);  
	       
	    String imei = mTm.getDeviceId();  
	       String imsi = mTm.getSubscriberId();  
	       String mtype = android.os.Build.MODEL; 
	       String mtyb= android.os.Build.BRAND;
	       String numer = mTm.getLine1Number(); 
	       String board = android.os.Build.BOARD;
	       String bootloader = android.os.Build.BOOTLOADER;
	       String brand = android.os.Build.BRAND;
	       String cpu_abi = android.os.Build.CPU_ABI;
	       String cpu_abi2 = android.os.Build.CPU_ABI2;
	       String device =     android.os.Build.DEVICE;
	       String display = android.os.Build.DISPLAY;
	       String fingerprint = android.os.Build.FINGERPRINT;
	       String hardware = android.os.Build.HARDWARE;
	       String host = android.os.Build.HOST;
	       String id =  android.os.Build.ID;
	       String model = android.os.Build.MODEL ;
	       String manufacturer = android.os.Build.MANUFACTURER;
	       String product = android.os.Build.PRODUCT;
	       long time =  android.os.Build.TIME;
	       String type =  android.os.Build.TYPE;
	    	String VERSION =  android.os.Build.VERSION.RELEASE;
	    	String sdk = android.os.Build.VERSION.SDK;
	    	int sdk_int =  android.os.Build.VERSION.SDK_INT;
	       if(i==1)return imei;
	       if(i==2)return imsi;
	       if(i==3)return mtype;
	       if(i==4)return mtyb;
	       if(i==5)return hardware;
	       if(i==6)return product;
	       if(i==7)return manufacturer;
	       if(i==8)return VERSION;
	       if(i==9)return type;
	       if(i==10)return board;
	       if(i==11)return sdk_int+"";
	       if(i==12)return host;
	       return null;
	}

	public  String getMacAddress(Context context) {
		String result = "";
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		result = wifiInfo.getMacAddress();
		return result;
	}
}
