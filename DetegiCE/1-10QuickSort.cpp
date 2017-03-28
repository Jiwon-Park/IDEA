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

void quick_sort(int left, int right)
{
    int i,j,temp;
    int pivot=data[left];
    if(left<right){
        i=left;
        j=right+1;
        while(i<=j){
            do{
                i++;
            }while(data[i]<pivot);
            do{
                j--;
            }while(data[j]>pivot);
            if(i<j){
                temp=data[i];
                data[i]=data[j];
                data[j]=temp;
            }
            else break;
        }
        temp=data[j];
        data[j]=data[left];
        data[left]=temp;

        quick_sort(left,j-1);
        quick_sort(j+1,right);
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
    quick_sort(0,N-1);
    output();
}
