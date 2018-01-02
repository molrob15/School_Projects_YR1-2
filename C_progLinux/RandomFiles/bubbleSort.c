#include <stdio.h>
#include <stdbool.h>

void sort(int * values, int n,int (*compare)(int ,int ) ){
	int finished=false;	
	while(!finished){
		finished=true;
		for(int i=0;i<n-1;i++){
			if(compare(values[i],values[i+1])){
				int tmp=values[i];
				values[i]=values[i+1];
				values[i+1]=tmp;
				finished=false;
			}
		}
	}
}
int less(int x,int y){
	return x<y;
}
int greater(int x,int y){
	return x>y;
}

int main(void){
	int values[12]={12,43,12,32,222,33,100,1,73,55,64,87};
	sort(values,12,&greater);
	for(int i=0;i<12;i++){
		printf("%d\n",values[i]);	
	}
}
