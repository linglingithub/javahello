package hw;

/**
 * 
Data Structure
Queue By Two Stacks
Java: Implement a queue by using two stacks. The queue should provide size(), isEmpty(), offer(), poll() and peek() operations. When the queue is empty, poll() and peek() should return null.

C++: Implement a queue by using two stacks. The queue should provide size(), isEmpty(), push(), front() and pop() operations. When the queue is empty, front() should return -1.

Assumptions

The elements in the queue are all Integers.
size() should return the number of elements buffered in the queue.
isEmpty() should return true if there is no element buffered in the queue, false otherwise. * 
 * 
 * @author 
 *
 */
import java.util.Stack;

public class Solution {

	private Stack<Integer> in_stack = new Stack<Integer>();
	private Stack<Integer> out_stack = new Stack<Integer>();

	public Solution() {
		// Write your solution here.
		// Stack<Integer> in_stack = new Stack<Integer>();
		// Stack<Integer> out_stack = new Stack<Integer>();
		// java does NOT allow this above way to write 'private' member variables,
		// two ways to
		// declare and define:
		/**************************
		 * (1) inside class and outside of constructor, write the following, and do
		 * nothing inside the constructor: private Stack<Integer> in_stack = new
		 * Stack<Integer>(); private Stack<Integer> out_stack = new Stack<Integer>();
		 * 
		 * (2) declare the variable outside constructor, then init it in constructor; //
		 * when declare private Stack<Integer> in_stack; // declare
		 * 
		 * // when in constructor this.in_stack = new Stack<Integer>();
		 * 
		 */
	}

	private void check_and_transfer() {
		if (this.out_stack.empty()) {
			while (!this.in_stack.empty()) {
				this.out_stack.push(this.in_stack.pop());
			}
		}
	}

	public Integer poll() {
		this.check_and_transfer();
		if (out_stack.empty())
			return null;
		return this.out_stack.pop();
	}

	public void offer(int element) {
		this.in_stack.push(element);
	}

	public Integer peek() {
		this.check_and_transfer();
		if (this.out_stack.empty())
			return null;
		else
			return this.out_stack.peek();
	}

	public int size() {
		return this.in_stack.size() + this.out_stack.size();
	}

	public boolean isEmpty() {
		return (this.in_stack.empty() && this.out_stack.empty()); // !!! not || here
	}
}
