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

class opr {
    static String s,inp,res,main,out;
    static int i,j,k,l,n,m;
    static ArrayList<String> list=new ArrayList();
    static ArrayList<String> input = new ArrayList();
    static ArrayList<String> output=new ArrayList();
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        s=sc.next();
        n=s.length();
        main=s+"$";
        m=n+1;
        for(i=0;i<m;i++)
            input.add((""+main.charAt(i)));
        System.out.println("Stack Input\tPrec\tAction");
        System.out.println("-\t"+main+"\t-\tShift");
        list.add("$");
        i=0;
        boolean done=false;
        boolean wrong=false;
        while(i<m && done==false) {
            String curr="";
            curr+=main.charAt(i);
            Top top = getTop();
            String topo=top.top;
            if(top.isTop==true && (topo.equals(curr) || (topo.equals("*") && curr.equals("+")) || (topo.equals("+") && curr.equals("*")))) {
                printStack();
                System.out.println("Error\tEnd");
                break;
            }
            if(chkprec(top.top,curr)) {
                printStack();
                System.out.println(top.top+"<"+curr+"\tShift");
                list.add(curr);
                input.remove(0);
                i++;
            }
            else {
                printStack();
                System.out.println(top.top+">"+curr+"\tPop,Reduce");
                for(j=list.size()-1;j>=0;j--) {
                    String t1=list.get(j);
                    if(t1.equals("a")) {
                        list.set(j,"E");
                        break;
                    }
                }
                if(!list.contains("a")) {
                    String rep=getString();
                    Top top1=getTop();
                    if(top1.top.equals("*") && !curr.equals("$")) {
                        printStack();
                        System.out.println("*>"+curr+"\tPop,Reduce");
                        if(top1.top.equals("*")) {
                            k=list.size()-1;
                            while(!list.get(k).equals("*")) {
                                list.remove(k);
                                k--;
                            }
                            list.remove(k);
                        }
                    }
                    if(top1.top.equals("+") && !curr.equals("$")) {
                        printStack();
                        System.out.println("+>"+curr+"\tPop,Reduce");
                        if(top1.top.equals("+")) {
                            k=list.size()-1;
                            while(!list.get(k).equals("+")) {
                                list.remove(k);
                                k--;
                            }
                            list.remove(k);
                        }
                    }

                    while(!top1.top.equals("$") && curr.equals("$")) {
                        printStack();
                        System.out.println(top1.top+">"+curr+"\tPop,Reduce");
                        if(top1.top.equals("*")) {
                            k=list.size()-1;
                            boolean del=false;
                            while(!list.get(k).equals("*")) {
                                list.remove(k);
                                k--;
                                del=true;
                            }
                            if(!del)
                                wrong=true;
                            else
                                list.remove(k);
                        }
                        else if(top1.top.equals("+")) {
                            k=list.size()-1;
                            boolean del=false;
                            while(!list.get(k).equals("+")) {
                                list.remove(k);
                                k--;
                                del=true;
                            }
                            if(!del) 
                                wrong=true;
                            else
                                list.remove(k);
                        }
                        if(wrong)
                            break;
                        top1=getTop();
                    }
                    //printStack();
                    //System.out.println("$accept$\tEnd");
                    if(wrong) {
                        printStack();
                        System.out.println("Error\tEnd");
                        break;
                    }
                    top1=getTop();
                    if(top1.top.equals("$") && curr.equals("$")) {
                        done=true;
                        printStack();
                        System.out.println("$accept$\tEnd");
                    }
                }
            }
        }
        if(done) 
            System.out.println("\nGiven string belongs to grammar");
        else if(done || wrong)
            System.out.println("\nGiven string does not belong to grammar");
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

