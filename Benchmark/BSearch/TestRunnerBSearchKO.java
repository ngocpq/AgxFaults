package agxfaults.array_bsearch;
public class TestRunnerBSearchKO {
	
	static int[] input_a;
	static int input_x;
	private static int out_expected;
	
	public static void main(String[] argv) {
		//argv=new String[]{"3","1","2","3","4"};		
		if (argv.length < 1) {
			System.out.println("Error: Command line arguments are: x array");			
			System.exit(1);
		}				
		input_x = Integer.parseInt(argv[0]);
		input_a = new int[argv.length-1];
		for(int i=0;i<input_a.length;i++)
			input_a[i]=Integer.parseInt(argv[i+1]);
		out_expected = bSearch_golden(input_a,input_x);
		
		run_agx();
		System.out.println("finish");
	}

	private static void run_agx() {		
		run_symb(input_a,input_x);
	}

	private static void run_symb(int[] a,int x) {
		int out = BSearchKO.bSearch(a,x);
		checkAssert(out);
	}

	private static void checkAssert(int out) {
		assert out_expected == out;
	}

	static int bSearch_golden(int[] a, int x) {
		int result = -1;
		int milieu = 0;
		int gauche = 0;
		int droite = a.length - 1;
		while ((result == -1) && (gauche <= droite)) {
			milieu = (gauche + droite) / 2;
			if (a[milieu] == x) {
				result = milieu;
			} else if (a[milieu] <= x) {
				gauche = milieu + 1;
			} else {
				droite = milieu - 1;
			}
		}
		return result;
	}

}