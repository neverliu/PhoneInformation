package com.megafone.PhoneInformation.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.megafone.PhoneInformation.adapter.BaseFragment;
import com.megafone.PhoneInformation.tools.BatteryTools;

public class BatteryFragment extends BaseFragment {

	private BatteryTools bt;
	private List<Map<String, Object>> list;
	private List<String[]> inputDescriptor;

	public BatteryFragment() {
		bt = new BatteryTools();
	}

	@Override
	public List getData() {
		list = new ArrayList<Map<String, Object>>();
		SensorManager sm = (SensorManager) getContext().getSystemService(
				getContext().SENSOR_SERVICE);
		List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);
		inputDescriptor = bt.getInputDescriptor(getContext());
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < inputDescriptor.size(); i++) {
			if(i == 4){
				map.put("title", "NAME");
				map.put("info", inputDescriptor.get(i)[2]);
				list.add(map);
			}
		}
		String[] aaa = bt.getLcdModel().split(":");
		for (int i = 0; i < aaa.length; i++) {
			if(i == aaa.length-3){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("title", "NAME");
				map2.put("info", aaa[i]);
				list.add(map2);
			}
	}
		Map<String, Object> mapCamera1 = new HashMap<String, Object>();
		Map<String, Object> mapCamera2 = new HashMap<String, Object>();
		String camera1 = bt.getCameraDevice("/sys/bus/i2c/drivers/camera/3-0001/front_cam");
		String camera2 = bt.getCameraDevice("/sys/bus/i2c/drivers/camera/3-0001/back_cam");
		mapCamera1.put("title", "frontCam");
		mapCamera1.put("info", camera1);
		mapCamera2.put("title", "backCam");
		mapCamera2.put("info", camera2);
		list.add(mapCamera1);
		list.add(mapCamera2);
		for (int i = 0; i < allSensors.size(); i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("title", "NAME");
			map1.put("info", allSensors.get(i).getName());
			list.add(map1);
		}
	
		return list;
	}

	@Override
	public void initView(TextView title, TextView info) {
		title.setWidth(130);
	}

	@Override
	public List<String[]> initListView() {
		List<String[]> list = new ArrayList<String[]>();
		String[] z = { 4 + "", "CAMERA" };
		String[] W = { 7 + "", "SENSORS" };
		String[] i = { 2 + "", "LCD" };
		String[] j = { 0 + "", "TP" };
		list.add(j);
		list.add(i);
		list.add(z);
		list.add(W);
		return list;
	}

}
