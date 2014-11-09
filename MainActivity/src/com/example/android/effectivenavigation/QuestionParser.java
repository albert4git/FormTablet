package com.example.android.effectivenavigation;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.*;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.android.effectivenavigation.QuestionTypes.*;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;

// DATE CHANGER FILE ! ToDo here
public class QuestionParser {
    // We don't use namespaces
    private static final String ns = null;
	private String type,root;
	private int raw;
	private Context context;
	private  File[] files;
	private List <survey> surveys;
	 List <Question> questions;
	 List <Question> entries;
   
	 public QuestionParser(String root) {
		
	}
	 
	 private class survey
	 {
		 public Evaluator evaluator; 
		public survey(File file2) {
			file=file2.toString();
			evaluator= new Evaluator();
		}
		String file;
		int length;
		String equation=null;
		
	 }
	 
	 public List<Question> parseAll(String dir)
	 {
		
		 File f = new File(dir);
		 files = f.listFiles();
		 Arrays.sort(files);
		 questions=new ArrayList();
		 surveys=new ArrayList();
		 if(files.length==0)
			 Toast.makeText(context, "No surveys found in the selected folder", Toast.LENGTH_SHORT);
		 
		 for (File file : files) {
			 try {
				FileInputStream stream = new FileInputStream(file);
				survey cSurvey=new survey(file);
				surveys.add(cSurvey);
				List queTemp = this.parse(stream);
				
				cSurvey.length=queTemp.size();
				
				questions.addAll(queTemp);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("parsing error in "+file);				
				e.printStackTrace();
				
			} catch (IOException e) {
				System.out.println("file opening problem in "+file);		
				e.printStackTrace();
			}
		}
		 return questions;
	}
	 
	 
	 
	 
	 
    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readQuestions(parser);
        } finally {
            in.close();
        }
    }




// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
private Question readQuestion(XmlPullParser parser) throws XmlPullParserException, IOException {
    parser.require(XmlPullParser.START_TAG, ns, "question");
    int id = 0;
    String content = null;
    Question question=new Question();
    question.inputs= new ArrayList();
    String type;
    InputElement currentElement=null;
   
    try {
    	 question.equation= parser.getAttributeValue(null, "equation");
    	 question.id = Integer.parseInt(parser.getAttributeValue(null, "id"));
    	 question.name = parser.getAttributeValue(null, "name");

    	
    	 
	} catch (Exception e) {
		// TODO: handle exception
	}
   
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name = parser.getName();
        if (name.equals("content")) {
        	question.content = readContent(parser);
        } else if (name.equals("input")) {
        	currentElement=readInput(parser, question);
        	if(currentElement!=null)
        	question.inputs.add(currentElement);
      
        } 
        else {
            skip(parser);
        }
    }
    

    return question;
    

}

// Processes title tags in the feed.

// Processes link tags in the feed.


// Processes summary tags in the feed.
private InputElement readInput(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	parser.require(XmlPullParser.START_TAG, ns, "input");
	String type;
	type=parser.getAttributeValue(null, "type"); 	
	
	if(type.equals("edittext"))
	{
		return readEditText(parser,question);
	}
	else if(type.equals("checkbox"))
	{
		return readCheckBox(parser,question);
	}
	else if(type.equals("textview"))
	{
		return readTextView(parser,question);
	}
	
	else if(type.equals("radio"))
	{
		return readRadio(parser,question);
	}
	
	else
	{
		Toast.makeText(context, "Uknown input element type :"+type, Toast.LENGTH_LONG).show();
		
	}

    parser.require(XmlPullParser.END_TAG, ns, "input");
    
  
    return null;
}

// For the tags title and summary, extracts their text values.
private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
    String result = "";
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.getText();
        parser.nextTag();
    }
    return result;
}
 


  private void skip (XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
  private List readQuestions(XmlPullParser parser) throws XmlPullParserException, IOException {
	     entries = new ArrayList();

	    
	     	survey cSurvey;
	    	parser.require(XmlPullParser.START_TAG, ns, "survey");
	        // Starts by looking for the entry tag
	    	cSurvey=surveys.get(surveys.size() - 1);
	    	try {
		    	cSurvey.equation=parser.getAttributeValue(null, "equation");
			} catch (Exception e) {
				// TODO: handle exception
			}

	        while (parser.next() != XmlPullParser.END_TAG) {
	        	if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
	        	 String name = parser.getName();
	        if (name.equals("question")) {
	            entries.add(readQuestion(parser));
	            
	        }
	        else {
	            skip(parser);
	        }
	    }  
	    return entries;
	}
  
  private String readContent(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "content");
	    String title = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "content");
	    return title;
	}
  private InputElement readEditText(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	TextBox	tb=new TextBox(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    tb.defaultText = parser.getAttributeValue(null, "default"); 	
	    tb.row=Integer.parseInt(parser.getAttributeValue(null, "row"));
	    parser.next();
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) tb;
	}
  private InputElement readCheckBox(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	  	Check cb=new Check(question);
	    parser.require(XmlPullParser.START_TAG, ns, "input");
	    cb.defaultState=parser.getAttributeValue(null, "default");
	    try {
			cb.name=parser.getAttributeValue(null, "name");
			cb.coef=parser.getAttributeValue(null, "coefficient");	 
			} catch (Exception e) {
				// TODO: handle exception
			}
	    
	    cb.row=Integer.parseInt(parser.getAttributeValue(null, "row"));
	    parser.next();
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) cb;
	}
  private InputElement readTextView(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	   	Label	lb=new Label(question);
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	    lb.row=Integer.parseInt(parser.getAttributeValue(null, "row"));
	    lb.text =readText(parser);	
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) lb;
	}


  private InputElement readRadio(XmlPullParser parser,Question question) throws IOException, XmlPullParserException {
	  	Radio	r=new Radio(question);
	  	if(question.RadioGroup==null)
	  	question.RadioGroup=new RadioButtonGroup();
	   	parser.require(XmlPullParser.START_TAG, ns, "input");
	   	r.row=Integer.parseInt(parser.getAttributeValue(null, "row"));
	    try {
			r.name=parser.getAttributeValue(null, "name");
			r.coef=parser.getAttributeValue(null, "coefficient");
			} catch (Exception e) {
				// TODO: handle exception
			}
	 	r.group=Integer.parseInt(parser.getAttributeValue(null, "group"));
	   	r.text=readText(parser);
	   	question.RadioGroup.addToGroup(r);
	    parser.require(XmlPullParser.END_TAG, ns, "input");
	    return (InputElement) r;
	}

//-----------------------------/IRA/------------------------------------------------  
  
public void saveToFile(String filename,Context context){
	String iTimeString = null;

	String exStorageState = Environment.getExternalStorageState();
	String outBuf = null;
	Question entry=null;
	int lastInd=0;
	if (Environment.MEDIA_MOUNTED.equals(exStorageState)){
		for (int current = 0; current < surveys.size(); current++) {
			
	survey cSurvey=(survey) surveys.get(current);
				
			try {
				File root = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date(System.currentTimeMillis()));
				int hours = calendar.get(Calendar.HOUR_OF_DAY);
				int minutes = calendar.get(Calendar.MINUTE);
				int seconds = calendar.get(Calendar.SECOND);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int month = calendar.get(Calendar.MONTH)+1;
				int year = calendar.get(Calendar.YEAR);
                // --------------------IRA---------------------------
                    iTimeString = year+"_"+month+"_"+day+"_"+minutes; 
				// Test if the path exists
				String surveyName[] =cSurvey.file.split("/");
				String name;
                // NAME is the SurveyName !!!
				name=surveyName[surveyName.length-1];
				// String path = root+"/SurveyResults/"+minutes+"-"+day+"-"+month+"-"+year+"/"+name+"/";
				String path = root+"/SurveyResults/";
				// If not, create dirs // IRA // whats name ?
				boolean exists = (new File(path).exists());
				if (!exists) {new File(path).mkdirs();}
				// Open the file and a writer
				//+"-"+hours+"-"+minutes+"-"+seconds+".txt"
				// apply PATH
				File logFile = new File(path+year+"-"+month+"-"+day+"-"+minutes+"-IRA-"+name+filename+".xls");
				logFile.createNewFile();
				FileWriter logWriter = new FileWriter(logFile);
				BufferedWriter outer = new BufferedWriter(logWriter);
				// Write log entries to file
	
				//
				
				
				//
			
				outBuf="";
				int i;
				for ( i= lastInd ; i < lastInd +cSurvey.length; i++) {
					
					entry = questions.get(i);
					
				
					outBuf+=entry.writeData(context);
					if(entry.equation!=null)
					{	
						//entry.equation
	
					
					System.out.println(entry.equation);
					}
					outBuf+="\r\n";
					
				}
				lastInd=i;
                // IRA ?
				outer.write(outBuf);
				outer.close();
				Toast.makeText(context, "Survey saved", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(context, "Couldn't save", Toast.LENGTH_SHORT).show();
				
			}
		
		}
	
	}
	else{
		//FAIL
		System.out.println("File not accessible");
		//Toast.makeText(context, R.string.pp_omni_file_not_accessible, Toast.LENGTH_SHORT).show();
	}
}

//-----------------------------/NAT/---------------------------------------------  

public void saveToPdf(String filename,Context context) {
String exStorageState = Environment.getExternalStorageState();
Document document;

String outBuf = null;
String xoutBuf = null;

Question entry=null;
int lastInd=0;

String iTimeString = null;
String USER_PASS = "asd";
String OWNER_PASS = "asdf";


if (Environment.MEDIA_MOUNTED.equals(exStorageState)){
	for (int current = 0; current < surveys.size(); current++) {
		
survey cSurvey=(survey) surveys.get(current);
document = new Document(PageSize.A4, 10, 10, 10, 10);		
		try {
			
			File root = Environment.getExternalStorageDirectory();  // getExternalStorageDirectory();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis()));
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			int minutes = calendar.get(Calendar.MINUTE);
			int seconds = calendar.get(Calendar.SECOND);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH)+1;
			int year = calendar.get(Calendar.YEAR);
                iTimeString = year+"_"+month+"_"+day+"_"+minutes; 
			// Test if the path exists
			String surveyName[] =cSurvey.file.split("/");
			String name;
            // NAT the survay name !!!
			name=surveyName[surveyName.length-1];
		
			//String path = root+"/SurveyResults/"+day+"-"+month+"-"+year+"/"+name+"/";
			String path = root+"/SurveyResults/aTest/";
			boolean exists = (new File(path).exists());
			if (!exists) {new File(path).mkdirs();}
			//AB ___.XLS.___ long write out
            //--------------------------------------------------------------------------------
			File logFile = new File(path+year+"-"+month+"-"+day+"-"+filename+"-"+name+".xls");
			File xlogFile = new File(path+name+".csv");
    		//if file doesnt exists, then create it
    		if(!xlogFile.exists()){
    			xlogFile.createNewFile();
    		}
            //--------------------------------------------------------------------------------
			logFile.createNewFile();
			// DEL xlogFile.createNewFile();
			FileWriter logWriter = new FileWriter(logFile);
			//FileWriter xlogWriter = new FileWriter(xlogFile);
			BufferedWriter outer = new BufferedWriter(logWriter);
			// BufferedWriter xouter = new BufferedWriter(xlogWriter);
			outBuf="";
			xoutBuf="";
            xoutBuf+=iTimeString+";";
            xoutBuf+=filename+";";
            xoutBuf+=name+";";
			// If not, create dirs
			// AB File logFile = new File(path+year+"-"+month+"-"+day+"-"+minutes+"-"+filename+".xls");

			try {
                //--------------------------- // ---------------------------
                //    OutputStream file = new FileOutputStream(new File("D:\\Test.pdf"));
                //    Document document = new Document();
                //    PdfWriter writer = PdfWriter.getInstance(document, file);
                //    writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
                //--------------------------- // ---------------------------
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+year+"-"+month+"-"+day+"-"+filename+"-"+name+".pdf"));
                writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Write log entries to file
			//
			document.open();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.lmubanner);
			bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,
			                        100 /* Ratio */, stream);
			Image jpg = Image.getInstance(stream.toByteArray());
		
			jpg.scalePercent(26f, 26f);
			document.add(jpg);
			String score = null;
			int i;
			for ( i= lastInd ; i < lastInd +cSurvey.length; i++) {
				
				entry = questions.get(i);
				
			
				try {
					Element element = entry.writeDataToPdf(context);
	
					if(element!=null)
					{
						if(entry.equation!=null)
						{	
							 score=entry.evaluator.evaluate(entry.equation);
							 outBuf+=score+"\r\n";
							 xoutBuf+=score+";";
							 if(entry.name!=null)
								 cSurvey.evaluator.putVariable(entry.name, score);
							 document.add(new Paragraph(" \nQuestion "+(i+1)+") score:"+score+"\n\n"));
						}	
						else
							outBuf+="null"+"\r\n";
							//xoutBuf+="null"+"\r\n";
					
					document.add(element);
					
					}
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EvaluationException e) {
					Toast.makeText(context, "Problem with equation in survey "+cSurvey.file , Toast.LENGTH_LONG).show();
										e.printStackTrace();
				}
				
			
				
			}
			lastInd=i;
			String overall = null;
			if(cSurvey.equation!=null)
			{
		    overall=cSurvey.evaluator.evaluate(cSurvey.equation);
			document.add(new Paragraph(" \nOverall Score: "+overall+"\n\n"));
			}

            ///
            // xoutBuf+="\r\n";
            ///
			outer.write(outBuf);
			outer.close();
			//xouter.append(xoutBuf);
		    //xouter.close();
			document.close();
			logWriter.close();
			//xlogWriter.close();

          // String csv =xlogFile;
           CSVWriter writer = new CSVWriter(new FileWriter(xlogFile, true));
           String [] record = xoutBuf.split(";");
           writer.writeNext(record);
          // XXX
           writer.close();

			
			Toast.makeText(context, "Survey saved", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(context, "Couldn't save", Toast.LENGTH_LONG).show();
			
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
else{
	//FAIL
	System.out.println("File not accessible");
	//Toast.makeText(context, R.string.pp_omni_file_not_accessible, Toast.LENGTH_SHORT).show();
}
}
//-----------------------------/YYY/---------------------------------------------  

}
  
  
  
