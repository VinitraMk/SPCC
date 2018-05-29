import java.util.*;
import java.io.*;
import java.lang.*;

class lexana {
    static ArrayList<String> bfunc = new ArrayList();
    static ArrayList<String> keywords = new ArrayList();
    static ArrayList<String> operators = new ArrayList();
    static ArrayList<String> spsymb = new ArrayList();
    static ArrayList<String> symb = new ArrayList();
    static ArrayList<String> constants = new ArrayList();
    public static void main(String args[]) {
        File file = new File("add.c");
        library();
        try {
            FileInputStream fis = new FileInputStream(file);
            char curr,prev='\0';
            String s="";
            System.out.println("Extracted token\tType\t\tType Number");
            int f=0;
            String pe = "";
            while(fis.available()>0) {
                curr = (char)fis.read();
                String sc = ""+curr;
                if(curr=='\n') {
                    s="";
                    prev=curr;
                    continue;
                }

                if(pe.equals("builtfunc") && curr!=')')
                    continue;
                else if(pe.equals("builtfunc") && curr==')')
                    pe="";

                if(spsymb.contains(sc)) {
                    System.out.println(curr+"\t\tSpecial Symbol\t\t"+spsymb.indexOf(sc));
                    if(!s.equals(""))
                        s=s.trim();
                    if(!isInteger(s)) {
                        if(!symb.contains(s) && !s.equals(""))
                            symb.add(s);
                        if(symb.contains(s))
                            System.out.println(s+"\t\tSymbol\t\t\t"+symb.indexOf(s));
                        s="";
                    }
                    else {
                        if(!constants.contains(s) && !s.equals(""))
                            constants.add(s);
                        System.out.println(s+"\t\tConstant\t\t"+constants.indexOf(s));
                        s="";
                    }
                }
                if(operators.contains(sc)) {
                    if(curr!=')' && curr!='(')
                        System.out.println(curr+"\t\tOperator\t\t"+operators.indexOf(sc));
                    if(!s.equals(""))
                        s=s.trim();
                    if(curr=='(' && !symb.contains(s) && !s.equals("")) {
                        if(!bfunc.contains(s))
                            bfunc.add(s);
                        System.out.println(s+"\t\tBuilt-in Function\t"+bfunc.indexOf(s));
                        pe="builtfunc";
                    }
                    if(symb.contains(s)) {
                        System.out.println(s+"\t\tSymbol\t\t\t"+symb.indexOf(s)); 
                        s="";
                    }
                    if(curr!=')')
                        s="";
                }


                if(curr==' ') {
                    if(keywords.contains(s) && !s.equals("")) 
                        System.out.println(s+"\t\tKeyword\t\t\t"+keywords.indexOf(s));
                    s="";
                }
                if(!spsymb.contains(sc) && !operators.contains(sc) && !constants.contains(sc) && !(sc.equals(""))) 
                    s+=curr;
                prev=curr;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        System.out.print("\nSymbols: ");
        for(String s:symb)
            System.out.print(s+", ");
        System.out.print("\nConstants: ");
        for(String s:constants)
            System.out.print(s+", ");
        System.out.println();
    }
    
    public static void library() {
        spsymb.add(",");
        spsymb.add(";");
        
        operators.add("{");
        operators.add("}");
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("(");
        operators.add(")");
        operators.add("=");
        
        keywords.add("int");
        keywords.add("float");
        keywords.add("double");
        keywords.add("long");
    }

	public static boolean isInteger(String s) {
		return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
		if(s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++) {
		    if(i == 0 && s.charAt(i) == '-') {
		        if(s.length() == 1) return false;
		        else continue;
		    }
		    if(Character.digit(s.charAt(i),radix) < 0) return false;
		}
		return true;
	}
}
