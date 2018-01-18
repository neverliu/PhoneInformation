package com.megafone.PhoneInformation.fragment;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.megafone.PhoneInformation.adapter.BaseFragment;

public class AboutFragment extends BaseFragment {
	
	public AboutFragment() {
	}
	
	private static final String ARG_POSITION = "position";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
		
		
		
	}
	@Override
	public List getData() {
		return null;
	}
	@Override
	public void initView(TextView title, TextView info) {
	}
	@Override
	public List<String[]> initListView() {
		return null;
	}
	
}
