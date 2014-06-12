
/**provide the info. of each cell. 
*/
public class CellInf {
	String cellName;
	int cellPosition;
	boolean isSafe=false;
	boolean isUnsafe=false;
	boolean haveWumpus=false;
	boolean havePit=false;
	boolean haveGold=false;
	boolean haveBreeze=false;
	boolean haveStench=false;
	double proPit=0;
	double proWumpus=0;
	boolean goRight=true;//can walk toward right
	boolean goLeft=true;
	boolean goUp=true;
	boolean goDown=true;
	boolean hasLeft=true;
	boolean hasRight=true;
	boolean hasUp=true;
	boolean hasDown=true;
	boolean haveVisited=false;
	boolean lastPosiLeft,lastPosiRight,lastPosiUp,lastPosiDown;
	String history="";
	
	CellInf(String s,int n){
		cellName=s;
		cellPosition=n;
		lastPosiLeft=false;
		lastPosiRight=false;
		lastPosiUp=false;
		lastPosiDown=false;
		if(cellPosition==1 || cellPosition==13 ||cellPosition==9 || cellPosition==5){
			goLeft=false;
			hasLeft=false;
		}
		if(cellPosition==4 || cellPosition==16 || cellPosition==8 || cellPosition==12){
			goRight=false;
			hasRight=false;
		}
			
		if(cellPosition==1|| cellPosition==2|| cellPosition==3||cellPosition==4){
			goUp=false;
			hasUp=false;
		}
		if(cellPosition==13|| cellPosition==14 || cellPosition==15 ||cellPosition==16){
			goDown=false;
			hasDown=false;
		}
	}
}


