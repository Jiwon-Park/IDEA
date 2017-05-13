#include<stdio.h>

#define MAX 100

struct Stack{
    int data[MAX];
    int top;
    int overflow;
    int underflow;
};

void reset(struct Stack *a)
{
    a->top=-1;
    a->overflow=0;
    a->underflow=0;
}

void push(struct Stack *a,int x)
{
    if(a->top >= MAX-1){
        a->overflow = 1;
    }
    else{
        a->top++;
        a->data[a->top] = x;
    }
}

void pop(struct Stack *a)
{
    if(a->top < 0){
        a->underflow = 1;
    }
    else{
        a->top--;
    }
}

int main()
{
    struct Stack stack;
    reset(&stack);
    push(&stack,100);
    pop(&stack);
}
