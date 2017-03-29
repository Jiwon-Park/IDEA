#include<stdio.h>
 
int main()
{
    int n,i,j,t;
    int score[1003][3];
    scanf("%d",&n);
    for(i=1 ; i<=n ; i++){
        score[i][0]=i;
        scanf("%d %d",&score[i][1],&score[i][2]);
    }
    for(i=1 ; i<=n ; i++){
        for(j=i ; j<=n ; j++){
            if(score[i][1]<=score[j][1]){
                t=score[i][0];
                score[i][0]=score[j][0];
                score[j][0]=t;
                t=score[i][1];
                score[i][1]=score[j][1];
                score[j][1]=t;
                t=score[i][2];
                score[i][2]=score[j][2];
                score[j][2]=t;
            }
        }
    }
    for(i=1 ; i<=n ; i++){
        for(j=i ; j<=n ; j++){
            if(score[i][1]==score[j][1] && score[i][2]<=score[j][2]){
                t=score[i][0];
                score[i][0]=score[j][0];
                score[j][0]=t;
                t=score[i][1];
                score[i][1]=score[j][1];
                score[j][1]=t;
                t=score[i][2];
                score[i][2]=score[j][2];
                score[j][2]=t;
            }
        }
    }
    for(i=1 ; i<=n ; i++){
        printf("%d %d %d\n",score[i][0],score[i][1],score[i][2]);
    }
}