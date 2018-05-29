import java.util.*;
import java.io.*;
import java.lang.*;
class Top {
    String top;
    boolean isTop;
    public Top(String top,boolean isTop) {
        this.top=top;
        this.isTop=isTop;
    }
}

class oprpreparser {
    static String s,curr,out="";
    static Top top;
    static int i,j,k,n;
    static ArrayList<String> list = new ArrayList();
    static ArrayList<String> input = new ArrayList();
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string: ");
        s=sc.next();
		n=s.length();
        list.add("$");
        out+="$";
        int outi=1;
        int fl=0;
        int mi=-1;
        for(i=0;i<n;i++) {
            curr=""+s.charAt(i);
            top=getTop();
            if(top.isTop==true && top.top.equals(curr)) {
                fl=1;
                mi=outi;
            }
            if(chkprec(top.top,curr)) {
                list.add(curr);
                out+="<";
                out+=curr;
                outi+=2;
            }
            else {
                list.set(list.size()-1,"E");
                list.add(curr);
                out+=">";
                out+=curr;
                outi+=2;;
            }
        }


        out+=">$";
        //System.out.println();
        System.out.println("\nDefined precedence: "+out+" "+mi+"\n");
        list.clear();

        int m=out.length();
        for(i=0;i<n;i++)
            input.add(""+s.charAt(i));
        input.add("$");
        i=0;
        list.add("$");
        System.out.println("Stack\tInput\tPrecedence Operation");
        System.out.println("-\t$"+s+"$\t\t-  Shift");
        String prev="",next="";
        while(i<m) {
            if(out.charAt(i)=='<') {
                i++;
                next=""+out.charAt(i);
                printStack();
                list.add((""+out.charAt(i)));
                System.out.println(prev+" < "+next+"\t   Shift");
                input.remove(0);
            }
            else if(out.charAt(i)=='>'){
                printStack();
                if(mi==i) {
                    System.out.println("Error\t   End");
                    break;
                }
                i++;
                next=""+out.charAt(i);
                System.out.println(prev+" > "+next+"\t   Pop,Reduce");
                if(list.get(list.size()-1).equals("a"))
                    list.set(list.size()-1,"E");
                if(next.equals("$"))
                    break;
                printStack();
                System.out.println(out.charAt(i-4)+" "+out.charAt(i-3)+" "+out.charAt(i)+"\t   Shift");
                input.remove(0);
                list.add((""+out.charAt(i)));
                //printStack();
                //top=getTop();
            }
            else {
                prev=""+out.charAt(i);
                i++;
            }
        }
        //printStack();
        String main="";

        for(String str:list) {
            //System.out.print(str+" ");
            if(!str.equals("$") && !str.equals(" "))
                main+=str;
        }
        //System.out.println("\n"+main);
        m=main.length();
        String tmain="$";
        tmain+=main;
        //System.out.print(tmain+"\t$");
        i=tmain.length()-1;

        /*while(i>=0) {
            //System.out.print(tmain+"\t$");
            if(tmain.charAt(i)=='*') {
                System.out.print(tmain+"\t$");
                tmain=tmain.replaceAll("E\\*E","E");
                System.out.println("\t* > $ \t   Pop,Reduce");
                i=tmain.length()-1;
            }
            else if(tmain.charAt(i)=='+') {
                System.out.print(tmain+"\t$");
                tmain=tmain.replaceAll("E\\+E","E");
                System.out.println("\t+ > $ \t   Pop,Reduce");
                i=tmain.length()-1;
            }
            i--;
        }
        System.out.println(tmain+"\t$\t$accept$\tEnd");*/

        while(main.length()>1) {
            if(main.contains("E+E") || main.equals("E+E")) {
                main=main.replaceAll("E\\+E","E");
            }
            if(main.contains("E*E") || main.equals("E*E")) {
                main=main.replaceAll("E\\*E","E");
            }
            if(!main.contains("E+E") && !main.contains("E*E")) {
                break;
            }
        }
        if(main.equals("E")) {
            while(i>=0) {
                //System.out.print(tmain+"\t$");
                if(tmain.charAt(i)=='*') {
                    System.out.print(tmain+"\t$");
                    tmain=tmain.replaceAll("E\\*E","E");
                    System.out.println("\t* > $ \t   Pop,Reduce");
                    i=tmain.length()-1;
                }
                else if(tmain.charAt(i)=='+') {
                    System.out.print(tmain+"\t$");
                    tmain=tmain.replaceAll("E\\+E","E");
                    System.out.println("\t+ > $ \t   Pop,Reduce");
                    i=tmain.length()-1;
                }
                i--;
            }
            System.out.println(tmain+"\t$\t$accept$\tEnd");

            System.out.println("\nThe input string belongs to the given grammar");
        }
        else
            System.out.println("\nThe input string does not belong to the given grammar");
    }

    public static String getString() {
        String main="";
        for(int i=0;i<list.size();i++) 
            main+=list.get(i);
        return main;
    }

    public static Top getTop() {
        String temp="";
        boolean isTop=true;
        for(int i=list.size()-1;i>=0;i--) {
            String str = list.get(i);
            if(!str.equals("E")) {
                temp=str;
                break;
            }
            else
                isTop=false;
        }
        Top mTop = new Top(temp,isTop);
        return mTop;
    }

    public static void printStack() {
        for(int i=0;i<list.size();i++) 
            System.out.print(list.get(i));
        System.out.print("\t");
        for(int i=0;i<input.size();i++) 
            System.out.print(input.get(i));
        System.out.print("\t");
    }

    public static boolean chkprec(String s,String r) {
        if(s.equals("$"))
            return true;
        else if(s.equals(r) && !s.equals("$"))
            return false;
        else if (s.equals("a"))
            return false;
        else if (r.equals("a"))
            return true;
        else if (s.equals("*"))
            return false;
        else if (r.equals("*"))
            return true;
        return false;
    }
}
