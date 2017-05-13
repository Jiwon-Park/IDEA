#include<iostream>
#include<algorithm>

#define MAX 1000

int N;
struct score{
    int num;
    int math;
    int info;
};

struct score data[MAX+1];

void input()
{
    int i;
    std::cin >> N;
    for(i=0 ; i<N ; i++){
        std::cin >> data[i].math >> data[i].info;
        data[i].num = i+1;
    }
}

bool compare(score a,score b){
    if(a.math==b.math){
        if(a.info==b.info) return a.num<b.num;
        else return a.info>b.info;
    }
    else return a.math>b.math;
}

void output()
{
    int i;
    for(i=0 ; i<N ; i++)
        std::cout << data[i].num << " " << data[i].math << " " << data[i].info << std::endl;
}

int main()
{
    input();
    std::sort(data,data+N,compare);
    output();
}
