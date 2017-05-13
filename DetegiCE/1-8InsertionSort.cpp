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

void insertion_sort()
{
    int i,j,temp;
    for(i=1 ; i<N ; i++){
        for(j=i ; j>0 ; j--){
            if(data[j-1]>data[j]){
                temp=data[j-1];
                data[j-1]=data[j];
                data[j]=temp;
            }
            else break;
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
    insertion_sort();
    output();
}
