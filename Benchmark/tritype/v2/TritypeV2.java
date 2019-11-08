/* program for triangle classification 
 * returns 1 if (i,j,k) are the sides of any triangle
 * returns 2 if (i,j,k) are the sides of an isosceles triangle
 * returns 3 if (i,j,k) are the sides of an equilateral triangle
 * returns 4 if (i,j,k) are not the sides of a triangle 
 * an error has been inserted in the condition line 53
 * when (i,j,k) = (2,2,4) returns 2 while it would return
 *    4 since the triangular inequality 2+2>4 is not verified
 */
class TritypeV2 {
	/*@ requires 
          @ ((i ==2) && (j == 2) && (k ==4));
	  @ ensures
	  @	(((i+j) <= k || (j+k) <= i || (i+k) <= j) ==> (\result == 4))
	  @  && ((!((i+j) <= k || (j+k) <= i || (i+k) <= j) && (i==j && j==k)) ==> (\result == 3))
	  @  && ((!((i+j) <= k || (j+k) <= i || (i+k) <= j) && !(i==j && j==k) && (i==j || j==k || i==k)) ==> (\result == 2))
	  @  && ((!((i+j) <= k || (j+k) <= i || (i+k) <= j) && !(i==j && j==k) && !(i==j || j==k || i==k)) ==> (\result == 1));
	  @*/
	static int caller (int i, int j, int k) {
		int trityp;
		if (i == 0 || j == 0 || k == 0) {
			trityp = 4;		
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
				if ((i+j) <= k || (j+k) <= i || (i+k) <= j) {
					trityp = 4;				
				}
				else {
					trityp = 1;
				}			
			}
			else {
				if (trityp > 3) {
					trityp = 3;
				}
				else {
					if (trityp == 1 && (i+j) > k) {
						trityp = 2;
					}
					else {
						
						if (trityp == 1 && (i+k) > j) { // error in the condition : trityp == 1 instead of trityp == 2
							trityp = 2;						
						}
						else {
							if (trityp == 3 && (j+k) > i) {
								trityp = 2;
							}
							else {
								trityp = 4;
							}
						}
					}
				}
			}
		}
		return trityp;
	}	
	public static void main(String[] args) {
		TritypeV2 p = new TritypeV2();
		System.out.println(p.caller(2, 2, 4));		
	}
}
