import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Controller
{
	private int n, m;
	public int[][] board; 
	public int[][] energia; 
	Map<Integer,Integer> ID = new HashMap<Integer,Integer>();
	int[][] tab = new int[3][3]; 
	Controller(int n, int m)
	{
		 this.n = n;
		 this.m = m;
		 this.board = new int[this.n][this.m];
		 this.energia = new int[this.n][this.m];
		 for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++) 
			 {
				 this.board[i][j]=0;
				 this.energia[i][j]=8;
			 }
		 }
	 }
	 
	public int moore(int x, int y,boolean w)
	{
		if(w)
		{
			tab = period(x,y);
		}
		else 
		{
			tab = noperiod(x, y);
		}
		int[] pom = sasiedzi(tab);
		int max = 0;
		for(int i=1;i<ID.size();i++)
		{
			if(board[x][y]!=ID.get(i) && pom[i]!=0)
			{
				max+=pom[i];
			}
		}
		return max;
	 }

	public int[] sasiedzi(int[][] tab)
	{
		int[] pomid = new int[ID.size()];
		for(int i=0;i<ID.size();i++)
		{
			pomid[i]=0;
		} 
		 for(int i=0;i<3;i++){
			 for(int j=0;j<3;j++)
			 {
				 if(i==1 && j==1)
				 {  
					 //
				 }
				 else
				 {
					 for(int k=0;k<ID.size();k++)
					 {
						 if(tab[i][j]==ID.get(k))
						 {
							 pomid[k]++;
						 }
						 else
						 {	 
							 //
						 }
					 }
				 }
			 }
		 }
		 return pomid;
	}
	
	public int[][] period(int x, int y)
	{
		//warunki periodczyne
		 if(x==0 && y==0){
			 tab[0][0] = this.board[n-1][m-1];  tab[0][1] = this.board[n-1][y];  tab[0][2] = this.board[n-1][y+1];
			 tab[1][0] = this.board[x][m-1];      tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][m-1];      tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
		 else if(x==0 && y==this.m-1){
			 tab[0][0] = this.board[n-1][y-1];    tab[0][1] = this.board[n-1][y];  tab[0][2] = this.board[n-1][0];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][0];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][0];
		 }
		 else if(x==this.n-1 && y==0){
			 tab[0][0] = this.board[x-1][m-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][m-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[0][m-1];    tab[2][1] = this.board[0][y];	 tab[2][2] = this.board[0][y+1];
		 }
		 else if(x==this.n-1 && y==this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][0];
			 tab[1][0] = this.board[x][y-1];  	tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][0];
			 tab[2][0] = this.board[0][y-1];  	tab[2][1] = this.board[0][y];	 tab[2][2] = this.board[0][0];
		 }	
		 else if(x==0 && y>0 && y<this.m-1){
			 tab[0][0] = this.board[n-1][y-1]; tab[0][1] = this.board[n-1][y];  tab[0][2] = this.board[n-1][y+1];;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
		 else if(x>0 && x<this.n-1 && y==0){
			 tab[0][0] = this.board[x-1][m-1]; 	tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][m-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][m-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];	
		 }
		 else if(x==this.n-1 && y>0 && y<this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[0][y-1];  	tab[2][1] = this.board[0][y];  	 tab[2][2] = this.board[0][y+1];		
		 }
		 else if(y==this.m-1 && x>0 && x<this.n-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][0];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][0];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][0];
		 }
		 else{
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
		 return tab;
	}
	
	public int[][] noperiod(int x, int y)
	{
		 if(x==0 && y==0){
			 tab[0][0] = 0;  	 tab[0][1] = 0;  				  tab[0][2] = 0;
			 tab[1][0] = 0;      tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;      tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
		 else if(x==0 && y==this.m-1){
			 tab[0][0] = 0;    					tab[0][1] = 0;  				 tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = 0;
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = 0;
		 }
		 else if(x==this.n-1 && y==0){
			 tab[0][0] = 0;    tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = 0;    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;    tab[2][1] = 0;				    tab[2][2] = 0;
		 }
		 else if(x==this.n-1 && y==this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];  	tab[1][1] = this.board[x][y];    tab[1][2] = 0;
			 tab[2][0] = 0;  					tab[2][1] = 0;	 				 tab[2][2] = 0;
		 }	
		 else if(x==0 && y>0 && y<this.m-1){
			 tab[0][0] = 0; 					tab[0][1] = 0;  				 tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
		 else if(x>0 && x<this.n-1 && y==0){
			 tab[0][0] = 0; 	tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = 0;    	tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;  	tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];	
		 }
		 else if(x==this.n-1 && y>0 && y<this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;  					tab[2][1] = 0;  	 			 tab[2][2] = 0;		
		 }
		 else if(y==this.m-1 && x>0 && x<this.n-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = 0;
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = 0;
		 }
		 else{
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
	
		 return tab;
	}
	
	public void count(int a,boolean b)
	{
		int[][] apt = new int[this.n][this.m]; 
		int[][] previous = new int[n][m];
		
		for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++)
			 {
				 	if(energia[i][j]!=0)
				 	{
					 	previous[i][j] = board[i][j];
						Random generator = new Random();
						int rand = generator.nextInt(a)+1;
						this.board[i][j] = rand;
						if(energia[i][j]!=0)
						 {
							apt[i][j] = moore(i,j,b);
						 	if(apt[i][j]<energia[i][j])
							{
								energia[i][j] = apt[i][j];
							}
							else
							{
								board[i][j] = previous[i][j];
							}
						 }
				 	}
			 }
		 }
		
		/*for(int i=0;i<n;i++)
		{
			 for(int j=0;j<m;j++)
			 {
				 if(energia[i][j]!=0)
				 {
					apt[i][j] = moore(i,j,b);
				 	if(apt[i][j]<energia[i][j])
					{
						energia[i][j] = apt[i][j];
					}
					else
					{
						board[i][j] = previous[i][j];
					}
				 }
			 }
		 }*/
	 }




}
