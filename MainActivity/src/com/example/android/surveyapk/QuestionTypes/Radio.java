package com.example.android.surveyapk.QuestionTypes;
import android.R.*;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
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

import com.example.android.effectivenavigation.R;
import com.example.android.surveyapk.CollectionDemoActivity;
import com.example.android.surveyapk.CustomViewPager;
import com.example.android.surveyapk.MainActivity;
import com.lowagie.text.pdf.RadioCheckField;

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
	public String def=null;

	public boolean isGroupValidated=false;
	public static int isValidCount=0; // lets use it 
	public static int isValidNr=0;  // validate ... 
	public static int radioBoxCount = 0; //TEST 
	// public static boolean statBoxIGV = false; //TEST


	public Radio(Question question) {
		super(question);
		Log.w("01Radio InputElement():", "-------???--------");	
	}
    // DISPLAY //
	@Override
	public View display(Activity context) {
		radio = new RadioButton(context);
		radio.setText(text);
        // radio.setGravity(Gravity.CENTER_HORIZONTAL); 
        //radio.setBackgroundColor(Color.rgb(77, 77, 77));
        //radio.setBackgroundResource(R.drawable.g_cub45); //SUPER oder
        radio.setBackgroundResource(R.drawable.border3); //SUPER oder       
		radio.setTag(this);
		radio.setOnClickListener(this.question.RadioGroup);
		radio.setTextSize(20);
	    radio.setTextColor(Color.BLACK);
			
        // ====WICHTIG======================================
		isValidCount ++;
		       
		if (val!=null)
		{
            Log.w("02RADIO ", "InputElement.dsplay.val ?:"+val);
		    radio.setChecked((Boolean.valueOf(val)));
            //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	        radio.setGravity(Gravity.CENTER_HORIZONTAL); 
		    
		} // WoZu ???
		// ********************************************************
		// 111
     
	    Log.w("03Radio.D--YYY YYY YYY---RAMKA---", "Radio.D1000 --- YYY YYY YYY YYY  ");       
	    //-START--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC---Logic--
	    RadioButtonGroup.statBoxFlag = 0; // The FLAG
	    RadioButtonGroup.statBoxIGV = false;
	    RadioButtonGroup.statBoxSFlag = "aaa"; 
			
	        if( 
	    	     CollectionDemoActivity.albertNameNow.matches("mq.*") 
	    	     && !RadioButtonGroup.statBoxBuffer.matches(".*yy.*") 
	    	   ) 
			 {           
			     CustomViewPager.enabled = true; //$$$$$
		    	 Log.w("CVP ", "CVP $$$$$HH11   1001  TRUE");
				 Log.w("27POINT- RBG-onClick ", "*RBG100 @333 albertNameNow: "+CollectionDemoActivity.albertNameNow);
				 Log.w("28POINT- RBG-onClick ", "*RBG100 @333 KLUCH statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
			     Log.w("29POINT- RBG-onClick", "RBG100 @333 enabled ?: "+CustomViewPager.enabled);
			 }// end_if	|| ODER
	    	if( CollectionDemoActivity.albertNameNow.matches(".*q.*") && CollectionDemoActivity.dofRelease == 0 )
	        {  				       
	    	      CustomViewPager.enabled = false;     //$$$$$
			      Log.w("CVP ", "CVP $$$$$HH13   1001  FALSE ");
	      	      Log.w("04Radio.D inputElement: ", "Radio.D1000 333 FALSE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
	        }// end_if
	    	//++++++++ 
	    	if(	CollectionDemoActivity.albertNameNow.matches("t.*") && CollectionDemoActivity.dofRelease == 0)
	        {  				       
	    	    CustomViewPager.enabled = true;     //$$$$$
		    	Log.w("CVP ", "CVP $$$$$HH14   1001  TRUE");
	    	    Log.w("05Radio.D inputElement: ", "Radio.D1000 333 TRUE CustomViewPager.enabled: "+CustomViewPager.enabled+"###");
	        }//////// end_if ///////
	    	// >>> POINT >>>
	    	
	    	
	    	//RadioButtonGroup.statBoxBuffer+="-"+RadioButtonGroup.statBoxName;
	    	RadioButtonGroup.statBoxBuffer="-"+CollectionDemoActivity.albertEquationNow ;	    //DUPONT	
			Log.w("-22RBG onClick:", "B2 Radio100- kluch1 333 -RadioButtonGroup.statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);

	        //-STOP--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC--LOGIC---Logic--------
		    Log.w("05Pre------ ", "Radio.D1000 --Pre------------- ");
	    	Log.w("06Pre Radio.d:", "Radio.D1000 albertNamePre: "+CollectionDemoActivity.albertNamePre);
	        // Log.w("07Pre Radio.d:", "Radio.D1000 albertEquationPre: "+CollectionDemoActivity.albertEquationPre);
	    	// Log.w("08Pre Radio.d:", "Radio.D1000 albertContentPre: "+CollectionDemoActivity.albertContentPre);
	    	Log.w("09Pre Radio.d>", "Radio.D1000 albertIdPre: "+CollectionDemoActivity.albertIdPre+"<<<");
		    Log.w("10Now------ ", "Radio.D1000 --Now------------- ");
	    	Log.w("11Now Radio.d:", "Radio.D1000 albertNameNow: "+CollectionDemoActivity.albertNameNow);
	    	 // Log.w("12Now Radio.d:", "Radio.D1000 albertEquationNow: "+CollectionDemoActivity.albertEquationNow);
	    	 // Log.w("13Now Radio.d:", "Radio.D1000 albertContentNow: "+CollectionDemoActivity.albertContentNow);
	    	Log.w("14Now Radio.d>", "Radio.D1000 albertIdNow: "+CollectionDemoActivity.albertIdNow+"<<<");	
	    	// Log.w("15Nxt Radio.d:", "Radio.D1000 isValidCount: "+isValidCount);
	    	// Log.w("16>Now-Buffer:", "Radio.D1000 statBoxBuffer: "+RadioButtonGroup.statBoxBuffer);
		    Log.w("17Nxt------ ", "Radio.D1000 --Nxt------------- ");
	    	Log.w("18Nxt Radio.d:", "Radio.D1000 albertNameNext: "+CollectionDemoActivity.albertNameNext);
	    	 // Log.w("19Nxt Radio.d:", "Radio.D1000 albertEquationNext: "+CollectionDemoActivity.albertEquationNext);
	    	 // Log.w("20Nxt Radio.d:", "Radio.D1000 albertContentNext: "+CollectionDemoActivity.albertContentNext);
	    	Log.w("21Nxt Radio.d>", "Radio.D1000 albertIdNext: "+CollectionDemoActivity.albertIdNext+"<<<");
	    	// Log.w("22--Radio.d ", "Radio.D1000 ------------------ ");  	    	
	    	// Log.w("23Radio.D: ", "Radio.D1000 ENABLED ? 333 : "+CustomViewPager.enabled+"###");
	    	// Log.w("24Radio.D: ", "Radio.D1000 RadioButtonGroup.statBoxName: "+RadioButtonGroup.statBoxName );
	        // Log.w("25Radio.D: ", "Radio.D1000 RadioButtonGroup.statBoxG: "+RadioButtonGroup.statBoxG );
	        // Log.w("26Radio.D: ", "Radio.D1000 RadioButtonGroup.statBoxCoef: "+RadioButtonGroup.statBoxCoef );
	        // Log.w("27Radio.D: ", "Radio.D1000 RadioButtonGroup.statBoxIGV: "+RadioButtonGroup.statBoxIGV );
	    	// Log.w("28--Radio.d ", "Radio.D1000 ------------------ ");
		    // Log.w("29Radio.D: ", "Radio.D1000 question.name: "+question.name);
	        // Log.w("30Radio.D: ", "Radio.D1000 question.equation: "+question.equation);
	        // Log.w("31Radio.D: ", "Radio.D1000 question.content: "+question.content);
	        // Log.w("32Radio.D: ", "Radio.D1000 question.name: "+RadioButtonGroup.statBoxName);
		    Log.w("def", "Radio.D1000 DEF1: "+def) ;

        //**************************************************************************
        // Log.w("@122 Radio.D: ", "val: "+val);
        // Log.w("@123 Radio.D: ", "radioBoxCount: "+radioBoxCount);
        //Log.w("@126 Radio.D: ", "statBoxFlag: "+RadioButtonGroup.statBoxFlag );
        //Log.w("##3 Radio.ViEW.D: ", "##3.statBoxSFlag:"+RadioButtonGroup.statBoxSFlag );
        //**************************************************************************        
        //Log.w("@111 ZZZ ZZZ ZZZ  ---RAMKA---", "111 --- ZZZ ZZZ ZZZ ZZZ ");
        radioBoxCount ++;                   
		return radio;
	}// End-Radio
//=====================================================================================================================	
//=======================================================================================================================	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
    {
    	//val=String.valueOf(progress);
    }
	
	@Override
	public String writeData() {
		if(name!=null)
		{
	         Log.w("RADIO", " iSCORE11: TAPORb2 PATROL name:"+name);
	         Log.w("RADIO", " iSCORE11: TAPORb2 PATROL coef:"+coef);

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
	         Log.w("RADIO", " iSCORE11: TAPORb2 PATROL2 name:"+name);
	         Log.w("RADIO", " iSCORE11: TAPORb2 PATROL2 coef:"+coef);
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
