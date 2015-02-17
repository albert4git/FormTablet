package com.example.android.effectivenavigation;
import com.example.android.effectivenavigation.QuestionTypes.RadioButtonGroup;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
//*****
//*****
public class CustomViewPager extends ViewPager
{ 
	
float mStartDragX;
OnSwipeOutListener mListener;
Context context;
public static boolean enabled;
float lastX = 0;

boolean lockScroll = false;
//****************************************************************

public CustomViewPager(Context context, AttributeSet attrs)
{
    super(context, attrs);
    this.context = context;
    enabled = true;
    Log.w("01CVP Construktor CustomViewPager ???", "1001 333 CustomViewPager enabled = true DDD");
    // ^^^^^^^^???^^^^^^^^
    // Toast.makeText(context, "##4 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();

}

//****************************************************************
//ON TOUCH EVENT Drill-it
@Override
public boolean onTouchEvent(MotionEvent ev)
{

	 switch (ev.getAction()) {

  case MotionEvent.ACTION_MOVE:

      if (lastX > ev.getX()) {
          lockScroll = false;
      } else {
          lockScroll = true;
      }

      lastX = ev.getX();
      break;
  }
   Log.w("02CVP onTouchEvent :", "1001 333 RadioButtonGroup.statBoxIGV:"+RadioButtonGroup.statBoxIGV );

 if (enabled) //view pager scrolling enable if true
 {
     Log.w("03CVP onTouchEvent", "@1001 VW 333 onTouchEvent statBoxBuffer"+RadioButtonGroup.statBoxBuffer);
      //RadioButtonGroup.statBoxBuffer ="VW";
      RadioButtonGroup.statBoxBuffer =CollectionDemoActivity.albertEquationNow;
     Log.w("04CVP onTouchEvent", "@1001 VW 333 onTouchEvent statBoxBuffer"+RadioButtonGroup.statBoxBuffer);
   
     // return super.onTouchEvent(ev); // unneedet ???
 }
 // ^^^^^^^^???^^^^^^^^ das pop ab und zu , schwerfalig
 // Toast.makeText(context, "##1 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();

 //return false;
 lastX = ev.getX();

 if(lockScroll) {
     //Toast.makeText(context, "##A swipe <<<!",   Toast.LENGTH_SHORT).show();
     // return false; // >> Dirction Rechts !
     return super.onTouchEvent(ev);

 } else {
       //Toast.makeText(context, "##B swipe >>>!",   Toast.LENGTH_SHORT).show();
	   // CustomViewPager.enabled = false; //$$$$$ // Direction 2 Left <<<
     return super.onTouchEvent(ev);
 }
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
    Log.w("CVP setOnSwipeOutListener", " 1001 CustomViewPager DDD DDD DDD");
    mListener = listener;
}
//****************************************************************

public interface OnSwipeOutListener
{
    public void onSwipeOutAtStart();
    public void onSwipeOutAtEnd();
}

}