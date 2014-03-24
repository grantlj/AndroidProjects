package com.example.bluetoothmouse;

import java.io.IOException;

import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public class MyOnGestureListener extends SimpleOnGestureListener {
    Context applicationContext=null;
	public MyOnGestureListener(Context applicationContext) {
		// TODO Auto-generated constructor stub
		this.applicationContext=applicationContext;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		

		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		//Mouse Move
      
      //  Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show();
        
		int mx=(int) Math.abs(arg2);
        int my=(int) Math.abs(arg3);
        char cmd[]={'0','0','0','0','0','0','0','0','0'};
        cmd[0]='1';
        if (mx>999) 
        	mx=mx%1000;
       
        if (my>999)
        	my=my%1000;
       
        
        for (int i=4;i>=2;i--)
        {
        	cmd[i]=(char) (mx%10+'0');
            mx=mx/10;
        }
        
        if (arg2<=0)
        	cmd[1]='1';
        
        for (int i=8;i>=6;i--)
        {
        	cmd[i]=(char) (my%10+'0');
            my=my/10;
        }
        
        if (arg3<=0)
        	cmd[5]='1';
        
       String str=String.valueOf(cmd);
       Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show();
       BTOperator.send(cmd);
       return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
   //Right key down.
		
		char cmd[]={'0','0','0','0','0','0','0','0','0'};
		cmd[0]='4';
		String str=String.valueOf(cmd);
		Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show();
		BTOperator.send(cmd);
			


	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		//left double click.
		
		char cmd[]={'0','0','0','0','0','0','0','0','0'};
		cmd[0]='3';
		String str=String.valueOf(cmd);
		Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show();
		BTOperator.send(cmd);
		return super.onDoubleTap(e);
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return super.onDoubleTapEvent(e);
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		//Left single click.
		
		
	    char cmd[]={'0','0','0','0','0','0','0','0','0'};
		cmd[0]='2';
		
		String str=String.valueOf(cmd);
		Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show();
		BTOperator.send(cmd);
		
		return super.onSingleTapConfirmed(e);
	}
	
	

}
