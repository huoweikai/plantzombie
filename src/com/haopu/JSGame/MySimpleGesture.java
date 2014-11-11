package com.haopu.JSGame;



import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;

public class MySimpleGesture extends GestureDetector.SimpleOnGestureListener implements OnGestureListener {

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		System.err.println(" onDown  ");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		System.err.println(" onFling  ");
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		System.err.println(" onLongPress  ");
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		
//	MyGameCanvas.openX=(int) e2.getX();
//	MyGameCanvas.openY=(int) e2.getY();
//		
//		System.err.println(MyGameCanvas.openX+"    "+MyGameCanvas.openY);
		System.err.println(" onScroll  ");
		
		return super.onScroll(e1, e2, distanceX, distanceY);



	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		System.err.println(" onShowPress  ");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		System.err.println(" onSingleTapUp  ");
		return true;
	}

}

