import java.util.Random;
import java.util.Scanner;
import java.lang.Math.*;
/**extends the L-WUMPUS-AGENTP so that it keeps track 
* of all relevant facts within the knowledge base, this contains
* a main method 
* 
* @author Jingyi Guo
*/

public class Test {
	static int step=0;
	static boolean complete=false;
	static boolean failed=false;
	static double max=1000;
	static CellInf [] t=new CellInf[17];
	static int pos=13;
	String [][] world=new String[4][4];
	

	static boolean checkWG(CellInf t){
		if(t.cellName.contains("W") || t.cellName.contains("P")){
			return true;
		}
		return false;
	}
	public static void main(String args[]){
		InitEnv e=new InitEnv();
		System.out.println("\nThe initial environment of wumpus world is as follows.\n");
		String [][] world=e.initial();
		System.out.println("*A:Agent; S:Stench; B: Breeze; G: Gold; W: Wumpus; P:Pit");
		System.out.println("\n\nUpdate wumpus world...");
		
		int c=1;
		out:for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(c>16)
					break out;
				t[c]=new CellInf(world[i][j],c);//add initial condition to tiles
				++c;
			}
		}
		t[13].isSafe=true;
		t[13].haveVisited=true;
		
		System.out.println("\ngo now at start position="+pos);
		do{
			step++;
			
			Random rnumber = new Random();
			int numDirection=4;
	        int forward = Math.abs(rnumber.nextInt() % numDirection);
	        //System.out.println("\nThis is time="+forward);
	        switch (forward) {
	            case 0://move down first
	            	if(t[pos].goDown==true && t[pos].lastPosiDown==false && (pos+4)<=16 &&  t[pos+4].proPit<max && t[pos+4].proWumpus<max && 
	    			t[pos+4].havePit==false && t[pos+4].haveWumpus==false){
	    				t[pos].goDown=false;
	    				pos=pos+4;
	    				System.out.println("\ngo down pos= "+pos);
	    				t[pos].history+="u";
	    				updateStatus(t,pos);
	    				}//move right first
	    		else if((t[pos].goRight==true && t[pos].lastPosiRight==false && t[pos+1].proPit<max && t[pos+1].proWumpus<max && 
	    			t[pos+1].havePit==false && t[pos+1].haveWumpus==false &&!t[pos].history.contains("r") )|| (t[pos].goDown==false && t[pos].goLeft==false && t[pos].goUp==false) ){
	    			t[pos].history=t[pos].history+"r";
	    			t[pos].goRight=false;
	    			pos++;
	    			System.out.println("\ngo right position="+pos);
	    			t[pos].history=t[pos].history+"l";
	    			updateStatus(t,pos);
	    		}
	    		else if((t[pos].goUp==true && t[pos].lastPosiUp==false&& (pos-4)>=1 && t[pos-4].proPit<max && t[pos-4].proWumpus<max && 
	    				t[pos-4].havePit==false && t[pos-4].haveWumpus==false && !t[pos].history.contains("u"))||(t[pos].goDown==false && t[pos].goLeft==false && t[pos].goRight==false)){
	    					t[pos].goUp=false;
	    					pos=pos-4;
	    					System.out.println("\ngo up pos= "+pos);
	    					t[pos].history+="d";
	    					updateStatus(t,pos);
	    				}
	    		else if((t[pos].goLeft==true && t[pos].lastPosiLeft==false && t[pos-1].proPit<max && t[pos-1].proWumpus<max && t[pos-1].havePit==false&& t[pos-1].haveWumpus==false 
	    			&& !t[pos].history.contains("l"))||(t[pos].goDown==false && t[pos].goRight==false&& t[pos].goUp==false)){
	    				t[pos].goLeft=false;//go this direction
	    				pos=pos-1;
	    				System.out.println("\ngo left pos= "+pos);
	    				t[pos].history+="r";
	    				updateStatus(t,pos);
	    			}
	    		else{
	    			if(t[pos].hasDown){
	    				pos=pos+4;
	    				System.out.println("\ngo down pos= "+pos);
	    				t[pos].history+="u";
	    				updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasLeft){
	    				pos=pos-1;
	    				System.out.println("\ngo left pos= "+pos);
	    				t[pos].history+="r";
	    				updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasRight){
	    				t[pos].history=t[pos].history+"r";
		    			pos++;
		    			System.out.println("\ngo right position="+pos);
		    			t[pos].history=t[pos].history+"l";
		    			updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasUp){
    					pos=pos-4;
    					System.out.println("\ngo up pos= "+pos);
    					t[pos].history+="d";
    					updateStatus(t,pos);
	    			}
	    		}
	                break;
	            case 1://move up
	            	if((t[pos].goUp==true && t[pos].lastPosiUp==false&& (pos-4)>=1 && t[pos-4].proPit<max && t[pos-4].proWumpus<max && 
	    			t[pos-4].havePit==false && t[pos-4].haveWumpus==false && !t[pos].history.contains("u"))||(t[pos].goDown==false && t[pos].goLeft==false && t[pos].goRight==false)){
	    				t[pos].goUp=false;
	    				pos=pos-4;
	    				System.out.println("\ngo up pos= "+pos);
	    				t[pos].history+="d";
	    				updateStatus(t,pos);
	    				}
	    		else if((t[pos].goLeft==true && t[pos].lastPosiLeft==false && t[pos-1].proPit<max && t[pos-1].proWumpus<max && t[pos-1].havePit==false&& t[pos-1].haveWumpus==false 
	    				&& !t[pos].history.contains("l"))||(t[pos].goDown==false && t[pos].goRight==false&& t[pos].goUp==false)){
	    					t[pos].goLeft=false;//go this direction
	    					pos=pos-1;
	    					System.out.println("\ngo left pos= "+pos);
	    					t[pos].history+="r";
	    					updateStatus(t,pos);
	    				}
	    		//move right first
	    		else if((t[pos].goRight==true && t[pos].lastPosiRight==false && t[pos+1].proPit<max && t[pos+1].proWumpus<max && 
	    			t[pos+1].havePit==false && t[pos+1].haveWumpus==false &&!t[pos].history.contains("r") )|| (t[pos].goDown==false && t[pos].goLeft==false && t[pos].goUp==false) ){
	    			t[pos].history=t[pos].history+"r";
	    			t[pos].goRight=false;
	    			pos++;
	    			System.out.println("\ngo right position="+pos);
	    			t[pos].history=t[pos].history+"l";
	    			updateStatus(t,pos);
	    		}
	    		//go to the down direction
	    		else if(t[pos].goDown==true && t[pos].lastPosiDown==false && (pos+4)<=16 &&  t[pos+4].proPit<max && t[pos+4].proWumpus<max && 
	    					t[pos+4].havePit==false && t[pos+4].haveWumpus==false){
	    						t[pos].goDown=false;
	    						pos=pos+4;
	    						System.out.println("\ngo down pos= "+pos);
	    						t[pos].history+="u";
	    						updateStatus(t,pos);
	    		}
	    		else{
	    			if(t[pos].hasDown){
	    				pos=pos+4;
	    				System.out.println("\ngo down pos= "+pos);
	    				t[pos].history+="u";
	    				updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasLeft){
	    				pos=pos-1;
	    				System.out.println("\ngo left pos= "+pos);
	    				t[pos].history+="r";
	    				updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasRight){
	    				t[pos].history=t[pos].history+"r";
		    			pos++;
		    			System.out.println("\ngo right position="+pos);
		    			t[pos].history=t[pos].history+"l";
		    			updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasUp){
    					pos=pos-4;
    					System.out.println("\ngo up pos= "+pos);
    					t[pos].history+="d";
    					updateStatus(t,pos);
	    			}
	    		}
	                break;
	            case 2://move left
	            	if((t[pos].goLeft==true && t[pos].lastPosiLeft==false && t[pos-1].proPit<max && t[pos-1].proWumpus<max && t[pos-1].havePit==false&& t[pos-1].haveWumpus==false 
					&& !t[pos].history.contains("l"))||(t[pos].goDown==false && t[pos].goRight==false&& t[pos].goUp==false)){
						t[pos].goLeft=false;//go this direction
						pos=pos-1;
						System.out.println("\ngo left pos= "+pos);
						t[pos].history+="r";
						updateStatus(t,pos);
					}
			
			else if((t[pos].goUp==true && t[pos].lastPosiUp==false&& (pos-4)>=1 && t[pos-4].proPit<max && t[pos-4].proWumpus<max && 
					t[pos-4].havePit==false && t[pos-4].haveWumpus==false && !t[pos].history.contains("u"))||(t[pos].goDown==false && t[pos].goLeft==false && t[pos].goRight==false)){
						t[pos].goUp=false;
						pos=pos-4;
						System.out.println("\ngo up pos= "+pos);
						t[pos].history+="d";
						updateStatus(t,pos);
					}
			//go to the down direction
			else if(t[pos].goDown==true && t[pos].lastPosiDown==false && (pos+4)<=16 &&  t[pos+4].proPit<max && t[pos+4].proWumpus<max && 
						t[pos+4].havePit==false && t[pos+4].haveWumpus==false){
							t[pos].goDown=false;
							pos=pos+4;
							System.out.println("\ngo down pos= "+pos);
							t[pos].history+="u";
							updateStatus(t,pos);
			}
			//move right first
			else if((t[pos].goRight==true && t[pos].lastPosiRight==false && t[pos+1].proPit<max && t[pos+1].proWumpus<max && 
						t[pos+1].havePit==false && t[pos+1].haveWumpus==false &&!t[pos].history.contains("r") )|| (t[pos].goDown==false && t[pos].goLeft==false && t[pos].goUp==false) ){
						t[pos].history=t[pos].history+"r";
						t[pos].goRight=false;
						pos++;
						System.out.println("\ngo right position="+pos);
						t[pos].history=t[pos].history+"l";
						updateStatus(t,pos);
					}
			else{
    			if(t[pos].hasDown){
    				pos=pos+4;
    				System.out.println("\ngo down pos= "+pos);
    				t[pos].history+="u";
    				updateStatus(t,pos);
    			}
    			else if(t[pos].hasLeft){
    				pos=pos-1;
    				System.out.println("\ngo left pos= "+pos);
    				t[pos].history+="r";
    				updateStatus(t,pos);
    			}
    			else if(t[pos].hasRight){
    				t[pos].history=t[pos].history+"r";
	    			pos++;
	    			System.out.println("\ngo right position="+pos);
	    			t[pos].history=t[pos].history+"l";
	    			updateStatus(t,pos);
    			}
    			else if(t[pos].hasUp){
					pos=pos-4;
					System.out.println("\ngo up pos= "+pos);
					t[pos].history+="d";
					updateStatus(t,pos);
    			}
			}
			
	                break;
	            case 3://move right
	            	if((t[pos].goRight==true && t[pos].lastPosiRight==false && t[pos+1].proPit<max && t[pos+1].proWumpus<max && 
	    			t[pos+1].havePit==false && t[pos+1].haveWumpus==false &&!t[pos].history.contains("r") )|| (t[pos].goDown==false && t[pos].goLeft==false && t[pos].goUp==false) ){
	    			t[pos].history=t[pos].history+"r";
	    			t[pos].goRight=false;
	    			pos++;
	    			System.out.println("\ngo right position="+pos);
	    			t[pos].history=t[pos].history+"l";
	    			updateStatus(t,pos);
	    		}

	    		else if((t[pos].goLeft==true && t[pos].lastPosiLeft==false && t[pos-1].proPit<max && t[pos-1].proWumpus<max && t[pos-1].havePit==false&& t[pos-1].haveWumpus==false 
	    			&& !t[pos].history.contains("l"))||(t[pos].goDown==false && t[pos].goRight==false&& t[pos].goUp==false)){
	    				t[pos].goLeft=false;//go this direction
	    				pos=pos-1;
	    				System.out.println("\ngo left pos= "+pos);
	    				t[pos].history+="r";
	    				updateStatus(t,pos);
	    			}
	    		else if((t[pos].goUp==true && t[pos].lastPosiUp==false&& (pos-4)>=1 && t[pos-4].proPit<max && t[pos-4].proWumpus<max && 
	    				t[pos-4].havePit==false && t[pos-4].haveWumpus==false && !t[pos].history.contains("u"))||(t[pos].goDown==false && t[pos].goLeft==false && t[pos].goRight==false)){
	    					t[pos].goUp=false;
	    					pos=pos-4;
	    					System.out.println("\ngo up pos= "+pos);
	    					t[pos].history+="d";
	    					updateStatus(t,pos);
	    				}
	    		//go to the down direction
	    		else if(t[pos].goDown==true && t[pos].lastPosiDown==false && (pos+4)<=16 &&  t[pos+4].proPit<max && t[pos+4].proWumpus<max && 
	    					t[pos+4].havePit==false && t[pos+4].haveWumpus==false){
	    						t[pos].goDown=false;
	    						pos=pos+4;
	    						System.out.println("\ngo down pos= "+pos);
	    						t[pos].history+="u";
	    						updateStatus(t,pos);
	    		}
	    		else{
	    			if(t[pos].hasDown){
	    				pos=pos+4;
	    				System.out.println("\ngo down pos= "+pos);
	    				t[pos].history+="u";
	    				updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasLeft){
	    				pos=pos-1;
	    				System.out.println("\ngo left pos= "+pos);
	    				t[pos].history+="r";
	    				updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasRight){
	    				t[pos].history=t[pos].history+"r";
		    			pos++;
		    			System.out.println("\ngo right position="+pos);
		    			t[pos].history=t[pos].history+"l";
		    			updateStatus(t,pos);
	    			}
	    			else if(t[pos].hasUp){
    					pos=pos-4;
    					System.out.println("\ngo up pos= "+pos);
    					t[pos].history+="d";
    					updateStatus(t,pos);
	    			}
	    		}
	                break;
	        }
	        
	        
			if(t[pos].cellName.contains("W")){
			t[pos].isSafe=false;
			t[pos].isSafe=true;
			t[pos].proWumpus=Double.POSITIVE_INFINITY;
			t[pos].haveWumpus=true;
			System.out.println("\nIt is Wumpus! You are failed....rethinking...");
			failed=true;
				for(int l=1;l<=16;++l){
					if(l!=pos){
						t[l].proWumpus=0;
					}
				}
			}
			else if(t[pos].cellName.contains("P")){
				t[pos].havePit=true;
				t[pos].isSafe=false;
				t[pos].isSafe=true;
				t[pos].proPit=Double.POSITIVE_INFINITY;
				System.out.println("\nFall in pit! You are failed....rethinking");
				failed=true;
			}
		try{
			Thread.sleep(200);
			}
		catch(Exception p){}
		int numFi=0;
		for(int k=1;k<=16;k++){
			if(t[k].haveVisited==false){
				complete=false;
				break;
			}
			else{
				numFi++;
			}
		}
		if(numFi==16){
			complete=true;
			System.out.println("\n\nget all infor. for wupus world");
		}
	}while(complete==false);	
	//draw the new world
		String [][] newW=new String[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				newW[i][j]="";
			}
		}
		int c1=1;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				
				if(t[c1].haveWumpus==true){
					newW[i][j]+="W";
				}
				if(t[c1].haveGold==true){
					newW[i][j]+="G";
				}
				if(t[c1].havePit==true){
					newW[i][j]+="P";
				}
				if(t[c1].haveBreeze==true){
					newW[i][j]+="B";
				}
				if(t[c1].haveStench==true){
					newW[i][j]+="S";
				}
				c1++;
			}
		}
		e.show(newW);
		System.out.println("*S:stench; B: Breeze; G: Gold; W:Wumpuss; P: Pit\n\nTotal step used is "+step);
		
}

	//update the current status in one cell
	public static void updateStatus(CellInf[] t, int pos){
		if(t[pos].cellName.contains("W"))
			t[pos].isUnsafe=true;
		//mark the around of breeze of the possible pit
		else {
			if(t[pos].cellName.contains("B") && t[pos].haveVisited==false){
				if(t[pos].goRight==true && t[pos+1].isSafe==false)
					t[pos+1].proPit+=1;
				if(t[pos].goUp==true && (pos-4)>=1 && t[pos-4].isSafe==false)
					t[pos-4].proPit+=1;
				if(t[pos].goLeft==true && t[pos-1].isSafe==false )
					t[pos-1].proPit+=1;
				if(t[pos].goDown==true && (pos+4)<=16 && t[pos+4].isSafe==false)
					t[pos+4].proPit+=1;
			}
		//mark the around of stench of the possible wumpus
		if(t[pos].cellName.contains("S") && t[pos].haveVisited==false){
			if(t[pos].goRight==true && t[pos+1].isSafe==false)
				t[pos+1].proWumpus+=1;
			if(t[pos].goUp==true && (pos-4)>=1 && t[pos-4].isSafe==false)
				t[pos-4].proWumpus+=1;
			if(t[pos].goLeft==true && t[pos-1].isSafe==false )
				t[pos-1].proWumpus+=1;
			if(t[pos].goDown==true && (pos+4)<=16 && t[pos+4].isSafe==false)
				t[pos+4].proWumpus+=1;
			t[pos].isSafe=true;
		}
		if(t[pos].cellName.contains("G")){
			t[pos].isUnsafe=false;
			t[pos].isSafe=true;
			t[pos].haveGold=true;
			System.out.println("\nfind Gold!!");
		}
		if(t[pos].cellName.contains("B")){
			t[pos].isUnsafe=false;
			t[pos].isSafe=true;
			t[pos].haveBreeze=true;
		}
		if(t[pos].cellName.contains("S")){
			t[pos].isUnsafe=false;
			t[pos].isSafe=true;
			t[pos].haveStench=true;
		}
		}
		t[pos].haveVisited=true;
}
}




