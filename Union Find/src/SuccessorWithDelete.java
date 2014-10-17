import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * @author Kevin Fray
 * 
 *         Implements the successor with delete data structure, which
 *         operates over the intergers 0...N-1 and supports deleting an
 *         element and finding the smallest y in the structure
 *         that is greater than or equal to a given number.
 *
 */
public class SuccessorWithDelete {
    
    private CanonicalUnionFind CUF;
    
    public SuccessorWithDelete(int N) {
        CUF = new CanonicalUnionFind(N);
    }
    
    public void remove(int x) {
        // Check if it is already removed
        if (successor(x) != x) return;
        CUF.union(x, successor(x+1));
    }
    
    public int successor(int x) {
        return CUF.find(x);
    }
    
    private static class UnitTest {
        public String filename;
        public int[] elements;
        public int[] expected;
        
        public UnitTest(String filename, int[] elements, int[] expected) {
            this.filename = filename;
            this.elements = elements;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        UnitTest[] tests = {
            new UnitTest("tests/UnionFind/swd1", new int[]{0,2,3,6,8}, new int[]{0,4,4,7,9})
            };
        
        for(UnitTest x: tests) {
            try {
                System.setIn(new FileInputStream(x.filename));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            int N = StdIn.readInt();
            
            SuccessorWithDelete SWD = new SuccessorWithDelete(N);

            while (!StdIn.isEmpty()) {
                int i = StdIn.readInt();
                SWD.remove(i);
            }
            
            boolean passed = true;
            for (int i = 0; i < x.elements.length; i++) {
                int result = SWD.successor(x.elements[i]);
                if (result != x.expected[i]) {
                    passed = false;
                    StdOut.println(">>>> Test " + x.filename + " failed; got " + result 
                            + " where " + x.expected[i] + " was expected for element " + x.elements[i]);
                }
            }
            
            if (passed) StdOut.println("Test " + x.filename + " worked as expected.");
        }

    }

}
