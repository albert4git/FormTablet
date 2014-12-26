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
	public Radio(Question question) {
		super(question);
		
	}

	@Override
	public View display(Activity context) {
		radio = new RadioButton(context);
		radio.setText(text);
        radio.setBackgroundColor(Color.rgb(77, 77, 77));
        //radio.setHeight(15);
        //radio.setWidth(15);
		radio.setTag(this);
		radio.setOnClickListener(this.question.RadioGroup);


		if (val!=null)
		{
		 radio.setChecked((Boolean.valueOf(val)));
		}
		radio.setTextSize(25);
		// JESUS1
		// Toast.makeText(context, " DiSPLAY :"+text+" val:"+val , Toast.LENGTH_LONG).show();
		// Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
        // Toast.makeText(context, "This is a toast!", Toast.LENGTH_LONG).show();
		// JESUS1
        Toast myToast = Toast.makeText(context, " RadioView :"+text+" val:"+val+" this.isGrValid:"+this.isGroupValidated, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.RIGHT, 0, 0);
        myToast.show();                
        Log.w("Radio JESUS1:", "val is:"+val);

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
	
	@Override
	public boolean validate()
	{
	 //noToast
	return this.isGroupValidated;
	// System.out.println("error in popup");

	}
	
}
