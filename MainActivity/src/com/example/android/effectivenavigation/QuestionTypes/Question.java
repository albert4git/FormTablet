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
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

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
import com.lowagie.text.Rectangle;
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
		// DISPLAY GOGO
		//Toast.makeText(context, " DISPLAY :", Toast.LENGTH_LONG).show();

		TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT,0.5f
		        );
		InputElement currentInput;
		LinearLayout l;
       //EXMPL  LinearLayout l=(LinearLayout) findViewById(R.id.mainlayout);

		// ->> l=(LinearLayout) rootView.findViewById(R.id.inputs_layout);
		l=(LinearLayout) rootView.findViewById(R.id.inputs_layout);

        l.setBackgroundColor(Color.DKGRAY);
		TextView questionText=(TextView) rootView.findViewById(R.id.text1);
		questionText.setText(content);
		questionText.setPadding(20, 20, 20, 20);  
		// questionText.setBackgroundColor(Color.GRAY);
		///TableLayout tbl=new TableLayout(context);
		 TableLayout tbl=new TableLayout(context);
		//TableLayout tbl;
		//   tbl = (TableLayout) rootView.findViewById(R.id.main_layout);
		// -->> setContentView(R.layout.my_table);
	    // Stone // setContentView(R.layout.main);
		// tbl.setBackgroundColor(Color.rgb(99, 99, 99));
			
		
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT
		        );
		tbl.setLayoutParams(lp);
		tbl.setShrinkAllColumns(true);
		//   tbl.setBackgroundColor(Color.DKGRAY);		
		// ->> l=(LinearLayout) rootView.findViewById(R.id.inputs_layout);

		TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
		        0,
		        ViewGroup.LayoutParams.WRAP_CONTENT,1.0f );
		
		//TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
	    //        TableRow.LayoutParams.FILL_PARENT,
	    //        TableRow.LayoutParams.WRAP_CONTENT);
		// ->> l=(LinearLayout) rootView.findViewById(R.id.inputs_layout);
		// ->> l=(LinearLayout) rootView.findViewById(R.id.main_layout);
		l.addView(tbl);
		int crow=0;
		int ccol=0;
		TableRow tr = null;
		  cellLp.topMargin = 2;  
		  cellLp.rightMargin = 2;  
	//	  cellLp.setMarginEnd(2);
	//	  cellLp.setMarginStart(0);

	    
		for (int i = 0; i < inputs.size(); i++) 
		{
			current=inputs.get(i);

			if(current.row>crow)
			{
				tr=new TableRow(context);
				if (i % 2 ==1){
				// tr=(TableRow) rootView.findViewById(R.id.mainlayout);
			    // TextView t = new TextView(rootView.getContext());
				// tr.addView(t, new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
				//--------
				  tr.setBackgroundColor(Color.rgb(100, 100, 100));
				 			 
				} else {
				  tr.setBackgroundColor(Color.DKGRAY);
			    }
				// tr.setPadding(5, 5, 5, 5); 
				//tr.setBackground(null);
				//tr.setPaddingRelative(start, top, end, bottom)
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

	 
	
	
	
	
	

	
	public boolean validate()
	{
		boolean finalValidation=true;
		for(InputElement input:inputs)
		{
		 finalValidation =finalValidation & input.validate();	
		}
		// JESUS2
		// Toast.makeText(getApplicationContext(), "Problem with equation"+this.id, Toast.LENGTH_SHORT).show();
        // Toast myToast = Toast.makeText(context, " Question: finValid:"+finalValidation, Toast.LENGTH_SHORT);
        // myToast.setGravity(Gravity.TOP, 0, 0);
        // myToast.show(); 
        Log.w("Question Validate():", "finalVal is:"+finalValidation);

		return finalValidation;
		
	
	}
}


