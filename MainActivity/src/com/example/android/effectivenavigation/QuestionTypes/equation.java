package com.example.android.effectivenavigation.QuestionTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.example.android.effectivenavigation.R;//1

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView; //1

public class equation {
	public int id;
	public String equation;
	Evaluator evaluator ;
	public equation(Question question) {	
		// TODO Auto-generated constructor stub
		evaluator = new Evaluator();
				
	}
	void eval(){
		   
	       String result=null;
	       evaluator.putVariable("a", "2");
	       try {
				result=evaluator.evaluate("#{a}*5");
			} catch (EvaluationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

}
