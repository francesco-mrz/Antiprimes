package antiprimes;

import java.util.logging.Logger;

public class NumberProcessor extends Thread {

	private AntiPrimesSequence sequence;

	private final static Logger LOGGER = Logger.getLogger(NumberProcessor.class.getName());

	private Number request = null;

	NumberProcessor(AntiPrimesSequence sequence) {
		this.sequence = sequence;
	}

	public void run() {
		LOGGER.info("Processor ready");
		acceptRequests();

		while (true) {
			try {
				LOGGER.info("Waiting a new request");
				Number n = getNextToProcess();
				LOGGER.info("Searching the successor of " + n.getValue() + "...");
				Number m = AntiPrimes.nextAntiPrimeAfter(n);
				LOGGER.info("Found " + m.getValue() + " with " + m.getDivisors() + " divisors");
				sequence.addAntiPrime(m);
				acceptRequests();
			} catch (InterruptedException e) {
				LOGGER.severe(e.getMessage());
				break;
			}
		}

	}

	synchronized public void nextAntiPrime(Number n) {
		while(request != null) {
			if(request.getValue() == n.getValue()) {
				
			}
		}
	}

	public Number getNextToProcess() {

	}

	public void acceptRequests() {
		request = null;
		notify();
	}

}
