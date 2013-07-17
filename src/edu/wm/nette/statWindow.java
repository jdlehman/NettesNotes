package edu.wm.nette;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class statWindow extends Activity {
	
	private final int mode = Activity.MODE_PRIVATE;
	private final String APP_PREFS = "App_Prefs";
	
	TextView trebleRight;
	TextView bassRight;
	TextView altoRight;
	TextView tenorRight;
	TextView trebleTotal;
	TextView bassTotal;
	TextView altoTotal;
	TextView tenorTotal;
	TextView noteTotal;
	TextView noteRight;

	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.clef_statistics);
	        
	        
	        trebleRight = (TextView) findViewById(R.id.trebeleRight);
	        bassRight = (TextView) findViewById(R.id.bassRight);
	        tenorRight = (TextView) findViewById(R.id.tenorRight);
	        altoRight = (TextView) findViewById(R.id.altoRight);   
	        trebleTotal = (TextView) findViewById(R.id.trebleTotal);
	        bassTotal = (TextView) findViewById(R.id.bassTotal);
	        tenorTotal = (TextView) findViewById(R.id.tenorTotal);
	        altoTotal = (TextView) findViewById(R.id.altoTotal);
	        noteTotal = (TextView) findViewById(R.id.noteTotal);
	        noteRight = (TextView) findViewById(R.id.noteRight);
	        
	        SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
	        
	        altoTotal.setText(prefs.getInt("altTotal", 0) + "");
	        altoRight.setText(prefs.getInt("altRight", 0) + "");
		    bassTotal.setText(prefs.getInt("basTotal", 0) + "");
		    bassRight.setText(prefs.getInt("basRight", 0) + "");
		    tenorTotal.setText(prefs.getInt("tenTotal", 0) + "");
		    tenorRight.setText(prefs.getInt("tenRight", 0) + "");
		    trebleTotal.setText(prefs.getInt("trebTotal", 0) + "");
		    trebleRight.setText(prefs.getInt("trebRight", 0) + "");
		    noteTotal.setText(prefs.getInt("noteTotal", 0) + "");
		    noteRight.setText(prefs.getInt("noteRight", 0) + "");
		    
	        
	  }
	  
	  public void btnClear(View v){
		  SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
		    SharedPreferences.Editor editor = prefs.edit();
		    //editor.clear();
		    
		    editor.putInt("noteRight", 0);
			editor.putInt("noteTotal", 0);
			editor.putInt("trebRight", 0);
			editor.putInt("trebTotal", 0);
			editor.putInt("altRight", 0);
			editor.putInt("altTotal", 0);
			editor.putInt("basRight", 0);
			editor.putInt("basTotal", 0);
			editor.putInt("tenRight", 0);
			editor.putInt("tenTotal", 0);
		    
		    // Commit to storage
		    editor.commit();
		    
		    altoTotal.setText("0");
		    altoRight.setText("0");
		    bassTotal.setText("0");
		    bassRight.setText("0");
		    tenorTotal.setText("0");
		    tenorRight.setText("0");
		    trebleTotal.setText("0");
		    trebleRight.setText("0");
		    noteTotal.setText("0");
		    noteRight.setText("0");
		    
		    
	  }
}
