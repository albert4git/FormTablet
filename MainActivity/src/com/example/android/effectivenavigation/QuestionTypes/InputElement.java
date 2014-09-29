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

public abstract class InputElement  {

	String val;
	Activity context;
	Question question;
	public int row;
	public String type;
	public InputElement(Question question)
	{
		this.question=question;
	}
	public abstract View display(Activity context);
	public abstract String writeData();
	public abstract String writeDataToPdf();
	
	
}