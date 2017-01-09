
package tictactoev3;
import java.util.*;

public class TicTacToeV3 {
    
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        Player p1 = null;
        Player p2 = null;        
        String name1;
        String name2;
        
        int input = 0;
        int pointCount;
             
        boolean newGame = true;       
                
        while(newGame == true){     //Körs igen om man väljer det i menyn som visas när spelet är klart
            System.out.println("    Carlito's TicTacToe  " + "\n" + "  -----------------------");
            System.out.println("\n" + "    --- Alternativ ---  ");
            System.out.println("\n" + "1. En spelare (Spela mot dator)");
            System.out.println("2. Två spelare");            

            boolean reMatch = true;
            boolean correctInput = false;
            boolean easyHard = false;
            
            while(correctInput == false){   //Kör menyn tills något alternativ har valts

                char settings = scan.next().charAt(0);                   

                switch(settings){
                    case '1':  
                        System.out.print("\n" + "Namn på spelare 1: ");
                        name1 = scan.next();
                        System.out.println("\n" + "Svårighetsgrad:" + "\n" + "1. Lätt" + "\n" + "2. Svår");  
                        
                        while(easyHard == false){
                        char difficulty = scan.next().charAt(0);                       
                        
                            switch(difficulty){     //Kör meny2 tills en svårighetsgrad är vald
                                case '1':
                                    p1 = new Human(name1, "O");
                                    p2 = new Carlito("Carlito (Dator)", "X");
                                    easyHard = true;
                                    break;
                                case '2':
                                    p1 = new Human(name1, "O");
                                    p2 = new SmartAi("Carlito (Dator)", "X");
                                    easyHard = true;
                                    break;
                                default:
                                    System.out.println("Alternativet finns inte, testa igen!");
                                    break;
                            }
                        }                        
                        correctInput = true;
                        break;
                    case '2':
                        System.out.print("\n" + "Namn på spelare 1: ");
                        name1 = scan.next();
                        System.out.print("Namn på spelare 2: ");
                        name2 = scan.next();
                        p1 = new Human(name1, "O");
                        p2 = new Human(name2, "X");
                        correctInput = true;
                        break;
                    default:
                        System.out.println("Alternativet finns inte, testa igen!");
                    break;
                }
            }

            boolean correctSize = false;
            
            while(correctSize == false){    //Kör tills den fått ett korrekt värde till brädstorleken
                System.out.print("\n" + "Storlek på bräde (3 - 9): ");
                input = scan.nextInt();
                if(input > 2 && input <= 9){
                    correctSize = true;
                }
                else{
                    System.out.println("Fel inmatning, testa igen!");
                    correctSize = false;
                }
            }
            System.out.print("\n" + "Hur många poäng vill ni spela till: ");
            pointCount = scan.nextInt();
            System.out.println("\n");
           
            while(reMatch == true){     //Kör igen om man väljer revanch               
                do{     //Kör rundor tills någon har poäng som räcker till vinst
                    boolean fullBoard = false;
                    // Här skapas spelplanen
                    Gameboard game = new Gameboard(input);
                    game.fillArray();
                    game.createBoard(input);
                    
                    do{     //Kör så länge det finns plats på spelplan
                        if(fullBoard == false){
                            fullBoard = p1.takeTurn(game, input);        
                        }
                        if(fullBoard == false){
                            fullBoard = p2.takeTurn(game, input);
                        }
                    } while(fullBoard == false);

                    System.out.println("\n" + " Poängställning:");
                    System.out.println("-----------------");
                    System.out.println("\n" + p1.name + ": " + p1.points + "    " + p2.name + ": " + p2.points + "\n");

                }while (p1.points < pointCount && p2.points < pointCount);

                if(p1.points == pointCount){
                    System.out.println("Grattis " + p1.name + " du vann spelet!");
                }
                if(p2.points == pointCount){
                    System.out.println("Grattis " + p2.name + " du vann spelet!");
                }
                
                //Spela igen - meny
                System.out.println("\n" + "Vill du spela igen?" + "\n" + "  -----------------------");
                System.out.println("\n" + "1. Jag vill ha revansch! (samma inställningar)");
                System.out.println("2. Jag vill spela igen, men med nya inställningar!");
                System.out.println("3. Nej, avsluta!"); 
                
                boolean quit = true;
                while(quit == true){
                    char reRun = scan.next().charAt(0);
                    switch(reRun){
                        case '1':
                            newGame = false;
                            reMatch = true;
                            p1.points = 0;
                            p2.points = 0;
                            quit = false;
                            break;
                        case '2':
                            newGame = true;
                            reMatch = false;
                            quit = false;
                            p1.points = 0;
                            p2.points = 0;
                            break;
                        case '3':
                            newGame = false;
                            reMatch = false;
                            quit = false;
                            break;
                        default:
                            System.out.println("Alternativet finns inte, testa igen!");
                            break;
                    }
                }
            }  
        }
        
        System.out.println("Tack för att du spelade!");
    }
    
}
