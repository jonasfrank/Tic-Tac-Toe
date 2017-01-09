
package tictactoev3;
import java.util.*;

abstract class Player {
    
    Scanner scan = new Scanner(System.in);
    String name;
    String symbol;
    int points;
    boolean xyFree;
    boolean fullBoard = false;
    boolean winnerCheck = false;

    Player(String symbolIn){
        
        symbol = symbolIn;
        
    }
    
    Player(String nameIn, String symbolIn){
        
        name = nameIn;
        symbol = symbolIn;
        points = 0;
    }
    
    public boolean takeTurn(Gameboard gameIn, int sizeIn){
         
        return fullBoard;

    }       
   
    public void addPoint(){
        
        points += 1;
    }
    
    
}
