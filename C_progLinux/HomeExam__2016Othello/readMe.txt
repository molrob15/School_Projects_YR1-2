
/**
 * @author Robert Mattias Molin.
 *	2016.12.13 
 *<-------------------------------->
 *	Rules of the game:
 *  
 *	The dark player moves first, so in our case BLACK moves first.
 *	A valid move is one where at least one piece is reversed.
 *  Players take alternate turns. If one player can not make a valid move, play passes back to the other player. 
 *  When neither player can move, the game ends. 
 *  This occurs when the grid has filled up or when neither player can legally place a piece in any of the remaining squares.
 *  This means the game may end before the grid is completely filled.
 *  This possibility may occur because one player has no pieces remaining on the board in that player's color.
 *  In over-the-board play this is generally scored as if the board were full (64–0).
 */
	
	Decisions and thoughts along the way:
	
	The whole program is divided up into three .c files and one .h file, it also hold's a Makefile for compiling all files. 
	The main function calls the function othello that run's the game loop. To achieve desired functionality I've 
	used a, do while loop contruct for the main game loop and appropriate functions to solve the diffrent tasks. The file game.c also has 
	an array to hold the moves made through out the game, and it get's passed to functions makeMove and returnValidMove.  
	parts of the code are inspired by the source I'm mentioning down below, especially the functions 
	for implementing the rules. The game should run without any problem as long as you play by the rules when entering position coordinates. 
	I am also aware that scanf doesn't provide any input 
	validation, which is a shame but I decided to use it so i could focus on other more complicated parts in the code.
	The comments in the source code should explain the enough to understand what's going on.
	The program could have been a lot more robust against bogus input, I'm well aware of the weaknesses in the code.
	But i struggled to make the game the way i wanted, so play by the rules and please enjoy.  

	Note: /*TODO*/ The game is supposed to save all the moves from the game but i haven't manged to get the input in properly, char y needs to be converted in some way
	at the moment the code is "//out commented" because the input is just bogus. So feel free to fix this if you like.         

	Code inspired by: 		
	/*
	Beginning C, Third Edition
 	By Ivor Horton
 	ISBN: 1-59059-253-0
 	Published: Apr 2004
 	Publisher: apress
	*/
	source available from: http://www.java2s.com/Code/C/String/REVERSIAnOthellotypegame.htm
	2016.12.14
 
