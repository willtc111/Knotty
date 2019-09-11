
public class Shuffler {

	static LFSR rand;
	
	public Shuffler( long seed ) throws Exception {

		//set up random number generator
		rand = null;
		//seed = 9876543210L;
		rand = new LFSR( seed );
	}
	
	public int[] shuffle( int[] input ) {
		int buf;
		int swapIndex;
		for( int i = input.length-1; i >= 0; i-- ) {
			swapIndex = getIndex(i);
	//		System.out.println( ">> swap " + i + " ~ " + swapIndex );
			buf = input[i];
			input[i] = input[swapIndex];
			input[swapIndex] = buf;
		}
		return input;
	}
	
	public void setSeed( long seed ) throws Exception {
		rand.newSeed( seed );
	}
	
	private static int getIndex(int max) {
		return (int)((double)max*rand.getRand());
	}
	
	@SuppressWarnings("unused")
	private static void getSmall() {
		//test how long it takes to get a super small number
		double n = 1;
		int count = 0;
		while( n > 0.00001 ) {
			n = rand.getRand();
			System.out.println(count + "\t" + n);
			count++;
		}
	}
	
	@SuppressWarnings("unused")
	private static void getSpread() {
		//just for testing the randomness of my LFSRs
		double normal = 0;
		double control = 0;
		for( int i = 0; i < 10000; i++ ) {
			normal += rand.getRand();
			normal -= rand.getRand();
			control += Math.random();
			control -= Math.random();
		}
		System.out.println("normal:" + normal + "\ncontrol:" + control);
	}

}