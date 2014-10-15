import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Kevin Fray
 * 
 *         Tests for the Social Network problem:
 *          Given N people and M pairs of friendships in the order formed, find
 *          at which point all people are connected.
 *
 */
public class SocialNetwork {

    private static int timeUntilConnected(String filename) {
        try {
            System.setIn(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        int N = StdIn.readInt();
        
        CanonicalUnionFind UF = new CanonicalUnionFind(N);

        int steps = 0;
        
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            UF.union(i, j);
            steps++;
            if (UF.numComponents() == 1) 
                return steps;
        }
        
        return -1;
    }
    
    private static class UnitTest {
        private String filename;
        private int expected;
        
        public UnitTest(String filename, int expected) {
            this.filename = filename;
            this.expected = expected;
        }
    }
    
    public static void main(String[] args) {
        UnitTest[] tests = {
            new UnitTest("sn1", 4)
            };
        
        for(UnitTest x: tests) {
            int result  = timeUntilConnected("tests/UnionFind/" + x.filename);
            if (result == x.expected) {
                StdOut.print("Test " + x.filename + " worked as expected.");
            } else {
                StdOut.print(">>>> Test " + x.filename + " failed; got ");
            }
        }
    }
}
