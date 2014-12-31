/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.effectivenavigation;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import org.xmlpull.v1.XmlPullParserException;

import com.example.android.effectivenavigation.QuestionTypes.*;
import com.example.android.effectivenavigation.QuestionParser.*;



public class CollectionDemoActivity extends FragmentActivity {
	// DISPLAY GOGO
	//Toast.makeText(context, " DiiiSPLAY :", Toast.LENGTH_LONG).show();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    public QuestionParser Qparser;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;
    Mydata data;
    static List <Question> questions;
    static int current=0;
    static int oldFragment=0;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);
        // final TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        Intent intent=getIntent();
        data= (Mydata) intent.getSerializableExtra("Mydata");        
        Qparser=new QuestionParser(data.file);			
        BufferedInputStream buffer = null;
        //  buffer= new BufferedInputStream(this.getResources().openRawResource(R.raw.bdi)); 
        //	stream = new FileInputStream();//new FileInputStream(data.file);
        questions=new ArrayList();
        questions=Qparser.parseAll(data.file);
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        // actionBar.setDisplayHomeAsUpEnabled(true);
        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        OnPageChangeListener listener= new OnPageChangeListener(){ 
            // ?? setPageView(R.layout.activity_collection_demo);

			@Override
			public void onPageSelected(int arg0) {
				
				  if(mViewPager.getCurrentItem()==questions.size())
		            {
					// DIGN ? //
		            CollectionDemoActivity.this.Qparser.saveToPdf(data.name , data.dign ,mViewPager.getContext());
		        	System.out.println("Qparser, mydata, data,  works ? Albert ");
		    		// DISPLAY GOGO nach dem Save Datat ...
		    		// Toast.makeText(getBaseContext(), " ViewPager DISPLAY :", Toast.LENGTH_LONG).show();
		            }
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		};
        mViewPager.setOnPageChangeListener(listener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
  
  	// DISPLAY GOGO
    public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
 	
            Fragment fragment = new DemoObjectFragment();

            // Parabelum !!! %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
              Log.w("--CDA-P1 FRAGMENT.getItem", "fragment.getId() #:"+fragment.getId());
              Log.w("--CDA-P1 FRAGMENT.getTag", "fragment.getId() #:"+fragment.getTag());
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
         
            Bundle args = new Bundle();
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
            fragment.setArguments(args);
            // Parabelum !!! %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            Log.w("--CDA-P2 FRAGMENT.getItem", "fragment.getId() #:"+fragment.getId());
            Log.w("--CDA-P2 FRAGMENT.getTag", "fragment.getId() #:"+fragment.getTag());
          //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 100-object collection.
            return questions.size()+1;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "Frage " + (position + 1);
        }
      	// DISPLAY GOGO
    }

    
    /**
     * Mondragon
     */
    public static class DemoObjectFragment extends Fragment {
        //check the static current ???
        public static final String ARG_OBJECT = "question";
        int testVal; // Yes
      
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            String content;
            int current=args.getInt(ARG_OBJECT)-1;
            int currentFragment =args.getInt(ARG_OBJECT)-1;
            // Modragon !!! %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            Log.w("661 CDA Fragment:", "quest.size is:"+questions.size());
            Log.w("662 CDA FRAGMENT", "Current %:"+current);
            Log.w("663 CDA FRAGMENT", "CurrentFragment %:"+currentFragment);
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            

            
            if (current==questions.size())
            {
         	   TextView questionText=(TextView) rootView.findViewById(R.id.text1);
        			questionText.setText(" Vielen dank !");
        			
         	   return rootView;
            }
            
            if (current < -1)
            {
                // Napoleon !!! %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                Question questionPre=questions.get(current -1);
                testVal= questionPre.validate(); // Yes
                Log.w("!!!! CDA FRAGMENT.Pre", "!!!!!");
                Log.w("!881 CDA FRAGMENT.Pre", "testVal%:"+testVal);
                Log.w("882 CDA FRAGMENT.Pre", "questionPre.content %:"+questionPre.content);
                Log.w("883 CDA FRAGMENT.Pre", "questionPre.equation %:"+questionPre.equation);
                //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            }

            // THE Current Question !!
            // Napoleon !!! %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            Log.w("---!-!-!!A CDA FRAGMENT.Post", "!!-!-!-!---");
            Question question=questions.get(current);
            testVal= question.validate(); // Yes
            Log.w("---!-!-!!B CDA FRAGMENT.Post", "!!-!-!-!---");
            Log.w("!888 CDA FRAGMENT.Post", "testVal%:"+testVal);
            Log.w("887 CDA FRAGMENT.Post", "question.equation %:"+question.equation);
            Log.w("886 CDA FRAGMENT.Post", "question.content %:"+question.content);
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            Log.w("@889 CDA.RRR:", "RadioButtonGroup.statBoxName:"+RadioButtonGroup.statBoxName );
            Log.w("@889 CDA.RRR:", "RadioButtonGroup.statBoxTest:"+RadioButtonGroup.statBoxText );
            Log.w("@889 CDA.RRR:", "RadioButtonGroup.statBoxG:"+RadioButtonGroup.statBoxG );
            Log.w("@889 CDA.RRR:", "RadioButtonGroup.statBoxCoef:"+RadioButtonGroup.statBoxCoef );

            return  question.display(getActivity(),rootView);
        }


    }
}
