#include<stdio.h>
#include<stdlib.h>

int main()
{
    int arr1[10];
    int *arr2;
    int i;

    for(i=0 ; i<10 ; i++)
        arr1[i]=i+1;

    arr2=(int*) malloc(sizeof(int)*5);

    for(i=0 ; i<5 ; i++){
        arr2[i] = arr1[i];
        printf("%d ",arr2[i]);
    }

    printf("\n");

    realloc(arr2, sizeof(int)*10);

    for(i=0 ; i<10 ; i++){
        arr2[i]=arr1[i];
        printf("%d ",arr2[i]);
    }

    free(arr2);

    return 0;
}
