package com.smallemperor.bloodhound;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class RegisterActivity extends Activity {

	ImageView image;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	ImageView image4;
	ImageView image5;
	  private EditText  username=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	

        setContentView(R.layout.registerxml);
        
        image = (ImageView) findViewById(R.id.imageView1);
    	image.setImageResource(R.drawable.navbar);
    	
        username = (EditText)findViewById(R.id.editText1);
        image1 = (ImageView) findViewById(R.id.imageView2);
    	image1.setImageResource(R.drawable.anonicon100);
    	
    	image2 = (ImageView) findViewById(R.id.imageView3);
    	image2.setImageResource(R.drawable.selectphoto);
    	
    	image3 = (ImageView) findViewById(R.id.imageView4);
    	image3.setImageResource(R.drawable.camera);
    	
    	image4 = (ImageView) findViewById(R.id.imageView5);
    	image4.setImageResource(R.drawable.check);
    	
    	
    	image5 = (ImageView) findViewById(R.id.imageView6);
    	image5.setImageResource(R.drawable.submit);
    	
    	
    	
    //	   image1 = (ImageView) findViewById(R.id.imageView2);
     //  	image1.setImageResource(R.drawable.regis_img1);
    //   	image1.setScaleType(ImageView.ScaleType.FIT_XY  );
    	
       	
	    // Get the message from the intent
	    /*Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);*/
	}
  
}