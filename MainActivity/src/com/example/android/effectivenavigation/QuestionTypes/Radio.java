package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.android.effectivenavigation.CollectionDemoActivity;
import com.example.android.effectivenavigation.CustomViewPager;
import com.example.android.effectivenavigation.R;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class Radio extends InputElement {
   
	public String text;
	public int group;
	public int id;
	public RadioButton radio;
	public String coef=null;
	public String name=null;
	public boolean isGroupValidated=false;
	public static int isValidCount=0; // lets use it 
	public static int isValidNr=0;  // validate ... 
	public static int radioBoxCount = 0; //TEST 
	// public static boolean statBoxIGV = false; //TEST


	public Radio(Question question) {
		super(question);
		Log.w("Radio InputElement():", "-------???--------");	
	}
    // DISPLAY //
	@Override
	public View display(Activity context) {
		radio = new RadioButton(context);
		radio.setText(text);
        radio.setBackgroundColor(Color.rgb(77, 77, 77));
		radio.setTag(this);
		radio.setOnClickListener(this.question.RadioGroup);
        // ====WICHTIG======================================
		isValidCount ++;
		
        // WoZu ???     ******************************************           
       
		if (val!=null)
		{
            Log.w("*** RADIO 1", "InputElement.dsplay.val ?:"+val);
		    radio.setChecked((Boolean.valueOf(val)));

		    Toast myToast2 = Toast.makeText(context, 
		      		"##-IF-1 : "
		    		+" //val: "+val  
		    		+" //question.name: "+question.name
		    		+" //id: "+question.id
		      		,Toast.LENGTH_LONG);
		      		myToast2.setGravity(Gravity.TOP, 0, 0);
		      		// myToast2.show(); 
		    
		} // WoZu ???
		// ********************************************************
		// 111
		// radio.setTextSize(35);
     
    Log.w("@111 YYY YYY YYY  ---RAMKA---", "Page-Aufbau --- YYY YYY YYY YYY  ");       
    //-START--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC---Logic--
    RadioButtonGroup.statBoxFlag = 0; // The FLAG
    RadioButtonGroup.statBoxIGV = false;
    RadioButtonGroup.statBoxSFlag = "aaa"; 
    RadioButtonGroup.statBoxStartWith = false; 
     // TOAST 1 -- TOAST 1 -- TOAST 1 -- TOAST 1 -- TOAST 1 -- TOAST 1 -- TOAST 1 --
     Toast myToast2 = Toast.makeText(context, 
       "##1 "+RadioButtonGroup.statBoxName
        +" //question.name: "+question.name
        +" //val: "+val    		
        +" //statBoxSFlag: "+RadioButtonGroup.statBoxSFlag
        +" //isValidCount: "+isValidCount
      	,Toast.LENGTH_LONG);
      	myToast2.setGravity(Gravity.LEFT, 0, 0);
      // myToast2.show();      		
      // TOAST 2 -- TOAST 2 -- TOAST 2 -- TOAST 2 -- TOAST 2 -- TOAST 2 -- TOAST 2 --
      Toast myToast = Toast.makeText(context, 
		"###2: "
		+" //albertNameNow: "+CollectionDemoActivity.albertNameNow
		+" //albertEquationNow: "+CollectionDemoActivity.albertEquationNow
		+" //albertContentNow: "+CollectionDemoActivity.albertContentNow	
		+" /albertIdNow: "+CollectionDemoActivity.albertIdNow
		+" /albertIdNext: "+CollectionDemoActivity.albertIdNext
		,Toast.LENGTH_SHORT);
		myToast.setGravity(Gravity.RIGHT, 0, 0);
	    //myToast.show(); 	    
			
	    	if( CollectionDemoActivity.albertNameNow.matches(".*q.*"))
	        {  				       
		       //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	    	      CustomViewPager.enabled = false;     //$$$$$
	      	      Log.w("@1001 Radio.D inputElement: ", "@1001 FALSE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
	   	       //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	        }// end_if
	    	//++++++++ 
	    	if(	CollectionDemoActivity.albertNameNow.matches(".*t.*"))
	        {  				       
		       //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	    	    CustomViewPager.enabled = true;     //$$$$$
	    	    Log.w("@1001 Radio.D inputElement: ", "@1001 TRUE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
	   	       //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	        }//////// end_if ///////
	    RadioButtonGroup.statBoxBuffer+="-"+CollectionDemoActivity.albertNameNow;
	    	
        //-STOP--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC---Logic--------
    	Log.w("@351-Nxt Radio.d :", "@1001 CollectionDemoActivity.albertNameNext: "+CollectionDemoActivity.albertNameNext);
    	//8 Log.w("@350-Nxt Radio.d onClick:", "CollectionDemoActivity.albertEquationNext: "+CollectionDemoActivity.albertEquationNext);
    	Log.w("@352-Nxt Radio.d :", "CollectionDemoActivity.albertContentNext: "+CollectionDemoActivity.albertContentNext);
    	Log.w("@351-Nxt Radio.d >>>>>>", "1002 CollectionDemoActivity.albertIdNext: "+CollectionDemoActivity.albertIdNext+"<<<<<<");
        Log.w("@111 R: ", "isValidCount: "+isValidCount);
    	Log.w("@351-Now-Buffer:", "@1001 statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);

    	Log.w("@351-Now  Radio.d :", "@1001 CollectionDemoActivity.albertNameNow: "+CollectionDemoActivity.albertNameNow);
    	//8 Log.w("@350-Now Radio.d onClick:", "CollectionDemoActivity.albertEquationNow: "+CollectionDemoActivity.albertEquationNow);
    	Log.w("@352-Now  Radio.d :", "CollectionDemoActivity.albertContentNow: "+CollectionDemoActivity.albertContentNow);
    	Log.w("@351-Nxt Radio.d :", "1002 CollectionDemoActivity.albertIdNow: "+CollectionDemoActivity.albertIdNow);
    	Log.w("@333 ###: ", "### CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
        Log.w("@117 Radio.D: ", "RadioButtonGroup.statBoxName: "+RadioButtonGroup.statBoxName );
        Log.w("@119 Radio.D: ", "RadioButtonGroup.statBoxG: "+RadioButtonGroup.statBoxG );
        Log.w("@120 Radio.D: ", "RadioButtonGroup.statBoxCoef: "+RadioButtonGroup.statBoxCoef );
        Log.w("@121 Radio.D: ", "RadioButtonGroup.statBoxIGV: "+RadioButtonGroup.statBoxIGV );
    	// Log.w("@35- **************** ", " ***************************** ");
        // Log.w("@114 Radio.D: ", "question.name: "+question.name);
        // Log.w("@115 Radio.D: ", "question.equation: "+question.equation);
        // Log.w("@116 Radio.D: ZukunftsQuestion ?", "question.content: "+question.content);
        // Log.w("@118 Radio.D: ", "RadioButtonGroup.statBoxText: "+RadioButtonGroup.statBoxText );
        //**************************************************************************
        // Log.w("@122 Radio.D: ", "val: "+val);
        // Log.w("@123 Radio.D: ", "radioBoxCount: "+radioBoxCount);
        // Log.w("@124 Radio.D: ", "statBoxStartWith: "+RadioButtonGroup.statBoxStartWith );      
        //Log.w("@126 Radio.D: ", "statBoxFlag: "+RadioButtonGroup.statBoxFlag );
        //Log.w("##3 Radio.ViEW.D: ", "##3.statBoxSFlag:"+RadioButtonGroup.statBoxSFlag );
        //**************************************************************************        
        //Log.w("@111 ZZZ ZZZ ZZZ  ---RAMKA---", "111 --- ZZZ ZZZ ZZZ ZZZ ");
        radioBoxCount ++;                   
		return radio;
	}// End-Radio
	

	@Override
	public String writeData() {
		if(name!=null)
		{
			if(val=="true")
				question.evaluator.putVariable(name, coef);
				else
					question.evaluator.putVariable(name, "0");	
			
		}
		String outBuf = "";
		outBuf+=this.val;
		return outBuf;
	}

	@Override
	public String writeDataToPdf() {
		if(name!=null)
		{
			if(val=="true")
				question.evaluator.putVariable(name, coef);
				else
					question.evaluator.putVariable(name, "0");	
			
		}
		String outBuf = "";
		if(Boolean.valueOf(val))
		outBuf+="X";
		return outBuf;
	}

	//-------------------------------------------------------------------------------------
	/*	*/
	@Override
	public int validate()
	{		
		isValidNr ++;
		Log.w("#001 Radio int validate():", "isValidNr:"+isValidNr);
		Log.w("002 Radio int validate():", "isValidCount: "+isValidCount);
		//Log.w("003 Radio int validate():", "this.isValidCount:"+this.isValidCount);
		//Log.w("004 Radio int validate():", "super.isValidCount:"+super.isValidCount);
		Log.w("<<<006 Radio int validate():", "isGroupValidated: "+isGroupValidated);		
		// return this.isValidCount;
		// return isValidCount; 
		return isValidNr; 

	}
	@Override
	public int validate(String albertRadioTest)
	{	// GLOBAL
		isValidNr ++;
		Log.w(">>>999 Radio.Validate2 RADIO:", "albertRadioTest: "+albertRadioTest);
		// return this.isValidCount;
		// return isValidCount; 
		Log.w("___999 Radio.Validate2:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName);
		return isValidNr; 

	}
	
	@Override
	public String validate(String albertRadioTest,String albertRadioName, Activity context )
	{		
		// this.isGroupValidated ??? ------------------------------------------------------------------------------------------------------------------------------
		//Toast myToast = Toast.makeText(context, "007! albertRadioTest:"+albertRadioTest, Toast.LENGTH_LONG);
		//myToast.setGravity(Gravity.LEFT, 0, 0);
		//myToast.show(); 
		//=-----------------------------------------
		String stringBox=albertRadioTest;
		Log.w(">>>999 RadioVaid(it):", "albertRadioTest: "+albertRadioTest);
		// return this.isValidCount;
		// return isValidCount; 
		return stringBox; 

	}
	
}
