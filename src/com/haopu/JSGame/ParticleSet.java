package com.haopu.JSGame;
import java.util.ArrayList;		//引入相关类

import com.haopu.kbz.GameDraw;
import com.haopu.kbz.Tools;
import com.haopu.pak.PAK_IMAGES;

import android.graphics.Color;	//引入相关类
import android.util.Log;

class Particle{
//	int color;//粒子颜色
//	int r;//粒子半径
	double vertical_v;//垂直速度
	double horizontal_v;//水平速度
	int startX;//开始x坐标
	int startY;//开始y坐标
	int x;//当前x坐标
	int y;//当前y坐标
	int targetX,targetY;//粒子的目标运动点
	double startTime;//起始时间
	//构造器，初始化成员变量
	public Particle(double vertical_v,double horizontal_v, int x, int y,double startTime){
		this.vertical_v = vertical_v;	//初始化竖直方向速度
		this.horizontal_v = horizontal_v;	//初始化水平方向上速度
		this.startX = x;		//初始化开始位置的X坐标
		this.startY = y;		//初始化开始位置的Y坐标
		this.x = x;						//初始化实时X坐标
		this.y = y;							//初始化实时Y坐标
		this.startTime = startTime;			//初始化开始运动的时间
	}
}
	
	
	//ParticleSet类负责管理和添加粒子对象
public class ParticleSet{
		ArrayList<Particle> particleSet;		//用于存放Particle对象的集合
		//构造器，初始化粒子集合
		public ParticleSet(){
			particleSet = new ArrayList<Particle>();
		}
		
/**		
 * 向粒子集合中添加指定个数的粒子对象
 * 注：初始坐标，速度等随机性较大
 * @param count
 * @param startTime
 */
		public void add(int count, double startTime){
			for(int i=0; i<count; i++){		//创建count个数的Particle对象
				int tempR = 1;		//粒子半径
				double tempv_v = -30 + 10*(Math.random());	//随机产生粒子竖直方向上速度
				double tempv_h = 10 - 20*(Math.random());	//随机产生粒子水平方向上速度
				int tempX = 160;	//粒子的X坐标是固定的
				int tempY = (int)(100 - 10*(Math.random()));	//随机产生粒子Y坐标
				//创建Particle对象
				Particle particle = new Particle(tempv_v, tempv_h, tempX, tempY, startTime);
				particleSet.add(particle);//将创建好的Particle对象添加到列表中
			}
		}
/**
 * 	向粒子集合中添加指定个数的粒子对象
 * 粒子的运动轨迹：从屏幕任意位置朝屏幕左上顶点运动	
 * @param count
 * @param startTime
 * @param x
 * @param y
 * @param targetX
 * @param targetY
 */
		public void addTemp(int count, double startTime,int x,int y){
			for(int i=0; i<count; i++){		//创建count个数的Particle对象
				int tempR = 1;		//粒子半径
				double tempv_v = -20 ;	
				double tempv_h = -20;
				//创建Particle对象
				Particle particle = new Particle(tempv_v, tempv_h, x, y,startTime);
				particleSet.add(particle);//将创建好的Particle对象添加到列表中
			}
		}
		//粒子的运动
		public void run(int num,int time,int x,int y,int targetX,int targetY){
			addTemp(num,time,x,y);
//			ArrayList<Particle> tempSet =particleSet;//获取粒子集合
//			int count = tempSet.size();		//记录粒子集合的大小
//			for(int i=0; i<count; i++){		//遍历粒子集合，修改其轨迹
//				Particle particle = tempSet.get(i);
//				if((particle.y-targetY)>0){
//					int len = (int) Math.sqrt((particle.x-targetX)*(particle.x-targetX)+(particle.y-targetY)*(particle.y-targetY));
//					particle.x+=((particle.x-targetX)*20/len);
//					particle.y+=((particle.y-targetY)*20/len);
//				}else{
//					tempSet.remove(particle);		//从粒子集合冲移除该Particle对象
//					count = tempSet.size();			//重新设置粒子个数
//				}
				
				
//				int tempX = (int) (particle.startX+particle.horizontal_v*time);
//				int tempY = (int) (particle.startY+particle.vertical_v*time);
//				if(tempY<0){
//					tempSet.remove(particle);		//从粒子集合冲移除该Particle对象
//					count = tempSet.size();			//重新设置粒子个数
//				}
//				particle.x = tempX;					//修改粒子的X坐标
//				particle.y = tempY;		
//			}
		}
		void drawParticle(){
			if(particleSet==null)return;
			ArrayList<Particle> tempSet =particleSet;//获取粒子集合
			for(int i=0;i<tempSet.size();i++){
				   Particle particle = tempSet.get(i);
				   GameDraw.add_Image(PAK_IMAGES.IMG_GOLD,particle.x,particle.y,Tools.TOP_LEFT,Tools.TRANS_NONE,300);			   
				   if((particle.y-6)>0){
						int len = (int) Math.sqrt((particle.x-10)*(particle.x-10)+(particle.y-6)*(particle.y-6));
						particle.x-=((particle.x-10)*15/len);
						particle.y-=((particle.y-6)*15/len);
						if((particle.y-6)<0){
							tempSet.remove(particle);	
						}
					}else{
						tempSet.remove(particle);		//从粒子集合冲移除该Particle对象
					}
				   
			  }
		}
		
		
		
	   int iTime;
	   void aabbcc(){
		   if(particleSet==null)return;
		   if(iTime>0){iTime--;}
		   if(iTime==0){
			   for(int i=0;i<particleSet.size();i++){
				   
			   }
		   }
	   }
	
}