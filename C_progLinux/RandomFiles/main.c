#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "employee.h"

int main(void){
	employee kjetil;
	kjetil.name=malloc(100);
	strncpy(kjetil.name,"Kjetil Raaen",100);
	kjetil.salary=100000;
	printEmployee(&kjetil);
 	addSalary(&kjetil,100000000);
	printEmployee(&kjetil);

	free(kjetil.name);
	kjetil.name=NULL;
 	
	return 0;

}


