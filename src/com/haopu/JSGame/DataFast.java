package com.haopu.JSGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class DataFast {
	Context ct;
	public PackageTools pps;
	
	FileOutputStream fOut;
	FileInputStream fIn;

	
	public DataFast(Context context,PackageTools pls)
	{
		ct=context;
		pps=pls;
	}
	
	public void SaveTo(String fn)
	{
		try {
			fOut=ct.openFileOutput(fn,Context.MODE_PRIVATE);
			fOut.write(pps.databuf, 0, pps.iOffset);
			Log.i("Write "+fn+" Length",""+pps.iOffset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ReadFrom(String fn)
	{
		try {
			fIn=ct.openFileInput(fn);
			pps.iLength=fIn.read(pps.databuf, 0, 64*1024);
			pps.iOffset=0;
			Log.i("Read "+fn+" Length",""+pps.iLength);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pps.databuf[0] = 99;
		}
	}

    public void deleteBy(String fn) {
        try {
            ct.deleteFile(fn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long fileLength(String fn) {
        try {
			fIn=ct.openFileInput(fn);
            return fIn.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }
}
