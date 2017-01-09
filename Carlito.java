
package tictactoev3;
import java.util.*;

public class Carlito extends Player {
    
    //Carlito är en Ai-class med bara random-markeringar
    Random random = new Random();
    
    public Carlito(String nameIn, String symbolIn){
        super(nameIn, symbolIn);
    }
    
    @Override
    public boolean takeTurn(Gameboard gameIn, int sizeIn){
        
        int row;
        int col;
        int size = sizeIn;
        Gameboard game = gameIn;
        
        do{     //Tar fram random kordinater tills den hittar en ledig plats
            System.out.println("\n" + name + "s tur!");
            
            row = random.nextInt(size) + 1;
            col = random.nextInt(size) + 1;
            
            xyFree = game.insertMarker(col, row, symbol);
            
        } while(xyFree == false);
        
        game.createBoard(size);
        winnerCheck = game.winner(symbol);
        
        if(winnerCheck == true){
            System.out.println("\n" + "Grattis " + name + " du vann omgången!");
            fullBoard = true;
            addPoint();
        }
        else{
            fullBoard = game.fullBoard();
        }
        if(fullBoard == true && winnerCheck != true){
            System.out.println("\n" + "Omgången slutade oavgjort!");
        }
          
        return fullBoard;
    }       
    
}
