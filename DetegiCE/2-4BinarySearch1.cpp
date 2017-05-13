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

void binarySearch()
{
    int low=0, mid, high=N-1;
    while(low<=high){
        mid=(low+high)/2;
        if(data[mid]==val){
            printf("%d",mid+1);
            return;
        }
        else if(data[mid]<val) low=mid+1;
        else high=mid-1;
    }
    printf("-1");
}

int main()
{
    input();
    binarySearch();
}
