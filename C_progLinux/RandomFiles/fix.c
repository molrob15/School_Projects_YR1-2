#include<stdio.h>
#include<string.h>
#include<stdlib.h>
int main(void){
	FILE* f=fopen("part_0_0_mickey.txt","r");
	//char img[30][31];
	char ** img=malloc(30*sizeof(char*));
	for(int i=0;i<30;i++){
		img[i]=calloc(31,sizeof(char));
		fread(img[i], 1, 30, f);

	}
	fclose(f);
	for(int i=0;i<30;i++){
		printf("%s\n",img[i]);
	}

}
