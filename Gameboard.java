
package tictactoev3;


public class Gameboard {
    
    int size;   
    int fields;
    String[][] marker; 
      
    public Gameboard(int fieldsIn){
        
        fields = fieldsIn;
        marker = new String[fields][fields];
    }
       
    public void fillArray(){    //Bygger upp array i önskad storlek
        
        for(int i = 0; i < fields; i++){
            for( int j = 0; j < fields; j++){
                marker[i][j] = " ";
            }
        }
    }
    
    public void createBoard(int sizeIn){    //Bygger upp Spelplan i önskad storlek
        
        size = sizeIn;               
        
        System.out.print("  ");
        for(int j = 1; j <= size; j++){
                int letter = j + 96;
                char abc = (char)letter;
                System.out.print(" " + abc + "  ");
            }
        System.out.print("\n");
        
        for(int i = 0; i < size - 1; i++){
            System.out.print(i + 1 + " ");
            for(int j = 0; j < size - 1; j++){
                System.out.print(" " + marker[i][j] + " |");
            }
            System.out.print(" " + marker[i][size - 1] + "\n");
            System.out.print("  ");

            for(int j = 0; j < size - 1; j++){
                System.out.print("---+");
            }
            System.out.print("---" + "\n");
        }
        System.out.print(size + " ");
        for(int j = 0; j < size - 1; j++){
            System.out.print(" " + marker[size - 1][j] + " |");
        }
        System.out.print(" " + marker[size - 1][size - 1] + " " + "\n");
    }
    
    public boolean insertMarker(int xIn, int yIn, String symbolIn){     //Tar emot kordinater och stoppar in i array
        
        int x = xIn;
        int y = yIn;
        String symbol = symbolIn;
        boolean set = false;
        
        if(marker[y-1][x-1] == " "){
            marker[y-1][x-1] = symbol;
            set = true;
        }
        else if(marker[y-1][x-1] == "X" || marker[y-1][x-1] == "O"){
            System.out.println("Platsen är upptagen!");
        }
        
        return set;
    }
    
    public boolean fullBoard(){    //Ser om spelplanen är full
        
        boolean full = true;
        boolean stop = false;                
        int i = 0;
        int j = 0;

        while(stop == false){
            if(marker[i][j] == " "){
                full = false;
                stop = true;
            }
            else{
                if(j < fields - 1){
                    j++;
                }
                else{
                    if(i < fields - 1){
                        i++;
                        j = 0;
                    }
                    else{
                        stop = true;
                    }
                }
            }
        }                
        return full;
    }
    
    public boolean winner(String symbolIn){
        
        String symbol = symbolIn;
        boolean winner = false;
                
        for(int i = 0; i < fields; i++){    // Kollar tre i rad horisontelt
            for(int j = 0; j < fields - 2; j++){
                if( (marker[i][j] == symbol) && (marker[i][j+1] == symbol) && (marker[i][j+2] == symbol) ){
                    winner = true;
                }
            }
        }
                
        for(int i = 0; i < fields; i++){    //Kollar tre i rad vertikalt
            for(int j = 0; j < fields - 2; j++){
                if( (marker[j][i] == symbol) && (marker[j+1][i] == symbol) && (marker[j+2][i] == symbol) ){
                    winner = true;
                }
            }
        }
               
        for(int i = 0; i < fields - 2; i++){    //Kollar tre i rad diagonalt vänster till höger
            for(int j = 0; j < fields - 2; j++){
                if( (marker[j][i] == symbol) && (marker[j+1][i+1] == symbol) && (marker[j+2][i+2] == symbol) ){
                    winner = true;
                }
            }
        }
               
        for(int i = 0; i < fields - 2; i++){    //Kollar tre i rad diagonalt höger till vänster
            for(int j = fields - 1; j > 1; j--){
                if( (marker[i][j] == symbol) && (marker[i+1][j-1] == symbol) && (marker[i+2][j-2] == symbol) ){
                    winner = true;
                }
            }
        }
        
        return winner;
    }
    
}
