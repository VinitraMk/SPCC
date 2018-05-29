#include<stdio.h>
void add(int a,int b) {
    int c=a+b;
    printf("The addition of %d and %d is: %d\n",a,b,c);
}

void revarr(int *l,int *r) {
    int i=0,j=r-l-1;
    while(i<=j) {
        int t=*(l+i);
        *(l+i)=*(l+j);
        *(l+j)=t;
        i++;
        j--;
    }
}

void slicearr(int *l,int *r,int b[]) {
    int i=0;
    while((l+i)<r) {
        b[i]=*(l+i);
        i++;
    }
}

void replacearr(int *l,int *r,int x) {
    int i=0;
    while((l+i)<r) {
        *(l+i)=x;
        i++;
    }
}

int sumrange(int *l,int *r) {
    int i=0,c=0;
    while((l+i)<r) {
        c+=*(l+i);
        i++;
    }
    return c;
}

void merge(int *l,int *m,int *r) {
    int i,j,n1,n2;
    n1=m-l+1;
    n2=r-m;
    int L[n1],R[n2];
    for(i=0;i<n1;i++) 
        L[i]=*(l+i);
    for(i=0;i<n2;i++)
        R[i]=*(m+i+1);

    i=0,j=0;
    while(i<n1 && j<n2) {
        if(L[i]<=R[j]) {
            *l=L[i];
            i++;
        }
        else {
            *l=R[j];
            j++;
        }
        l++;
    }

    while(i<n1) {
        *l=L[i];
        i++;
        l++;
    }

    while(j<n2) {
        *l=R[j];
        j++;
        l++;
    }
}


void sortutil(int *l,int *r) {
    int *m;
    m=l+((r-l)/2);
    if(l<r) {
        sortutil(l,m);
        sortutil(m+1,r);
        merge(l,m,r);
    }
}

void sort(int *l,int *r) {
    r--;
    sortutil(l,r);
}

int * bin_search(int *l,int *r,int x) {
    int *m;
    while(l<=r) {
        m=l+((r-l)/2);
        if(*m==x) 
            return m;
        if(*m<x)
            l=m+1;
        else 
            r=m-1;
    }
    return NULL;
}
int binarysearch(int *l,int *r,int x) {
    r--;
    int *m=bin_search(l,r,x);
    if(m==NULL)
        return -1;
    else
        return (m-l);
}


int gcd(int a,int b) {
    if(a==0)
        return b;
    return gcd(b%a,a);
}
