import java.util.*;
import java.io.*;
import java.lang.*;
class codegen {
    static int i,j,k,rn=0,n,l;
    static String inp,res,s;
    static char tv='a';
    static HashMap<String,String> addr = new HashMap<String,String>();
    static HashMap<String,Integer> reg = new HashMap();
    static ArrayList<String> out = new ArrayList();
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Code Generation");
        System.out.print("Input string: ");
        s=sc.next();
        genThreeCode(s);
        System.out.println("\nThree address codes:");
        for(String s1:addr.keySet()) 
            System.out.println(s1+"="+addr.get(s1));
        System.out.println();
        genMc();
	}

    public static void genMc() {
        int f=0;
        for(String s1:addr.keySet()) {
            String t1=addr.get(s1);
            String t2="",t3="";
            t2+=t1.charAt(0);
            t3+=t1.charAt(2);
            String tt="";
            tt+=t1.charAt(1);
            if(t1.contains("=")) {
                String res="MOV "+"R"+reg.get(t3)+","+t2;
                out.add(res);
                f=1;
                continue;
            }
            genReg(t2);
            genReg(t3);
            String op="";
            if(t1.contains("+")) 
                op="ADD";
            else if(t1.contains("-"))
                op="SUB";
            else if(t1.contains("*"))
                op="MUL";
            else if(t1.contains("/"))
                op="DIV";

            op+=" R"+reg.get(t2)+",R"+reg.get(t3);
            int r=reg.get(t2);
            reg.remove(t2);
            reg.put(s1,r);
            out.add(op);
        }

        for(String s2:out) {
            System.out.println(s2);
            if(!s2.contains("MOV"))
                System.out.println();
        }
    }

    public static void genThreeCode(String s) {
        String res="";
        if(s.contains("=") && s.length()==3) {
            addr.put((""+tv),s);
            return;
        }

        if(s.equals(""))
            return;
        if(s.contains("(")) {
            int si=s.indexOf("(");
            int ei=s.indexOf(")");
            String t1="";
            String eq=s.substring(si+1,ei);
            String pres=hasVal(eq);
            if(pres.equals("")) {
                t1+=tv+"="+eq;
                res=s.substring(0,si)+tv+s.substring(ei+1);
                addr.put((""+tv),eq);
                tv++;
            }
            else 
                res=s.substring(0,si)+pres+s.substring(ei+1);
        }
        else if(s.contains("/")) {
            int si=s.indexOf("/");
            String op="/";
            res=change(op,si,s);
        }
        else if(s.contains("*")) {
            int si=s.indexOf("*");
            String op="*";
            res=change(op,si,s);
        }
        else if(s.contains("+")) {
            int si=s.indexOf("+");
            String op="+";
            res=change(op,si,s);
        }
        else if(s.contains("-")) {
            int si=s.indexOf("-");
            String op="-";
            res=change(op,si,s);
        }
        //System.out.println(res);
        genThreeCode(res);
    }

    public static String change(String op,int si,String s) {
        String t1="";
        String eq=s.substring(si-1,si)+op+s.substring(si+1,si+2);
        String res=s.substring(0,si-1)+tv+s.substring(si+2);
        t1+=tv+"="+eq;
        addr.put((""+tv),eq);
        tv++;
        return res;
    }

    public static void genReg(String st) {
        if(!reg.containsKey(st)) {
            reg.put(st,rn);
            String t1="MOV "+st+",R"+rn;
            out.add(t1);
            rn++;
        }
        else {
            String t1="MOV "+st+",R"+reg.get(st);
            out.add(t1);
        }
    }

    public static String hasVal(String se) {
        String res="";
        for(String s1:addr.keySet()) {
            String t1=addr.get(s1);
            if(t1.equals(se)) 
                return s1;

        }
        return res;
    }
}

