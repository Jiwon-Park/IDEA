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

void bubble_sort()
{
    int i,j,temp;
    for(i=0 ; i<N ; i++){
        for(j=0 ; j<i ; j++){
            if(data[i]<data[j]){
                temp=data[i];
                data[i]=data[j];
                data[j]=temp;
            }
        }
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
    bubble_sort();
    output();
}
