package com.megafone.PhoneInformation.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.megafone.PhoneInformation.R;
import com.megafone.PhoneInformation.factory.ThreadPoolFactory;
import com.megafone.PhoneInformation.listViewAnmi.ListViewLongClick;

public abstract class BaseFragment extends Fragment implements OnTouchListener{
	public static Activity mainActivity;
	private float rawX = -1L;
	private float rawY = -1L;
	private float downX = -1L;
	private float downY = -1L;
	private static final int MSG_SUCCESS_DETAILS = 0;
	private static final int MSG_FAILURE = -1;
	private ListView listView;
	public Context context;
	public List<Map<String, Object>> list = null;
	public List<String[]> lists = null;;
	private static final String ARG_POSITION = "position";
	protected static final String TAG = "BaseFragment";
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			Log.d(TAG,"liuhao msg.what:"+msg.what);
			switch (msg.what) {
			case MSG_SUCCESS_DETAILS:
				list = (List<Map<String, Object>>) msg.obj;
//				sendEmptyMessageAtTime(MSG_SUCCESS, 1000);
				refresh();
				break;
			}

		};
	};
	private ListViewLongClick lvlk;
	public Activity getMyActivity() {
		return mainActivity;
	}

	public Context getContext() {
		return context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainActivity=getMyActivity();
		getActivity();
	}
	
	private void moveViewWithFinger(View view, int x , int y){
		
	}
	
@Override
public boolean onTouch(View v, MotionEvent event) {
	switch (event.getAction()) {
	case MotionEvent.ACTION_DOWN:
		break;
	case MotionEvent.ACTION_MOVE:
		rawY =event.getY();
		rawX = event.getX();
		
		break;
	case MotionEvent.ACTION_UP:
		downY =event.getY();
		downX = event.getX();
		break;
	}
	return true;
}
	@Override
	public void onDestroy() {
		super.onDestroy();
		mainActivity = null;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		lvlk = new ListViewLongClick();
		View root = inflater.inflate(R.layout.fragment_sor, container, false);
		context = root.getContext();
		listView = (ListView) root.findViewById(R.id.sor_lv);
//		listView.setOnTouchListener(new ListTouch(lvlk));
		if (list == null) {
			ThreadPoolFactory.getmNormalPool().execute(new loadDataTask());
		} else {
			refresh();
		}

		return root;
	}

	private void refresh() {
		lists = initListView();
		listView.setAdapter(new BaseListViewAdapter(getActivity(), list, lists) {
			@Override
			public void initView(TextView title, TextView info) {
				BaseFragment.this.initView(title, info);
			}

			@Override
			public List<String[]> initListView() {
				return BaseFragment.this.initListView();
			}

		});
	}
	
	private List<Map<String, Object>> initSubtitle(List<Map<String, Object>> data,
			List<String[]> subtitleLine) {
		if(subtitleLine!=null){
			for (int i = 0; i < subtitleLine.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Log.d(TAG,"liuhao 0:"+subtitleLine.get(i)[0]+" 1:"+subtitleLine.get(i)[1]);
				map.put("titleLine", subtitleLine.get(i)[1]);
				data.add(Integer.parseInt(subtitleLine.get(i)[0]),map);
			}
			return data;
		}
		return data;
	}
	class loadDataTask implements Runnable {
		@Override
		public void run() {
			list = initSubtitle(getData(),initListView());
			mHandler.obtainMessage(MSG_SUCCESS_DETAILS, list).sendToTarget();

		}
	}

	public abstract void initView(TextView title, TextView info );
	public abstract List<String[]> initListView();
	public abstract List<Map<String, Object>> getData();
}