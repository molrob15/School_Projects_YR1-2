/** 
 * This function is the game's main loop
 * It takes the names from the two players and there after
 * sends the input from the users to appropriate functions
 * for validation.
 * <------------------------------------->  
 * @author molrob15 ==> Robert Mattias Molin
 * Inspired by: Beginning C, Third Edition: By Ivor Horton
 * 2016.12.13
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "game.h"

//Main game loop
void Othello()
{	
   /**
	* Array for storing the valid moves being made,
	* the move array also gets passed to both function validMove and makeMove.		 
	*/
	int moves[BOARD_SIZE][BOARD_SIZE] = { 0 }; 
	
	//Counters for keeping track of valid and invalid moves. 
	int validMoves = 0;
	int invalidMoves = 0;	

	//For storing row(x) and column(y)	
	char y = 0;	
	int x = 0;
    
	int scoreBlack = 0;
	int scoreWhite = 0;		
	char player1_name [20];
	char player2_name [20];
	char passMove = 0; //If no move is available it makes the game move on.                          	
			
	printf("\n<--The thrilling game of OTHELLO brace yourself!-->\n\n");
	printf("For game rules please check out the readMe.txt file.");
	printf("Choose a square for your move by typing (1-8) for the row\n"
    "and (a-h) for the column with NO SPACES in between plzz or you'll mess up the game, soo let's not ok :) highfive! Black moves first .\n\n");
	
	printf("\nPlayer1 will be BLACK (@) & Player2 will be WHITE (.)\n");

	printf("\nEnter name of Player1:\n\n");
	scanf("%s", player1_name);

	printf("\nEnter name of Player2:\n\n");
	scanf("%s", player2_name);		

	printf("\n"); //Only for readability in terminal		

	//Print & initialize board to starting position			
	Board currentBoard;
    initBoard(&currentBoard);
    printBoard(&currentBoard);
	
	printf("\n"); //Only for readability in terminal
	do{
		if(returnValidMove(&currentBoard, moves, BLACK) ) //Calls function returnValidMove to evaluate if the move is allowed
         {
//			invalidMoves = 0;
           // Reads player1's move until a valid move is entered 
           while(1)
           {
             fflush(stdin); //clears stdin buffer before reading              
             printf("Please enter your move %s first by entering row no(1-8), 2nd by column no (a-h): ", player1_name); 
             scanf("%d%c", &x, &y);              
             y = tolower(y) - 'a';         
             x--;                          
             if( x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE && moves[x][y])
             {
				FILE *fp = fopen("gameHistory.txt", "a");
				if(fp == NULL) {
        			printf("***File could not be opened.\n");
        			exit(-1);
				}				
				//TODO fix input:fprintf(fp, "%d%c\n", x, y); //writes all moves to a gameHistory file				

               	makeMove(&currentBoard, x, y, BLACK); //Calls function makeMove
				validMoves++;
				printBoard(&currentBoard);
				fclose(fp); //close file
				printf("\n"); //Only for readability in terminal           
               break;
             }
             else
               printf("Not a valid move, try again please.\n");
           }
         }			
			else                              
				if(++invalidMoves < 2) //pre-incrementing the value of invalidMoves
			{
             	fflush(stdin);
             	printf("\nDooh! Black has no legal moves\n");
             	scanf(" %c", &passMove);
           	}
           	else {
             	printf("\nNo valid moves for any players\n");
				break;       			
			}
			printf("\n"); //Only for readability in terminal
			if(returnValidMove(&currentBoard, moves, WHITE) ) //Calls function returnValidMove
         {
//			invalidMoves = 0; 
          // Reads player2's move until a valid move is entered 
           while(1)
           {
             fflush(stdin); //clears stdin buffer before reading             
             printf("Please enter your move %s first by entering row no(1-8), 2nd by column no (a-h): ", player2_name); 
             scanf("%d%c", &x, &y);              
             y = tolower(y) - 'a';         
             x--;                          
             if( x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE && moves[x][y])
             {
				FILE *fp = fopen("gameHistory.txt", "a");
				if(fp == NULL) {
       			printf("***File could not be opened.\n");
        			exit(-1);
				}
				//TODO fix input:	fprintf(fp, "%d%c\n", x, y); //writes all moves to a gameHistory file

               	makeMove(&currentBoard, x, y, WHITE); //Calls function makeMove				
				validMoves++;
				printBoard(&currentBoard);
				fclose(fp); //close file
				printf("\n");             
               	break;
             }
             else
               printf("Not a valid move, try again please.\n");					
           }
         }
		else                              
		// If there are no valid moves
		if(++invalidMoves < 2) {
             fflush(stdin);
             printf("\nDooh! WHITE has no legal moves\n");
             scanf(" %c", &passMove);
           }
           else{ 
             printf("\nNo valid moves for any player.\n");
				break;			 
			}
	/**
     * Keep going as long as the number of valid moves doesn't fill up the board
	 * & the number of invalid moves is less than 2
	 * End do while
	 */	       		
	} while(validMoves < BOARD_SIZE * BOARD_SIZE && invalidMoves < 2);  
	printf("\nGame Over\n");

	//Prints out the final score, the winner is the player with the most disc's on the board
	scoreBlack = scoreWhite = 0; 
    for(int row = 0; row < BOARD_SIZE; row++)
       for(int col = 0; col < BOARD_SIZE; col++) {
		scoreBlack += currentBoard.fields[row][col] == BLACK;
    	scoreWhite += currentBoard.fields[row][col] == WHITE;
   }
	printf("\nFinal Score:\n");
	printf("\nBlack: %d\nWhite: %d\n\n", scoreBlack, scoreWhite);

	return;

}



