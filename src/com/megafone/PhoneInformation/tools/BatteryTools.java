package com.megafone.PhoneInformation.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.content.Context;
//import android.hardware.input.InputDeviceIdentifier;
import android.hardware.input.InputManager;
import android.util.Log;
import android.view.InputDevice;

public class BatteryTools {

	private static final String TAG = "BatteryTools";
	public static final String EXTRA_INPUT_DEVICE_IDENTIFIER = "input_device_identifier";
	private static final String DEFAULT_RESULT = "N/A";
	private  InputManager mIm;
//	private InputDeviceIdentifier mInputDeviceIdentifier;
	public String getMemoryModel() {
		String result = "N/A";
		try {
			FileReader fr = new FileReader("/sys/bus/mmc/devices/mmc0:0001/cid");
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			result = text.trim();
			if (result != null)
				Log.d(TAG, "liuhao BufferedReader:" + result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getCameraDevice(String filePath) {
	public String getCameraDevice(String filePath) {
		String result = "N/A";
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			result = text.trim();
				Log.d(TAG, "liuhao BufferedReader:" + result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getLcdModel() {
		String result = "N/A";
		try {
			Log.d(TAG, "liuhao getLcdModel");
			FileReader fr = new FileReader("/sys/bus/mmc/devices/mmc0:0001/lcdline");
			Log.d(TAG, "liuhao 1");
			BufferedReader br = new BufferedReader(fr);
			Log.d(TAG, "liuhao 2");
			String text = br.readLine();
			Log.d(TAG, "liuhao text:"+text);
			result = text.trim();
			if (result != null)
				Log.d(TAG, "liuhao BufferedReader:" + result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
public List<String[]> getInputDescriptor(Context context ){
	List<String[]> list = new ArrayList<String[]>();
	
    	mIm = (InputManager)context.getSystemService(Context.INPUT_SERVICE);
    	 int[] aaa = null;
    	 aaa = mIm.getInputDeviceIds();
    	 if(aaa!=null){
    		 for (int i = 0; i < aaa.length; i++) {
    			 //id , name 
    			 InputDevice id = mIm.getInputDevice(aaa[i]);
    			 String[] parameter = {id.getId()+"",id.getDescriptor(),id.getName()};
    			 list.add(parameter);
    		 }
    	 }
    	return list;
//    }
}
	public String getTPFreq(String result) {
		if(result!=null){
			if(result.length()>18){
				String mResult = (String) result.substring(6, 18);
				if(mResult.indexOf("464e58324d42") != -1){
					return "KMFNX0012M-B214";
				}else if(mResult.indexOf("464e58324d42") != -1){
					return "H9TQ64A8GTCCUR";
				}else{
					return DEFAULT_RESULT;
				}
			}
		}
		return DEFAULT_RESULT;
	}

	public String getCmdLine(String cmd) {
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				String Deivce = line.trim();
				Log.d(TAG, "liuhao Deivce:"+Deivce);
				return Deivce;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		return null;
	}

	public void chmod(String instruct) {
		try {
			Process process = null;
			DataOutputStream os = null;
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(instruct);
			os.flush();
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
