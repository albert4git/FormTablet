package com.example.android.surveyapk;
import com.example.android.surveyapk.QuestionTypes.RadioButtonGroup;

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
    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu8 onSwipeOut 1001 CustomViewPager DDD");
    // ^^^^^^^^???^^^^^^^^
    // Toast.makeText(context, "##4 Please fill in the details, then swipe !",   Toast.LENGTH_LONG).show();

}

//****************************************************************
//ON TOUCH EVENT Drill-it // MAi MAI mai
@Override
public boolean onTouchEvent(MotionEvent ev)
{
	//+++++++++++++++++++++++++++++++++++++++++	
	
	Log.w("CVP onSWIPE", "CVP TOUCH SWIPE");

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
	 
      //RadioButtonGroup.statBoxBuffer ="VW";
      RadioButtonGroup.statBoxBuffer =CollectionDemoActivity.albertEquationNow;
	  Log.w("CVP ", "CVP $$$$HH3.1   1001  DDD");
	  Log.w("03CVP onTouchEvent", "@1001 VW 333 onTouchEvent statBoxBuffer"+RadioButtonGroup.statBoxBuffer);
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
	 Log.w("CVP ", "CVP $$$HH3   1001  DDD");

     return super.onTouchEvent(ev);

 } else {
       //Toast.makeText(context, "##B swipe >>>!",   Toast.LENGTH_SHORT).show();
 	     Log.w("CVP ", "CVP $$$HH2   1001  DDD");

     return super.onTouchEvent(ev);
 }
}

//*******************XXX*********************************************

@Override // new 
public boolean onInterceptTouchEvent(MotionEvent event) {
    Log.w("CVP ", "CVP $$$HH0.2   1001  DDD");

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
     Log.w("CVP ", "CVP $$$HH0   1001  DDD");

} 
//****************************************************************

public void setOnSwipeOutListener(OnSwipeOutListener listener)
{   // ^^^^^^^^???^^^^^^^^ pop am anfang
    //Toast.makeText(context, "##4 Please fill in the details, then swipe !", Toast.LENGTH_LONG).show();
    Log.w("CVP ", "CVP $$$$HH0.1   1001  DDD");

    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu1 onSwipeOut 1001 CustomViewPager DDD");
    mListener = listener;
}
//****************************************************************

public interface OnSwipeOutListener
{
	
    public void onSwipeOutAtStart();
    public void onSwipeOutAtEnd();
}

}