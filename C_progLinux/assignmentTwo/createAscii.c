#include <stdio.h>
#include <stdlib.h>
#include <string.h> 

#include "asciiArt.h"

/** \brief
 * 
 * This function opens an outputfile.txt and it reads 30 lines and gets 30 chars from each file in the targeted directory  
 * and appends them into the output file which puzzle the parts into ascii art.
 * <------------------------------------->  
 * Author: @MolRob15 ==> Robert Mattias Molin
 * 2016.11.26
 * Version 1.0
 */


void createAscii(int fileHeight, int fileWidth, char *fileDescriptor) {
	const int charsPerLineInFile = 30;
	const int linesPerFile = 30;
	int i, j, k, l, m;

	FILE *fpArray[fileWidth];

	//Your output file 
	FILE *fpNewFile = fopen("/yourFile_path/directory/your_outputFile.txt", "a"); 
	
	for(i = 0; i < fileHeight; i++) {
		for(j = 0; j < fileWidth; j++) {
			char *str = malloc(sizeof(char) * 30);
			
			snprintf(str,30,"./%s/part_%d_%d_wolf.txt",fileDescriptor,i,j);
			printf("%s\n",str);
			fpArray[j] = fopen(str, "r");

			if(fpArray[j] == NULL){
				printf("File not found: %s\n",str);
				exit(-1);			
			}

			free(str);
		}

 		for(k = 0; k < linesPerFile; k++) {
			for(l = 0; l < fileWidth; l++) {
				for(m = 0; m < charsPerLineInFile; m++) {
					//Calls function readChar for '\n' check						
					fputc(readChar(fpArray[l]), fpNewFile);
				}
			}

			fputc('\n', fpNewFile);
		}	
		//Free memory in fpArray
		for(j = 0; j < fileWidth; j++) {
			fclose(fpArray[j]);	
		}	
	}

	fclose(fpNewFile);
}

/**
 * Recursive function readChar 
 * checks for new line character 
 */
char readChar(FILE *fp) {

	char ch = fgetc(fp);				
	if (ch == '\n') {
		return readChar(fp);	
	} else {		
		return ch;	
	}  	
}	



