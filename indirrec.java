import java.util.*;
import java.io.*;
import java.lang.*;
class indirrec{
	static int i,j,k,n,l;
    static String lf,rt,prod,main,sal,sbeta;
    static ArrayList<String> der = new ArrayList();
    static HashMap<String,List<String>> hp = new HashMap();
    static HashMap<String,List<String>> orig = new HashMap();
    static ArrayList<String> alpha = new ArrayList();
    static ArrayList<String> beta = new ArrayList();
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Removal of direct recursion");
        System.out.println("\n@:Ebsolon\n");
        System.out.print("Enter no of productions: ");
        n=sc.nextInt();
        List<String> temp = new ArrayList();
        for(i=0;i<n;i++) {
            lf=sc.next();
            rt=sc.next();
            if(orig.containsKey(lf)) {
                temp=orig.get(lf);
                temp.add(rt);
            }
            else 
                temp.add(rt);
            orig.put(lf,new ArrayList(temp));
            temp.clear();
        }
        System.out.println("Productions: ");
        for(String s1:orig.keySet()) {
            System.out.print(s1+" -> ");
            temp=orig.get(s1);
            for(String s2:temp) 
                System.out.print(s2+" | ");
            System.out.println();
        }
        hp.putAll(orig);
        modify();
        removal();
	}

    public static void modify() {
        List<String> temp = new ArrayList();
        Stack<String> stack = new Stack<String>();
        HashMap<String,Integer> vis = new HashMap();
        String prev="";

        for(String s1:orig.keySet()) {
            temp=orig.get(s1);
            String tts=temp.get(0);
            String fs="";
            fs+=tts.charAt(0);
            stack.push(fs);
            int fo=0;
            String subst="";
            while(!stack.empty()) {
                String ss=(String)stack.peek();
                List<String> temp1 = new ArrayList();
                temp1=orig.get(ss);
                String ts=temp1.get(0);
                char ch=ts.charAt(0);
                String cs="";
                cs+=ts.charAt(0);
                stack.pop();
                if(cs.equals(s1)) {
                    fo=1;
                    subst=cs;
                    break;
                }
                if(Character.isUpperCase(ch) && orig.containsKey(cs) && !vis.containsKey(cs)) {
                    vis.put(cs,1);
                    stack.push(cs);
                }
            }
            if(fo==1) {
                String news=tts.replaceAll(fs,subst);
                List<String> temp1= new ArrayList();
                temp1=hp.get(s1);
                //main=s1;
                temp1.remove(tts);
                temp1.add(news);
                hp.put(s1,new ArrayList(temp1));
                break;
            }
        }
    }

    public static void removal() {
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

