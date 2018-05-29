import java.lang.*;
import java.io.*;
import java.util.*;

class recdecentparser {
    static String inp="";
    static int n;
    public static void main(String args[]) {
        System.out.println("** Recursive Decent Parser **\n"); 
        System.out.print("Enter a string: ");
        Scanner sc = new Scanner(System.in);
        inp=sc.next();
        n=inp.length();
        if(S(0))
            System.out.println(inp+" is a valid string.");
        else 
            System.out.println(inp+" is not a valid string.");
    }

    public static boolean S(int in) {
        //System.out.println("S "+in);
        if(n==1 && inp.charAt(0)=='b')
            return true;
        if(in<n) 
            return A(in);
        return false;
    }


    public static boolean A(int in) {
        //System.out.println("A "+in);
        if(inp.charAt(in)=='b' && inp.charAt(in+1)=='d' && (in+2)<n) {
            in+=2;
            return B(in);
        }
        else if(in<n)
            return B(in);
        return false;
    }
 
    public static boolean B(int in) {
        //System.out.println("B "+in+" "+inp.charAt(in));
        if((in+1)==n && inp.charAt(in)=='a')
            return true;
        else if(inp.charAt(in)=='c') {
            if((in+1)==n)
                return true;
            else {
                in+=1;
                return B(in);
            }
        }
        else if(inp.charAt(in)=='a' && inp.charAt(in+1)=='d') {
            if((in+2)<n) {
                in+=2;
                return B(in);
            }
            else
                return true;
        }
        return false;
    }
}
