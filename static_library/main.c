#include<stdio.h>
#include "lib_mylib.h"

int main() {
    int i,n,j,k,m;
    printf("Enter n: ");
    scanf("%d",&n);
    int a[n];
    printf("Enter array elements: ");
    for(i=0;i<n;i++)
        scanf("%d",&a[i]);
    revarr(a,a+n);
    printf("Reverse of the array: ");
    for(i=0;i<n;i++)
        printf("%d ",a[i]);
    printf("\n\nEnter the range you want to extract [l,r): ");
    int l,r;
    scanf("%d %d",&l,&r);
    m=r-l;
    int b[r-l];
    slicearr(a+l,a+r,b);
    printf("The extracted range is: ");
    for(i=0;i<m;i++)
        printf("%d ",b[i]);
    printf("\n\nEnter the range you want to replace [l,r): ");
    scanf("%d %d",&l,&r);
    printf("Enter the substitute: ");
    int x;
    scanf("%d",&x);
    replacearr(a+l,a+r,x);
    printf("The new array is: ");
    for(i=0;i<n;i++)
        printf("%d ",a[i]);

    printf("\n\nEnter the range [l,r) for sum: ");
    scanf("%d %d",&l,&r);
    printf("The sum of the elements is: %d\n",sumrange(a+l,a+r));

    printf("\nThe sorted array is: ");
    sort(a,a+n);
    for(i=0;i<n;i++)
        printf("%d ",a[i]);

    printf("\n\nEnter the element to search: ");
    scanf("%d",&x);
    printf("The index of %d is: %d\n",x,binarysearch(a,a+n,x));

    printf("\nEnter 2 numbers: ");
    scanf("%d %d",&l,&r);
    printf("The GCD of %d and %d is: %d\n",l,r,gcd(l,r));

    return 0;
}
