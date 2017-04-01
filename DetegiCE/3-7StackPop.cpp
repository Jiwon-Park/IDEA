#include<stdio.h>

int M, N;
int data[102];
int stack[102];
int top=-1;
int underflow=0;

void input()
{
    int i;
    scanf("%d %d",&M,&N);
    for(i=0 ; i<N ; i++)
        scanf("%d",&data[i]);
}

void push(int x)
{
    top++;
    stack[top]=x;
}

void pop()
{
    if(top<0){
        printf("stack underflow");
        underflow++;
    }
    else{
        top--;
    }
}

void working()
{
    int i;
    for(i=0 ; i<N ; i++){
        if(data[i]==-1){
            pop();
        }
        else push(data[i]);
        if(underflow) break;

    }
    if(i==N){
        for(i=0 ; i<=top ; i++){
            printf("%d ",stack[i]);
        }
    }
}

int main()
{
    input();
    working();
}
