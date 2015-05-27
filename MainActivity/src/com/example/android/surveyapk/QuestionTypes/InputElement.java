package com.example.android.surveyapk.QuestionTypes;

import android.R.*;
import android.text.Editable;
import android.util.Log;
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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public abstract class InputElement {

	String val;
	Activity context;
	Question question;
	public int isValidCount;
	public int row;
	public String type;
	public String radioBoxCount;

	// ************************************************
	public InputElement(Question question) {
		this.isValidCount = isValidCount;
		this.question = question;

	}

	public abstract View display(Activity context);

	public abstract String writeData();

	public abstract String writeDataToPdf();

	public abstract int validate();

	public abstract int validate(String albertRadioTest);

	public abstract String validate(String albertRadioTest,
			String albertRadioName, Activity context);

	//
	public abstract void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser);

	public abstract void onItemSelected(AdapterView<?> parent, View view,
			int position, long id);

}
