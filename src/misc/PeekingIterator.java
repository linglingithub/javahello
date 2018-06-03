package misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * 284. Peeking Iterator DescriptionHintsSubmissionsDiscussSolution Given an
 * Iterator class interface with methods: next() and hasNext(), design and
 * implement a PeekingIterator that support the peek() operation -- it
 * essentially peek() at the element that will be returned by the next call to
 * next().
 * 
 * Example:
 * 
 * Assume that the iterator is initialized to the beginning of the list:
 * [1,2,3].
 * 
 * Call next() gets you 1, the first element in the list. Now you call peek()
 * and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. Calling
 * hasNext() after that should return false. Follow up: How would you extend
 * your design to be generic and work with all types, not just integer?
 * 
 * 
 * Difficulty:Medium Total Accepted:56.6K Total Submissions:159.5K
 * Contributor:porker2008 Companies
 * 
 * Related Topics
 * 
 * Similar Questions Binary Search Tree IteratorFlatten 2D VectorZigzag Iterator
 * 
 * 
 * @author
 *
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator1 implements Iterator<Integer> {
	private Integer next = null;
	private Iterator<Integer> iter;

	public PeekingIterator1(Iterator<Integer> iterator) {
		// initialize any member here.
		iter = iterator;
		if (iter.hasNext()) {
			next = iter.next(); // note iterator move pointer here!!!
		}

	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer rst = next;
		if (iter.hasNext()) {
			next = iter.next();
		} else {
			next = null;
		}
		return rst;
	}

	@Override
	public boolean hasNext() {
		return iter.hasNext() || next != null;
	}
}

class PeekingIterator implements Iterator<Integer> {
	private Integer next = null;
	private Iterator<Integer> iter;
	boolean noSuchElement;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		iter = iterator;
		advanceIter();
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		// you should confirm with interviewer what to return / throw
		// if there are no more values
		return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if (noSuchElement)
			throw new NoSuchElementException();
		Integer rst = next;
		advanceIter();
		return rst;
	}

	@Override
	public boolean hasNext() {
		return !noSuchElement;
	}
	
	private void advanceIter() {
		if (iter.hasNext()) {
			next = iter.next();
		} else {
			noSuchElement = true;
		}
	}
}
