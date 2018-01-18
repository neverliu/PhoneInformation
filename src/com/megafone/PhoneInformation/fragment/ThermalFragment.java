package com.megafone.PhoneInformation.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.megafone.PhoneInformation.adapter.BaseFragment;
import com.megafone.PhoneInformation.tools.BatteryTools;

public class ThermalFragment extends BaseFragment {
	private List<Map<String, Object>> list;
	private BatteryTools bt;
	public ThermalFragment() {
	}
	@Override
	public List getData() {
		BatteryTools bt = new BatteryTools();
		list = new ArrayList<Map<String, Object>>();
		bt = new BatteryTools();
		String[] aaa = bt.getLcdModel().split(":");
		for (int i = 0; i < aaa.length; i++) {
			if(i == aaa.length-3){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", "NAME");
				map.put("info", aaa[i]);
				list.add(map);
			}
	}
		return list;
	}
	@Override
	public void initView(TextView title, TextView info) {
		title.setWidth(75);
	}
	@Override
	public List<String[]> initListView() {
		return null;
	}

}
