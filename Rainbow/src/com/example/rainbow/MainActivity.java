package com.example.rainbow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
    
	private Button[] buttons=new Button[8];
	
	private Button btn_start,btn_stop;
	private SeekBar spd;
	
    private Timer mTimer;
    
    private int sleepInterval;
    
    private TimerTask myTask;
    
    
    private boolean run=false;
    
    private int i=0;
	
	private void findViews()
	{
		 buttons[1]=(Button) findViewById(R.id.Button1);
		 buttons[2]=(Button) findViewById(R.id.Button2);
		 buttons[3]=(Button) findViewById(R.id.Button3);
		 buttons[4]=(Button) findViewById(R.id.Button4);
		 buttons[5]=(Button) findViewById(R.id.Button5);
		 buttons[6]=(Button) findViewById(R.id.Button6);
		 buttons[7]=(Button) findViewById(R.id.Button7);
		 
		 btn_start=(Button)  findViewById(R.id.btn_start);
		 btn_stop =(Button)  findViewById(R.id.btn_stop);
		 
		 spd=(SeekBar) findViewById(R.id.bar_spd);
		 
	}
	
	private void setListeners()
	{
		btn_start.setOnClickListener(doStart);
		btn_stop.setOnClickListener(doStop);
		spd.setOnSeekBarChangeListener(doChangeSpd);
		
	}
	
	private Button.OnClickListener doStart=new Button.OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		  
		
			
			sleepInterval=(5000/(spd.getProgress()+1));
			mTimer=new Timer();
			setTimerTask(sleepInterval);
			
			run=true;
			btn_start.setEnabled(false);
			btn_stop.setEnabled(true);
		}
		
	};
	
	private Button.OnClickListener doStop=new Button.OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		    mTimer.cancel();
			run=false;
			btn_start.setEnabled(true);
			btn_stop.setEnabled(false);
		}
		
	};
	
	private SeekBar.OnSeekBarChangeListener doChangeSpd=new SeekBar.OnSeekBarChangeListener() {
			
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			sleepInterval=(5000/(spd.getProgress()+1));
			if (run)
			{
			   mTimer.cancel();
	           mTimer=new Timer();	
			   setTimerTask(sleepInterval);
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toast.makeText(MainActivity.this,"Programmed by Liu Jiang", Toast.LENGTH_LONG).show();
		
		findViews();
		
	    setListeners();
	}
	
	final Handler handler=new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
		   int x=msg.what;
		   if (msg.arg1==1)
			   buttons[x].setVisibility(Button.VISIBLE);
		   else
			   buttons[x].setVisibility(Button.INVISIBLE);
		   super.handleMessage(msg);
		}
	};
	
	  private void setTimerTask(int sleepInterval) {
	        mTimer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	               if (run==true)
	               {
	                i=(i%7)+1;
	                for (int j=1;j<7;j++)
	                {
	                	Message m=new Message();
	                	m.what=j;
	                	if (i==j) m.arg1=1;
	                	else
	                		m.arg2=0;
	                	handler.sendMessage(m);
	                		
	                }
	               
	            }
	            }
	        }, 0, sleepInterval);
	        
	    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
