#include<stdio.h>

int N;
int data[8];

void input()
{
    int i;
    scanf("%d",&N);
    for(i=0 ; i<N ; i++)
        scanf("%d",&data[i]);
}

void selection_sort()
{
    int i,j,min,t,temp;
    for(i=0 ; i<N-1 ; i++){
        min=data[i];
        for(j=i ; j<N ; j++){
            if(data[j]<min){
                min=data[j];
                t=j;
            }
        }
        temp=data[t];
        data[t]=data[i];
        data[i]=temp;
    }
}

void output()
{
    int i;
    for(i=0 ; i<N ; i++)
        printf("%d ",data[i]);
}

int main()
{
    input();
    selection_sort();
    output();
}
