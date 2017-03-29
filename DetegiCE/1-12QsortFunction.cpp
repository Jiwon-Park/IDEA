#include<stdio.h>
#include<stdlib.h>

int N;
int data[8];

void input()
{
    int i;
    scanf("%d",&N);
    for(i=0 ; i<N ; i++)
        scanf("%d",&data[i]);
}

int comp(const void *a, const void *b)
{
    return (*(int *)a - *(int *)b);
}

void qsort_function()
{
    qsort(data,N,sizeof(data[0]),comp);
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
    qsort_function();
    output();
}
