package com.example.android.surveyapk;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

/**
 * Detects left and right swipes across a view.
 */
public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        Log.w("CVP setOnSwipeOutListener", "CVP HoerZu10 onSwipeOut 1001 CustomViewPager DDD");

        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void onSwipeLeft() {
        Log.w("CVP setOnSwipeOutListener", "CVP HoerZu11 onSwipeOut 1001 CustomViewPager DDD");

    }

    public void onSwipeRight() {
        Log.w("CVP setOnSwipeOutListener", "CVP HoerZu12 onSwipeOut 1001 CustomViewPager DDD");

    }

    public boolean onTouch1(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener implements OnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.w("CVP setOnSwipeOutListener", "CVP HoerZu13 onSwipeOut 1001 CustomViewPager DDD");

            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }

      //****************************************************************
      /*  private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0){
         		    Toast.makeText(context, "##1 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "##1B Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();
                }
                return true;
            }
            return false;
        } */
        
        
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
		    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu14 onSwipeOut 1001 CustomViewPager DDD");

			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
		    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu14 onSwipeOut 1001 CustomViewPager DDD");

		}
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
