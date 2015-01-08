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
 Log.w("-OOO OOO OOO  RAMKA", "START OOO OOO OOO");
 Log.w("-301 RBG-onClick:", "1. radioCont.val:"+radioCont.val);
   // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
  CollectionDemoActivity.albertIsCheckPage = true; 
  // %-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-
  CustomViewPager.enabled = true; // SUPER ODER ???-%-%-%
  // %-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-%-

  Log.w("-302 RBG-onClick", " albertIsCheckPage: "+CollectionDemoActivity.albertIsCheckPage);
  Log.w("-303 RBG-onClick", " enabled ?: "+CustomViewPager.enabled);
   // mViewPager.setPagingEnabled(true);
   //### !!! The holly3 BoxIGV is a good switch !!! //
        if( 
        	RadioButtonGroup.statBoxG >0
        	&& Radio.isValidCount > 0
        	&& RadioButtonGroup.statBoxIGV 
           ) 
        {      
        	
            Log.w("-304 RBG-onClick", " albertIsCheckPage: "+CollectionDemoActivity.albertIsCheckPage);
            Log.w("-305 RBG-onClick", " enabled 1: "+CustomViewPager.enabled);
            // FLAG FLAG FLAG FLAG FLAG FLAG set YES TRUE
            CollectionDemoActivity.albertIsCheckPage = true;            
            CustomViewPager.enabled = true; // GENUG To Stop // SUPER ODER ??? wird wicht ausgefŸhrt !
            Log.w("-306 RBG-onClick", " albertIsCheckPage: "+CollectionDemoActivity.albertIsCheckPage);
            Log.w("-307 RBG-onClick", " enabled 2: "+CustomViewPager.enabled);

        }
   //### !!! The holly3 BoxIGV is a good switch !!! //
        if( 
        	RadioButtonGroup.statBoxG >0
        	&& Radio.isValidCount > 0
        	&& !RadioButtonGroup.statBoxIGV 
           ) 
        {           
           Log.w("@NO-RBG-onClick holly3 ", " onClick(View v) ");
           // --- mViewPager.setPagingEnabled(false);
        }	
  // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
        int j=0;
		for (Radio myradio : group) {
	        
	        Log.w("-308 RBG-onClick:", "2A. myradio.radio.getId:"+myradio.radio.getId()+" j: "+j);
	        
		    myradio.isGroupValidated=true;   // WoZu kann ich das nuzen ?
		    CollectionDemoActivity.albertIsCheckPage = true;
			myradio.radio.setChecked(false); // Wichtig for Sigle Radio Select !!
			myradio.val="false";             // WoZu
	        Log.w("-309 RBG-onClick", "2B. myradio.isGroupValidated:"+myradio.isGroupValidated+" j: "+j);
			
			j++;
		}// END_for
		
        Log.w("--- RBG onClick:", "4. radioCont.val:"+radioCont.val);
		//----------------------------XXX------------------------------------------		
		radio.setChecked(true); //WoZu
		radioCont.val="true";  // WoZu
		
		Log.w("-310 RBG onClick:", "radioCont.val: "+radioCont.val);
		Log.w("-311 RBG onClick:", "radioCont.id: "+radioCont.id);
		Log.w("-312 RBG onClick:", "radioCont.name: "+radioCont.name);
		Log.w("-313 RBG onClick:", "radioCont.text: "+radioCont.text);
		Log.w("-314 RBG onClick:", "radioCont.group: "+radioCont.group);
		Log.w("-315 RBG onClick:", "radioCont.coef: "+radioCont.coef);
		Log.w("-316 RBG onClick:", "radioCont.row: "+radioCont.row);
		// Log.w("-317 RBG onClick:", "radioCont.question: "+radioCont.question);
		Log.w("-318 RBG onClick:", "radioCont.isGroupValidated: "+radioCont.isGroupValidated);
		Log.w("-319 RBG onClick:", "-?- radio.getId: "+radio.getId());
		Log.w("-320 RBG onClick:", "statBoxName: "+RadioButtonGroup.statBoxName);

		//----------------------------XXX-------------------------------------------
		// validate(String albertRadioTest,String albertRadioName, Activity context );
		//=======================================================
		RadioButtonGroup.statBoxName = radioCont.name ; // Kut ?
		RadioButtonGroup.statBoxText = radioCont.text ; // Kut ?
		RadioButtonGroup.statBoxG = radioCont.group ;   // Kut ?
		RadioButtonGroup.statBoxCoef = radioCont.coef ; // Kut ?
		RadioButtonGroup.statBoxIGV = radioCont.isGroupValidated;
		
		   // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
		   // mViewPager.setPagingEnabled(true);
		   //### !!! The holly3 BoxIGV is a good switch !!! //
		        if( 
		        	RadioButtonGroup.statBoxG >0
		        	&& Radio.isValidCount > 0
		        	&& RadioButtonGroup.statBoxIGV 
		           ) 
		        {            
		        Log.w("-320 RBG onClick: ", " @3YES-RADIO the holly3 TEST ");
		        // --- mViewPager.setPagingEnabled(false);
		        }
		   //### !!! The holly3 BoxIGV is a good switch !!! //
		        if( 
		        	RadioButtonGroup.statBoxG >0
		        	&& Radio.isValidCount > 0
		        	&& !RadioButtonGroup.statBoxIGV 
		           ) 
		        {           
		        Log.w("-320 RBG onClick:", "  @NO-RADIO holly3 TEST ");
		          // --- mViewPager.setPagingEnabled(false);
		        }	
		  // --- ---- ---- ---- ---- ---- --- --- --- --- --- -- -
Log.w("-PPP PPP  PPP RAMKA", "FIN PPP PPP PPP PPP");

	}








}
