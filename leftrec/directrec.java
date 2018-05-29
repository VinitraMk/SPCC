import java.util.*;
import java.io.*;
import java.lang.*;

class directrec{
    static int i,j,k,n,l;
    static String lf,rt,prod,main,sal,sbeta;
    static ArrayList<String> der = new ArrayList();
    static HashMap<String,List<String>> hp = new HashMap();
    static ArrayList<String> alpha = new ArrayList();
    static ArrayList<String> beta = new ArrayList();
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Removal of direct recursion");
        System.out.println("\n@:Ebsolon\n");
        System.out.println("Enter no of productions: ");
        n=sc.nextInt();
        for(i=0;i<n;i++) {
            main=lf;
            lf=sc.next();
            rt=sc.next();
            der.add(rt);
        }
        System.out.println();
        for(String s1:der) {
            if(s1.charAt(0)==main.charAt(0)) {
                System.out.println(main+"->"+s1);
                System.out.println("The above grammar is directly left recursive");
                sal=s1.substring(1);
                alpha.add(sal);
            }
            else 
                beta.add(s1);
        }
        System.out.println();

        String tmain=main+"'";
        List<String> temp= new ArrayList();
        for(String s3:beta) {
            String t1=s3;
            t1+=tmain;
            //Main production modified
            temp.add(t1);
        }
        hp.put(main,new ArrayList(temp));
        temp.clear();

        for(String s2:alpha) {
            //Main' productions
            String t1=s2;
            t1+=tmain;
            temp.add(t1);
        }
        temp.add("@");
        hp.put(tmain,new ArrayList(temp));
        temp.clear();
        for(Map.Entry<String,List<String>> entry:hp.entrySet()) {
            String t1=entry.getKey();
            System.out.print(t1+" -> ");
            String t2="";
            temp=entry.getValue();
            for(String s5:temp) 
                t2+=s5+" | ";
            //t2=t2.substring(0,t2.length()-3);
            System.out.println(t2);
        }
	}
}

