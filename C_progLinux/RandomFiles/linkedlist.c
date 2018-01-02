#include <stdio.h>
#include <stdlib.h>
typedef int ElemeValue;
typedef struct Element{
	ElemeValue value;
	struct Element* next;
} Element;

typedef struct LinkedList{
	Element * first;
} LinkedList;
void initLL(LinkedList* this){
	this->first=NULL;
}
void addLL(LinkedList* this,int value){
	Element * newElement=malloc(sizeof(Element));
	newElement->value=value;
	newElement->next=NULL;
	if(this->first==NULL)
		this->first=newElement;
	else{
		Element* lastElement=this->first;
		while(lastElement->next!=NULL)
			lastElement=lastElement->next;
		lastElement->next=newElement;	
	}

}

int main(void){
	LinkedList primes;
	initLL(&primes);
	addLL(&primes,2);
	addLL(&primes,5);
	addLL(&primes,7);
	addLL(&primes,11);
	Element* lastElement=primes.first;
	while(lastElement!=NULL){
		printf("Element value=%d\n",lastElement->value);
		lastElement=lastElement->next;
	}
}
