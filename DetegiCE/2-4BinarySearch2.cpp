#include<stdio.h>
#include<algorithm>

int N;
int val;
int data[8];

void input()
{
    int i;
    scanf("%d %d",&N,&val);
    for(i=0 ; i<N ; i++) scanf("%d",&data[i]);
}

void binarySearch(int low,int high)
{
    int mid=(low+high)/2;
    if(low>high){
        printf("-1");
        return;
    }
    if(data[mid]==val){
        printf("%d",mid+1);
        return;
    }
    else if(data[mid]<val){
        binarySearch(mid+1,high);
    }
    else binarySearch(low,mid-1);
}

int main()
{
    input();
    binarySearch(0,N-1);
}
