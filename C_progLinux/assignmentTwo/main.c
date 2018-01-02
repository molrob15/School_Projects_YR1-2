#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "asciiArt.h"

/** \brief
 * The goal of this program is to read and merge 30x30 character parts of	
 * ASCII-drawings together from several files.  
 * The main function calls function createAscii to complete task.
 *  	
 * <------------------------------------->  
 * Author: @MolRob15 ==> Robert Mattias Molin
 * 2016.11.26
 * Version 1.0
 */


int main(int argc, char *argv[]) 
{
	//ascii to interger
	createAscii(atoi(argv[1]), atoi(argv[2]), argv[3]);
	
	return 0;
}

