package hw;

/**
 * Reservoir Sampling
 * 
 * Consider an unlimited flow of data elements. How do you sample one element
 * from this flow, such that at any point during the processing of the flow, you
 * can return a random element from the n elements read so far.
 * 
 * You will implement two methods for a sampling class:
 * 
 * read(int value) - read one number from the flow sample() - return at any time
 * the sample, if n values have been read, the probability of returning any one
 * of the n values is 1/n, return null(Java)/INT_MIN(C++) if there is no value
 * read so far You may need to add more fields for the class.
 * 
 * Medium probability sampling-and-randomization
 * 
 * @author
 *
 */

// This class will be used as a reader and it read each of the
// element by calling the read() method.
// by calling the sample() method, it will return the sample
// of whatever read before.
public class ReservoirSampling {
	// how many numbers have been read so far.
	private int count;
	// only need to maintain the current sample.
	private Integer sample;

	public ReservoirSampling() {
		// initialization, count is initialized as 0 since there is
		// no single number read at the beginning.
		this.count = 0;
		this.sample = null;
	}

	// read() means takes in one number from the stream, the number is
	// passed to the method by parameter 'value'
	public void read(int value) {
		count++;
		int prob = (int) (Math.random() * count);
		// the current read value has the probability of 1 / count to be as the
		// current sample.
		if (prob == 0) {
			sample = value;
		}
	}

	public Integer sample() {
		return sample;
	}
}

class Solution_self {
	// class ReservoirSampling {
	private int count;
	private int sample;

	public void read(int value) {
		count++;
		int random = (int) (Math.random() * count);
		if (random == 0) {
			sample = value;
		}
	}

	public Integer sample() {
		if (count == 0) {
			return null;
		}
		return sample;
	}
	// }
}
