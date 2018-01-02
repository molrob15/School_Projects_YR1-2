#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/** \brief
 * The goal of this program is to read names from a text file
 * And save them in a 2D array
 * Then use bubble sort to sort the array
 * And finally print out the sorted array
 *
 *TODO implement realloc function for perfection :)  
 * <------------------------------------->  
 * Author: @MolRob15 ==> Robert Mattias Molin
 * 2016.11.02
 * Version 1.2
 */


//--- Functions sort and freeMemory
void sort(char**, int);
void freeMemory(char**, int);
//---

//Main function
int main(int argc, char **argv) {
    
	// Allocates space for strings with max 100 characters.
    char buf[100];

    // Allocates space for list with a max 3500 strings/ lines
    char** nameArray = (char **)malloc(sizeof(char *)*3500);
    
   /* Takes file name as an argument from command line ex: 
	*	./ main(executable file) + (*filename) = for example (someNames.txt)	 
	*/ 
	char *filename = argv[1];
    int size;
	
    FILE *fp = fopen(filename, "r");
    
	if(fp == NULL) {
        printf("***File could not be opened.\n");
        exit(1);
    }
    else {
        printf("\t\nList before sorting\n\n"); 
        
		// Reads strings into list one by one 
        while(fgets(buf, sizeof(buf), fp) ) {
               nameArray[size] = malloc(sizeof(buf)); 
               strncpy(nameArray[size], buf, 100); 
               printf("%s ", nameArray[size]); 
               size++;  
        }
    }

    
    // Calls function sort() and prints the list after its sorted
    printf("\t\nSorted list:\n\n"); 
    sort(nameArray, size);
    for(int i=0; i<size-1; i++) {
        printf("%s", nameArray[i]); 
    }
    
	 

	// Calls function freeMemory and deallocates (free) used memory	
	freeMemory(nameArray, size);
	

	// close file	
	 fclose(fp);    

    return 0;

}
	
	/*
     * Functions Sort 
	 * And freeMemory
	 */


	// Function for bubble sort of array
	void sort(char **list, int size) {
    			
		int i,j; 
    	for(j = 0; j <size-1; j++) {
        	for (i = 0; i < size -1; i++) {
      			if (0 < strncmp(list[i], list[i +1], 100) ) {
        		char* temp = list[i];
        		list[i] = list[i +1];
        		list[i +1] = temp;
		
      		}
    	}
  	}
	


	
   /* Function for deallocation of memory
	*
    * for loop deallocates all the memory locations inside array  
	* free(array) deallocates the array itself	 
    */
	void freeMemory(char **array, int size) {
					int a;	
					for(a = 0; a < size; a++) {     						
						free(array[a]);							
					}
					free(array);
				}
					




    

