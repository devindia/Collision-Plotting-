
/*//Code for Elastic Collision detection and plotting of Kinetic energy before and after collision 
// few restrictions :
// 1. works for Elastic collision only
// 2. works only for linear collisions
// 3. works according to law of conservation of kinetic energy 
// 4. calculation of kinetic energy is approximated to adjust to the graph plotting 
// test values :  (u1=5, u2=-2 , m1=50, m2= 50;) 
//                (u1=15, u2=3 , m1=50, m2= 70;) 
 //               (u1=5, u2=0 , m1=50, m2= 70;) 
//				  (u1=0, u2=-5 , m1=50, m2= 70;) 
//                (u1=10, u2=-10 , m1=80, m2= 80;) 
//  impulsive collision  (u1=100, u2=-10 , m1=80, m2= 80;)

// Code by Debasish Roy (github handle : "devindia")
//date 14-03-2017
*/

import java.awt.*;

import javax.swing.*;



public class CollisionPlot extends JFrame {
	
	int x1,y1,x2,y2;
	int u1=5;    //initial velocity
	int u2=-2;     //initial velocity
	int v1,v2;
	int m1=50; //mass
	int m2=50;  //mass
	Image img;
	Graphics dbg;
	int KE1,KE2;   // kinetic energy 
	int tx1,tx2;
	boolean collisionOccur = false;
	public CollisionPlot(){
		setTitle("Collision Simulation");
		setSize(1000,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		//setBackground(Color.YELLOW);
		x1=20;
		y1=100;
		x2=500;
		y2=100;
		tx1=0;
		tx2=0;
	}
	
	public void paint(Graphics g){
		
		img = createImage(getHeight(),getWidth());
		dbg = img.getGraphics();
		paintComponent(dbg);
		g.drawImage(img,0,300,this);
		detectCollision();
		calKineticEnergy();
		 tx1++;
		 tx2++;
		// System.out.println(KE1+" "+KE2);
		if(KE1<0) KE1=(1)*KE1;
		else if(KE1>0) KE1=(-1)*KE1;
		g.fillOval(tx1, KE1+250, 2, 2);
		g.drawString("E="+KE1,25, KE1+250);
		if(KE2<0) KE2=(1)*KE2;
		else if(KE2>0) KE2=(-1)*KE2;
		g.setColor(Color.BLUE);
		g.fillOval(tx2+500, KE2+250, 2, 2);
		g.drawString("E="+KE2,505, KE2+250);
		g.setColor(Color.BLACK);
		g.drawLine(15, 0, 15, 251);
		g.drawLine(0, 250, 500, 250);
		g.setColor(Color.BLACK);
		g.drawLine(500, 0, 500, 251);
		g.drawLine(250,250,990,250);
	}
	
	public void paintComponent(Graphics g){
		
		
		
		g.setColor(Color.BLACK);
		g.drawString("v="+u1, x1+5, y1-10);
		g.setColor(Color.GREEN);
		g.fillOval(x1, y1, 30, 30);
		
		g.setColor(Color.BLACK);
		g.drawString("v="+u2, x2+5, y2-10);
		g.setColor(Color.RED);
		g.fillOval(x2,y2,30,30);
		run();
		repaint();
	}
	public void run(){
		try {
			//detect collision
			
				if(detectCollision()){
					calculateVelocity();
				}
				else{
					x1+=u1;
					x2+=u2;
					
				}
			
			
			
			
			Thread.sleep(100);
			
			
			
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public boolean detectCollision(){
		if(x2-x1-30<1){
			/*x1-=5;
			x2+=2;*/
			
			return true;
		}
		return false;
	}
	public void calculateVelocity(){
		//int rForce = M*3-m*4;
	//	x1-=rForce/m;
		//x2-=rForce/M;
		v1 = ((m1-m2)*u1+2*m2*u2)/(m1+m2);
		v2 = ((m2-m1)*u2+2*m1*u1)/(m1+m2);
		x1+=v1;
		x2+=v2;
		u1=v1;
		u2=v2;
		//System.out.println(v1+" "+v2);
		
	}
  public void calKineticEnergy(){
	 // if(detectCollision()){
		  KE1=(int) (0.5*m1*Math.pow(u1,2));
		  KE2=(int) (0.5*m2*Math.pow(u2,2));
		  
		  //for nice plotting convert the value in 200 pixel
		  int bigno = KE1>KE2?KE1:KE2;
		int scale = 10;
		while(bigno>scale){
			scale*=10;
		}
		
		KE1=(KE1*100)/scale;
		KE2=(KE2*100)/scale;
		  
	//  }else{
	//	  KE1=(int) (0.5*m*Math.pow(x1,2));
	//	  KE2=(int) (0.5*M*Math.pow(x2,2));
	//  }
	
	  
   }
	public static void main(String[] args){
		new CollisionPlot();
	}
}
