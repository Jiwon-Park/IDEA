#include<stdio.h>

int M, N;
int data[102];
int stack[102];
int top=-1;
int overflow=0;

void input()
{
    int i;
    scanf("%d %d",&M,&N);
    for(i=0 ; i<N ; i++)
        scanf("%d",&data[i]);
}

void push(int x)
{
    if(top>=M-1){
        printf("stack overflow");
        overflow++;
    }
    else{
        top++;
        stack[top]=x;
    }
}

void pushing()
{
    int i;
    for(i=0 ; i<N ; i++){
        push(data[i]);
        if(overflow) break;
    }
    if(i==N) printf("%d",stack[top]);
}

int main()
{
    input();
    pushing();
}
