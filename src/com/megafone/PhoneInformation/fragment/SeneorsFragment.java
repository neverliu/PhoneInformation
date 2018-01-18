package com.megafone.PhoneInformation.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.TextView;

import com.megafone.PhoneInformation.adapter.BaseFragment;

public class SeneorsFragment extends BaseFragment {
	
	Context content;

	private static final String TAG = "SorFragment";
	List<Map<String, Object>> list;
	 SensorManager sm = null;

	@Override
	public List getData() {
		content = getContext();
		 SensorManager sm = (SensorManager)content.getSystemService(content.SENSOR_SERVICE); 
		 List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);  
		 list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < allSensors.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", allSensors.get(i).getName());
			map.put("info", allSensors.get(i).getVendor());
			list.add(map);
		}
		return list;
	}

	@Override
	public void initView(TextView title, TextView info) {
//		title.setWidth(R.dimen.sensor_title_width);
		title.setWidth(220);
		info.setHeight(100);
	}

	@Override
	public List<String[]> initListView() {
		return null;
	}

}
