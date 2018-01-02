/** 
 * This c file holds all the functions that are requiered for the game 
 * the functionality for each function is declared over each function itself.  
 * 
 * <------------------------------------->  
 * Author: @MolRob15 ==> Robert Mattias Molin
 * 2016.12.13
 * Inspired by: Beginning C, Third Edition: By Ivor Horton 
 */

#include "game.h"
#include <stdio.h>

/**
 * Set board to starting position
 * @author Kjetil Raaen
 */
void initBoard(Board* board) {
	for(int y = 0; y < BOARD_SIZE; y++) {
		for(int x = 0; x < BOARD_SIZE; x++){
			board->fields[y][x] = EMPTY;
		}
	}
	board->fields[3][3]=WHITE;
	board->fields[4][4]=WHITE;
	board->fields[3][4]=BLACK;
	board->fields[4][3]=BLACK;
}

/**
 * Prints the board using traditional ascii-art type graphics
 * @author Kjetil Raaen
 */ 
void printBoard(const Board* board) {
	printf("    a   b   c   d   e   f   g   h  \n");
	for(int y = 0; y < BOARD_SIZE; y++) {
		printf("  +---+---+---+---+---+---+---+---+\n");
		printf(" %d", y+1); //Computer counts from 0, the game counts from 1!
		for(int x = 0; x < BOARD_SIZE; x++) {
			printf("| %c ", board->fields[y][x]);
		}
		printf("| %d\n", y+1); //Computer counts from 0, the game counts from 1!
	}
	printf("  +---+---+---+---+---+---+---+---+\n");
	printf("    a   b   c   d   e   f   g   h  \n");
}

/**
 * The function makes sure that a valid move must be on a blank square and must enclose 
 * at least one opponent square between two player squares
 * hence all the tricky checks phew. 
 *
 *@param1: Pointer to access the actual board array fields[][]; 
 *
 *@param2: Array for storing valid moves.
 *	
 *@param3: Checks if player is BLACK or WHITE. 	
 *
 */

int returnValidMove(Board* currentBoard, int moves[][BOARD_SIZE], enum Field player)
{	
	int rowIncrease = 0; //Row increment around a square     
   	int colIncrease = 0; //Column increment around a square    

   	int row = 0; //Row index         
   	int col = 0; //Column index         

   	int x = 0; //Used when searching        
   	int y = 0; //Used when searching          
   	int validMoves = 0; //Number of valid moves, (1 == valid) & (0 == invalid); 
 
   // Sets player to BLACK or WHITE with a short hand if else statement
   char adversary = (player == WHITE) ? BLACK : WHITE;    

   for(row = 0; row < BOARD_SIZE; row++)
     for(col = 0; col < BOARD_SIZE; col++)
       moves[row][col] = 0; //Initialize moves array to zero 

   // Find squares for valid moves.                            
   for(row = 0; row < BOARD_SIZE; row++)
     for(col = 0; col < BOARD_SIZE; col++)
     {
       if(currentBoard->fields[row][col] != EMPTY)  
         continue;                  
		//Checks all the squares around the empty square for the adversary's disc ('.' / '@')
       for(rowIncrease = -1; rowIncrease <= 1; rowIncrease++)
         for(colIncrease = -1; colIncrease <= 1; colIncrease++)
         { 
           //makes sure the search doesn't check outside the boundaries of the array or the current square
           if(row + rowIncrease < 0 || row + rowIncrease >= BOARD_SIZE ||
              col + colIncrease < 0 || col + colIncrease >= BOARD_SIZE || 
              (rowIncrease == 0 && colIncrease == 0))
             	continue;
           /**
			* From here the current square now gets checked, And adversary's disc is found
			* the code moves over the adversary's discs and checks for the current players disc
			* and if the search is succesfull, boom! we have a valid move.  
			*/
           if(currentBoard->fields[row + rowIncrease][col + colIncrease] == adversary)
           {           
             x = row + rowIncrease;                
             y = col + colIncrease;                             
             while(1) {
               x += rowIncrease;                  
               y += colIncrease;
                 
               if(x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)
                 break;
               
               if(currentBoard->fields[x][y] == EMPTY)
                 break;
                            
               if(currentBoard->fields[x][y] == player)
               {
                 moves[row][col] = 1;   // 1 marks as valid! happy days!
                 validMoves++;         
                 break;                 
               }
             } 
           } 
         }  
     }
   return validMoves; 
}

/**
 * This function makes the move happen, and flips the other player's discs (counters '@', '.')
 * according to the rules of the game
 *
 * @param1: Pointer to access actual board array fields[][];
 *
 * @param2:	Row index
 *
 * @param3: Column index
 *
 * @param4: Checks if player is BLACK or WHITE. 
 */

void makeMove(Board* currentBoard, int row, char col, enum Field player)
{	
   	int rowIncrease = 0;                  
   	int colIncrease = 0;                   
   	int x = 0;                         
   	int y = 0;                          
    	
    // Sets adversary/player to BLACK or WHITE with a short hand if else statement
   	char adversary = (player == WHITE) ? BLACK : WHITE;  

   	currentBoard->fields[row][col] = player; // Makes move     
  
	for(rowIncrease = -1; rowIncrease <= 1; rowIncrease++)
     	for(colIncrease = -1; colIncrease <= 1; colIncrease++) {  

    //makes sure the search doesn't check outside the boundaries of the array or the current square
    if(row + rowIncrease < 0 || row + rowIncrease >= BOARD_SIZE ||
          col + colIncrease < 0 || col + colIncrease >= BOARD_SIZE || 
          (rowIncrease == 0 && colIncrease == 0))
         	continue;
   /**
	* Checks the entered square for the current player's disc, 
	* if adversary's disc is found then look for the current players disc in the same direction	
	*/
	if(currentBoard->fields[row + rowIncrease][col + colIncrease] == adversary) {              
         x = row + rowIncrease;        
         y = col + colIncrease;                
	while(1) 
		{
           x += rowIncrease;           
           y += colIncrease;          
          
			if(x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)
				break;         
    		if(currentBoard->fields[x][y] == EMPTY)
             break;           
				/** 
				 *	If the player's disc is found, then reverse and flip
           		 *  adversary's discs to the current player's!         
				 */
    			if(currentBoard->fields[x][y] == player) {
             	//If player disc is found, then change it
				while(currentBoard->fields[x -= rowIncrease][y -= colIncrease] == adversary) 
               		currentBoard->fields[x][y] = player;     
             break;                     
           } 
         }//End while
       }
     }
}


