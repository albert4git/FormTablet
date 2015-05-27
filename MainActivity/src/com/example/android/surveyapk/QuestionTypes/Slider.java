package com.example.android.surveyapk.QuestionTypes;
import android.R;
import android.R.*;



import android.location.GpsStatus.Listener;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
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
import java.util.List;

import com.example.android.surveyapk.CustomViewPager;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

//import com.example.android.effectivenavigation.R;


public class Slider extends InputElement implements OnCheckedChangeListener {
	CheckBox checkBox;
	public String defaultState;
	public String coef=null;
	public String name=null;
	public int stepSize =1 ;
	public String dMax ;

	public Slider(Question question) {
		super(question);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View display(Activity context) {
		checkBox = new CheckBox(context);
		checkBox.setOnCheckedChangeListener(this);
		 if (val!=null)
			{
			 checkBox.setChecked((Boolean.valueOf(val)));
			}
		 checkBox.setTextSize(35);
		//return checkBox;
		 /*
		         TextView texta = (TextView) context.findViewById(R.layout.list_content);
		 		 TextView questionTextt=(TextView) rootView.findViewById(R.id.text1);
	             Button btn=(Button) findViewById(R.id.demo_collection_button);
         */		 
		 
        SeekBar slider = new SeekBar(context);
        int foo = Integer.parseInt(coef);
		Log.w("SeekBar", "SeekBar Robo2: dMax: "+dMax);
		Log.w("SeekBar", "SeekBar Robo2: foo: "+foo);

        slider.setMax(foo);        
        // slider.setProgress(20);
        slider.setTag(this);
        String[] percent = { "0", "10", "20", "30", "40", "50"};
        
        slider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       

            @Override       
            public void onStopTrackingTouch(SeekBar seekBar) {      
                // TODO Auto-generated method stub      
            }       

            @Override       
            public void onStartTrackingTouch(SeekBar seekBar) {     
                // TODO Auto-generated method stub      
            }       

            @Override       
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
                // TODO Auto-generated method stub                  	
        	    progress = ((int)Math.round(progress/stepSize))*stepSize;
        	    seekBar.setProgress(progress);
            	
                val=String.valueOf(progress);
        		Log.w("SeekBar", "SeekBar Robo: progress: "+progress);
        		coef = val;

            }       
        });             
        if (val!=null)
		{
        	
        	
        	slider.setProgress(Integer.valueOf(val));
        	//((Boolean.valueOf(val)));
		}
        
		return slider;

	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
		// TODO Auto-generated method stub
	}
	
	@Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
    {
        val=String.valueOf(progress);
		Log.w("SeekBar", "SeekBar Robo1: progress: "+progress);
		Log.w("SeekBar", "SeekBar Robo1: val: "+val);
		coef = val;

    }
	
	@Override
	public String writeData() {
		Log.w("SeekBar", "SeekBar Robo2WD: progress: this.val "+this.val);
		Log.w("SeekBar", "SeekBar Robo2WD: name "+name);
		Log.w("SeekBar", "SeekBar Robo2WD: coef "+coef);
		
		String outBuf = "";
		outBuf+=this.val;
		if(name!=null)
		{
			// remooved if //
			question.evaluator.putVariable(name, coef);		
		}
		return outBuf;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		 val=String.valueOf(checkBox.isChecked()); 
		 //val=Long.valueOf(Listener.this);

	}
    


	
	@Override
	public String writeDataToPdf() {
		Log.w("SeekBar", "SeekBar Robo3Pdf: name: "+name);
		Log.w("SeekBar", "SeekBar Robo3Pdf: coef: "+coef);
		Log.w("SeekBar", "SeekBar Robo3Pdf: val: "+this.val);

		String outBuf = "";
		//if(Boolean.valueOf(val))
			outBuf+=val;
	    // CHANGED .............
		if(name!=null)
		{		
			question.evaluator.putVariable(name, coef);	
		}
		return outBuf;
	}


	/*	*/
	@Override
	public int validate()
	{
		// Log.w("Radio boolean validate()77:", "this.isValidCount:"+this.isValidCount);
		// return this.isValidCount;
		  int tst=1;
		  return tst;
	}
	@Override
	public int validate(String albertRadioTest)
	{	//ToDo	
		int isValidNr =1 ;
		Log.w(">>>999 RadioVaid(it):", "albertRadioTest: "+albertRadioTest);
		// return this.isValidCount;
		// return isValidCount; 
		return isValidNr; 

	}
	@Override
	public String validate(String albertRadioTest,String albertRadioName, Activity context )
	{		
		// this.isGroupValidated ??? ------------------------------------------------------------------------------------------------------------------------------
		Toast myToast = Toast.makeText(context, "007 albertRadioTest:"+albertRadioTest, Toast.LENGTH_SHORT);
		myToast.setGravity(Gravity.RIGHT, 0, 0);
		myToast.show(); 
		//=-----------------------------------------
		String stringBox=albertRadioTest;
		Log.w(">>>999 RadioVaid(it):", "albertRadioTest: "+albertRadioTest);
		return stringBox; 

	}	
}
