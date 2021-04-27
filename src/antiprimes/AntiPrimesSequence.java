package antiprimes;

import java.util.ArrayList;
import java.util.List;


/**
 * Represent the sequence of antiprimes found so far.
 */
public class AntiPrimesSequence {

    /**
     * The numbers in the sequence.
     */
    private List<Number> antiPrimes = new ArrayList<>();
    private List<Observer> observers= new ArrayList<>();
    private NumberProcessor processor;
    

    /**
     * Create a new sequence containing only the first antiprime (the number '1').
     */
    public AntiPrimesSequence() {
    	processor = new NumberProcessor(this);
        this.reset();
        processor.start();
    }

    /**
     * Clear the sequence so that it contains only the first antiprime (the number '1').
     */
    public void reset() {
        antiPrimes.clear();
        //INIZIALIZZA NUMERO DI PARTENZA
        addAntiPrime(new Number(1,1));
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    /**
     * Find a new antiprime and add it to the sequence.
     */
    public void computeNext() {
        //antiPrimes.add(AntiPrimes.nextAntiPrimeAfter(getLast()));
        try{
            processor.nextAntiPrime(getLast());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Return the last antiprime found.
     */
    public Number getLast() {
        int n = antiPrimes.size();
        return antiPrimes.get(n - 1);
    }

    /**
     * Return the last k antiprimes found.
     */
    public List<Number> getLastK(int k) {
        int n = antiPrimes.size();
        if (k > n)
            k = n;
        return antiPrimes.subList(n - k, n);
    }

	synchronized public void addAntiPrime(Number number) {
		antiPrimes.add(number);
		for (Observer observer : observers){
		    observer.update();
        }
	}
}
