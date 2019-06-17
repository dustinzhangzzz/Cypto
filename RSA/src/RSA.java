import java.util.Random;
import java.math.BigInteger;
public class RSA {
    private BigInteger prime1;
    private BigInteger prime2;
    private BigInteger phi;
    private BigInteger N;
    private BigInteger decryptKey;
    public  BigInteger encryptKey;
    public RSA(){
        Random random1 = new Random();
        Random random2 = new Random();
        prime1 = BigInteger.probablePrime(512,random1);
        prime2 = BigInteger.probablePrime(512,random2);
        phi = prime1.subtract(BigInteger.ONE);
        phi = phi.multiply(prime2.subtract(BigInteger.ONE));//calculate phi
        N = prime1;
        N = N.multiply(prime2);
        BigInteger e = phi.divide(BigInteger.TWO);
        while(e.gcd(phi)!=BigInteger.ONE){
           e = e.add(BigInteger.ONE);
        }
        decryptKey = encryptKey.modInverse(phi);
    }
    public boolean RSA(BigInteger first, BigInteger second, BigInteger encryptKey){
        if (first.isProbablePrime(5)&&second.isProbablePrime(5)){
            prime1 = first;
            prime2 = second;
            N = prime1.multiply(prime2);
            phi = first.subtract(BigInteger.ONE);
            phi = phi.multiply(second.subtract(BigInteger.ONE));//calculate phi
            N = prime1;
            N = N.multiply(prime2);
        }
        else {
            System.out.println("p1 or p2 is not a Prime, please set again");
            return false;
        }
        if(phi.gcd(encryptKey)==BigInteger.ONE){
            this.encryptKey = encryptKey;
            decryptKey = encryptKey.modInverse(phi);
            return true;
        }
        else{
            System.out.println("EncryptKeys is not Relative prime to phi, please use same p1 and p2 to try again");
            return false;
        }

    }
    public BigInteger encrption(BigInteger message){
        BigInteger result = message.modPow(encryptKey,N);
        return result;

    }
    public BigInteger decrption(BigInteger message){
        return message.modPow(decryptKey,N);
    }
}

