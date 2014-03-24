package com.example.bluetoothmouse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
		setListener();
		mGestureDetector=new GestureDetector(this, new MyOnGestureListener(getApplicationContext()));;
		
	}
    
	private Button moveUp,moveDown;
	private View mButton;
	private ToggleButton kbButton;

	
	private boolean kb=false;
	
	private GestureDetector mGestureDetector;
	private InputMethodManager imm=null;
	
	
	private void findViews()
	{
		moveUp=(Button) findViewById(R.id.button1);
		moveDown=(Button) findViewById(R.id.button2);
		mButton=(View) findViewById(R.id.view1);

	}
	
	private void setListener()
	{
		
		moveUp.setOnClickListener(doUpListener);
		moveDown.setOnClickListener(doDownListener);
		mButton.setOnTouchListener(doTouch);
	
	
		
	}
	
	
    
    
	private View.OnTouchListener doTouch=new OnTouchListener()
	{
		public boolean onTouch(View v, MotionEvent event) {
            mGestureDetector.onTouchEvent(event);
            return true;
        }
	};
	
	private Button.OnClickListener doUpListener=new OnClickListener()
	{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 char cmd[]={'0','0','0','0','0','0','0','0','0'};
			 cmd[0]='5';
			 cmd[1]='0';cmd[2]='5';cmd[3]='0';
			 BTOperator.send(cmd);
		}
	  	
	};
	
	
	private Button.OnClickListener doDownListener=new OnClickListener()
	{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 char cmd[]={'0','0','0','0','0','0','0','0','0'};
			 cmd[0]='5';
			 cmd[1]='1';cmd[2]='5';cmd[3]='0';
			 BTOperator.send(cmd);
		}
	  	
	};
	
    
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	

}
