package com.smallemperor.bloodhound;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.splashTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Thread loadingThread = new Thread(){
            @Override
            public void run(){
                try{
                    super.run();
                    sleep(5000);
                }catch(Exception e){

                }finally {
                    Intent i = new Intent(SplashActivity.this,
                    		MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        loadingThread.start();
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}
}