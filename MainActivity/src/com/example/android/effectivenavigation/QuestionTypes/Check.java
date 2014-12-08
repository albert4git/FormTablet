package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
import android.text.Editable;
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
import java.util.List;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

public class Check extends InputElement implements OnCheckedChangeListener {
	CheckBox checkBox;
	public String defaultState;
	public String coef=null;
	public String name=null;
	public Check(Question question) {
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
		 checkBox.setTextSize(40);
		return checkBox;
		
	}


	@Override
	public String writeData() {
	
		String outBuf = "";
		outBuf+=this.val;
		if(name!=null)
		{
			if(val=="true")
			question.evaluator.putVariable(name, coef);
			else
				question.evaluator.putVariable(name, "0");	
		
		}
		return outBuf;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		 val=String.valueOf(checkBox.isChecked()); 
		
	}

	@Override
	public String writeDataToPdf() {
		String outBuf = "";
		if(Boolean.valueOf(val))
			outBuf+="X";
	
		if(name!=null)
		{
			if(val=="true")
			question.evaluator.putVariable(name, coef);
			else
				question.evaluator.putVariable(name, "0");	
		
		}
		return outBuf;
	}


	

	
	
}
