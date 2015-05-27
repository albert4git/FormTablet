package com.example.android.surveyapk.QuestionTypes;
import android.R.*;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
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
import com.example.android.surveyapk.MainActivity;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

public class TextBoxN extends  InputElement  implements TextWatcher
{	
	Question question;
	public String defaultText;
	public String name=null;

	EditText textbox;
	EditText textB;

	TextBox textBox2; // Add !!
	public TextBoxN(Question question) {
		super(question);
		this.question=question;
		
	}

	@Override
	public View display(Activity context) {				
		 //- textbox = new EditText(context) ;	
		 textB = new EditText(context) ;		 

		 //no- textbox=(EditText) context.findViewById(R.layout.spinner_top);		
		 textbox = (EditText) context.findViewById(R.id.isNumber);		
		 textbox = new EditText(context) ;
         //-------------------------------	 
		 int maxLength = 4;    
		 textbox.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
	     textbox.setBackgroundResource(R.drawable.border5n); //SUPER oder    
	     textbox.setInputType(InputType.TYPE_CLASS_PHONE);
         //-------------------------------------------------------------------
	     
		 textbox.setSingleLine();
		 textbox.addTextChangedListener(this);
		 if (val!=null)
			{
			 textbox.setText(val);
			}
		 
		 textbox.setTextSize(25);
		return textbox;
	}

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
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		val=textbox.getText().toString();
		System.out.println(val);
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}




	@Override
	public String writeData() {
		String outBuf = "";
		outBuf+=this.val;
		return outBuf;
	}

	@Override
	public String writeDataToPdf() {
		String outBuf = "";
		outBuf+=this.val;
		
        MainActivity.alphaDef+=  ", `"+question.name+"-"+name+"` TEXT";
        MainActivity.alphaName+=", "+question.name+"-"+name;
        MainActivity.alphaVal+=",'"+val+"'";
		 Log.w("SeekBar", " Robo3Pdf: gras34 MainActivity.alphaName: "+MainActivity.alphaName );
		 Log.w("SeekBar", " Robo3Pdf: gras35 MainActivity.alphaVal: "+MainActivity.alphaVal );
		 Log.w("SeekBar", " Robo3Pdf: gras36 MainActivity.alphaDef: "+MainActivity.alphaDef );
		 Log.w("ANGARA", "  ANGARA2 MainActivity.alphaName: "+MainActivity.alphaName );
		 Log.w("ANGARA", "  ANGARA2 MainActivity.alphaVal: "+MainActivity.alphaVal );
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
