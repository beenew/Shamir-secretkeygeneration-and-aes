package shamir;
import java.math.BigInteger;
public class combines {
	 static BigInteger primer = BigInteger.valueOf(172394); 
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

	 
	 private static BigInteger modInverse(BigInteger k, BigInteger prime)
	    { 
	        k = k.mod(prime);
	        BigInteger r = (k.compareTo(BigInteger.ZERO) == -1) ? (gcdD(prime, k.negate())[2]).negate() : gcdD(prime,k)[2];
	        return prime.add(r).mod(prime);
	    }
    public static BigInteger combine(final SecretShare[] shares, final BigInteger prime)
    {
        BigInteger accum = BigInteger.ZERO;

        for(int formula = 0; formula < shares.length; formula++)
        {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for(int count = 0; count < shares.length; count++)
            {
                if(formula == count)
                    continue; // If not the same value

                int startposition = shares[formula].getNumber();
                int nextposition = shares[count].getNumber();

                numerator = numerator.multiply(BigInteger.valueOf(nextposition).negate()).mod(prime); // (numerator * -nextposition) % prime;
                denominator = denominator.multiply(BigInteger.valueOf(startposition - nextposition)).mod(prime); // (denominator * (startposition - nextposition)) % prime;
            }
            BigInteger value = shares[formula].getShare();
            BigInteger tmp = value.multiply(numerator) . multiply(modInverse(denominator, prime));
            accum = prime.add(accum).add(tmp) . mod(prime); //  (prime + accum + (value * numerator * modInverse(denominator))) % prime;
        }

        System.out.println("The secret is: " + accum + "\n");

        return accum;
        //main
//      // we can use any combination of 2 or more parts of secret
//      SecretShare[] sharesToViewSecret = new SecretShare[] {shares[0],shares[1]}; // 0 & 1
//      BigInteger result = Shamir.combine(sharesToViewSecret, prime);
//      System.out.println(result);

    }
    public static void combination(String [] args) {
    	
    	SecretShare[] sharesToViewSecret = new SecretShare[] {Shamir.shares[0],Shamir.shares[1]}; // 0 & 1
    	
    	BigInteger result = combine(sharesToViewSecret, primer);
    	System.out.println(result);

    }
}
