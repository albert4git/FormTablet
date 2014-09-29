package com.example.android.effectivenavigation.QuestionTypes;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import android.app.Activity;
import android.view.View;

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
