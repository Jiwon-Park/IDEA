#include<stdio.h>

int N;
int val;
int data[8];

void input()
{
    int i;
    scanf("%d %d",&N,&val);
    for(i=0 ; i<N ; i++) scanf("%d",&data[i]);
}

void linear_search()
{
    int i;
    for(i=0 ; i<N ; i++){
        if(data[i]==val){
            printf("%d",i+1);
            return;
        }
    }
    if(i==N) printf("-1");
}

int main()
{
    input();
    linear_search();
}
