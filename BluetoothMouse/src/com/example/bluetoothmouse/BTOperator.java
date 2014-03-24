package com.example.bluetoothmouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;


public class BTOperator {
	private static final UUID uuid=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	
    private static BTOperator instance=null;
    private static BluetoothDevice mydevice=null;
    private static String addr=null;
    private static BluetoothSocket socket=null;
	private static boolean connected=false;
	private static int connectTime=0;
	
	private static InputStream is=null;
	private static OutputStream os=null;
	
	private BTOperator()
    {
    	
    }
    
    public static void getInstance() throws IOException    {
    	if  (connected==false)
    	{
    		//not ready for bluetooth....
    		//so we connect it.
    		BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();
    		if (!adapter.isEnabled())
    	      adapter.enable();   //turn on....
    		
    		Set<BluetoothDevice> pairedDevices=adapter.getBondedDevices();
    		
    		
    		if (pairedDevices.size()>0)
    		{
    			for (BluetoothDevice device:pairedDevices)
    			if (device.getName().equals("HC-05"))
    				{
    					addr=device.getAddress();
    				    mydevice=device;
    				    adapter.cancelDiscovery();
    				    break;
    				}
    		}
    	
    	if (mydevice!=null)
    	{
    		try {
				socket=mydevice.createRfcommSocketToServiceRecord(uuid);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
    		
    	
    		
    		while (!connected && connectTime<=10)
    		{
    			connectDevice();
    		}
    		
    		if (connected)
    		{
    			is=socket.getInputStream();
    			os=socket.getOutputStream();
    		}
    	}
     
       
    	}
    }
    
 private static void connectDevice() throws IOException
 {
	 try
	 {
		 if (mydevice.getBondState()==BluetoothDevice.BOND_NONE)
		 {
			 Method creMethod=BluetoothDevice.class.getMethod("createBond");
			 creMethod.invoke(mydevice);
			 
		 }
		 socket.connect();
		 connected=true;
		 
	 }
	 catch (Exception e)
	 {
		 //e.printStackTrace();
		 connectTime++;
		 connected=false;
		 socket.close();
	 }
	 
 }

public static void send(char[] cmd) {
	if (!connected)
		try {
			getInstance();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	// TODO Auto-generated method stub
   String str=new String(cmd);
   byte[] b=str.getBytes();
   try {
	os.write(b);
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
   try {
	os.flush();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
    
 
}
