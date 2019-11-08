public class TestRunnerSquareRoot {
	
	private static int input_n;
	private static int out_expected;
	public static void main(String[] argv) {
		if (argv.length != 1) {
			System.out.println("Error: Command line arguments are: n");			
			System.exit(1);
		}
		input_n = Integer.parseInt(argv[0] + "");
		out_expected = SquareRoot_golden.SquareRoot(input_n);
		
		run_agx();
		System.out.println("finish");
	}

	private static void run_agx() {		
		run_symb(input_n);
	}

	private static void run_symb(int n) {		
		int out = SquareRoot.SquareRoot(n);
		checkAssert(out);
	}

	private static void checkAssert(int out) {
		assert out_expected == out;
	}
}

class SquareRoot_golden {
	static int SquareRoot(int val)
  {
      int i = 1;
      int v = 0;
      int res = 0;
      while (v < val){
           v = v + 2*i + 1;
           i = i + 1; 
      }
      res = i-1;

      return res;
 }

}