#ifndef __BOARD_H__
#define __BOARD_H__


#define BOARD_SIZE 8


//Defines the three states each field in a reversi board may have.
//Uses ASCII-characters as values to make it easier to draw the board.

typedef enum Field {

EMPTY = ' ',
BLACK = '@',
WHITE = '.'

} Field;

//Struct for storing a board

typedef struct Board {

	Field fields [BOARD_SIZE][BOARD_SIZE];

} Board;

#endif //__BOARD_H__


//Function declarations

void Othello();

void initBoard(Board* board);

void printBoard(const Board* board);

int returnValidMove(Board* currentBoard, int moves[][BOARD_SIZE], enum Field player);

void makeMove(Board* currentBoard, int row, char col, enum Field player);

