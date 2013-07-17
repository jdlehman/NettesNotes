package edu.wm.nette;

import java.util.Random;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class noteWindow extends Activity{
	
	private final int mode = Activity.MODE_PRIVATE;
	private final String APP_PREFS = "App_Prefs";
	
	ImageView im;
	TextView cor;
	TextView tot;
	
	Random rand;
	Boolean useTreble = false;
	Boolean useBass = false;
	Boolean useAlto = false;
	Boolean useTenor = false;
	Boolean validClef = false;
	Boolean first = true;
	String correctAnswer = "";
	String image = "";
	String abox = "";
	String bbox = "";
	String cbox = "";
	String dbox = "";
	String ebox = "";
	String fbox = "";
	String gbox = "";
	
	int correct;
	int total;
	
	int numUsing = 0;
	int treb = -1;
	int alt = -1;
	int bas = -1;
	int ten = -1;
	
	String curClef = "";
	int noteRight = 0;
	int noteTotal = 0;
	int trebRight = 0;
	int trebTotal = 0;
	int altRight = 0;
	int altTotal = 0;
	int basRight = 0;
	int basTotal = 0;
	int tenRight = 0;
	int tenTotal = 0;

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.notewin);
	        
	        //get widget objects
	        im = (ImageView) findViewById(R.id.notepic);
	        tot = (TextView) findViewById(R.id.totalq);
	        cor = (TextView) findViewById(R.id.numcorrect);
	        
	        //load shared preferences
	        SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
	    	//set any shared preferences
	    	cor.setText(prefs.getInt("correct", 0) + "");
	    	tot.setText(prefs.getInt("total", 0) + "");
	        
	        correct = prefs.getInt("correct", 0);
	    	total = prefs.getInt("total", 0);
	    	image = prefs.getString("image", "none");
	    	correctAnswer =  prefs.getString("corrAns", "");
	    	
	        numUsing = 0;
	    	treb = -1;
	    	alt = -1;
	    	bas = -1;
	    	ten = -1;
	        
	        Bundle bundle = getIntent().getExtras();
	        
	        //get user data from bundle
	        if((useTreble = (Boolean) bundle.get("treble"))){
	        	treb = numUsing;
	        	numUsing++;
	        }
	        if((useBass = (Boolean) bundle.get("bass"))){
	        	bas = numUsing;
	        	numUsing++;
	        }
	        if((useAlto = (Boolean) bundle.get("alto"))){
	        	alt = numUsing;
	        	numUsing++;
	        }
	        if((useTenor = (Boolean) bundle.get("tenor"))){
	        	ten = numUsing;
	        	numUsing++;
	        }
	        
	        setButtonColors();
	          
	        if(image.equals("none")){
	        	generateRandomNote();
	        }
	        else{
	        	useOldImage(image);
	        }
	  }
	 
	 protected void onPause(){
		 super.onPause();
		 
		 	// Store values 
	    	SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
		    SharedPreferences.Editor editor = prefs.edit();
		    //editor.clear();
			
			//get old statistic values to add to current values
			int nRight = prefs.getInt("noteRight", 0);
			int nTot = prefs.getInt("noteTotal", 0);
			int trRight = prefs.getInt("trebRight", 0);
			int trTot = prefs.getInt("trebTotal", 0);
			int aRight = prefs.getInt("altRight", 0);
			int aTot = prefs.getInt("altTotal", 0);
			int bRight = prefs.getInt("basRight", 0);
			int bTot = prefs.getInt("basTotal", 0);
			int teRight = prefs.getInt("tenRight", 0);
			int teTot = prefs.getInt("tenTotal", 0);
			
			editor.putInt("noteRight", (nRight + noteRight));
			editor.putInt("noteTotal", (nTot + noteTotal));
			editor.putInt("trebRight", (trRight + trebRight));
			editor.putInt("trebTotal", (trTot + trebTotal));
			editor.putInt("altRight", (aRight + altRight));
			editor.putInt("altTotal", (aTot + altTotal));
			editor.putInt("basRight", (bRight + basRight));
			editor.putInt("basTotal", (bTot + basTotal));
			editor.putInt("tenRight", (teRight + tenRight));
			editor.putInt("tenTotal", (teTot + tenTotal));
		    
		    editor.putInt("correct", Integer.parseInt(cor.getText().toString()));
		    editor.putInt("total", Integer.parseInt(tot.getText().toString()));
		    editor.putString("image", image);
		    editor.putString("corrAns", correctAnswer);
		    editor.putString("Abox", abox);
		    editor.putString("Bbox", bbox);
		    editor.putString("Cbox", cbox);
		    editor.putString("Dbox", dbox);
		    editor.putString("Ebox", ebox);
		    editor.putString("Fbox", fbox);
		    editor.putString("Gbox", gbox);
		    
		    // Commit to storage
		    editor.commit();
	 }
	 
	 public void setButtonColors(){
		//load shared preferences
        SharedPreferences prefs = getSharedPreferences(APP_PREFS, mode);
    	//set existing button colors
    	if(prefs.getString("Abox", "").equals("red")){
    		Drawable d = findViewById(R.id.a).getBackground();  
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
            d.setColorFilter(filter); 
            abox = "red";
    	}
    	if(prefs.getString("Bbox", "").equals("red")){
    		Drawable d = findViewById(R.id.b).getBackground();  
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
            d.setColorFilter(filter); 
            bbox = "red";
    	}
    	if(prefs.getString("Cbox", "").equals("red")){
    		Drawable d = findViewById(R.id.c).getBackground();  
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
            d.setColorFilter(filter); 
            cbox = "red";
    	}
		if(prefs.getString("Dbox", "").equals("red")){
			Drawable d = findViewById(R.id.d).getBackground();  
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
            d.setColorFilter(filter); 
            dbox = "red";
		}
		if(prefs.getString("Ebox", "").equals("red")){
			Drawable d = findViewById(R.id.e).getBackground();  
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
            d.setColorFilter(filter); 
            ebox = "red";
		}
		if(prefs.getString("Fbox", "").equals("red")){
			Drawable d = findViewById(R.id.f).getBackground();  
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
            d.setColorFilter(filter); 
            fbox = "red";
		}
		if(prefs.getString("Gbox", "").equals("red")){
			Drawable d = findViewById(R.id.g).getBackground();  
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
            d.setColorFilter(filter); 
            gbox = "red";
		}
	 }
	 
	 public void generateRandomNote(){
		 resetButtons();
		 
		 //seed random generator with time
		 rand = new Random(System.nanoTime());
		 
		 int clefNum = rand.nextInt(numUsing);
		 
		 if(treb == clefNum){
			 curClef = "treb";
			 generateTrebleNote();
		 }
		 else if(bas == clefNum){
			 curClef = "bas";
			 generateBassNote();
		 }
		 else if(ten == clefNum){
			 curClef= "ten";
			 generateTenorNote();
		 }
		 else if(alt == clefNum){
			 curClef = "alt";
			 generateAltoNote();
		 }
		 
		 
	 }
	 
	 public void generateTrebleNote(){
		 rand = new Random(System.nanoTime());
		 
		 int noteNum = rand.nextInt(17);
		 
		 switch(noteNum){
		 	case 0:
		 		im.setImageResource(R.drawable.treblea1);
		 		image = "treblea1";
		 		correctAnswer = "a";
		 		break;
		 	case 1:
		 		im.setImageResource(R.drawable.treblea2);
		 		image = "treblea2";
		 		correctAnswer = "a";
		 		break;
		 	case 2:
		 		im.setImageResource(R.drawable.treblea3);
		 		image = "treblea3";
		 		correctAnswer = "a";
		 		break;
		 	case 3:
		 		im.setImageResource(R.drawable.trebleb1);
		 		image = "trebleb1";
		 		correctAnswer = "b";
		 		break;
		 	case 4:
		 		im.setImageResource(R.drawable.trebleb2);
		 		image = "trebleb2";
		 		correctAnswer = "b";
		 		break;
		 	case 5:
		 		im.setImageResource(R.drawable.trebleb3);
		 		image = "trebleb3";
		 		correctAnswer = "b";
		 		break;
		 	case 6:
		 		im.setImageResource(R.drawable.treblec1);
		 		image = "treblec1";
		 		correctAnswer = "c";
		 		break;
		 	case 7:
		 		im.setImageResource(R.drawable.treblec2);
		 		image = "treblec2";
		 		correctAnswer = "c";
		 		break;
		 	case 8:
		 		im.setImageResource(R.drawable.treblec3);
		 		image = "treblec3";
		 		correctAnswer = "c";
		 		break;
		 	case 9:
		 		im.setImageResource(R.drawable.trebled1);
		 		image = "trebled1";
		 		correctAnswer = "d";
		 		break;
		 	case 10:
		 		im.setImageResource(R.drawable.trebled2);
		 		image = "trebled2";
		 		correctAnswer = "d";
		 		break;
		 	case 11:
		 		im.setImageResource(R.drawable.treblee1);
		 		image = "treblee1";
		 		correctAnswer = "e";
		 		break;
		 	case 12:
		 		im.setImageResource(R.drawable.treblee2);
		 		image = "treblee2";
		 		correctAnswer = "e";
		 		break;
		 	case 13:
		 		im.setImageResource(R.drawable.treblef1);
		 		image = "treblef1";
		 		correctAnswer = "f";
		 		break;
		 	case 14:
		 		im.setImageResource(R.drawable.treblef2);
		 		image = "treblef2";
		 		correctAnswer = "f";
		 		break;
		 	case 15:
		 		im.setImageResource(R.drawable.trebleg1);
		 		image = "trebleg1";
		 		correctAnswer = "g";
		 		break;
		 	case 16:
		 		im.setImageResource(R.drawable.trebleg2);
		 		image = "trebleg2";
		 		correctAnswer = "g";
		 		break;
		 }
	 }
	 
	 public void generateBassNote(){
		 rand = new Random(System.nanoTime());
		 
		 int noteNum = rand.nextInt(17);
		 
		 switch(noteNum){
		 	case 0:
		 		im.setImageResource(R.drawable.bassa);
		 		image = "bassa";
		 		correctAnswer = "a";
		 		break;
		 	case 1:
		 		im.setImageResource(R.drawable.bassa2);
		 		image = "bassa2";
		 		correctAnswer = "a";
		 		break;
		 	case 2:
		 		im.setImageResource(R.drawable.bassb);
		 		image = "bassb";
		 		correctAnswer = "b";
		 		break;
		 	case 3:
		 		im.setImageResource(R.drawable.bassb2);
		 		image = "bassb2";
		 		correctAnswer = "b";
		 		break;
		 	case 4:
		 		im.setImageResource(R.drawable.bassc);
		 		image = "bassc";
		 		correctAnswer = "c";
		 		break;
		 	case 5:
		 		im.setImageResource(R.drawable.bassc2);
		 		image = "bassc2";
		 		correctAnswer = "c";
		 		break;
		 	case 6:
		 		im.setImageResource(R.drawable.bassc3);
		 		image = "bassc3";
		 		correctAnswer = "c";
		 		break;
		 	case 7:
		 		im.setImageResource(R.drawable.bassd);
		 		image = "bassd";
		 		correctAnswer = "d";
		 		break;
		 	case 8:
		 		im.setImageResource(R.drawable.bassd2);
		 		image = "bassd2";
		 		correctAnswer = "d";
		 		break;
		 	case 9:
		 		im.setImageResource(R.drawable.bassd3);
		 		image = "bassd3";
		 		correctAnswer = "d";
		 		break;
		 	case 10:
		 		im.setImageResource(R.drawable.basse);
		 		image = "basse";
		 		correctAnswer = "e";
		 		break;
		 	case 11:
		 		im.setImageResource(R.drawable.basse2);
		 		image = "basse2";
		 		correctAnswer = "e";
		 		break;
		 	case 12:
		 		im.setImageResource(R.drawable.basse3);
		 		image = "basse3";
		 		correctAnswer = "e";
		 		break;
		 	case 13:
		 		im.setImageResource(R.drawable.bassf);
		 		image = "bassf";
		 		correctAnswer = "f";
		 		break;
		 	case 14:
		 		im.setImageResource(R.drawable.bassf2);
		 		image = "bassf2";
		 		correctAnswer = "f";
		 		break;
		 	case 15:
		 		im.setImageResource(R.drawable.bassg);
		 		image = "bassg";
		 		correctAnswer = "g";
		 		break;
		 	case 16:
		 		im.setImageResource(R.drawable.bassg2);
		 		image = "bassg2";
		 		correctAnswer = "g";
		 		break;
		 }
	 }
	 
	 public void generateTenorNote(){
		 rand = new Random(System.nanoTime());
		 
		 int noteNum = rand.nextInt(17);
		 
		 switch(noteNum){
		 	case 0:
		 		im.setImageResource(R.drawable.tenora);
		 		image = "tenora";
		 		correctAnswer = "a";
		 		break;
		 	case 1:
		 		im.setImageResource(R.drawable.tenora2);
		 		image = "tenora2";
		 		correctAnswer = "a";
		 		break;
		 	case 2:
		 		im.setImageResource(R.drawable.tenora3);
		 		image = "tenora3";
		 		correctAnswer = "a";
		 		break;
		 	case 3:
		 		im.setImageResource(R.drawable.tenorb);
		 		image = "tenorb";
		 		correctAnswer = "b";
		 		break;
		 	case 4:
		 		im.setImageResource(R.drawable.tenorb2);
		 		image = "tenorb2";
		 		correctAnswer = "b";
		 		break;
		 	case 5:
		 		im.setImageResource(R.drawable.tenorb3);
		 		image = "tenorb3";
		 		correctAnswer = "b";
		 		break;
		 	case 6:
		 		im.setImageResource(R.drawable.tenorc);
		 		image = "tenorc";
		 		correctAnswer = "c";
		 		break;
		 	case 7:
		 		im.setImageResource(R.drawable.tenorc2);
		 		image = "tenorc2";
		 		correctAnswer = "c";
		 		break;
		 	case 8:
		 		im.setImageResource(R.drawable.tenord);
		 		image = "tenord";
		 		correctAnswer = "d";
		 		break;
		 	case 9:
		 		im.setImageResource(R.drawable.tenord2);
		 		image = "tenord2";
		 		correctAnswer = "d";
		 		break;
		 	case 10:
		 		im.setImageResource(R.drawable.tenore);
		 		image = "tenore";
		 		correctAnswer = "e";
		 		break;
		 	case 11:
		 		im.setImageResource(R.drawable.tenore2);
		 		image = "tenore2";
		 		correctAnswer = "e";
		 		break;
		 	case 12:
		 		im.setImageResource(R.drawable.tenorf);
		 		image = "tenorf";
		 		correctAnswer = "f";
		 		break;
		 	case 13:
		 		im.setImageResource(R.drawable.tenorf2);
		 		image = "tenorf2";
		 		correctAnswer = "f";
		 		break;
		 	case 14:
		 		im.setImageResource(R.drawable.tenorg);
		 		image = "tenorg";
		 		correctAnswer = "g";
		 		break;
		 	case 15:
		 		im.setImageResource(R.drawable.tenorg2);
		 		image = "tenorg2";
		 		correctAnswer = "g";
		 		break;
		 	case 16:
		 		im.setImageResource(R.drawable.tenorg3);
		 		image = "tenorg3";
		 		correctAnswer = "g";
		 		break;
		 }
	 }
	 
	 public void generateAltoNote(){
		 rand = new Random(System.nanoTime());
		 
		 int noteNum = rand.nextInt(17);
		 
		 switch(noteNum){
		 	case 0:
		 		im.setImageResource(R.drawable.altoa);
		 		correctAnswer = "a";
		 		image = "altoa";
		 		break;
		 	case 1:
		 		im.setImageResource(R.drawable.altoa2);
		 		image = "altoa2";
		 		correctAnswer = "a";
		 		break;
		 	case 2:
		 		im.setImageResource(R.drawable.altob);
		 		image = "altob";
		 		correctAnswer = "b";
		 		break;
		 	case 3:
		 		im.setImageResource(R.drawable.altob2);
		 		image = "altob2";
		 		correctAnswer = "b";
		 		break;
		 	case 4:
		 		im.setImageResource(R.drawable.altob3);
		 		image = "altob3";
		 		correctAnswer = "b";
		 		break;
		 	case 5:
		 		im.setImageResource(R.drawable.altoc);
		 		image = "altoc";
		 		correctAnswer = "c";
		 		break;
		 	case 6:
		 		im.setImageResource(R.drawable.altoc2);
		 		image = "altoc2";
		 		correctAnswer = "c";
		 		break;
		 	case 7:
		 		im.setImageResource(R.drawable.altoc3);
		 		image = "altoc3";
		 		correctAnswer = "c";
		 		break;
		 	case 8:
		 		im.setImageResource(R.drawable.altod);
		 		image = "altod";
		 		correctAnswer = "d";
		 		break;
		 	case 9:
		 		im.setImageResource(R.drawable.altod2);
		 		image = "altod2";
		 		correctAnswer = "d";
		 		break;
		 	case 10:
		 		im.setImageResource(R.drawable.altod3);
		 		image = "altod3";
		 		correctAnswer = "d";
		 		break;
		 	case 11:
		 		im.setImageResource(R.drawable.altoe);
		 		image = "altoe";
		 		correctAnswer = "e";
		 		break;
		 	case 12:
		 		im.setImageResource(R.drawable.altoe2);
		 		image = "altoe2";
		 		correctAnswer = "e";
		 		break;
		 	case 13:
		 		im.setImageResource(R.drawable.altof);
		 		image = "altof";
		 		correctAnswer = "f";
		 		break;
		 	case 14:
		 		im.setImageResource(R.drawable.altof2);
		 		image = "altof2";
		 		correctAnswer = "f";
		 		break;
		 	case 15:
		 		im.setImageResource(R.drawable.altog);
		 		image = "altog";
		 		correctAnswer = "g";
		 		break;
		 	case 16:
		 		im.setImageResource(R.drawable.altog2);
		 		image = "altog2";
		 		correctAnswer = "g";
		 		break;
		 }
	 }
	 
	 public void resetButtons(){
		 Drawable d = findViewById(R.id.a).getBackground();  
         findViewById(R.id.a).invalidateDrawable(d);  
         d.clearColorFilter();  
         
         d = findViewById(R.id.b).getBackground();  
         findViewById(R.id.b).invalidateDrawable(d);  
         d.clearColorFilter();
         
         d = findViewById(R.id.c).getBackground();  
         findViewById(R.id.c).invalidateDrawable(d);  
         d.clearColorFilter(); 
         
         d = findViewById(R.id.d).getBackground();  
         findViewById(R.id.d).invalidateDrawable(d);  
         d.clearColorFilter();  
         
         d = findViewById(R.id.e).getBackground();  
         findViewById(R.id.e).invalidateDrawable(d);  
         d.clearColorFilter();  
         
         d = findViewById(R.id.f).getBackground();  
         findViewById(R.id.f).invalidateDrawable(d);  
         d.clearColorFilter();  
         
         d = findViewById(R.id.g).getBackground();  
         findViewById(R.id.g).invalidateDrawable(d);  
         d.clearColorFilter();  
         
	 }
	 
	 public void isCorrect(){
		 noteRight++;
		 noteTotal++;
		 
		 if(curClef.equals("treb")){
			 trebRight++;
			 trebTotal++;
		 }
		 else if(curClef.equals("bas")){
			 basRight++;
			 basTotal++;
		 }
		 else if(curClef.equals("alt")){
			 altRight++;
			 altTotal++;
		 }
		 else if(curClef.equals("ten")){
			 tenRight++;
			 tenTotal++;
		 }
	 }
	 
	 public void isWrong(){
		 noteTotal++;
		 
		 if(curClef.equals("treb")){
			 trebTotal++;
		 }
		 else if(curClef.equals("bas")){
			 basTotal++;
		 }
		 else if(curClef.equals("alt")){
			 altTotal++;
		 }
		 else if(curClef.equals("ten")){
			 tenTotal++;
		 }
	 }
	 
	 public void btnA(View v){
		 total++;
		 
		 if(correctAnswer.equals("a")){
//			 Drawable d = findViewById(R.id.a).getBackground();  
//             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);  
//             d.setColorFilter(filter); 
			 abox = "";
			 bbox = "";
			 cbox = "";
			 dbox = "";
			 ebox = "";
			 fbox = "";
			 gbox = "";
             correct++;
             
             isCorrect();
         	 
             generateRandomNote();
		 }
		 else{
			 Drawable d = findViewById(R.id.a).getBackground();  
             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
             d.setColorFilter(filter); 
             
             isWrong();
             
             abox = "red";
		 }
		 
		 cor.setText(correct + "");
		 tot.setText(total + "");
		 
	 }
	 
	 public void btnB(View v){
		 total++;
		 
		 if(correctAnswer.equals("b")){
//			 Drawable d = findViewById(R.id.b).getBackground();  
//             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);  
//             d.setColorFilter(filter); 
			 abox = "";
			 bbox = "";
			 cbox = "";
			 dbox = "";
			 ebox = "";
			 fbox = "";
			 gbox = "";
             correct++;
             
             isCorrect();
             
             generateRandomNote();
		 }
		 else{
			 Drawable d = findViewById(R.id.b).getBackground();  
             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
             d.setColorFilter(filter); 
             
             isWrong();
             
             bbox = "red";
		 }
		 
		 cor.setText(correct + "");
		 tot.setText(total + "");
		 
	 }
	 
	 public void btnC(View v){
		 total++;
		 
		 if(correctAnswer.equals("c")){
//			 Drawable d = findViewById(R.id.c).getBackground();  
//             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);  
//             d.setColorFilter(filter); 
			 abox = "";
			 bbox = "";
			 cbox = "";
			 dbox = "";
			 ebox = "";
			 fbox = "";
			 gbox = "";
             correct++;
             
             isCorrect();
             
             generateRandomNote();
		 }
		 else{
			 Drawable d = findViewById(R.id.c).getBackground();  
             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
             d.setColorFilter(filter); 
             
             isWrong();
             
             cbox = "red";
		 }
		 
		 cor.setText(correct + "");
		 tot.setText(total + "");
		 
	 }
	 
	 public void btnD(View v){
		 total++;
		 
		 if(correctAnswer.equals("d")){
//			 Drawable d = findViewById(R.id.d).getBackground();  
//             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);  
//             d.setColorFilter(filter); 
			 abox = "";
			 bbox = "";
			 cbox = "";
			 dbox = "";
			 ebox = "";
			 fbox = "";
			 gbox = "";
             correct++;
             
             isCorrect();
             
             generateRandomNote();
		 }
		 else{
			 Drawable d = findViewById(R.id.d).getBackground();  
             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
             d.setColorFilter(filter); 
             
             isWrong();
             
             dbox = "red";
		 }
		 
		 cor.setText(correct + "");
		 tot.setText(total + "");
		 
	 }
	 
	 public void btnE(View v){
		 total++;
		 
		 if(correctAnswer.equals("e")){
//			 Drawable d = findViewById(R.id.e).getBackground();  
//             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);  
//             d.setColorFilter(filter); 
			 abox = "";
			 bbox = "";
			 cbox = "";
			 dbox = "";
			 ebox = "";
			 fbox = "";
			 gbox = "";
             correct++;
             
             isCorrect();
             
             generateRandomNote();
		 }
		 else{
			 Drawable d = findViewById(R.id.e).getBackground();  
             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
             d.setColorFilter(filter); 
             
             isWrong();
             
             ebox = "red";
		 }
		 
		 cor.setText(correct + "");
		 tot.setText(total + "");
		 
	 }
	 
	 public void btnF(View v){
		 total++;
		 
		 if(correctAnswer.equals("f")){
//			 Drawable d = findViewById(R.id.f).getBackground();  
//             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);  
//             d.setColorFilter(filter); 
			 abox = "";
			 bbox = "";
			 cbox = "";
			 dbox = "";
			 ebox = "";
			 fbox = "";
			 gbox = "";
             correct++;
             
             isCorrect();
             
             generateRandomNote();
		 }
		 else{
			 Drawable d = findViewById(R.id.f).getBackground();  
             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
             d.setColorFilter(filter); 
             
             isWrong();
             
             fbox = "red";
		 }
		 
		 cor.setText(correct + "");
		 tot.setText(total + "");
		 
	 }
	 
	 public void btnG(View v){
		 total++;
		 
		 if(correctAnswer.equals("g")){
//			 Drawable d = findViewById(R.id.g).getBackground();  
//             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);  
//             d.setColorFilter(filter); 
			 abox = "";
			 bbox = "";
			 cbox = "";
			 dbox = "";
			 ebox = "";
			 fbox = "";
			 gbox = "";
             correct++;
             
             isCorrect();
             
             generateRandomNote();
		 }
		 else{
			 Drawable d = findViewById(R.id.g).getBackground();  
             PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);  
             d.setColorFilter(filter); 
             
             isWrong();
             
             gbox = "red";
		 }
		 
		 cor.setText(correct + "");
		 tot.setText(total + "");
		 
	 }
	 
	 public void useOldImage(String img){
		 //put the old picture up
		 if(img.equals("treblea1")){
			 im.setImageResource(R.drawable.treblea1);
		 }
		 else if(img.equals("treblea2")){
			 im.setImageResource(R.drawable.treblea2);
		 }
		 else if(img.equals("treblea3")){
			 im.setImageResource(R.drawable.treblea3);
		 }
		 else if(img.equals("trebleb1")){
			 im.setImageResource(R.drawable.trebleb1);
		 }
		 else if(img.equals("trebleb2")){
			 im.setImageResource(R.drawable.trebleb2);
		 }
		 else if(img.equals("trebleb3")){
			 im.setImageResource(R.drawable.trebleb3);
		 }
		 else if(img.equals("treblec1")){
			 im.setImageResource(R.drawable.treblec1);
		 }
		 else if(img.equals("treblec2")){
			 im.setImageResource(R.drawable.treblec2);
		 }
		 else if(img.equals("treblec3")){
			 im.setImageResource(R.drawable.treblec3);
		 }
		 else if(img.equals("trebled1")){
			 im.setImageResource(R.drawable.trebled1);
		 }
		 else if(img.equals("trebled2")){
			 im.setImageResource(R.drawable.trebled2);
		 }
		 else if(img.equals("treblee1")){
			 im.setImageResource(R.drawable.treblee1);
		 }
		 else if(img.equals("treblee2")){
			 im.setImageResource(R.drawable.treblee2);
		 }
		 else if(img.equals("treblef1")){
			 im.setImageResource(R.drawable.treblef1);
		 }
		 else if(img.equals("treblef2")){
			 im.setImageResource(R.drawable.treblef2);
		 }
		 else if(img.equals("trebleg1")){
			 im.setImageResource(R.drawable.trebleg1);
		 }
		 else if(img.equals("trebleg2")){
			 im.setImageResource(R.drawable.trebleg2);
		 }
		 else if(img.equals("bassa")){
			 im.setImageResource(R.drawable.bassa);
		 }
		 else if(img.equals("bassa2")){
			 im.setImageResource(R.drawable.bassa2);
		 }
		 else if(img.equals("bassb")){
			 im.setImageResource(R.drawable.bassb);
		 }
		 else if(img.equals("bassb2")){
			 im.setImageResource(R.drawable.bassb2);
		 }
		 else if(img.equals("bassc")){
			 im.setImageResource(R.drawable.bassc);
		 }
		 else if(img.equals("bassc2")){
			 im.setImageResource(R.drawable.bassc2);
		 }
		 else if(img.equals("bassc3")){
			 im.setImageResource(R.drawable.bassc3);
		 }
		 else if(img.equals("bassd")){
			 im.setImageResource(R.drawable.bassd);
		 }
		 else if(img.equals("bassd2")){
			 im.setImageResource(R.drawable.bassd2);
		 }
		 else if(img.equals("bassd3")){
			 im.setImageResource(R.drawable.bassd3);
		 }
		 else if(img.equals("basse")){
			 im.setImageResource(R.drawable.basse);
		 }
		 else if(img.equals("basse2")){
			 im.setImageResource(R.drawable.basse2);
		 }
		 else if(img.equals("basse3")){
			 im.setImageResource(R.drawable.basse3);
		 }
		 else if(img.equals("bassf")){
			 im.setImageResource(R.drawable.bassf);
		 }
		 else if(img.equals("bassf2")){
			 im.setImageResource(R.drawable.bassf2);
		 }
		 else if(img.equals("bassg")){
			 im.setImageResource(R.drawable.bassg);
		 }
		 else if(img.equals("bassg2")){
			 im.setImageResource(R.drawable.bassg2);
		 }
		 else if(img.equals("tenora")){
			 im.setImageResource(R.drawable.tenora);
		 }
		 else if(img.equals("tenora2")){
			 im.setImageResource(R.drawable.tenora2);
		 }
		 else if(img.equals("tenora3")){
			 im.setImageResource(R.drawable.tenora3);
		 }
		 else if(img.equals("tenorb")){
			 im.setImageResource(R.drawable.tenorb);
		 }
		 else if(img.equals("tenorb2")){
			 im.setImageResource(R.drawable.tenorb2);
		 }
		 else if(img.equals("tenorb3")){
			 im.setImageResource(R.drawable.tenorb3);
		 }
		 else if(img.equals("tenorc")){
			 im.setImageResource(R.drawable.tenorc);
		 }
		 else if(img.equals("tenorc2")){
			 im.setImageResource(R.drawable.tenorc2);
		 }
		 else if(img.equals("tenord")){
			 im.setImageResource(R.drawable.tenord);
		 }
		 else if(img.equals("tenord2")){
			 im.setImageResource(R.drawable.tenord2);
		 }
		 else if(img.equals("tenore")){
			 im.setImageResource(R.drawable.tenore);
		 }
		 else if(img.equals("tenore2")){
			 im.setImageResource(R.drawable.tenore2);
		 }
		 else if(img.equals("tenorf")){
			 im.setImageResource(R.drawable.tenorf);
		 }
		 else if(img.equals("tenorf2")){
			 im.setImageResource(R.drawable.tenorf2);
		 } 
		 else if(img.equals("tenorg")){
			 im.setImageResource(R.drawable.tenorg);
		 }
		 else if(img.equals("tenorg2")){
			 im.setImageResource(R.drawable.tenorg2);
		 }
		 else if(img.equals("altoa")){
			 im.setImageResource(R.drawable.altoa);
		 }
		 else if(img.equals("altoa2")){
			 im.setImageResource(R.drawable.altoa2);
		 }
		 else if(img.equals("altob")){
			 im.setImageResource(R.drawable.altob);
		 }
		 else if(img.equals("altob2")){
			 im.setImageResource(R.drawable.altob2);
		 }
		 else if(img.equals("altob3")){
			 im.setImageResource(R.drawable.altob3);
		 }
		 else if(img.equals("altoc")){
			 im.setImageResource(R.drawable.altoc);
		 }
		 else if(img.equals("altoc2")){
			 im.setImageResource(R.drawable.altoc2);
		 }
		 else if(img.equals("altoc3")){
			 im.setImageResource(R.drawable.altoc3);
		 }
		 else if(img.equals("altod")){
			 im.setImageResource(R.drawable.altod);
		 }
		 else if(img.equals("altod2")){
			 im.setImageResource(R.drawable.altod2);
		 }
		 else if(img.equals("altod3")){
			 im.setImageResource(R.drawable.altod3);
		 }
		 else if(img.equals("altoe")){
			 im.setImageResource(R.drawable.altoe);
		 }
		 else if(img.equals("altoe2")){
			 im.setImageResource(R.drawable.altoe2);
		 }
		 else if(img.equals("altof")){
			 im.setImageResource(R.drawable.altof);
		 }
		 else if(img.equals("altof2")){
			 im.setImageResource(R.drawable.altof2);
		 }
		 else if(img.equals("altog")){
			 im.setImageResource(R.drawable.altog);
		 }
		 else if(img.equals("altog2")){
			 im.setImageResource(R.drawable.altog2);
		 }
	 }
}
