import java.util.ArrayList;

/**
 * program about recursion of phi eulero
 */
public class Main {
    static ArrayList<Integer> arrayListPrimeFactors = new ArrayList<>(); // arrayList of factor of number insert
    static ArrayList<Integer> arrayListPrimeNumber = new ArrayList<>(); // arrayList of prime numbers
    static ArrayList<NumbersExp> arrayListNumbersWhitExp = new ArrayList<>(); // arrayList of rewrite arrayListPrimeFactors
    private static int exponent=2,index=0;

    /**
     * function main of recursion calculate of phi eulero
     */
    public static void main(String[] args) {
        do {
            arrayListNumbersWhitExp.clear();//clear all arraylist
            arrayListPrimeFactors.clear();
            arrayListPrimeNumber.clear();
            int numbers= it.unibs.fp.mylib.InputDati.leggiIntero("Number: ");//input number to calculate phi of eulero
            sieveOfEratosthenes(numbers);// generate prime numbers less than input numbers
            System.out.println(phiEulero(numbers));
        }while (true);

    }

    /**
     * method to calculate number of phi eulero
     * @param n number in input
     * @return value of phi eulero
     */
    private static int phiEulero(int n){
        primeFactors(n);
        if(n == 1) {// n is one
            return 1;
        }else if(isPrimo(n)){//n is a prime number
            return n-1;
        }else if(arrayListPrimeFactors.size() == 2) { //n is the multiply of two prime number to improve velocity
            return (((arrayListPrimeFactors.get(0)) - 1) * ((arrayListPrimeFactors.get(1) - 1)));
        }else if(isPowOfaPrimeNumber(n)){ //n is the power of a prime number
            return (int)(Math.pow(arrayListPrimeNumber.get(index),exponent)-Math.pow(arrayListPrimeNumber.get(index),exponent-1));
        }else{
            NumbersExp number;
            int multiply = arrayListPrimeFactors.get(0);// assignment  new value
            int count = 1;//initialization count at 1
            for(int i=1; i<arrayListPrimeFactors.size();i++){//loop to calculate exp of numbers of factors prime
                if(multiply==arrayListPrimeFactors.get(i)){// if is equals increment exp
                    count++;
                }else{// if is not equals add exponent and number at  arrayListNumbersWhitExp
                    number = new NumbersExp(multiply,count);
                    arrayListNumbersWhitExp .add(number );
                    multiply = arrayListPrimeFactors.get(i);// assignment of new value
                    count = 1;//reset count
                }
            }
            number = new NumbersExp(multiply,count);
            arrayListNumbersWhitExp .add(number );
            int result = 1; //initialization result at 1
            for ( NumbersExp numberofArray :  arrayListNumbersWhitExp ){ //calculate phi eulero by recursion
                result *= phiEulero((int)Math.pow(numberofArray.getNumber(),numberofArray.getExp()));
            }
            return result; //return value of phi eulero
        }
    }

    /**
     * method to check if numbers is prime
     * @param m number in input
     * @return true if is prime
     *          false if isn't prime
     */
    private static boolean isPrimo(int m){
        int num=m-1;
        do{//loop to check the condition of prime numbers
            if(m%num == 0){//if the module is 0 -> number is not prime
                return false;
            }
            num--;
        }while(num>=2);
        return true;
    }

    /**
     * method to find prime factor of numbers and the result is in arrayListPrimeFactors
     * @param n input numbers
     */
    public static void primeFactors(int n) {

        // Print the number of 2s that divide n
        while (n%2==0)
        {
            arrayListPrimeFactors.add(2);
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            // While i divides n, print i and divide n
            while (n%i == 0)
            {
                arrayListPrimeFactors.add(i);
                n /= i;
            }
        }

        // This condition is to handle the case whien
        // n is a prime number greater than 2
        if (n > 2)
            arrayListPrimeFactors.add(n);
    }

    /**
     * method to generate prime numbers less than input number n
     * @param n input number
     */
    private static void sieveOfEratosthenes(int n) {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                arrayListPrimeNumber.add(i);
        }
    }

    /**
     * method to check number isn't pow of prime numbers
     *
     * @param n input number to check
     * @return true if is pow of prime numbers
     *         false if isn't pow of prime numbers
     */
    private static boolean isPowOfaPrimeNumber(int n){
        for(int i=0; i<arrayListPrimeNumber.size();){//loop whit increment in is body
            if(n==Math.pow(arrayListPrimeNumber.get(i),exponent)){//check if n is equals to pow of prime numbers
                index=i;
                return true;
            }else{
                exponent++;
            }
            if(Math.pow(arrayListPrimeNumber.get(i),exponent)>n){// if pow of prime numbers is greater than n
                i++;// increment the position of arrayListPrimeNumber and set exponent to 1
                exponent=1;
            }
        }
        return false;
    }
}