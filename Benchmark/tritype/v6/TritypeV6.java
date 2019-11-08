/* program for triangle perimeter 
 * returns i+j+k
 *      i+j+k if (i,j,k) are the sides of any triangle
 *      2*i + j or 2*i+k or 2*j+i if (i,j,k) are the sides of an isosceles triangle
 *      3*i if (i,j,k) are the sides of an equilateral triangle
 * returns -1 if (i,j,k) are not the sides of a triangle
 * 
 * ERROR : in assignment line 58
 * returns 6 for (2,1,2) while it should return 5
 */

class TritypeV6{

	/*@ requires 
          @ ((i ==2) && (j == 1) && (k ==2));
	  @ ensures
	  @	(((i+j) <= k || (j+k) <= i || (i+k) <= j) ==> (\result == -1))
	  @  && ((!((i+j) <= k || (j+k) <= i || (i+k) <= j) && (i==j && j==k)) ==> (\result == i+j+k));
	  @*/	static int caller (int i, int j, int k) {
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
					res = i+j+k;
				}			
			}
			else {
				if (trityp > 3) {
					res = 3*i;
				}
				else {
					if (trityp == 1 && (i+j) > k) { // i==j
						res=2*i+k; 
					}
					else {
						
						if (trityp == 2 && (i+k) > j) {    // i==k
							res = 2*i + k; // error in the assignment : should be res = 2*i+j						
						}
						else {
							if (trityp == 3 && (j+k) > i) {   // j==k
								res=2*j+i;
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
	  public static void main(String[] args) {
			TritypeV6 p = new TritypeV6();
			System.out.println(p.caller(2, 1, 2));		
		}
}
