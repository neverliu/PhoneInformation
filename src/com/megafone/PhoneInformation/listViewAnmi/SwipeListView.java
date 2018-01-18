package com.megafone.PhoneInformation.listViewAnmi;

import com.megafone.PhoneInformation.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.LayoutParams;

public class SwipeListView extends ListView {
	public SwipeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private static boolean IsLongClick;
	private static int IsLongClickPosition = -1;
	private static View mConvertView;
	private float rawX = -1L;
	private float rawY = -1L;
	private float offsetX = -1L;
	private float offsetY = -1L;
	private float upX = -1L;
	private float upY = -1L;
	private Point mFloatLoc = new Point();
	
	private Bitmap mSelectedItemBitmap;
	private ImageView mFloatView;
	private int mDragDeltaX;
	private int mDragDeltaY;
	private int mWidthMeasureSpec;
	private boolean mFloatViewOnMeasured = false;
	
	
	int motionPosition;
	int currentItemHeight;
	private static final String TAG = "SwipeListView";
	private int x;
	private int y;

	public void ConvertViewLongClick(int position, View convertView) {
		SwipeListView.IsLongClick = true;
		SwipeListView.IsLongClickPosition = position;
		SwipeListView.mConvertView = convertView;
		mConvertView.setOnTouchListener(new convertViewTouch());
		Log.d(TAG, "liuhao IsLongClick:" + IsLongClick);
	}

	public SwipeListView(Context context) {
		super(context);
	}

	class convertViewTouch implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			float x = (int) event.getX();
			float y = (int) event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				rawY = event.getY();
				rawX = event.getX();
				break;

			case MotionEvent.ACTION_MOVE:
				if (IsLongClick) {
					offsetY = y - rawY;
					offsetX = x - rawX;
					mConvertView.scrollTo(-(int) offsetX / 2,
							-(int) offsetY / 2);
				}
				break;
			}
			return false;
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mFloatView != null) {
			if (mFloatView.isLayoutRequested()) {
				measureFloatView();
			}
			mFloatViewOnMeasured = true;
		}
		
		mWidthMeasureSpec = widthMeasureSpec;
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		x = (int) ev.getX();
		y = (int) ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "liuhao ACTION_DOWN");
			motionPosition = pointToPosition((int) rawX, (int) rawY);
			if (motionPosition >= 0) {
				View currentItemView = getChildAt(motionPosition
						- getFirstVisiblePosition());
				LinearLayout id = (LinearLayout) currentItemView
						.findViewById(R.id.ListViewLL);
				// id.getHeight()

			}
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "liuhao ConvertViewLongClick:" + IsLongClickPosition
					+ " motionPosition:" + motionPosition + "  IsLongClick:"
					+ IsLongClick);
			if (IsLongClick) {
				mConvertView.scrollTo(-(int) offsetX / 2, -(int) offsetY / 2);
				// mConvertView.getParent().requestDisallowInterceptTouchEvent(true);

				Log.d(TAG, "liuhao y:" + offsetY + " x:" + offsetX
						+ " IsLongClick:");
			}
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "liuhao ACTION_UP");
			upY = ev.getY();
			upX = ev.getX();
			break;
		case MotionEvent.ACTION_CANCEL:
			if (IsLongClick) {
				IsLongClick = false;
				// mConvertView.getParent().requestDisallowInterceptTouchEvent(false);
			}
		}
		return super.onTouchEvent(ev);
	}
	
	public ImageView onCreateFloatView(int position){
		View srcItem = getChildAt(position + getHeaderViewsCount() - getFirstVisiblePosition());
		
		if (srcItem == null) {
			return null;
		}
		
		srcItem.setPressed(false);
		
		srcItem.setDrawingCacheEnabled(true);
		mSelectedItemBitmap = Bitmap.createBitmap( srcItem.getDrawingCache() );
		srcItem.setDrawingCacheEnabled(false);
		
		if (mFloatView == null) {
			mFloatView = new ImageView(getContext());
		}
		
		mFloatView.setPadding(0, 0, 0, 0);
		mFloatView.setImageBitmap(mSelectedItemBitmap);
		mFloatView.setLayoutParams(new LayoutParams(srcItem.getWidth(), srcItem.getHeight()));
		
		return mFloatView;
		
	}
	public boolean startDrag(int position, int deltaX, int deltaY) {
		
		if (position == -1) {
			return false;
		}
		
		ImageView floatView = onCreateFloatView(position);
		
		if (floatView == null) {
			return false;
		} else {
			return startDrag(position, floatView, deltaX, deltaY);
		}
	}
	public boolean startDrag(int position, ImageView floatView, int deltaX, int deltaY) {

		if (floatView == null) {
			return false;
		}
		
		mFloatView = floatView;
		measureFloatView();
		
		mDragDeltaX = deltaX;
		mDragDeltaY = deltaY;
		
		mFloatLoc.x = x - mDragDeltaX;
		mFloatLoc.y = y - mDragDeltaY;
		
		View srcItem = getChildAt(position - getFirstVisiblePosition());
		if (srcItem != null) {
			srcItem.setVisibility(View.INVISIBLE);
		}
		
		requestLayout();
		
		return true;
	}
	private void measureFloatView() {
		if (mFloatView != null) {
			measureItem(mFloatView);
		}
	}
    private void measureItem(View item) {
        ViewGroup.LayoutParams lp = item.getLayoutParams();
        if (lp == null) {
            lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            item.setLayoutParams(lp);
        }
        
        int wspec = ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, getListPaddingLeft()
                + getListPaddingRight(), lp.width);
        
        int hspec;
        if (lp.height > 0) {
            hspec = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
        } else {
            hspec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        
        item.measure(wspec, hspec);
    }
}
