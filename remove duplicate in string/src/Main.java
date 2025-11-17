import java.util.*;
public class Main
{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int i;
        String s=sc.nextLine(),c="";
        for(i=0;i<s.length();i++)
        {
            if(!(c).contains(String.valueOf(s.charAt(i)))) //s.charAt(i)+""
                c+=s.charAt(i);
        }
        System.out.print(c);
        
    }
}