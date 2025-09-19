public class Stack<E> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /** Create an empty stack with default capacity */
    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /** Add an element to the top of the stack. Resizes automatically if needed. */
    public void push(E element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    /** Remove and return the top element. Throws IllegalStateException if stack is empty. */
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot pop from an empty stack.");
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[--size];
        elements[size] = null; // help GC
        return element;
    }

    /** Retrieve (but do not remove) the top element. Throws if empty. */
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek at an empty stack.");
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[size - 1];
        return element;
    }

    /** Return true if the stack has no elements. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of elements currently in the stack. */
    public int size() {
        return size;
    }

    /* Double the backing array capacity */
    private void resize() {
        int newCapacity = elements.length * 2;
        if (newCapacity == 0) newCapacity = DEFAULT_CAPACITY;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}

/* Driver for demonstrating the Stack implementation.
   This is deliberately package-private (no 'public') so the file can be named Stack.java
*/
class StackDriver {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("Pushing: 10, 20, 30, 40");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        System.out.println("Current size: " + stack.size());
        System.out.println("Top element: " + stack.top());

        System.out.println("\nPopping elements:");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }

        System.out.println("\nStack empty? " + stack.isEmpty());

        // Demonstrate error handling when popping an empty stack
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
