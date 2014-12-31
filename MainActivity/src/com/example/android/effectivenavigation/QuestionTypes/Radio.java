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
	public int isValidCount=0;
	public int isValidNr=0;





	public Radio(Question question) {
		super(question);
		Log.w("Radio InputElement():", "-------???--------");
	
	}
    // DISPLAY ----------------------------- //
	@Override
	public View display(Activity context) {
		radio = new RadioButton(context);
		radio.setText(text);
        radio.setBackgroundColor(Color.rgb(77, 77, 77));
		radio.setTag(this);
		radio.setOnClickListener(this.question.RadioGroup);

        // WoZu ???     %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		if (val!=null)
		{
            Log.w("??? RADIO 1", "InputElement.dsplay.val ?:"+val);
		    radio.setChecked((Boolean.valueOf(val)));
		} // WoZu ???
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// 111
		radio.setTextSize(35);
		isValidCount ++;
		this.isValidCount ++;
		super.isValidCount ++;

// this.isGroupValidated ??? ------------------------------------------------------------------------------------------------------------------------------
Toast myToast = Toast.makeText(context, "111 Radio.ViEW:"+"statBoxName:"+RadioButtonGroup.statBoxName+"statBoxCoef:"+RadioButtonGroup.statBoxCoef, Toast.LENGTH_SHORT);
myToast.setGravity(Gravity.RIGHT, 0, 0);
myToast.show(); 
//----------------------------------------------------------------------------------------------------------------------------------------------------------

        Log.w("111 Radio.display()3A:", "val isValidCount: "+isValidCount);
        Log.w("112 Radio.display()3B:", "val this.isValidCount: "+this.isValidCount);
        Log.w("113 Radio.display()3C:", "val super.isValidCount: "+super.isValidCount);
        //111
        //Log.w("114 Radio.display()3:", "val:"+val);
        //Log.w("115 Radio.display()4:", "radio.Id :"+radio.getId());
        Log.w("116 Radio.display()5:", "!! radio.length:"+radio.length());
		// this.isValidCount ++;
        validate();
        // ==========================================
		Log.w("-=113=- RADIO DISPLAY:", "------------");
		validate("albertRadioTest-USA","albertRadioName-USA", context );  
		Log.w("--=======--", "--===================--");

		return radio;
	}
	

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
