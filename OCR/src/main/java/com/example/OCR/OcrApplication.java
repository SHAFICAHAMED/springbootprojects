package com.example.OCR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrApplication.class, args);
	}

}
/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
class TestClass {
	static boolean reverse(long n){
		long s=0,t=n;
		while(n>0){
			s=n%10+s*10;
			n/=10;
		}
		return s==t;
	}

	static boolean isPrime(long n){
		long i;
		if(n==0||n==1) return false;
		for(i=2;i<=Math.sqrt(n);i++){
			if(n%i==0) return false;
		}
		return true;
	}
	public static void main(String args[] ) throws Exception {
		Scanner sc=new Scanner(System.in);
		long i=1;
		long n=sc.nextLong(),u=1000000;
		if(n<0) n*=-1;

		while(u>0){
			if(isPrime(n-i)&&reverse(n-i))
			{
				System.out.println(n-i);
				// return;
				break;
			}
			else if(isPrime(n+i)&&reverse(n+i)){
				System.out.println(n+i);
				// return;
				break;
			}
			i++;
			u--;
		}
		// System.out.println(i);
	}
}

*/
