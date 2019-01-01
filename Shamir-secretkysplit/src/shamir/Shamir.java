package shamir;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public final class Shamir
{
static BigInteger primer;
public int firefightersecret1=0;
public int civildefensesecret=0;
public static int secret1=0;
public static int secret2=0;
    public static SecretShare[] split(final BigInteger secret, int needed, int available, BigInteger prime, Random random)
    {
        System.out.println("Prime Number: " + prime);

        final BigInteger[] coeff = new BigInteger[needed];
        coeff[0] = secret;
        for (int i = 1; i < needed; i++)
        {
            BigInteger r;
            while (true)
            {
                r = new BigInteger(prime.bitLength(), random);
                if (r.compareTo(BigInteger.ZERO) > 0 && r.compareTo(prime) < 0)
                {
                    break;
                }
            }
            coeff[i] = r;
        }

        final SecretShare[] shares = new SecretShare[available];
        for (int x = 1; x <= available; x++)
        {
            BigInteger accum = secret;

            for (int exp = 1; exp < needed; exp++)
            {
                accum = accum.add(coeff[exp].multiply(BigInteger.valueOf(x).pow(exp).mod(prime))).mod(prime);
            }
            shares[x - 1] = new SecretShare(x, accum);
            System.out.println("Share " + shares[x - 1]);
            if (x==1) {
            	secret1=accum.intValue();	
            }else if(x==2 ) {
            	secret2=accum.intValue();
            }
        }

        return shares;
    }

    private static BigInteger[] gcdD(BigInteger a, BigInteger b)
    { 
        if (b.compareTo(BigInteger.ZERO) == 0)
            return new BigInteger[] {a, BigInteger.ONE, BigInteger.ZERO}; 
        else
        { 
            BigInteger n = a.divide(b);
            BigInteger c = a.mod(b);
            BigInteger[] r = gcdD(b, c); 
            return new BigInteger[] {r[0], r[2], r[1].subtract(r[2].multiply(n))};
        }
    }

   

    public static void shamirsplitplay(final String[] args)
    {
        final int CERTAINTY = 256;
        final SecureRandom random = new SecureRandom();

        final BigInteger secret = new BigInteger("576056");

        // prime number must be longer then secret number
        final BigInteger prime = new BigInteger(secret.bitLength() + 1, CERTAINTY, random);
        primer = BigInteger.valueOf(172394); 
        // 2 - at least 2 secret parts are needed to view secret
        // 3 - there are 5 persons that get secret parts
        final SecretShare[] shares = Shamir.split(secret, 2, 3, primer, random);

       
        
//        // we can use any combination of 2 or more parts of secret
//        SecretShare[] sharesToViewSecret = new SecretShare[] {shares[0],shares[1]}; // 0 & 1
//        BigInteger result = Shamir.combine(sharesToViewSecret, prime);
//        System.out.println(result);

    }
}