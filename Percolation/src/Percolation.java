import java.util.Random; 

public class Percolation {
	
	Random random = new Random(); 
	
	public static void main(String[] args) {    
    	new Percolation().run();
    }
	
	void run() {
		// Set size of the ground
		int[] nArray = {50, 100, 200};
		
		for (int i = 0; i < nArray.length; i++) {
			System.out.println();
			System.out.println("Processing...");
			double p = findProbability(nArray[i]); 
			System.out.println("For " + nArray[i] + "x" + nArray[i] + " ground,");
			System.out.println("the optimal packing probability is " + p);
		}

	}
	
	
	/** Returns an array of n arrays of integer, 
	 * where each array is of length n, 
	 * and each integer has probability p of being a sand grain, 
	 * (1-p) of being empty (and dry). 
	 * 0 = empty space, 1 = sand grain, 2 = water.
	 * @param n
	 * @param p
	 * @return
	 */
	int[][] ground(int n, double p) {
		int[][] grnd = new int[n][n];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				double rand_num = random.nextFloat();
				if (rand_num <= p) {
					grnd[row][col] = 1;
				} else {
					grnd[row][col] = 0;
				}
				// System.out.print(grnd[row][col] + " ,");
			}
			// System.out.println();
		}
		return grnd;
	}
	

	/** Modify the ground array corresponding to 
	 * water flowing from row into row + 1
	 * @param ground
	 * @param row
	 */
	void seep(int[][]grnd, int row) {
		
		for (int col = 0; col < grnd.length; col++) {
			if (row == 0) {
				if (grnd[row][col] == 0) grnd[row][col] = 2;
				if (grnd[row + 1][col] == 0) grnd[row + 1][col] = 2;
			} else {
				if (grnd[row][col] == 0) {
					if (isAdjacentWater(grnd, row, col)) grnd[row][col] = 2;
				}
				if ((row < grnd.length - 1) && (grnd[row][col] == 2)) {
					if (grnd[row + 1][col] == 0) grnd[row + 1][col] = 2;
				}					
			}
		}
		
	}
	
	/** Check horizontal neighbors of an element in the ground array
	 * return true if any neighbors(connected with empty space) are water,
	 * return false otherwise.
	 * @param ground
	 * @param row
	 * @param col
	 * @return
	 */
	boolean isAdjacentWater(int[][] ground, int row, int col) {
		if (col == 0) {
			if (isRightWater(ground, row, col)) {
					return true;
				} else {
					return false;
				}
		}
		
		if (col == ground.length - 1) {
			if (ground[row][col-1] ==2) {
					return true;
				} else {
					return false;
				}
		}
		
		if ((ground[row][col-1] == 2) || isRightWater(ground, row, col)) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Check horizontal neighbors to the right of 
	 * an element in the ground array
	 * 
	 * @param ground
	 * @param row
	 * @param col
	 * @return
	 */
	boolean isRightWater(int[][] ground, int row, int col) {
		if (col + 1 > ground.length - 1) return false;
		if (ground[row][col+1] == 1) { return false;
		} else if (ground[row][col+1] == 2) { return true;
		} else {
			return isRightWater(ground, row, col+1);
		}	
	}
	
	/** Determine if the water reaches the bottom row
	 * after water has "seeped" as far as it can
	 * @param ground
	 * @return
	 */
	boolean percolate(int[][] grnd) {
		int[][] ground = seepThroughGround(grnd);
		int bottom = ground.length - 1;
		for (int col = 0; col < ground.length; col++) {
			if (ground[bottom][col] == 2) {
				return true;
			}
		}
		return false; 
	}
	
	/** Loop through seep function until the bottom 
	 * is reached
	 * @param grnd
	 * @return
	 */
	int[][] seepThroughGround(int[][] grnd) {
		for (int row = 0; row < grnd.length; row++){
			seep(grnd, row);
		}
		return grnd;
	}
	
	/** For an n by n array, determines the packing probability p 
	 * that causes the array to have a 50% probability of water 
	 * seeping all the way to the bottom.
	 */
	double findProbability(int n) {
		double expectProb = 0.5;
		double deltaP = 0;
		double prob = 0; 
		int maxLoop = 1000;
		int i = 0; 
		double tol = 0.001;
		double diff = 1;
		double p = 0.5;
		
		while ((Math.abs(diff) > tol) && (i < maxLoop)) {
			// Set p
			p = p + deltaP; 
			if ((p < 0) || (p > 1)) p = p - deltaP;
			
			// Calculate probability of water reaching the bottom
			prob = calProbWaterAtBottom(n, p);
			//System.out.println("loop#: " + i);
			//System.out.println("p: " + p);
			//System.out.println("Prob. of water reaching bottom: " + prob);

			// Determine delta p
			diff = prob - expectProb;
			deltaP = 0.05*diff;
			
			i++;
		}
		return p; 
	}
	
	/** Calculate probability of water reaching the bottom
	 * @param n
	 * @param p
	 * @return
	 */
	double calProbWaterAtBottom(int n, double p) {
		double count = 0;
		double nLoop = 100000/n;
		
		// Iterate over the percolation process nLoop times
		for (int i = 0; i < nLoop; i++) {
			int[][] grnd = ground(n, p);
			if (percolate(grnd)) {
				count++; 
			}
		}
		double tot = Math.floor(nLoop); 
		return count/tot; 
	}

}

