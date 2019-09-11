
public class LFSR {

	int[] regs = new int[8];
	long seed;
	int length;
	//00101101
	
	public LFSR( long s ) throws Exception {
		//check if it is of length 8
		length = (int)(Math.log10(s)+1);
		if( length < 8 ) {
			defaultConfig();
			throw new Exception( "Seed length too small, must be >= 8. Using default config." );
		}
		seed = s;
		regs = new int[length];
		for( int i = length-1; i >= 0; i-- ) {
			regs[i] = (int)(s%10);
			s = (long) Math.floor(s/10);
		}
		loadSeed();
		initialize();
	}
	
	public void reseed() {
		loadSeed();
		initialize();
	}
	
	public void newSeed( long s ) throws Exception {
		length = (int)(Math.log10(s)+1);
		if( length < 8 ) {
			defaultConfig();
			throw new Exception( "Seed length too small, must be >= 8. Using default config." );
		}
		seed = s;
		loadSeed();
		initialize();
	}
	
	public double getRand() {
		double a = 0;
		double b = 99999999;
		step();
		for( int i = 0; i < 8; i++ ) {
			a = a + getValue()*Math.pow( 10, i );
			step();
		}
		return a/b;
	}
	
	public int getDigits( int digits ) {
		int result = getValue();
		step();
		for( int i = 1; i < digits; i++ ) {
			result = result * 10;
			result = result + getValue();
			step();
		}
		return result;
	}
	
	private void loadSeed() {
		long s = seed;
		regs = new int[length];
		for( int i = length-1; i >= 0; i-- ) {
			regs[i] = (int)(s%10);
			s = (long) Math.floor(s/10);
		}
	}
	
	private void initialize() {
		for( int i = 0; i < length*7; i++ ) {
			step();
		}
	}
	
	public int getValue() { 
		return regs[length-1];
	}
	
	private void step() {
		int feedback = addModTen(regs[length-1],regs[length-3]);
		feedback = addModTen(feedback, regs[length-4]);
		feedback = addModTen(feedback, regs[length-6]);
		for( int i = length-1; i > 0; i-- ) {
			regs[i]=regs[i-1];
		}
		regs[0] = feedback;
	}
	
	private void defaultConfig() {
		long s = 987654321L;
		seed = s;
		length = 8;
		regs = new int[length];
		for( int i = length-1; i >= 0; i-- ) {
			regs[i] = (int)(s%10);
			s = (long) Math.floor(s/10);
		}
		initialize();
	}
	
	private int addModTen(int a, int b) {
		return ((a+b)%10);
	}
	
	public void printSpread() {
		double[] n = new double[10];
		double precision = 1000000;
		for( int i = 0; i < precision; i++ ) {
			n[getValue()]+=1;
			step();
		}
		double diff;
		for( int i = 0; i < 10; i++ ) {
			diff = Math.abs(.1-(n[i]/precision));
			System.out.println( "P("+i+"): " + diff );
		}
	}
	
	
}
