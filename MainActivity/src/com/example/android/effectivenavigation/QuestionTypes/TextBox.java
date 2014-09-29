package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
import android.text.Editable;
import android.text.TextWatcher;
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
		 textbox = new EditText(context);
		 textbox.setSingleLine();
		 textbox.addTextChangedListener(this);
		 if (val!=null)
			{
			 textbox.setText(val);
			}
		 
		 textbox.setTextSize(40);
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
	
	
}
