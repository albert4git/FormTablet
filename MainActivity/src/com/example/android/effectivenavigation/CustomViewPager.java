package com.example.android.effectivenavigation;
import com.example.android.effectivenavigation.QuestionTypes.RadioButtonGroup;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CustomViewPager extends ViewPager
{ 
	
float mStartDragX;
OnSwipeOutListener mListener;
Context context;
public static boolean enabled;

//****************************************************************

public CustomViewPager(Context context, AttributeSet attrs)
{
    super(context, attrs);
    this.context = context;
    enabled = true;
    Log.w("Construktor CustomViewPager diff_enabled_ ???", "1001 CustomViewPager enabled = true DDD");
    // ^^^^^^^^???^^^^^^^^
    // Toast.makeText(context, "##4 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();

}



//****************************************************************

@Override
public boolean onTouchEvent(MotionEvent event)
{
	  switch (event.getAction()) {
	  case MotionEvent.ACTION_MOVE: 
		   // Toast.makeText(context, "##1 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();
	      break;
	  case MotionEvent.ACTION_UP:
		   // Toast.makeText(context, "##2 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();
		  break;
	  case MotionEvent.ACTION_CANCEL:
		   // Toast.makeText(context, "##3 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();
		  break;
	    
	  }
	
      Log.w("@EEE4 WICHTIG IGV :", "RadioButtonGroup.statBoxIGV:"+RadioButtonGroup.statBoxIGV );

    if (enabled) //view pager scrolling enable if true
    {
        Log.w("onTouchEvent CustomViewPager", "@1001 351 onTouchEvent DDD statBoxBuffer"+RadioButtonGroup.statBoxBuffer);
        RadioButtonGroup.statBoxBuffer ="xx";
        return super.onTouchEvent(event);
    }
    // ^^^^^^^^???^^^^^^^^ das pop ab und zu , schwerfalig
    // Toast.makeText(context, "##1 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();

    return false;
}

//*******************XXX*********************************************

@Override // new 
public boolean onInterceptTouchEvent(MotionEvent event) {
    if (enabled) {
        return super.onInterceptTouchEvent(event);
    } else {
    // ^^^^^^^^???^^^^^^^^ popt sehr schnell 
    Toast myToast = Toast.makeText(context, 
	 "###5: Please fill in the details, then swipe !"
	    +" //albertNameNow: "+CollectionDemoActivity.albertNameNow
		+" //albertIdNow: "+CollectionDemoActivity.albertIdNow
		+" //albertIdNext: "+CollectionDemoActivity.albertIdNext
	 ,Toast.LENGTH_SHORT);
	myToast.setGravity(Gravity.BOTTOM, 0, 0);
    // myToast.show();
    }
    return false;
}



//*******************XXX*********************************************
public void setPagingEnabled(boolean enabled) {
    // ^^^^^^^^???^^^^^^^^
    this.enabled = enabled;
} 
//****************************************************************

public void setOnSwipeOutListener(OnSwipeOutListener listener)
{   // ^^^^^^^^???^^^^^^^^ pop am anfang
    //Toast.makeText(context, "##4 Please fill in the details, then swipe !", Toast.LENGTH_LONG).show();
    Log.w("TTT DDD DDD CustomViewPager", " CustomViewPager DDD DDD DDD");
    mListener = listener;
}
//****************************************************************

public interface OnSwipeOutListener
{
    public void onSwipeOutAtStart();
    public void onSwipeOutAtEnd();
}

}