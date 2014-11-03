import java.nio.charset.MalformedInputException;
import java.util.LinkedList;

/**
 * 
 */

/**
 * @author Kevin Fray
 *
 */
public class DetectCycle {
    
    private class Node {
        private Node next;
        private int value;
        private Node (Node n, int i) {
            next = n;
            value = i;
        }
    }
    
    private Node head;
    
    public DetectCycle() {
        this.head = new Node(null, 0);
    }
    
    // attach a cycle of given length to the first argument
    public Node makeCycle(Node start, int length) {
        Node last = start;
        for (int i = 1; i < length; i++) {
            last.next = new Node(null, i);
            last = last.next;
        }
        last.next = start; // complete the cycle
        return start;
    }
    
    // Attach a chain to the first argument of given length
    public Node makeChain(Node head, int length) {
        Node cur = head;
        for (int i = 0; i < length; i++) {
            cur.next = new Node(null, i);
            cur = cur.next;
        }
        return cur;
    }
    
    public int firstCycleLength() {
        Node tort = head;
        Node hare = head.next;
        while (tort != hare) {
            tort = tort.next;
            
            hare = hare.next;
            if (tort != hare) hare = hare.next;
        }
        int length = 1;
        hare = hare.next;
        while (tort != hare) {
            hare = hare.next;
            length++;
        }
        return length;
    }
    
    public Node firstCycleStart () {
        Node tort = head;
        Node hare = head;
        int length = firstCycleLength();
        for (int i = 0; i < length; i++) {
            hare = hare.next;
        }
        while (tort != hare) {
            tort = tort.next;
            hare = hare.next;
        }
        return tort;
    }
    
    public static void main(String[] args) {
        DetectCycle DC = new DetectCycle();
        DC.makeCycle(DC.makeChain(DC.head, 25), 12);
        StdOut.println("testing---:");
        StdOut.println(DC.firstCycleLength());
        StdOut.println(DC.firstCycleStart().value);
    }
}
