
package tictactoev3;

import java.util.*;

public class SmartAi extends Player{
    
    Random random = new Random();
    
    public SmartAi(String nameIn, String symbolIn){
        super(nameIn, symbolIn);
    
    }
    
    @Override
    public boolean takeTurn(Gameboard gameIn, int sizeIn){
        
        int row;
        int col;
        int size = sizeIn;
        Gameboard game = gameIn;
        

        System.out.println("\n" + name + "s tur!");

        boolean smartMarker = false;
        
        smartMarker = firstRun(game, size);
        
        if(smartMarker == false){
            smartMarker = win(game, size);
        }
        
        if(smartMarker == false){
            smartMarker = blockWin(game, size);
        }                
        
        if(smartMarker == false){
            do{
                row = random.nextInt(size) + 1;
                col = random.nextInt(size) + 1;

                xyFree = game.insertMarker(col, row, symbol);
            }while(xyFree == false);
        }
        
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
    
    public boolean firstRun(Gameboard gameIn, int sizeIn){
        
        Gameboard game = gameIn;
        int size = sizeIn;
        boolean smartMarker = false;
        int middle = size / 2;
                
        if(game.marker[middle][middle] == " "){     //Markerar mittenrutan om den är ledig
            game.marker[middle][middle] = symbol;
            smartMarker = true;
        }
        
        if( (size < 4) && (game.marker[0][0] == " ") && (game.marker[size-1][size-1] == " ") && (game.marker[0][size-1] == " ") && (game.marker[size-1][0] == " ") && smartMarker == false){ //Markerar hörnruta i ett tre-rutorsspel om alla hörnen är lediga
            game.marker[0][0] = symbol;
            smartMarker = true;
        }
        
        return smartMarker;
    }
    
    public boolean win(Gameboard gameIn, int sizeIn){
        
        Gameboard game = gameIn;
        int size = sizeIn;
        boolean smartMarker = false;
        
        if(smartMarker == false){            
            for(int i = 0; i < size; i++){  //Kollar om det finns 2 lika i rad horisontelt.
                for(int j = 0; j < size - 1; j++){
                    if( (game.marker[i][j] == symbol) && (game.marker[i][j+1] == symbol) ){                        
                        if(j+2 < size){     
                            if(game.marker[i][j+2] == " " && smartMarker == false){     //Om det finns försöker den sätta in en tredje
                                game.marker[i][j+2] = symbol;      
                                smartMarker = true;
                            }   
                        }
                        if(j-1 >= 0 && smartMarker == false){
                            if(game.marker[i][j-1] == " "){
                                game.marker[i][j-1] = symbol;     
                                smartMarker = true;
                            }
                        }
                    }
                }
            }
        }
        if(smartMarker == false){            
            for(int i = 0; i < size - 1; i++){  //Kollar om det finns 2 lika i rad vertikalt
                for(int j = 0; j < size; j++){
                    if( (game.marker[i][j] == symbol) && (game.marker[i+1][j] == symbol) ){                        
                        if(i+2 < size && smartMarker == false){     
                            if(game.marker[i+2][j] == " "){     //Om det finns försöker den sätta in en tredje
                                game.marker[i+2][j] = symbol;  
                                smartMarker = true;
                            }
                        }
                        if(i-1 >= 0 && smartMarker == false){
                            if(game.marker[i-1][j] == " "){
                                game.marker[i-1][j] = symbol;  
                                smartMarker = true;
                            }
                        }
                    }
                }
            }
        }
        if(smartMarker == false){            
            for(int i = 0; i < size - 1; i++){  //Kollar om det finns 2 lika i rad diagonalt, vänster till höger
                for(int j = 0; j < size - 1; j++){
                    if( (game.marker[j][i] == symbol) && (game.marker[j+1][i+1] == symbol) ){                        
                        if(i+2 < size && j+2 < size && smartMarker == false){
                            if(game.marker[i+2][j+2] == " "){   //Om det finns försöker den sätta in en tredje
                                game.marker[i+2][j+2] = symbol;  
                                smartMarker = true;
                            }
                        }
                        if(i-1 >= 0 && j-1 >= 0 && smartMarker == false){
                            if(game.marker[i-1][j-1] == " "){
                                game.marker[i-1][j-1] = symbol; 
                                smartMarker = true;
                            } 
                        }
                    }
                }
            }
        }
        if(smartMarker == false){            
            for(int i = 0; i < size - 1; i++){  //Kolllar om det finns 2 lika i rad diagonalt, höger till vänster
                for(int j = size - 1; j >= 1; j--){
                    if( (game.marker[i][j] == symbol) && (game.marker[i+1][j-1] == symbol) ){                        
                        if(i+2 < size && j-2 >= 0 && smartMarker == false){     
                            if(game.marker[i+2][j-2] == " "){   //Om det finns försöker den sätta in en tredje
                                game.marker[i+2][j-2] = symbol;  
                                smartMarker = true;
                            }
                        }
                        if(i-1 >= 0 && j+1 < size && smartMarker == false){
                            if(game.marker[i-1][j+1] == " "){
                                game.marker[i-1][j+1] = symbol;  
                                smartMarker = true;
                            }    
                        }
                    }
                }
            }
        }                
        if(smartMarker == false){           
            for(int i = 0; i < size; i++){  // Kollar ifall det finns 2 lika med ett hål imellan horisontelt
                for(int j = 0; j < size - 2; j++){
                    if( (game.marker[i][j] == symbol) && (game.marker[i][j+2] == symbol) ){                        
                        if(game.marker[i][j+1] == " " && smartMarker == false){     //Om det finns försöker den sätta in en tredje
                            game.marker[i][j+1] = symbol;      
                            smartMarker = true;
                        }                                                                       
                    }
                }
            }
        }
        if(smartMarker == false){            
            for(int i = 0; i < size - 2; i++){  // Kollar ifall det finns 2 lika med ett hål imellan vertikalt
                for(int j = 0; j < size; j++){
                    if( (game.marker[i][j] == symbol) && (game.marker[i+2][j] == symbol) ){                       
                        if(game.marker[i+1][j] == " " && smartMarker == false){     //Om det finns försöker den sätta in en tredje
                            game.marker[i+1][j] = symbol;      
                            smartMarker = true;
                        }                                                                         
                    }
                }
            }
        }        
        return smartMarker;
    }
    
    public boolean blockWin(Gameboard gameIn, int sizeIn){
        
        Gameboard game = gameIn;
        int size = sizeIn;
        boolean smartMarker = false;
        
        if(smartMarker == false){            
            for(int i = 0; i < size; i++){      //Kollar om det finns 2 lika i rad horisontellt.
                for(int j = 0; j < size - 1; j++){
                    if( (game.marker[i][j] == "O") && (game.marker[i][j+1] == "O") ){                        
                        if(j+2 < size){
                            if(game.marker[i][j+2] == " "){     //Om det finns försöker den blockera
                                game.marker[i][j+2] = symbol;      
                                smartMarker = true;
                            }   
                        }
                        if(j-1 >= 0 && smartMarker == false){
                            if(game.marker[i][j-1] == " "){
                                game.marker[i][j-1] = symbol;     
                                smartMarker = true;
                            }
                        }
                    }
                }
            }
        }
        if(smartMarker == false){            
            for(int i = 0; i < size - 1; i++){      //Kolllar om det finns 2 lika i rad vertikalt
                for(int j = 0; j < size; j++){
                    if( (game.marker[i][j] == "O") && (game.marker[i+1][j] == "O") ){                        
                        if(i+2 < size){
                            if(game.marker[i+2][j] == " "){     //Om det finns försöker den blockera
                                game.marker[i+2][j] = symbol;  
                                smartMarker = true;
                            }
                        }
                        if(i-1 >= 0 && smartMarker == false){
                            if(game.marker[i-1][j] == " "){
                                game.marker[i-1][j] = symbol;  
                                smartMarker = true;
                            }
                        }
                    }
                }
            }
        }
        if(smartMarker == false){            
            for(int i = 0; i < size - 1; i++){      //Kolllar om det finns 2 lika i rad diagonalt, vänster till höger
                for(int j = 0; j < size - 1; j++){
                    if( (game.marker[j][i] == "O") && (game.marker[j+1][i+1] == "O") ){                        
                        if(i+2 < size && j+2 < size){
                            if(game.marker[i+2][j+2] == " "){       //Om det finns försöker den blockera
                                game.marker[i+2][j+2] = symbol;  
                                smartMarker = true;
                            }
                        }
                        if(i-1 >= 0 && j-1 >= 0 && smartMarker == false)
                            if(game.marker[i-1][j-1] == " "){
                                game.marker[i-1][j-1] = symbol; 
                                smartMarker = true;
                            }                    
                    }
                }
            }
        }
        if(smartMarker == false){            
            for(int i = 0; i < size - 1; i++){      //Kolllar om det finns 2 lika i rad diagonalt, höger till vänster
                for(int j = size - 1; j >= 1; j--){
                    if( (game.marker[i][j] == "O") && (game.marker[i+1][j-1] == "O") ){                        
                        if(i+2 < size && j-2 >= 0){
                            if(game.marker[i+2][j-2] == " "){       //Om det finns försöker den blockera
                                game.marker[i+2][j-2] = symbol;  
                                smartMarker = true;
                            }
                        }
                        if(i-1 >= 0 && j+1 < size && smartMarker == false){
                            if(game.marker[i-1][j+1] == " "){
                                game.marker[i-1][j+1] = symbol;  
                                smartMarker = true;
                            }    
                        }
                    }
                }
            }
        }  
        if(smartMarker == false){            
            for(int i = 0; i < size; i++){      // Kollar ifall det finns 2 lika med ett hål imellan horisontelt
                for(int j = 0; j < size - 2; j++){
                    if( (game.marker[i][j] == "O") && (game.marker[i][j+2] == "O") ){                        
                        if(game.marker[i][j+1] == " "){     //Om det finns försöker den blockera
                            game.marker[i][j+1] = symbol;      
                            smartMarker = true;
                        }                                                                       
                    }
                }
            }
        }
        if(smartMarker == false){           
            for(int i = 0; i < size - 2; i++){      // Kollar ifall det finns 2 lika med ett hål imellan vertikalt
                for(int j = 0; j < size; j++){
                    if( (game.marker[i][j] == "O") && (game.marker[i+2][j] == "O") ){                        
                        if(game.marker[i+1][j] == " "){     //Om det finns försöker den blockera
                            game.marker[i+1][j] = symbol;      
                            smartMarker = true;
                        }                                                                         
                    }
                }
            }
        }
        return smartMarker;
    }
}
