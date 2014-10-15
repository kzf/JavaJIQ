

/**
 * @author Kevin Fray
 * 
 *         Union-find implementation where each element has a canonical element.
 *         The canonical element in this implementation is the largest element
 *         in the same connected component.
 * 
 *         The interface assumes the union-find structure is over the integers
 *         [0..N].
 *
 */
public class CanonicalUnionFind {

    private int size;
    private int[] height;      // Height of each subtree
    private int[] component;   // Component containing each element
    private int[] canonical;   // The canonical element in each component
    private int ncomponents;

    /**
     * Set up a union-find structure with N singletons
     * 
     * @throws java.lang.IllegalArgumentException
     *            if N <= 0
     * @param N
     *            the number of elements
     */
    public CanonicalUnionFind(int N) {
        if (N <= 0) throw new java.lang.IllegalArgumentException();
        
        size = N;
        height = new int[N];
        component = new int[N];
        canonical = new int[N];
        for (int i = 0; i < N; i++) {
            component[i] = i;
            height[i] = 1;
            canonical[i] = i;
        }
        ncomponents = N;
    }

    /**
     * Merges components containing the elements i and j
     * 
     * @param i
     *             
     * @param j    
     *             
     */
    public void union(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) 
            throw new java.lang.IndexOutOfBoundsException();
        
        int p = root(i);
        int q = root(j);
        if (p == q) return;
        
        int hp = height[p];
        int hq = height[q];
        
        int cp = canonical[p];
        int cq = canonical[q];
        
        /**
         * Determine which of the canonical elements to keep.
         * 
         * For this implementation, we keep the largest.
         */
        if (cp > cq) {
            canonical[q] = cp;
        } else {
            canonical[p] = cq;
        }
        
        if (hp > hq) {
            component[q] = p;
        } else {
            component[p] = q;
            if (hp == hq)
                height[p] = hp + 1;
        }
        
        ncomponents--;
    }
    
    /**
     * Finds the root of the component containing i.
     * 
     * @param i
     *             
     * @return
     *             the id of the root of the subtree containing i
     */
    private int root(int i) {
        if (i < 0 || i >= size) 
            throw new java.lang.IndexOutOfBoundsException();
        
        while (i != component[i]) {
             i = component[i];
        }
        return i;
    }

    /**
     * Find the canonical element in the subtree containing i
     * 
     * @param i
     * 
     * @return 
     *             the id of the canonical element
     */
    public int find(int i) {
        if (i < 0 || i >= size) 
            throw new java.lang.IndexOutOfBoundsException();
        
        return canonical[i];
    }

    /**
     * Determine if two elements i, j are in the same connected component
     * 
     * @param i
     * 
     * @param j
     * 
     * @return
     *  
     */
    public boolean connected(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) 
            throw new java.lang.IndexOutOfBoundsException();
        
        return find(i) == find(j);
    }
    
    public int numComponents() {
        return ncomponents;
    }

}
