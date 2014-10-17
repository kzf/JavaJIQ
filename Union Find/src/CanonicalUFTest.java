import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Kevin Fray
 * 
 *         Tests canonical union find, which is union find with the additional
 *         feature of being able to return a canonical (in this case largest)
 *         element in each connected component.
 *
 */
public class CanonicalUFTest {
    
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
            new UnitTest("tests/UnionFind/cuf1", new int[]{1,2,6,9}, new int[]{9,9,9,9})
            };
        
        for(UnitTest x: tests) {
            try {
                System.setIn(new FileInputStream(x.filename));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            int N = StdIn.readInt();
            
            CanonicalUnionFind UF = new CanonicalUnionFind(N);

            while (!StdIn.isEmpty()) {
                int i = StdIn.readInt();
                int j = StdIn.readInt();
                UF.union(i, j);
            }
            
            boolean passed = true;
            for (int i = 0; i < x.elements.length; i++) {
                int result = UF.find(x.elements[i]);
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
