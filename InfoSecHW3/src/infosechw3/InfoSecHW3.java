package infosechw3;
import java.util.Random;

public class InfoSecHW3 
{
    //this will ensure that keys are prime
    public static boolean primeNum(int n)
    {
       for(int i = 2; i < n; ++i)
       {
           if (n % i == 0)
               return false;          
       }
        return true; 
    }
    //generates a key within a range
    public static int KeyGenerator (int range)
    {
        Random myrand = new Random();
        int key  = myrand.nextInt(range) + 1;
        
        while(!primeNum(key) || key == 1)
        {
            key = (myrand.nextInt(range) + 1);
        }
        return key;
    }
    //calculates the greastest common factor
    public static int GCD(int x, int y)
    {
        if (y == 0)
            return x;
        
        return GCD(y, x % y);
    }
    
    //main program
    public static void main(String[] args)
    {
    //int range,p,q,n,e,phi,d,k,message,encrypt,decrypt;
    int range, x, y, n, z, phi, q, k, m, encrypt, decrypt;
    range = 4;
    x = KeyGenerator(range);
    y = KeyGenerator(range);
    
    while (x == y)  
    { 
        x = KeyGenerator(range); 
        y = KeyGenerator(range);
    }
    n = x * y;  
    phi = (x - 1) * (y - 1);  
    z = 2; 
    k = 2;
    while ( z < phi)
    { 
        if (GCD(z,phi) == 1) 
    {
        break; 
    } 
    else 
        z++;
    }
    q = ((k * phi) + 1) / z;
    
    System.out.println("x \t=\t " + x); 
    System.out.println("y\t=\t " + y);
    System.out.println("n \t=\t " + n); 
    System.out.println("phi \t=\t " + phi);
    System.out.println("z \t=\t " + z); 
    System.out.println("q \t=\t " + q);
    m = KeyGenerator(n - 1);
    System.out.println("\nmessage \t=\t " + m);
    encrypt = ((int)Math.pow(m,z)) % n;
    System.out.println("\n\tencrypt message as : " + encrypt);
    decrypt = (int)Math.pow(encrypt,q) % n;
    System.out.println("\n\tdecrypt message as : " + decrypt);
    }
    
}
