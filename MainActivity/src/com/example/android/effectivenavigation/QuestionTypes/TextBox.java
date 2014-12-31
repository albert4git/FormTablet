package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

public class TextBox extends InputElement implements TextWatcher
{	
	Question question;
	public String defaultText;
	EditText textbox;
	public TextBox(Question question) {
		super(question);
		this.question=question;
		
	}

	@Override
	public View display(Activity context) {
		// Toast.makeText(context, " textbox :", Toast.LENGTH_LONG).show();
		 textbox = new EditText(context);
		 textbox.setSingleLine();
		 textbox.addTextChangedListener(this);
		 if (val!=null)
			{
			 textbox.setText(val);
			}
		 
		 textbox.setTextSize(35);
		return textbox;
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
