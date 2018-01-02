#include "employee.h"
#include <stdio.h>
#include <math.h>

void addSalary(employee* thisE,int x){
	thisE->salary+=x;
	thisE->salary=sqrt(thisE->salary);
}
void printEmployee(employee *thisE){
	printf("Employee.\nName: %s \nSalary: %d\n",thisE->name,(*thisE).salary);
}
