package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.content.res.Resources;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.android.effectivenavigation.CollectionDemoActivity;
import com.example.android.effectivenavigation.CustomViewPager;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
public class RadioButtonGroup implements OnClickListener 
{
	List <ArrayList<Radio>> groups;
	List <InputElement> childs;
	private static RadioButtonGroup ref;
	public static int statBoxG = 37; //TEST 
	public static String statBoxName = "aaa"; //TEST
	public static String statBoxText = "aaa"; //TEST
	public static String statBoxCoef = "aaa"; //TEST
	public static boolean statBoxIGV = false; //TEST
	public static int statBoxFlag = 0; //TEST
	public static String statBoxSFlag = "aaa"; //TEST
	public static boolean statBoxStartWith = false ; //TEST
	public static boolean statBoxStartHasA = false ; //TEST
	public static boolean statBoxStartHasB = false ; //TEST
	public static String statBoxBuffer = "xx"; //TEST


	
	public RadioButtonGroup() {
        Log.w("@BBB BBB BBB BBB", "BBB BBB BBB BBB");
		Log.w("__399__ RBG constructor:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName);
	    groups=new ArrayList<ArrayList<Radio>>();
	}
	

	public void addToGroup(Radio radio)
	{
		int g=radio.group;
		Log.w("__377__ RBG addToGroup:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName);
        Log.w("@AAA1 AAA AAA  AAA", "AAA AAA AAA  AAA");

		if(groups.size()<g) 
		{       
			Log.w("@AAA2 AAA AAA  AAA", "AAA AAA AAA  AAA");

			for (int i = 0; i<g; i++) {
				groups.add(new ArrayList<Radio>());

			}
		
		}
		groups.get(g-1).add(radio);
		
	}


	@Override
	public void onClick(View v) {
		Radio radioCont;
		RadioButton radio;
		radio= (RadioButton) v;
		radioCont=(Radio) v.getTag();
		List<Radio> group=groups.get(radioCont.group-1);
Log.w("-PPP PPP  PPP RAMKA", "FIN PPP PPP PPP PPP");
Log.w("-301 RBG-onClick:", "1. radioCont.val:"+radioCont.val); 
        
       // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
        int j=0;  // WoZu das ganze ???
		for (Radio myradio : group) {	        
	        Log.w("-308 RBG-onClick:", "2A. myradio.radio.getId:"+myradio.radio.getId()+" j: "+j);	        
		    myradio.isGroupValidated=true;   // WoZu kann ich das nuzen ?
			myradio.radio.setChecked(false); // Wichtig for Sigle Radio Select !!
			myradio.val="false";             // WoZu
	        Log.w("-309 RBG-onClick", "2B. myradio.isGroupValidated:"+myradio.isGroupValidated+" j: "+j);			
			j++;
		}// END_for
		
        Log.w("--- RBG onClick:", "4. radioCont.val:"+radioCont.val);
		//----------------------------XXX------------------------------------------		
		radio.setChecked(true); //WoZu
		radioCont.val="true";  // WoZu

		// CollectionDemoActivity.albertEquationNow ;
		// CollectionDemoActivity.albertNameNow ; 
		// CollectionDemoActivity.albertContentNow ; 

		// CollectionDemoActivity.albertEquationNext ;    	
		// CollectionDemoActivity.albertNameNext ;    	
		// CollectionDemoActivity.albertContentNext ; 

Log.w("*351-Nxt RBG onClick:", "CollectionDemoActivity.albertNameNext: "+CollectionDemoActivity.albertNameNext);		
Log.w("*350-Nxt RBG onClick:", "CollectionDemoActivity.albertEquationNext: "+CollectionDemoActivity.albertEquationNext);
Log.w("*352-Nxt RBG onClick:", "CollectionDemoActivity.albertContentNext: "+CollectionDemoActivity.albertContentNext);
Log.w("*351-Nxt RBG onClick:", "CollectionDemoActivity.albertIdNext: "+CollectionDemoActivity.albertIdNext);
Log.w("*35- ********** ", " ***************** ");
Log.w("*351-Now  RBG onClick:", "CollectionDemoActivity.albertNameNow: "+CollectionDemoActivity.albertNameNow);
Log.w("*350-Now RBG onClick:", "CollectionDemoActivity.albertEquationNow: "+CollectionDemoActivity.albertEquationNow);
Log.w("*352-Now  RBG onClick:", "CollectionDemoActivity.albertContentNow: "+CollectionDemoActivity.albertContentNow);


		
        Log.w("-318 RBG onClick:", "radioCont.isGroupValidated: "+radioCont.isGroupValidated);
        Log.w("-320 RBG onClick:", "statBoxName: "+RadioButtonGroup.statBoxName);
		Log.w("-310 RBG onClick:", "radioCont.val: "+radioCont.val);
		Log.w("-311 RBG onClick:", "radioCont.id: "+radioCont.id);
		Log.w("-312 RBG onClick:", "radioCont.name: "+radioCont.name);
		Log.w("-313 RBG onClick:", "radioCont.text: "+radioCont.text);
		Log.w("-314 RBG onClick:", "radioCont.group: "+radioCont.group);
		Log.w("-315 RBG onClick:", "radioCont.coef: "+radioCont.coef);
		// Log.w("-316 RBG onClick:", "radioCont.row: "+radioCont.row);
		// Log.w("-317 RBG onClick:", "radioCont.question: "+radioCont.question);
		// Log.w("-319 RBG onClick:", "-?- radio.getId: "+radio.getId());

		//----------------------------XXX-------------------------------------------
		// validate(String albertRadioTest,String albertRadioName, Activity context );
		//=======================================================
		RadioButtonGroup.statBoxName = radioCont.name ; // Kut ?
		RadioButtonGroup.statBoxText = radioCont.text ; // Kut ?
		RadioButtonGroup.statBoxG = radioCont.group ;   // Kut ?
		RadioButtonGroup.statBoxCoef = radioCont.coef ; // Kut ?
		RadioButtonGroup.statBoxIGV = radioCont.isGroupValidated; // Kut ?
		//POIONT
		RadioButtonGroup.statBoxBuffer=RadioButtonGroup.statBoxBuffer.replaceAll(radioCont.name, "D");
    	Log.w("@351-Now-Buffer: RBG ", "RBG @1001 statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);

    	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	    	 if( 
	    	     CollectionDemoActivity.albertNameNow.matches(".*mq.*") 
	    	     && !RadioButtonGroup.statBoxBuffer.matches(".*l.*") 
	    	   ) 
			 {           
			     Log.w("$$$-Now-Buffer: RBG-onClick ", "-POINT- @1001 @351 albertNameNow: "+CollectionDemoActivity.albertNameNow);
			     Log.w("$$$-Now-Buffer: RBG-onClick ", "-POINT- @1001 @351 statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			     CustomViewPager.enabled = true; //$$$$$
			     Log.w("$$$ RBG-onClick", "@1001 @351 enabled ?: "+CustomViewPager.enabled);
			 }	
	         if( CollectionDemoActivity.albertNameNow.matches("q.*"))
	         {  				       
	     	      CustomViewPager.enabled = true; //$$$$$
	       	      Log.w("$$$ RBG-onClick", "@1001 @351 FALSE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
	         }// end_if
	     	 if(	CollectionDemoActivity.albertNameNow.matches("t.*"))
	         {  				       
	     	    CustomViewPager.enabled = true; //$$$$$
	     	    Log.w("$$$ RBG-onClick", "@1001 @351 TRUE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
	         }// end_if
	     	//++++++++ 
    	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    	Log.w("$$$ RBG-onClick", "1001 @351 enabled ?: "+CustomViewPager.enabled);
    	 
    	Log.w("-RRR RRR  RRR RAMKA", "FIN RRR RRR RRR RRR");


	}








}
