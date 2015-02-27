/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.effectivenavigation;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import org.xmlpull.v1.XmlPullParserException;

import com.example.android.effectivenavigation.SimpleGestureFilter.SimpleGestureListener;
import com.example.android.effectivenavigation.directorychooser.DirectoryChooserDialog;
import com.example.android.effectivenavigation.QuestionTypes.*;
import com.example.android.effectivenavigation.QuestionParser.*;



public class CollectionDemoActivity extends FragmentActivity implements SimpleGestureListener{
// DISPLAY GOGO
//--------------------------------------------------------------------------//
//
private SimpleGestureFilter detector;    
      
@Override
public boolean dispatchTouchEvent(MotionEvent me){
    // Call onTouchEvent of SimpleGestureFilter class
     this.detector.onTouchEvent(me);
		Log.w("TOUCH", "TOUCH dispatchTouchEvent");

    return super.dispatchTouchEvent(me);
}

@Override
 public void onSwipe(int direction) {
  String str = "";
	Log.w("onSWIPE", "TOUCH SWIPE");
    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu5 onSwipeOut 1001 CustomViewPager DDD");

			 switch (direction) {	  
			  case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
			  Log.w("Swipe Right", "1000D Swipe Right");		
				Log.w("Swipe Right", "BUR O, Swipe Right");
	        	Log.w("BUR onSwipe:", "BUR Right O, CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
	        	Log.w("BUR onSwipe:", "BUR LRight O, CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // CollectionDemoActivity.
						///************************************************  
			  if(!CustomViewPager.enabled){
	        	    // CustomViewPager.enabled = false; //$$$$$
			 	    Log.w("CVP ", "CVP $$$$$HH7   1001  DDD");

					Log.w("Swipe Right", "BUR NO, Swipe Right");
		        	Log.w("BUR onSwipe:", "BUR Right NO, CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
		        	Log.w("BUR onSwipe:", "BUR LRight NO, CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
					///************************************************  
				    LayoutInflater inflater = getLayoutInflater();
	                View layout = inflater.inflate(R.layout.my_custom_toast,
	                                               (ViewGroup) findViewById(R.id.custom_toast_layout));	     
	                TextView text = (TextView) layout.findViewById(R.id.textToShow);
	                text.setText(" Falshe Richtung ! ");
	                Toast toast = new Toast(getApplicationContext());
	                toast.setGravity(Gravity.BOTTOM, 0, 0);
	                toast.setDuration(Toast.LENGTH_SHORT);
	                toast.setView(layout);
	                //toast.show();				  					 					  					  
					///************************************************  
			      }else {
			  	        Log.w("CVP", "CVP $$$$$HH8   1001  DDD");
			    	    Log.w("Swipe Right", "BUR YES, Swipe Right");
			        	Log.w("BUR onSwipe:", "BUR Right YES, CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
			        	Log.w("BUR onSwipe:", "BUR LRight YES, CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // CollectionDemoActivity.albertIdNow= currentNow ;  
			      }
						///************************************************  
			           if(CustomViewPager.enabled){  	     Log.w("CVP setOnSwipeOutListener", "CVP $$$$$8   1001  DDD");  }// if_enaled 
			        break;
			  case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
			    //!CustomViewPager.enabled , backTreck, timeLastFlag
			 
			  
			    if( dofRelease ==0 ){
					Log.w("Swipe Left", "BUR 1000D NO, Swipe Left");
		        	Log.w("BUR onSwipe:", "BUR Left NO, CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
		        	Log.w("BUR onSwipe:", "BUR Left NO, CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
		        	Log.w("BUR onSwipe:", "BUR Left NO, CollectionDemoActivity.albertEquationNow %:"+CollectionDemoActivity.albertEquationNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
		        	Log.w("BUR onSwipe:", "BUR Left NO, CollectionDemoActivity.albertIdPre %:"+CollectionDemoActivity.albertIdPre); // CollectionDemoActivity.albertIdNow= currentNow ; 
		        	Log.w("BUR onSwipe:", "BUR Left NO, CollectionDemoActivity.albertNamePre %:"+CollectionDemoActivity.albertNamePre); // CollectionDemoActivity.albertIdNow= currentNow ; 

					///************************************************  
				    LayoutInflater inflater = getLayoutInflater();
	                View layout = inflater.inflate(R.layout.my_custom_toast,
	                 (ViewGroup) findViewById(R.id.custom_toast_layout));	     
	                TextView text = (TextView) layout.findViewById(R.id.textToShow);
	                // text.setText(" Bitte treffen Sie eine/vollständige Auswahl ! F: "+CollectionDemoActivity.albertIdNow);
	                text.setText(" Bitte treffen Sie eine/vollständige Auswahl !  ");

	                Toast toast = new Toast(getApplicationContext());
	                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	                toast.setDuration(Toast.LENGTH_SHORT);
	                toast.setView(layout);
	                toast.show();				  					 					  					  
					///************************************************  
			      }else {
			    	  
			    	    Log.w("Swipe Left", "BUR YES, Swipe Left");
			        	Log.w("BUR onSwipe:", "BUR Left YES, CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
			        	Log.w("BUR onSwipe:", "BUR Left YES, CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // CollectionDemoActivity.albertIdNow= currentNow ;  
			      }
			        break;
			  case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
			  Log.w("Swipe Down", "1000D Swipe Down");
			        break;
			  case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
			  Log.w("Swipe UP", "1000D Swipe UP");
			        break;
			  
			  }
	        //Toast.makeText(this, " !!  "+str, Toast.LENGTH_SHORT).show();		
			 
   
 }
  
 @Override
 public void onDoubleTap() {
   // Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
 }
 //
 //---------------------------------------------------------------------------//
 
	//Toast.makeText(context, " DiiiSPLAY :", Toast.LENGTH_LONG).show();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
	// private static final int NUM_PAGES = 5;

	DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    public QuestionParser Qparser;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    // ******************************************
    CustomViewPager mViewPager;
    // ******************************************
    Mydata data;
    static List <Question> questions;
    static int current=0;
    static int oldFragment=0;
	public static boolean albertIsCheckPage= false ; //to remoove
	//-------------------------------------------------------------   	
	public static String albertEquationNow= "bb" ; //TEST
	public static String albertEquationNext= "bb" ; //TEST
	public static String albertEquationPre= "bb" ; //TEST

	public static String albertNameNow= "bb" ; //TEST
	public static String albertNameNext= "bb" ; //TEST
	public static String albertNamePre= "bb" ; //TEST

	public static String albertContentNow= "bb" ; //TEST
	public static String albertContentNext= "bb" ; //TEST
	public static String albertContentPre= "bb" ; //TEST

	public static int albertIdNow= 0 ; //TEST
	public static int albertIdNext= 0 ; //TEST
	public static int albertIdPre= 0 ; //TEST
    //!?
	public static int currentNow= 0 ; //TEST
	public static int dofID= 0 ; //TEST
	public static int dofID2= 0 ; //TEST
	public static int dofBuff= 0 ; //TEST
	public static int dofBuff2= 0 ; //TEST
	public static int dofCurrent= 0 ; //TEST
	public static int dofRelease= 0 ; //TEST
	public static int dofRelease2= 0 ; //TEST



    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);  
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 

        // setContentView(R.layout.demo2);
        // setContentView(R.layout.custom_v);
        // Detect touched area
        detector = new SimpleGestureFilter(this,this); // FFF
        // final TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        Intent intent=getIntent();
        data= (Mydata) intent.getSerializableExtra("Mydata");        
        Qparser=new QuestionParser(data.file);			
        BufferedInputStream buffer = null;
        //  buffer= new BufferedInputStream(this.getResources().openRawResource(R.raw.bdi)); 
        //	stream = new FileInputStream();//new FileInputStream(data.file);
        questions=new ArrayList();
        questions=Qparser.parseAll(data.file);
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        // actionBar.setDisplayHomeAsUpEnabled(true);
        // Set up the ViewPager, attaching the adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.getAdapter().notifyDataSetChanged();  //This notify that you need to recreate the views // pinocio
        // mViewPager.setCurrentItem(2); //buratino
    	Log.w("BUR onCreate:", "BUR DOFa N, CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
    	Log.w("BUR onCreate:", "BUR DOFa N, CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
    	Log.w("BUR onCreate:", "BUR DOFa N, CollectionDemoActivity.albertEquationNow %:"+CollectionDemoActivity.albertEquationNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
    	Log.w("BUR onCreate:", "BUR DOFa N, CollectionDemoActivity.albertIdPre %:"+CollectionDemoActivity.albertIdPre); // CollectionDemoActivity.albertIdNow= currentNow ; 
    	Log.w("BUR onCreate:", "BUR DOFa N, CollectionDemoActivity.albertNamePre %:"+CollectionDemoActivity.albertNamePre); // CollectionDemoActivity.albertIdNow= currentNow ; 


        OnPageChangeListener listener= new OnPageChangeListener(){ 
        // ?? setPageView(R.layout.activity_collection_demo);
        //2014 go here !!!
        	
        	
			@Override
			public void onPageSelected(int arg0) {
				// POST 1
			    Log.w("@EEE.1 ++++++CDA", " onPageSelected ++++++ EEE.1");
	            //**************************************************************************
	            Log.w("@EEE CDA:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName );
	            Log.w("@EEE CDA:", "RadioButtonGroup.statBoxCoef:"+RadioButtonGroup.statBoxCoef );
	            Log.w("@EEE1 CDA:", "RadioButtonGroup.statBoxStartWith:"+RadioButtonGroup.statBoxStartWith );
	            Log.w("@EEE2 CDA:", "RadioButtonGroup.statBoxG:"+RadioButtonGroup.statBoxG );
	            Log.w("@EEE3 CDA:", "Radio.isValidCount:"+Radio.isValidCount );
	            Log.w("@EEE4 CDA:", "RadioButtonGroup.statBoxIGV:"+RadioButtonGroup.statBoxIGV );
	      // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
          //###  BIG switch !!! //
		      dofCurrent = mViewPager.getCurrentItem();
	 	            Log.w("BUR onCreate:", "BUR DOFc IF2 BIG, 1# dofCurrent %:"+dofCurrent); // 

		    	 if (dofID2 > dofCurrent) // eshio menshe ...
		            {
		    		///************************************************  
		 	        Log.w("BUR onCreate:", "BUR DOFc IF2 BIG, 2# dofCurrent %:"+dofCurrent); // 
			 	    Log.w("BUR onCreate:", "BUR DOFc IF BIG,  3# dofID %:"+dofID); // 
		 	        Log.w("BUR onCreate:", "BUR DOFc IF2 BIG, 4# dofID2 %:"+dofID2); // 
                     //TRIGER 
		            	if(  dofID > 1 )
		                {  				       
					       // current=4; // TEST 
		    			    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu6.1 onSwipeOut 1001 CustomViewPager DDD");
		    		        // mViewPager.setOnSwipeOutListener(listener);
		    		        new Handler().post(new Runnable() {
		    		        @Override
		    		        public void run() {
		    		        	//mViewPager.setCurrentItem(dofID); //SMERCH //buratino
		    		        	mViewPager.setCurrentItem(dofID2); //SMERCH //buratino
                                dofRelease2 =1;
		    		    		// Toast.makeText(getBaseContext(), " mViewPager.setCurrentItem: dofID "+dofID, Toast.LENGTH_LONG).show();

		    		            }
		    		        });
		
		                }// end_if
		            }
			     // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
		    	 if (dofID > dofCurrent) // dofCurrent menche ..
		            {
		    		///************************************************  
		 	        Log.w("BUR onCreate: ---", "BUR DOFc IF BIG, 5qqq HH dofCurrent :"+dofCurrent); // 
			 	    Log.w("BUR onCreate:--- ", "BUR DOFc IF BIG, 6qqq HH dofID :"+dofID); // 
		 	        Log.w("BUR onCreate: ---", "BUR DOFc IF BIG, 7qqq HH dofID2 :"+dofID2); // 
		             dofID2 = dofID;
                     dofRelease =2; // BackTreck
   	     	         CustomViewPager.enabled = true; //$$$$$
			 	    Log.w("BUR onCreate: ---", "BUR DOFc IF BIG, 71qqq HH dofRelease :"+dofRelease); // 

						///************************************************  
					    LayoutInflater inflater = getLayoutInflater();
		                View layout = inflater.inflate(R.layout.my_custom_toast,
		                                               (ViewGroup) findViewById(R.id.custom_toast_layout));	     
		                TextView text = (TextView) layout.findViewById(R.id.textToShow);
		                text.setText(" Sie können ihre Eingaben überprüfen korrigieren, und weiter blättern ! ");
		                Toast toast = new Toast(getApplicationContext());
		                toast.setGravity(Gravity.CENTER, 0, 0);
		                toast.setDuration(Toast.LENGTH_LONG);
		                toast.setView(layout);
		                toast.show();				  					 					  					  
						///************************************************


		            } else {
		            	 if (dofRelease != 0){ 
	                     dofRelease --; // ?? 2 ?? ForTreck
		            	 }

		            }
	             dofID = dofCurrent;

		   // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
 	 
			Log.w("CVP setOnSwipeOutListener", "CVP HoerZu6 BIG 8# current"+current+"dofID"+dofID);
	        Log.w("@NNN NNN NNN NNN NNN -RAMKA-", "NNN NNN NNN NNN NNN NNN");	
	        //###############################################################################################
		    //### 

            	
		    //###
	        //###############################################################################################
   	         Log.w("@MMM MMM MMM MMM MMM  -RAMKA-", "MMM MMM MMM MMM MMM MMM ");

	        // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
	        Log.w("BUR onCreate:", "BUR DOFb N Zu6 , CollectionDemoActivity.dofID %:"+CollectionDemoActivity.dofID); // 
	        Log.w("BUR onCreate:", "BUR DOFb N Zu6 , CollectionDemoActivity.dofID2 %:"+CollectionDemoActivity.dofID2); // 
	        Log.w("BUR onCreate:", "BUR DOFb N Zu6-316 , CollectionDemoActivity.dofCurrent %:"+CollectionDemoActivity.dofCurrent); // 

	        Log.w("BUR onCreate:", "BUR DOFb N Zu6 , CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // 
	    	Log.w("BUR onCreate:", "BUR DOFb N Zu6 , CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // 
	    	Log.w("BUR onCreate:", "BUR DOFb N Zu6 , CollectionDemoActivity.albertIdPre %:"+CollectionDemoActivity.albertIdPre); // 
	    	Log.w("BUR onCreate:", "BUR DOFb N Zu6 , CollectionDemoActivity.albertNamePre %:"+CollectionDemoActivity.albertNamePre); // 
	       

				  if(mViewPager.getCurrentItem()==questions.size())
		            {
					  dofID = 0;
					  dofID2 =0;
					  dofCurrent =0;
					  dofRelease = 0;
					  dofRelease2 = 0;
					  currentNow =0;
					// DIGN ? //
		            CollectionDemoActivity.this.Qparser.saveToPdf(data.name , data.dign ,mViewPager.getContext());
		        	System.out.println("Qparser, mydata, data,  works ? Albert ");

		    		// Toast.makeText(getBaseContext(), " ViewPager DISPLAY :", Toast.LENGTH_LONG).show();
		            } else {
		            	///************************************************  
		           	 if( DirectoryChooserDialog.timeLastFlag ==1 ){
		           						    LayoutInflater inflater = getLayoutInflater();
		                   View layout = inflater.inflate(R.layout.my_custom_toast,
		                    (ViewGroup) findViewById(R.id.custom_toast_layout));	     
		                   TextView text = (TextView) layout.findViewById(R.id.textToShow);
		                   text.setText(
		                		   "========================================" +
		                		   "========================================" +
		                		   "========================================" +
		                		   "========================" +			                		  
		                   		    "   PLEASE UPDATE YOUR LICENCE !       " +
		                		   "========================================" +
		                		   "========================================" +
		                		   "========================================" +
		                		   "========================================" +
		                		   "========================================" +
		                		   "========================================" +
		                		   "================================================" +		                		  
		                   		    "   PLEASE UPDATE YOUR LICENCE !       " +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                	    "===============================================" +				                		  
		                   		    "   PLEASE UPDATE YOUR LICENCE !       " +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
			                	    "===============================================" +			                		  
		                   		    "   PLEASE UPDATE YOUR LICENCE !       " +
			                   		"========================================" +
			                   		"========================================" +
			                   		"========================================" +
		                   		    "========================"      		
		                		   );
		                   Toast toast = new Toast(getApplicationContext());
		                   toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		                   toast.setDuration(Toast.LENGTH_LONG);
		                   toast.setView(layout);
		                   toast.show();				  					 					  					  
		           	      }
		           	///************************************************  
		            }
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			    // Log.w("EEE-ii  " , " onPageScrolled ++++++ EEE-ii");
	
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			    Log.w("EEE3 PARICE ", " onPageScrollStateChanged ++++++ EEE3");
		        Log.w("@bEEE7 Radio.D: ", "RadioButtonGroup.statBoxName: "+RadioButtonGroup.statBoxName );
		        Log.w("@bEEE9 Radio.D: ", "RadioButtonGroup.statBoxG: "+RadioButtonGroup.statBoxG );
		        Log.w("@bEEE20 Radio.D: ", "RadioButtonGroup.statBoxCoef: "+RadioButtonGroup.statBoxCoef );
		        Log.w("@bEEE21 Radio.D: ", "RadioButtonGroup.statBoxIGV: "+RadioButtonGroup.statBoxIGV );

			}
		};
	    Log.w("CVP setOnSwipeOutListener", "CVP HoerZu7 onSwipeOut 1001 CustomViewPager DDD");

        mViewPager.setOnPageChangeListener(listener);
    } // End CollectionDemoActivity
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	    Log.w("EEE3 PARICE", " onOptionsItemSelected ++++++ EEE3");

        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
  
  	// DISPLAY GO
    public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
 	
            Fragment fragment = new DemoObjectFragment();
            // Parabelum !!! %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            Log.w("--CDA-P1 FRAGMENT.getItem", "fragment.getId() #:"+fragment.getId());
            Log.w("--CDA-P1 FRAGMENT.getTag", "fragment.getId() #:"+fragment.getTag());
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
         
            Bundle args = new Bundle();
            // args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P

            fragment.setArguments(args);
            // Parabelum !!! %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            Log.w("--CDA-P2 FRAGMENT.getItem", "fragment.getId() #:"+fragment.getId());
            Log.w("--CDA-P2 FRAGMENT.getTag", "fragment.getId() #:"+fragment.getTag());
          //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 100-object collection.
            // Log.w("size", "$$$-size: getPageTitle :"+questions.size());

            return questions.size() + 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Log.w("Frage", "$$$-Frage: getPageTitle $:"+position);

            CharSequence title = null;
            if (position == 0) {
                title = "a";
            } else if (position == 1) {
                title = "b";
            } else if (position == 2) {
                title = "c";
            }
            // return title;
            return "Frage " + (position + 1);           
           
        }
      	// DISPLAY GOGO getPageTitle
    }

    
    /**
     * DemoObjectFragment extends Fragment
     */
    public static class DemoObjectFragment extends Fragment {
    	
        //check the static current ???
        public static final String ARG_OBJECT = "question";
        int testVal; // Yes
        String kartonA; // Yes
        //public static int dofID ;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            Log.w("1...", "...");
          	 View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);

            Bundle args = getArguments();
            int current=args.getInt(ARG_OBJECT)-1; //&&&&&
            // Log.w("2CDA Fragment:", "quest.size is:"+questions.size()); 

            // was-dofID2	
            // LAST RADAR 
            if (current==questions.size()) // Vielen dank !
            {
           	   rootView = inflater.inflate(R.layout.fragment_last, container, false);
         	   TextView questionText=(TextView) rootView.findViewById(R.id.text1);
        	   questionText.setText(" Vielen dank ! Eingaben werden Gespeichert" );     
			    Log.w("ENDE --- CDA ---", " ENDE CDA questions.size()");
			   // tbl.setBackgroundResource(R.drawable.swp1); //TBL . Tbl . tbl ... oder ??? ---
               
			     Button btn=(Button) rootView.findViewById(R.id.buttonLL);
			     // btn.setOnClickListener((OnClickListener) this);
			     btn.setOnClickListener(new View.OnClickListener() {
			            @Override
			            public void onClick(View v) {
			                    //your btn action
  			                    System.exit(0);
			                    }
			        });
			   //####### #### ### ## ## ## 
			   //###
			   //##
			   //#

         	   return rootView; // 225 line
            } else {

            }
            
        	Log.w("BUR onCreate:", "BUR  DOF, CollectionDemoActivity.albertIdNow %:"+CollectionDemoActivity.albertIdNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
        	Log.w("BUR onCreate:", "BUR  DOF, CollectionDemoActivity.albertNameNow %:"+CollectionDemoActivity.albertNameNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
        	Log.w("BUR onCreate:", "BUR  DOF, CollectionDemoActivity.albertEquationNow %:"+CollectionDemoActivity.albertEquationNow); // CollectionDemoActivity.albertIdNow= currentNow ; 
        	Log.w("BUR onCreate:", "BUR  DOF, CollectionDemoActivity.albertIdPre %:"+CollectionDemoActivity.albertIdPre); // CollectionDemoActivity.albertIdNow= currentNow ; 
        	Log.w("BUR onCreate:", "BUR  DOF, CollectionDemoActivity.albertNamePre %:"+CollectionDemoActivity.albertNamePre); // CollectionDemoActivity.albertIdNow= currentNow ; 
            //**************************************************************************
            Log.w("01CDA FRAGMENT onCreateView:", "CDA10 888 RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName );
            Log.w("02CDA FRAGMENT onCreateView:", "CDA10 888 RadioButtonGroup.statBoxG:"+RadioButtonGroup.statBoxG );
            Log.w("03CDA FRAGMENT onCreateView:", "CDA10 888 RadioButtonGroup.statBoxCoef:"+RadioButtonGroup.statBoxCoef );
            Log.w("04CDA FRAGMENT onCreateView:", "CDA10 888 RadioButtonGroup.statBoxIGV:"+RadioButtonGroup.statBoxIGV );
            Log.w("05CDA FRAGMENT onCreateView:", "CDA10 888 Radio.isValidCount:"+Radio.isValidCount );
            //**************************************************************************
            // (".*[\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+].*") ||
            //**************************************************************************    
        	Log.w("06--------TRIO:", "CDA10- PRE --------------=---");  	
	            if(current >1) //PRE 
	            {  
	            	 currentNow = current -2; // PRE
	            	Question questionNow=questions.get(current-2); //&&&&&
	            	//+++++++++
	            	Log.w("06---=******** CDA FRAGMENT. PRE-Frage ?", "*******=---");  
	            	Log.w("07CDA FRAGMENT onCreateView:", "CDA10 PRE: CURRENT-Now %: "+currentNow);
	            	Log.w("08CDA FRAGMENT onCreateView:", "CDA10 PRE: questionNow.equation %:"+questionNow.equation);
	            	Log.w("09CDA FRAGMENT onCreateView:", "CDA10 PRE: nowFrage: questionNow.content %: "+questionNow.content);           	
	            	Log.w("10CDA FRAGMENT onCreateView:", "CDA10 PRE: questionNow.name %:"+questionNow.name);
	            	// Log.w("11CDA FRAGMENT onCreateView:", "CDA10 PRE: questionNow.id %:"+questionNow.id);
	            	//
	        	    Log.w("12CDA FRAGMENT onCreateView: ", "CDA10 @333 PRE Background ReCheck ENABLED: "+CustomViewPager.enabled+"###");
			            if( CollectionDemoActivity.albertNameNow.matches(".*q.*"))
			            {  				       
			          	   // Log.w("13CDA FRAGMENT onCreateView: ", "CDA10 @333 PRE FALSE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
			            }// end_if
			        	//++++++++ 
			        	if(	CollectionDemoActivity.albertNameNow.matches("t.*"))
			            {  				       
			        	   // Log.w("14CDA FRAGMENT onCreateView: ", "CDA10 @333 PRE TRUE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
			            }// end_if
			        	//++++++++ 
		           	CollectionDemoActivity.albertEquationPre= questionNow.equation ; //PRE 
		        	CollectionDemoActivity.albertNamePre= questionNow.name ;         //PRE 
		        	CollectionDemoActivity.albertContentPre= questionNow.content ;   //PRE 
		        	CollectionDemoActivity.albertIdPre= currentNow ;                 //PRE 
	             }//end_if
            //**************************************************************************      
	        	Log.w("15-------- NOW:", "CDA10- NOW --------------=---");  	

	            if(current >0) //NOW
	            {  	
	            	//§§§§§§----§§§§§§----§§§§§§----§§§§§§----§§§§§§---- 
	            	currentNow = current -1; // NOW NOW NOW
				    Question questionNow=questions.get(current-1); // ALLT
                    //§§§§§§----§§§§§§----§§§§§§----§§§§§§----§§§§§§----
				    
	            	//+++++++++
	            	Log.w("16CDA FRAGMENT onCreateView:", "CDA10 Now: CURRENT-Now %: "+currentNow);
	            	Log.w("17CDA FRAGMENT onCreateView:", "CDA10 Now: questionNow.equation %:"+questionNow.equation);
	            	Log.w("18CDA FRAGMENT onCreateView:", "CDA10 Now: nowFrage: questionNow.content %: "+questionNow.content);           	
	            	Log.w("19CDA FRAGMENT onCreateView:", "CDA10 Now: questionNow.name %:"+questionNow.name);
	        	    Log.w("20CDA FRAGMENT onCreateView: ", "CDA10 @333 Now:  PreCheck: ENABLED: "+CustomViewPager.enabled+"###");
			            if( CollectionDemoActivity.albertNameNow.matches(".*q.*") && CollectionDemoActivity.dofRelease == 0)
			            {  				       
			        	    CustomViewPager.enabled = false; //$$$$$
			        	     Log.w("CVP setOnSwipeOutListener", "CVP $$$$$HH6   1001  FALSE");		        	    
			          	     Log.w("21CDA FRAGMENT onCreateView: ", "CDA10 @333 Now FALSE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
			            }// end_if
			        	//++++++++ 
			        	if(	CollectionDemoActivity.albertNameNow.matches("t.*") && CollectionDemoActivity.dofRelease == 0)
			            {  				       
			        	    CustomViewPager.enabled = true; //$$$$$
			        	     Log.w("CVP setOnSwipeOutListener", "CVP $$$$$HH5   1001  TRUE");
			        	    Log.w("22CDA FRAGMENT onCreateView: ", "CDA10 @333 Now TRUE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
			            }// end_if
			        	//++++++++ 

		            
		           	CollectionDemoActivity.albertEquationNow= questionNow.equation ; //NOW
		        	CollectionDemoActivity.albertNameNow= questionNow.name ;         //NOW
		        	CollectionDemoActivity.albertContentNow= questionNow.content ;   //NOW
		        	CollectionDemoActivity.albertIdNow= currentNow ;                 //NOW
	             }//end_if
            //******************************************************************************** //NEXT
		        	Log.w("23-------- Next:", "CDA10- Next --------------=---");  	
		            Question question=questions.get(current);    // WiCHTiG!! &&&&&                //NEXT
		            // Question questionB=questions.set(current, question); //doom  
		            Log.w("24CDA FRAGMENT onCreateView:", "CDA10 Next CURRENT %: "+current); 
		            Log.w("25CDA FRAGMENT onCreateView:", "CDA10 Next question.equation %:"+question.equation);
		            Log.w("26CDA FRAGMENT onCreateView:", "CDA10 Next question.content %: "+question.content);
		        	Log.w("27CDA FRAGMENT onCreateView:", "CDA10 Next questionNow.name %:"+question.name); // CollectionDemoActivity.albertIdNow= currentNow ; 
		            //*************************************************************************************
		            // statBoxName to albertNameNow ? initial
			            if (current == 1 && RadioButtonGroup.statBoxName.matches("aaa")){
				            CustomViewPager.enabled = false;  //$$$$$
				    	     Log.w("CVP setOnSwipeOutListener", "CVP $$$$$HH4   1001  FALSE");
				    	     Log.w("29CDA FRAGMENT onCreateView:", "CDA10 333 current == 1 Name == aaa, FALSE ENABLED: "+CustomViewPager.enabled+"###");
			            }
		        	CollectionDemoActivity.albertEquationNext= question.equation ; //NEXT          	
		        	CollectionDemoActivity.albertNameNext= question.name ;         //NEXT            	
		        	CollectionDemoActivity.albertContentNext= question.content ;   //NEXT
		        	CollectionDemoActivity.albertIdNext= current ;                 //NEXT


		        	
            Radio.isValidCount = 0;  
    	    Log.w("CVP ", "CVP $$$$$HH4.1   1001  DDD");
            Log.w("'''...'''....'''....'''... STOP CDA FRAGMENT", " STOP '''...'''....'''....'''...");
            return  question.display(getActivity(),rootView); // line 225 //rootView is the Layout


        }


    }
}
