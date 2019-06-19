import java.util.*;
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
        prime1 = BigInteger.probablePrime(512,random1);
        prime2 = BigInteger.probablePrime(512,random1);
        phi = prime1.subtract(BigInteger.ONE);
        phi = phi.multiply(prime2.subtract(BigInteger.ONE));//calculate phi
        N = prime1.multiply(prime2);
        encryptKey = BigInteger.probablePrime(256, random1);
        while(encryptKey.gcd(phi).compareTo(BigInteger.ONE)>0&& encryptKey.compareTo(phi) < 0){
           encryptKey.add(BigInteger.ONE);
        }
        decryptKey = encryptKey.modInverse(phi);
    }
    public RSA(BigInteger first, BigInteger second, BigInteger encryptKey){
        if (first.isProbablePrime(5)&&second.isProbablePrime(5)){
            prime1 = first;
            prime2 = second;
            N = prime1.multiply(prime2);
            phi = first.subtract(BigInteger.ONE);
            phi = phi.multiply(second.subtract(BigInteger.ONE));//calculate phi
            N = prime1;
            N = N.multiply(prime2);
            decryptKey = encryptKey.modInverse(phi);

        }
//        else {
//            System.out.println("p1 or p2 is not a Prime, please set again");
//            return false;
//        }
//        if(phi.gcd(encryptKey)==BigInteger.ONE){
//            this.encryptKey = encryptKey;
//            decryptKey = encryptKey.modInverse(phi);
//            return true;
//        }
//        else{
//            System.out.println("EncryptKeys is not Relative prime to phi, please use same p1 and p2 to try again");
//            return false;
//        }

    }
    public BigInteger encrption(BigInteger message){
        BigInteger result = message.modPow(encryptKey,N);
        return result;

    }
    public BigInteger decrption(BigInteger message){
        return message.modPow(decryptKey,N);
    }

    public static void main(String [ ] args){
        BigInteger a = new BigInteger("5");
        BigInteger b = new BigInteger("7");
        BigInteger c = new BigInteger("11");
        RSA test = new RSA();
        BigInteger message = new BigInteger("1232333223321333333333333333333333333333333333333333");
        System.out.println(test.encrption(message));
        System.out.println(test.decrption(test.encrption(message)));
        System.out.println("finished");
    }
}

