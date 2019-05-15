
import java.util.ArrayList;
import java.util.Scanner;
public class PhiFunction {

	public static void main(String[] args) {
		
		int c = 0;
		do {
		Scanner in = new Scanner(System.in);
		System.out.print("Inserisci un naturale (0 per uscire): ");
		long n = in.nextLong();
		if(n == 0)
			break;
		System.out.println(phiFunction(n));
		}while(c == 0);
		
	
	}
	
	public static long phiFunction(long n) {
		ArrayList<Long> primeDivisorsOfN = new ArrayList<Long>(primeDivisors(n));
		long k;
		int phiOfN;
		if(n == 1)
			return 1;
		else if( primeDivisorsOfN.size() == 1) {
			long p = primeDivisorsOfN.get(0);
			for(k = 0; (n % p) == 0; k++)
				n /= p;
			phiOfN = (int)((p - 1)*Math.pow(p, k-1));
			return phiOfN;
		}
		else {
			long[] aANDb = numbersThatMultipliedGiveNAndAreNotDivisibleByThem(n);
			phiOfN = (int)(phiFunction(aANDb[0]) * phiFunction(aANDb[1]));
			return phiOfN;
		}
		
	}
	
	public static boolean isPrime(long number) {
		if(number > 2 && number % 2 == 0)
			return false;
		long top = (int)Math.sqrt(number) + 1;
		for(long i = 3; i <= top; i+=2) 
			if( number % i == 0)
				return false;
		return true;
	}
	
	public static ArrayList<Long> primeDivisors(long number) {
		ArrayList<Long> divisors = new ArrayList<Long>();
		if(isPrime(number)) {
			divisors.add(number);
			return divisors;
		}
		if(number > 2 && number % 2 == 0)
			divisors.add((long)2);
		long top = (int)Math.sqrt(number) + 1;
		for(long i = 3; i < top; i+=2)
			if(isPrime(i) && (number % i == 0)) {
				divisors.add(i);
				number /= i;
				
		}
		return divisors;
	}
	
	public static long[] numbersThatMultipliedGiveNAndAreNotDivisibleByThem(long N) {
		
		long num1 = 0, num2 = 0, i, tmpN = N;
		ArrayList<Long> divisorsOfN = new ArrayList<Long>(primeDivisors(N));
		num1 = divisorsOfN.get(divisorsOfN.size()-1);
		for(i = 0; (tmpN % num1) == 0; i++)
			tmpN /= num1;
		num1 = (long)Math.pow(num1, i);
		num2 = N/num1;
		long[] theNumbers = {num1, num2};
		return theNumbers;
	}

}
