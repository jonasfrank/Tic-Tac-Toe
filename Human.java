
package tictactoev3;

public class Human extends Player {
    
    public Human(String nameIn, String symbolIn){
        super(nameIn, symbolIn);
        name = nameIn;
    }
    
    @Override
    public boolean takeTurn(Gameboard gameIn, int sizeIn){
        
        boolean xy;
        char rowTemp;
        char colTemp;
        int row;
        int col;
        int size = sizeIn;
        Gameboard game = gameIn;
                
        do{     //Körs tills spelaren satt en pjäs på en ledig plats
            System.out.println("\n" + name + "s tur!");       
            
            do {    //Körs tills användaren användaren har markerat en plats som finns på brädet
                System.out.print("Placera en pjäs (t.ex a1): ");
                String pos = scan.next();
                
                while(pos.length() != 2){   //Körs så länge spelaren skriver in något annat än 2 tecken
                    if(pos.length() != 2){
                        System.out.println("Fel input, testa igen!");
                        pos = scan.next();
                    }
                }
                System.out.println("\n");    
               
                if( (pos.charAt(0) - 47 > 0 && pos.charAt(0) - 47 <= size + 1) && (pos.charAt(1) - 96 > 0 && pos.charAt(1) - 96 <= size) ){      //Kollar om kordinaterna är skrivna med siffra först
                    colTemp = pos.charAt(1); 
                    rowTemp = pos.charAt(0);
                    xy = true;
                } 
                
                else if( (pos.charAt(0) - 96 > 0 && pos.charAt(0) - 96 <= size) && (pos.charAt(1) - 47 > 0 && pos.charAt(1) - 47 <= size + 1) ){    //Kollar om kordinaterna är skrivna med bokstav först
                    colTemp = pos.charAt(0); 
                    rowTemp = pos.charAt(1);
                    xy = true;
                } 
                else{
                    System.out.println("Platsen finns inte på brädet, testa igen!");
                    colTemp = pos.charAt(0); 
                    rowTemp = pos.charAt(1);
                    xy = false;
                }
            } while(xy == false);

            row = rowTemp - 48;
            col = colTemp - 96;

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
