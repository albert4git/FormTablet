package com.example.android.effectivenavigation.QuestionTypes;
import android.R.*;
import android.os.Environment;
import android.provider.CalendarContract.Colors;
import android.renderscript.Font;
import android.text.format.DateFormat;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.example.android.effectivenavigation.QuestionParser;
import com.example.android.effectivenavigation.R;
import com.example.android.effectivenavigation.directorychooser.DirectoryChooserDialog;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
//
import java.io.FileOutputStream;
import java.io.IOException;
import android.content.Context;

public class Question
{

	///
	public String content;
	public String content2;
	public String contentB;
	public int qFlag = 0;
	public int qFlag2 = 0;


	public List <InputElement> inputs;
	int width,height;
	public Evaluator evaluator;
	public String equation=null;
	public String name=null;
	public  int id;
	InputElement current;
	InputElement currentB;

	public RadioButtonGroup RadioGroup;
	public int coef;
	public static String head2;

	// ------------- -------------------
     	
    public Question() {
		evaluator= new Evaluator();
	}
	

    
	@SuppressWarnings("resource")
	public View display(Activity context,View rootView)
	{   

		
    	///==================================================================
		//  DISPLAY GOGO
		//  Toast.makeText(context, " DISPLAY :", Toast.LENGTH_LONG).show();
    	//  public String getDeviceName() 
    	///==================================================================

		
		TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT,0.5f
		        );
        //####################################################################################
        //##
		InputElement currentInput;		
		LinearLayout l;
        // l=(LinearLayout) rootView.findViewById(R.id.main_layout);
        l=(LinearLayout) rootView.findViewById(R.id.inputs_layout);        
        l.setBackgroundColor(Color.DKGRAY); //GRAY GREEN // set view Background COLOR color Color 
         //##############################################################################	 
         // l.setBackgroundResource(R.drawable.border2); //SUPER oder
         // l.setBackgroundResource(R.drawable.swp4); //SUPER oder
         // SUPER ODER ???
		 // _path= _path + "/" + "All_Surveys/tpj/lb2.jpg";
		 // File imgFile = new  File("/sdcard/All_Surveys/tpj/lb2.jpg");
         // BitmapDrawable d = new BitmapDrawable(_path);
         // l.setBackgroundDrawable(d);
         // BitmapDrawable bmp = new BitmapDrawable(_path);
		 // Bitmap bmp = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
	     // Bitmap copyB = Bitmap.createScaledBitmap(bmp, 960, 640 , false);
         //##
         //################################################################################
        /* pic7 
		TextView questionText=(TextView) rootView.findViewById(R.id.text1);
		questionText.setText(content); 
		questionText.setBackgroundResource(R.drawable.border1);
        contentB = content;
		head2 = (String) questionText.getText();
	    Log.w("head:", "head head2 content: "+head2 );
	    
		//questionText.setBackgroundResource(R.drawable.g_cub45); //SUPER oder
		//questionText.setTextColor(Color.WHITE);
		questionText.setTextColor(Color.CYAN);

		questionText.setPadding(30, 10, 30, 10); 
		questionText.setGravity(Gravity.CENTER); 


		//questionText.setTextColor(R.layout.raduga);
        //radio.setTextColor(Color.rgb( 0, 10, 90));
        //radio.setHeight(48); 
	    //***********TicTac1*********************	
        */
		
		TableLayout tbl=new TableLayout(context);
        // tbl.setBackgroundResource(R.drawable.swp1); //TBL . Tbl . tbl ... oder ??? ---
        tbl.setGravity(Gravity.TOP);
        //# tbl.removeAllViews();
        // GO-HERE>>> content2
	    Log.w("img2:", "img 2 content: "+content);
	    Log.w("img2:", "img 2 content2: "+content2);
	    Log.w("img2:", "img 2 RadioButtonGroup.q_IMG: "+RadioButtonGroup.q_IMG);
	    Log.w("img2:", "img 2 RadioButtonGroup.q_IMG_Flag: "+RadioButtonGroup.q_IMG_Flag);
	         String _path = Environment.getExternalStorageDirectory().getAbsolutePath(); 
			 _path= _path + "/"+ content2 ;			 
	         BitmapDrawable d = new BitmapDrawable(_path);
	         tbl.setBackgroundDrawable(d);	     
	 	     Log.w("img3:", "img7 _path: "+_path);
	 	     Log.w("img3:", "img 3 RadioButtonGroup.q_IMG: "+RadioButtonGroup.q_IMG);
		     Log.w("img3:", "img 3 RadioButtonGroup.q_IMG_Flag: "+RadioButtonGroup.q_IMG_Flag);
		     RadioButtonGroup.q_IMG_Flag = 0; 
	         RadioButtonGroup.q_IMG = ""; 
	         content2="";
			 //_path= _path + "/" + "All_Surveys/tpj/lb4.jpg";

	     if (   RadioButtonGroup.q_IMG_Flag > 0) { 

		 } else {
		        tbl.setBackgroundResource(R.drawable.clum5); //SUPER oder	 
		 }
 	    Log.w("img4:", "img 4 RadioButtonGroup.q_IMG: "+RadioButtonGroup.q_IMG);
	    Log.w("img4:", "img 4 RadioButtonGroup.q_IMG_Flag: "+RadioButtonGroup.q_IMG_Flag);

	 
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT
		        );

         
	    //# addContentView(img, lp);
		tbl.setLayoutParams(lp);
		tbl.setShrinkAllColumns(true);
		TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
		        0, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f );
		//tbl.setPadding(5, 5, 5, 5);
		
       //=====================================================
		// tbl = (TableLayout) rootView.findViewById(R.id.main_table);
		// tbl = (TableLayout) rootView.findViewById(R.id.tblMain);
	   //*****************************************************
		l.addView(tbl);
		int crow=0;
		int ccol=0;
		TableRow tr = null;
		  // cellLp.topMargin = 1;  
		  // cellLp.bottomMargin = 1;  
		  cellLp.rightMargin = 1;  
		  cellLp.leftMargin = 1;  
          // tr.setBackgroundResource(R.drawable.kirsh1);
		  //cellLp.setMarginEnd(2);
		  //cellLp.setMarginStart(0);

		  /*
			 View questionTextB=(View) rootView.findViewById(R.id.text1);
		     tr=new TableRow(context);
		     tr.addView(questionTextB,cellLp); // keypoint !!!
			 tbl.addView(tr,lp); // keypoint !!!
		 	 // inputs.add(object) ;
          */
		  
			TextView label2 = new TextView(context);
			label2.setText(head2);
		    label2.setTextSize(25);
	        label2.setPadding(1, 0, 0, 1);
	        label2.setGravity(Gravity.LEFT );
	        label2.setTextColor(Color.WHITE);
	        
	        





		for (int i = 0; i < inputs.size(); i++) 
		{
			current=inputs.get(i);
			
			if(current.row>crow)
			{
				tr=new TableRow(context);
				if (i % 2 ==1){
				    // tr.setBackgroundColor(Color.GRAY);  // ColorChange
			        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
			        tr.setBackgroundColor(Color.rgb(70, 70, 70));
			        //tr.setBackgroundColor(Color.rgb( 140, 140, 140)); 
			        tr.setBackgroundResource(R.drawable.border4); //SUPER oder
			        // tr.setBackgroundResource(R.drawable.row_borders);
				     tr.setPadding(5, 5, 5, 5);
				} else {
				    //tr.setBackgroundColor(Color.GRAY);  // DKGRAY ColorChange
			        tr.setBackgroundColor(Color.rgb( 120, 120, 120)); 
			        tr.setBackgroundResource(R.drawable.border3); //SUPER oder
				    tr.setPadding(5, 5, 5, 5);
			    }
				// ---------------
				tbl.addView(tr,lp); // keypoint !!!
				crow++;
				if(width<ccol)
					width=ccol;
				ccol=0;
			}
			ccol++;
			View rad=current.display(context);     			
			tr.addView(rad,cellLp); // keypoint !!!
			}
		
		/* pic Super
        tr=new TableRow(context);
        tr.setBackgroundColor(Color.rgb( 120, 120, 120)); 
        tr.setBackgroundResource(R.drawable.border3); //SUPER oder
	    tr.setPadding(15, 5, 5, 5);
		tbl.addView(tr,lp); // keypoint !!!
		tr.addView(label2,cellLp); // keypoint !!!
		*/
		
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
	{   // head2 pic3
	     Log.w("head:", "head head PDF content: "+head2 );

		String result = "";
	    if(width==0)
	    	return null;
		PdfPTable datatable = new PdfPTable(width);	
		datatable.getDefaultCell().setPadding(3);
		datatable.getDefaultCell().setBorderWidth(1);
		datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
		datatable.setHeaderRows(1);
		
	    int ii =0;
	       
		for (InputElement input : inputs) {
			if (ii == 0){
			   String pod;
			   pod = input.writeDataToPdf();
		       
			   // PdfPCell cell = new PdfPCell(new Paragraph(pod));
			   PdfPCell cell = new PdfPCell(new Phrase(pod, FontFactory.getFont(FontFactory.HELVETICA, 14)));
			   cell.setBorderWidth(2);
		       cell.setColspan(7);
		       datatable.addCell(cell );
			} else {

		     // datatable.addCell("Header 1"); // pic3
		     // datatable.addCell("Header \n 2");
			datatable.addCell(input.writeDataToPdf());		
			}
			ii ++;

		}
		
		  String pod2="  ";
		   // PdfPCell cell = new PdfPCell(new Paragraph(pod));
		   PdfPCell cell = new PdfPCell(new Phrase(pod2, FontFactory.getFont(FontFactory.HELVETICA, 14)));
		   cell.setBorderWidth(0);
	       cell.setColspan(7);
	       datatable.addCell(cell );
		
		return datatable;
	}

	
	/*	*/
	public int validate()
	{
		int fV=0;
		int finalValidation=0;
		int finalValidation2=0;

		for(InputElement input:inputs)
		{
		    fV = input.validate();
		    finalValidation = finalValidation & input.validate();
			finalValidation2 =finalValidation2 + input.validate();	
			// Log.w(".441 Question validate():", "finalValidation: "+finalValidation);
			Log.w(".442 Question validate():", "finalValidation2: "+finalValidation2);
			//Log.w(".443 Question validate():", "fV: "+fV);

		}
		// 442
		// Log.w("..444 Question validate():", "finalValidation: "+finalValidation);
		Log.w("..442 Question validate():", "finalValidation2: "+finalValidation2);
		// Log.w(".446 Question validate():", "fV: "+fV);

		//return finalValidation2;			
		return fV;			
	}

	// Override
	/*	*/
	public int validate(String albertRadioTest)
	{	//ToDo	
		int isValidNr =1 ;
		Log.w(">>>999 Radio int validate(xxx):", "albertRadioTest: "+albertRadioTest);
		// return this.isValidCount;
		// return isValidCount; 
		return isValidNr; 

	}

	
}



