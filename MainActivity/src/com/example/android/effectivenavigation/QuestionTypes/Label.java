package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
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
import android.widget.LinearLayout;
public class Label extends InputElement {
public String text;
	public Label(Question question) {
		super(question);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View display(Activity context) {
		TextView label = new TextView(context);
		label.setText(text);
	    label.setTextSize(35);
        label.setPadding(5, 5, 5, 5);

		return label;
	}



	@Override
	public String writeData( ) {
		return "";
	}
	public String writeDataToPdf( ) {
		return text;
	}
}
	
