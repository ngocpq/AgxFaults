public class TestRunner {
	
	private static int input_i,input_j,input_k;
	private static int out_expected;
	public static void main(String[] argv) {
		if (argv.length != 3) {
			System.out.println("Error: Command line arguments are: i, j, k");			
			System.exit(1);
		}
		input_i = Integer.parseInt(argv[0] + "");
		input_j = Integer.parseInt(argv[1] + "");
		input_k = Integer.parseInt(argv[2] + "");
		out_expected = trityp_golden.caller(input_i, input_j, input_k);
		
		run_agx();
		System.out.println("finish");
	}

	private static void run_agx() {		
		run_symb(input_i, input_j, input_k);
	}

	private static void run_symb(int input_i2, int input_j2, int input_k2) {		
		int out = TritypeV7.caller(input_i, input_j, input_k);
		checkAssert(out);
	}

	private static void checkAssert(int out) {
		assert out_expected == out;
		/*assert ((((input_i+input_j) <= input_k || (input_j+input_k) <= input_i || (input_i+input_k) <= input_j) && (out == 4)))
	    || (((!((input_i+input_j) <= input_k || (input_j+input_k) <= input_i || (input_i+input_k) <= input_j) && (input_i==input_j && input_j==input_k)) && (out == 3)))
	    ||  ((!((input_i+input_j) <= input_k || (input_j+input_k) <= input_i || (input_i+input_k) <= input_j) && !(input_i==input_j && input_j==input_k) && (input_i==input_j || input_j==input_k || input_i==input_k)) && (out == 2))
	    ||  ((!((input_i+input_j) <= input_k || (input_j+input_k) <= input_i || (input_i+input_k) <= input_j) && !(input_i==input_j && input_j==input_k) && !(input_i==input_j || input_j==input_k || input_i==input_k)) && (out == 1));*/
	}

	
}

class trityp_golden {
	static int caller (int i, int j, int k) {
		int trityp = 0;
		int res=0;
		if (i == 0 || j == 0 || k == 0) {
			trityp = 4;	
			res = -1;
		}
		else {
			trityp = 0;
			if (i == j) {
				trityp = trityp + 1;
			}
			if (i == k) {
				trityp = trityp + 2;
			}
			if (j == k) {
				trityp = trityp + 3;
			}
			if (trityp == 0) {
				if ((i+j) <= k || ((j+k) <= i || (i+k) <= j)) {
					trityp = 4;
					res = -1;
				}
				else {
					trityp = 1;
					res = i*j*k;
				}			
			}
			else {
				if (trityp > 3) {
					res = i*i*i;
				}
				else {
					if (trityp == 1 && (i+j) > k) { // i==j
						res=i*i*k; 
					}
					else {
						
						if (trityp == 2 && (i+k) > j) {    // i==k
							res = i*i*j;
						}
						else {
							if (trityp == 3 && (j+k) > i) {   // j==k
								res=j*j*i;
							}
							else {
								res=-1;
							}
						}
					}
				}
			}
		}
		return res;
	}
}