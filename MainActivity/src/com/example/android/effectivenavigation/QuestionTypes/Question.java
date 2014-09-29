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
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
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

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.example.android.effectivenavigation.QuestionParser;
import com.example.android.effectivenavigation.R;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;


public class Question
{
	public String content;
	public List <InputElement> inputs;
	int width,height;
	public Evaluator evaluator;
	public String equation=null;
	public String name=null;
	public  int id;
	InputElement current;
	public RadioButtonGroup RadioGroup;
	public int coef;
	public Question() {
		evaluator= new Evaluator();
	}
	
	public View display(Activity context,View rootView)
	{
		TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT,0.5f
		        );
		InputElement currentInput;
		LinearLayout l;
		l=(LinearLayout) rootView.findViewById(R.id.inputs_layout);
		TextView questionText=(TextView) rootView.findViewById(R.id.text1);
		questionText.setText(content);
		TableLayout tbl=new TableLayout(context);
		
		
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT
		        );
		tbl.setLayoutParams(lp);
		tbl.setShrinkAllColumns(true);

		TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
		        0,
		        ViewGroup.LayoutParams.WRAP_CONTENT,1.0f
		      );
		l.addView(tbl);
		int crow=0;
		int ccol=0;
		TableRow tr = null;
	
		for (int i = 0; i < inputs.size(); i++) 
		{
			current=inputs.get(i);
			if(current.row>crow)
			{
				tr=new TableRow(context);
				
				tbl.addView(tr,lp);
				crow++;
				if(width<ccol)
					width=ccol;
				ccol=0;
			}
			ccol++;
			View rad=current.display(context);     
			
			tr.addView(rad,cellLp);
			}
		height=crow;
		
		
		return rootView;
		
		
		
	}
	
	public String writeData(Context context)
	{
		String result = "";
	
		String outBuf="";
		for (InputElement input : inputs) {
			outBuf+=input.writeData();
			outBuf+=" ";
			
		}
		if(equation!=null)
		{
			try {
				
				 result=evaluator.evaluate(equation);
			} catch (EvaluationException e) {
				// TODO Auto-generated catch block
				Toast.makeText(context, "Problem with equation"+this.id, Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		}
		outBuf+=result;
		return outBuf;
	}
	
	
	public Element writeDataToPdf(Context context)
	{
		String result = "";
	    if(width==0)
	    	return null;
		PdfPTable datatable = new PdfPTable(width);
	

		datatable.getDefaultCell().setPadding(3);
		datatable.getDefaultCell().setBorderWidth(2);
		datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (InputElement input : inputs) {
			datatable.addCell(input.writeDataToPdf());		
			
		}
		
		return datatable;
	}

	 
	
	
	
	
	
}



