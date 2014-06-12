import java.util.*;
/**provide the initial info. of wumpus Environment in text book page of 238,
 * including pit, glod, wumpus,agent start position and show the world in a grid
*/

class InitEnv{
	//Scanner scr=new Scanner(System.in);
	int numPit=3;     //number of pits
	int wumpPosi=5;// wumpus position
	int glodPosi=6;//gold position
	int [] pitPosi={4,7,15}; // position of pits
	int [] breezePosi=new int[16];//breeze position
	int [] stenchPosi=new int[16];//stench position
	String [][] world=new String[4][4];
	
	public String [][] initial(){
		for(int i=0;i<16;i++){
			breezePosi[i]=-1;
			stenchPosi[i]=-1;
		}
		for(int i=0;i<4;++i){
			for(int j=0;j<4;++j)
				world[i][j]="";
		}
		int count=1;
		System.out.println("\n\n********* Wumpus World*********\n\nPositions of pit, wumpus and glod are as follows:");
		for(int i=1;i<=4;++i){
			System.out.println("\n-----------------------------------------------------------------");
			System.out.print("|\t");
			for(int j=1;j<=4;++j)
				System.out.print((count++)+"\t|\t");
		}
		System.out.println("\n-----------------------------------------------------------------\nAgent start position: 13");
		world[3][0]="A";
		for(int i=0;i<numPit;++i){
			InsertBS(pitPosi[i],"pit",world);
		}
		InsertBS(wumpPosi,"wumpus",world);
		insertGWP(world);
		return world;
	}
	//update breeze/stench position info. to w
	void InsertBS(int a,String b,String[][] world){
		int left=a-1;//left
		int right=a+1;//right
		int down=a+4;//down
		int up=a-4;//up
		if(a==1||a==5 || a==9||a==13)
			left=0;
		if(a==4||a==8 || a==12||a==16)
			right=0;
		if(down>16)
			down=0;
		if(up<0)
			up=0;
		if(b.equalsIgnoreCase("pit")){
			breezePosi[0]=left;breezePosi[1]=right;breezePosi[2]=down;breezePosi[3]=up;
		}
		else if(b.equalsIgnoreCase("wumpus")){
			stenchPosi[0]=left;stenchPosi[1]=right;stenchPosi[2]=down;stenchPosi[3]=up;
		}
		int temp1,count;
		for(int i=0;i<4;++i){
			if(b.equalsIgnoreCase("pit"))
				temp1=breezePosi[i];
			else
				temp1=stenchPosi[i];
			count=0;
			for(int j=0;j<4;++j){
				for(int k=0;k<4;++k){
					++count;
					if(count==temp1 && b.equalsIgnoreCase("pit") && !world[j][k].contains("B")){
						world[j][k]+="B";
					}
					else if(count==temp1 && b.equalsIgnoreCase("wumpus") && !world[j][k].contains("S"))
							world[j][k]+="S";
				}
			}
		}
	}
	//update gold/wumpus/pit position info. to world
	void insertGWP(String world[][]){
		int temp=0;
		int count=0;
		boolean glod=false;
		boolean wumpus=false;
		for(int i=0;i<numPit;++i){
			temp=pitPosi[i];
			count=0;
			for(int j=0;j<4;++j){
				for(int k=0;k<4;++k){
					++count;
					if(count==temp)
						world[j][k]+="P";
					else if(count==glodPosi && glod==false){
							world[j][k]+="G";
							glod=true;
					}
					else if(count==wumpPosi && wumpus==false){
								world[j][k]+="W";
								wumpus=true;
					}
				}
			}
		}
		show(world);
	}
	void show(String world[][]){
		for(int i=0;i<4;++i){
			System.out.println("\n-----------------------------------------------------------------");
			System.out.print("|\t");
			for(int j=0;j<4;++j)
				System.out.print(world[i][j]+"\t|\t");
		}
		System.out.println("\n-----------------------------------------------------------------");
	}
}

