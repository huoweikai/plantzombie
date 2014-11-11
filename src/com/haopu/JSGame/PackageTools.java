package com.haopu.JSGame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

public class PackageTools {
	static int MAXDATALENGTH=1024*1024;
 	public int iOffset;
	int iLength;
	byte databuf[];

	byte buf[];
	float obf;
	int obi;
	long obl;
	public byte obs[];
	String obutf8;
	byte tmputf;
	String obo;
	int t1,t2,t3,t4;

	DataInputStream dis=null;
	byte utfs[];
	String utfb;
	boolean bCompress;
	boolean bError;
	
	Resources mRes;
	
	InputStream ins;
	OutputStream ots;
	
	Context ct;
	FileOutputStream fOut;
	FileInputStream fIn;
	public boolean InitDataFromAsset(String fn)
	{
		try{
//			Log.e("openFileInput","openFileInput="+fn);

			ins=ct.getAssets().open(fn);
			iLength=ins.read(databuf,0,MAXDATALENGTH);
			ins.close();
//			fIn=ct.openFileInput(fn);
//			Log.e("openFileInput","openFileInput="+fn);
//			iLength=fIn.read(databuf,0,MAXDATALENGTH);
//			fIn.close();
			iOffset=0;
		}catch(IOException e)
		{
			return false;
		}
		return true;
	}
	public boolean InitDataFromFile(String fn)
	{
		try{
//			Log.e("openFileInput","openFileInput="+fn);
			fIn=ct.openFileInput(fn);
//			Log.e("openFileInput","openFileInput="+fn);
			iLength=fIn.read(databuf,0,MAXDATALENGTH);
			fIn.close();
			iOffset=0;
		}catch(IOException e)
		{
			return false;
		}
		return true;
	}
	public boolean SaveDataToFile(String fn)
	{
		try{
			fOut=ct.openFileOutput(fn, Context.MODE_PRIVATE);
			fOut.write(databuf,0,iOffset);
			fOut.close();
		}catch(IOException e)
		{
			return false;
		}
		return true;
	}
	public PackageTools(Context context)
	{
		ct=context;
		mRes=context.getResources();
		
		bError=false;
		bCompress=false;
		buf=new byte[256];
		obs=new byte[1024];
		databuf=new byte[MAXDATALENGTH];
	}
	
	public void StringToByte(String str)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
			dos.writeUTF(str);
			dos.close();
			baos.close();
			utfs=baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int StringLength()
	{
		int i,j=0;
		for(i=2;i<utfs.length;i++)
		{
			if(utfs[i]>0)j++;
			else
			{
				i+=3;
				j+=2;
			}
		}
		return j;
	}
	public String GBKTOString(byte bt[])
	{
		try {
//			Log.e(""+bt[0]+bt[1]+bt[2],"btsize:"+bt.length+" obi:"+obi);
			obutf8=new String(bt,0,obi-1,"gb2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obutf8;
	}
	public String ByteToString(byte bt[])
	{
//		if(1==1)return "test";
		ByteArrayInputStream bais = new ByteArrayInputStream(bt);
        DataInputStream dis = new DataInputStream(bais);
        try {
        	utfb=dis.readUTF();
			dis.close();
			bais.close();
			return utfb;
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			if(1==1)return "test";
			e.printStackTrace();
		}
		return "error";
	}
	
	public void OpenStream(int irid)
	{
		ins=mRes.openRawResource(irid);
	}
	
	public void OpenStreamFromByte(byte bt[])
	{
        try {
    		ByteArrayInputStream bais = new ByteArrayInputStream(bt);
    		dis=null;
            dis = new DataInputStream(bais);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Open"+bt+"error!");
		}
	}

	public short _GetNextByte()
	{
		try {
			ins.read(buf, 0, 1);
			if(buf[0]<0)t1=256+buf[0];
			else t1=buf[0];
			return (short)t1;
//			obi=buf[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public short _bts(byte b1,byte b2)
	{
		if(b1<0)t1=256+b1;
		else t1=b1;
		if(b2<0)t2=256+b2;
		else t2=b2;
		obi=t1*256+t2;	
		return (short)obi;
	}
	public short _GetNextShort()
	{
		try {
//			obi=dis.readShort();
			ins.read(buf, 0, 2);
			if(buf[0]<0)t1=256+buf[0];
			else t1=buf[0];
			if(buf[1]<0)t2=256+buf[1];
			else t2=buf[1];
			obi=t1*256+t2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (short)obi;
	}
	
	public int _GetNextInt()
	{
		try {
			ins.read(buf, 0, 4);
			obi=buf[0]*256*256*256+buf[1]*256*256+buf[2]*256+buf[3];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obi;
	}
	
	public int _Seek(int iS)
	{
		try {
			ins.skip(iS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obi;
	}
	
	public String _GetNextUtf()
	{
		try {
			obo=dis.readUTF();
//	    	System.out.println("obo="+obo.length());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obo;
	}
	public void _extend_load3dfile()
	{
		int i=0;
		obi=0;
		byte tmp[]=new byte[1024*512];
		try {
			ins=mRes.getAssets().open("re/abc1.out");
			i=ins.read(databuf, 0, 1024*512);
			obi+=i;
			CloseStream();
//			Log.e("i="+i,"i="+databuf[0]);

			ins=mRes.getAssets().open("re/abc2.out");
			i=ins.read(tmp, 0, 1024*512);
			obi+=i;
			CloseStream();
			System.arraycopy(tmp, 0, databuf, 1024*512*1, 1024*512);
//			Log.e("i="+i,"i="+tmp[0]);

			ins=mRes.getAssets().open("re/abc3.out");
			i=ins.read(tmp, 0, 1024*512);
			obi+=i;
			CloseStream();
			System.arraycopy(tmp, 0, databuf, 1024*512*2, 1024*512);
//			Log.e("i="+i,"i="+tmp[0]);

			ins=mRes.getAssets().open("re/abc4.out");
			i=ins.read(tmp, 0, 1024*512);
			obi+=i;
			CloseStream();
			System.arraycopy(tmp, 0, databuf, 1024*512*3, i);
//			Log.e("i="+i,"i="+tmp[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean InitDataFromResFile(String fn)
	{
		try {
			ins=mRes.getAssets().open(fn);
			obi=ins.read(databuf, 0, MAXDATALENGTH);
			CloseStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(obi<=0)return false;
		else
		{
			iOffset=0;
			iLength=obi;
			return true;
		}
	}
	public boolean InitDataFromRes(int irid)
	{
		try {
			OpenStream(irid);
			obi=ins.read(databuf, 0, MAXDATALENGTH);
			CloseStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(obi<=0)return false;
		else
		{
			iOffset=0;
			iLength=obi;
			return true;
		}
	}
	public void CloseStream()
	{
		try {
			ins.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*	public int GetNextByte(int ip)
	{
		try {
//			dis.skip(ip);
			dis.read(buf, 0, 2);
			obi=buf[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;
	}
	public int GetNextInt(int ip)
	{
		try {
//			dis.skip(ip);
			dis.read(buf, 0, 5);
			obi=buf[1]*256*256*256+buf[2]*256*256+buf[3]*256+buf[4];
//			System.out.println("[0]"+buf[0]+"[1]"+buf[1]+"[2]"+buf[2]+"[3]"+buf[3]+"[4]"+buf[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 5;
	}
	public int GetNextShortString(int ip)
	{
		try {
//			dis.skip(ip);
			dis.read(buf, 0, 2);
			obi=buf[1];
			dis.read(obs, 0, 24);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 26;
	}
	public int GetNextLongString(int ip)
	{
		try {
//			dis.skip(ip);
			dis.read(buf, 0, 2);
			obi=buf[1];
			dis.read(obs, 0, 128);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 130;
	}
	public void GetNextData(int ip)
	{
	}
	*/
	
	public void SetDataAndOffset(byte pb[],int iOs,int iLen)
	{
        if (iLen > 0)
            System.arraycopy(pb, 0, databuf, 0, iLen);
		iOffset=iOs;
		iLength=iLen;
	}
	public int GetNextByte()
	{
		if(bCompress)iOffset--;
		else if(databuf[iOffset]!=1)
		{
			bError=true;
			return 0;
		}
		obi=bti(databuf[iOffset+1]);
		iOffset+=2;
		return obi;
	}

	public short GetNextShort()
	{
		if(bCompress)iOffset--;
		else if(databuf[iOffset]!=2)
		{
			bError=true;
			return 0;
		}
		obi=((databuf[iOffset+1]&0xff)<<8)|(databuf[iOffset+2]&0xff);
		iOffset+=3;
		return (short)obi;
	}

	public int GetNextInt()
	{
		if(bCompress)iOffset--;
		else if(databuf[iOffset]!=3)
		{
			bError=true;
			return 0;
		}
//		obi=bti(databuf[iOffset+1])*256*256*256+bti(databuf[iOffset+2])*256*256+bti(databuf[iOffset+3])*256+bti(databuf[iOffset+4]);
		obi = ((databuf[iOffset+1]&0xff)<<24)|((databuf[iOffset+2]&0xff)<<16)|((databuf[iOffset+3]&0xff)<<8)|((databuf[iOffset+4]&0xff));
		iOffset+=5;
		return obi;
	}

	public float GetNextFloat()
	{
		if(bCompress)iOffset--;
		else if(databuf[iOffset]!=8)
		{
			bError=true;
			return 0;
		}
//		obi=(databuf[iOffset+1]<<24)|(databuf[iOffset+2]<<16)|(databuf[iOffset+3]<<8)|databuf[iOffset+4];
//		obi=bti(databuf[iOffset+1])*256*256*256+bti(databuf[iOffset+2])*256*256+bti(databuf[iOffset+3])*256+bti(databuf[iOffset+4]);
//		obi = ((databuf[iOffset+1]&0xff)<<24)|((databuf[iOffset+2]&0xff)<<16)|((databuf[iOffset+3]&0xff)<<8)|((databuf[iOffset+4]&0xff));
		obi = ((databuf[iOffset+1]&0xff)<<0)|((databuf[iOffset+2]&0xff)<<8)|((databuf[iOffset+3]&0xff)<<16)|((databuf[iOffset+4]&0xff)<<24);
		iOffset+=5;
		
		obf=Float.intBitsToFloat(obi);

		return obf;
	}
	
	public long GetNextLong()
	{
		if(bCompress)iOffset--;
		else if(databuf[iOffset]!=6)
		{
			bError=true;
			return 0;
		}
		int i;
		for(i=0;i<8;i++)System.out.println(""+bti(databuf[iOffset+1+i]));
		obl=0;
		obl=obl*256+bti(databuf[iOffset+1]);
		obl=obl*256+bti(databuf[iOffset+2]);
		obl=obl*256+bti(databuf[iOffset+3]);
		obl=obl*256+bti(databuf[iOffset+4]);
		obl=obl*256+bti(databuf[iOffset+5]);
		obl=obl*256+bti(databuf[iOffset+6]);
		obl=obl*256+bti(databuf[iOffset+7]);
		obl=obl*256+bti(databuf[iOffset+8]);
		System.out.println("obl="+obl);
		iOffset+=9;
		return obl;
	}
	public String GetNextGBKString()
	{
		GetNextString();
		return GBKTOString(obs);
	}
	public String GetNextUTF8String()
	{
		GetNextString();
		return ByteToString(obs);
	} 
	public int GetNextString()
	{
		if(bCompress)iOffset--;
		else if(databuf[iOffset]!=4)
		{
			bError=true;
			return 0;
		}
		obi=bti(databuf[iOffset+1]);
		//if(obi>=128)return 0;
		System.arraycopy(databuf, iOffset+2, obs, 0, obi);
		obs[obi]=0;
		iOffset+=(obi+2);
//		while(databuf[iOffset]<0 && iOffset+1<iLength)iOffset++;
		return obi;
	}

	public int GetNextData()
	{
		if(bCompress)iOffset--;
		else if(databuf[iOffset]!=5)
		{
			bError=true;
			return 0;
		}
		obi=bti(databuf[iOffset+1])*256+bti(databuf[iOffset+2]);
		System.arraycopy(databuf, iOffset+3, obs, 0, obi);
		iOffset+=(obi+3);
		return obi;
	}

	public static int bti(byte ib)
	{//byte to int ,[0-255]
		int ir;
		if(ib<0)ir=256+ib;
		else ir=ib;
		return ir;
	}

	public int InsertByte(int b)
	{
		databuf[iOffset]=1;
		databuf[iOffset+1]=(byte)b;
		iOffset+=2;
		return 2;
	}

	public int InsertShort(int s)
	{
		databuf[iOffset]=2;
		databuf[iOffset+1]=(byte)(s/256);
		databuf[iOffset+2]=(byte)(s%256);
		iOffset+=3;
		return 3;
	}

	public int InsertInt(int i)
	{
		databuf[iOffset]=3;
		databuf[iOffset+1]=(byte)(i/(256*256*256)%256);
		databuf[iOffset+2]=(byte)(i/(256*256)%256);
		databuf[iOffset+3]=(byte)(i/(256)%256);
		databuf[iOffset+4]=(byte)(i%256);
		iOffset+=5;
		return 4;
	}

	public int InsertString(byte s[],int iLen)
	{
		databuf[iOffset]=4;
		databuf[iOffset+1]=(byte)iLen;
		System.arraycopy(s, 0, databuf, iOffset+2, iLen);
		iOffset+=(2+iLen);

		return 2+iLen;
	}

	public int InsertData(byte d[],int iLen)
	{
		databuf[iOffset]=5;
		databuf[iOffset+1]=(byte)(iLen/256);
		databuf[iOffset+2]=(byte)(iLen%256);
		System.arraycopy(d, 0, databuf, iOffset+3, iLen);
		iOffset+=(3+iLen);

		return 3+iLen;
	}
	Random rnd=new Random();
	public int GetRand(int iMin,int iMax)
	{//�����[iMin,iMax]
		int i=Math.abs(rnd.nextInt())%(iMax-iMin+1)+iMin;
//		int i=123%(iMax-iMin+1)+iMin;

		return i;
	}
//	public int zzz(int z)
//	{
//		if(z==1)return 0;
//		else return 1;
//	}
	
    public int checkfilesize()
    {
    	int i,j,k;
    	k=0;
    	InputStream is;
		//a97 j106 z122

//    	System.out.println("hashcode"+System.identityHashCode(this));
    	
    	try {
//    		is=this.getClass().getResourceAsStream("/mgm/"+((char)i)+".class");
//    		is=this.getClass().getResourceAsStream("/mgm/GmMe.class");
//    		System.out.println("/dats/mapdata/map_"+i+".map");
    		is=this.getClass().getResourceAsStream("Anima.class");
    		if(is==null)
    		{
    			k=0;
    			return 0;
    		}
    		while(true)
    		{
    			j=is.read(obs,0,1024);
    			if(j!=-1)k+=j;
    			else break;
    		}
    		is.close();
    	}catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
 //   	if(1==1)return k;

//    	DataInputStream in = newDataInputStream(new FileInputStream("asdf"));
		for(i=0;i<=103;i++)
		{
//			System.out.println("/dats/mapdata/map_"+i+".map");
	    	try {
//	    		is=this.getClass().getResourceAsStream("/mgm/"+((char)i)+".class");
//	    		is=this.getClass().getResourceAsStream("/mgm/GmMe.class");
//	    		System.out.println("/dats/mapdata/map_"+i+".map");
	    		is=this.getClass().getResourceAsStream("/dats/mapdata/map_"+i+".map");
	    		if(is==null)continue;
	    		while(true)
	    		{
	    			j=is.read(obs,0,1024);
	    			if(j!=-1)k+=j;
	    			else break;
	    		}
	    		is.close();
	    	}catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		k=System.identityHashCode(this);
    	return k;
    }
/*
	public short _GetNextByte()
	{
		try {
			dis.read(buf, 0, 1);
			if(buf[0]<0)t1=256+buf[0];
			else t1=buf[0];
			return (short)t1;
//			obi=buf[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public short _GetNextShort()
	{
		try {
//			obi=dis.readShort();
			dis.read(buf, 0, 2);
			if(buf[0]<0)t1=256+buf[0];
			else t1=buf[0];
			if(buf[1]<0)t2=256+buf[1];
			else t2=buf[1];
			obi=t1*256+t2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (short)obi;
	}
	
	public int _GetNextInt()
	{
		try {
			dis.read(buf, 0, 4);
			obi=buf[0]*256*256*256+buf[1]*256*256+buf[2]*256+buf[3];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obi;
	}
	
	public int _Seek(int iS)
	{
		try {
			dis.skip(iS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obi;
	}
	
	public String _GetNextUtf()
	{
		try {
			obo=dis.readUTF();
	    	System.out.println("obo="+obo.length());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obo;
	}*/

}
