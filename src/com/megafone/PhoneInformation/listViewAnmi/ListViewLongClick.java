package com.megafone.PhoneInformation.listViewAnmi;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

public class ListViewLongClick implements OnLongClickListener{

	private Context mContext;
	private int mPosition;
	private View mConvertView;
	private boolean aaa = false;
	
	private Vibrator vibrator;
	private SwipeListView slv;
	public ListViewLongClick(Context context , int position,View convertView) {
		this.mContext = context;
		this.mPosition = position;
		this.mConvertView = convertView;
		slv = new SwipeListView(context);
	}
	public ListViewLongClick() {
	}
	@Override
	public boolean onLongClick(View v) {
//		Toast.makeText(mContext, ""+mPosition, Toast.LENGTH_SHORT).show();
//		vibrator = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);  
//		
//        long [] pattern = {50,50};
//        vibrator.vibrate(pattern,-1);  
//		aaa = true;
//		slv.ConvertViewLongClick(mPosition, mConvertView);
//		
		return false;
	}
	public boolean IsLongClick(){
		boolean a = aaa;
		aaa = false;
		return a;
	}
	public View getConvertView(){
		return mConvertView;
	}
}
