import java.io.*;
public class Driver { 
public static void main(String [] args) { 
  Polynomial p = new Polynomial();  
  double [] c1 = {6,0,0,5}; 
  Polynomial p1 = new Polynomial(c1);
  System.out.println(p1.evaluate(3)); 
  double [] c2 = {0,-2,0,0,-9}; 
  Polynomial p2 = new Polynomial(c2); 
  File f = new File("input.txt");
  Polynomial p3 = new Polynomial(f);
  System.out.println(p3.evaluate(1)); 
  Polynomial s = p1.multiply(p2); 
  p3.saveToFile("a.txt");
  System.out.println("p(2) = " + p3.evaluate(2)); 
  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s");

 } 
} 