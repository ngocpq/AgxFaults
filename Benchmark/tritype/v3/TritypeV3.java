/* program for triangle classification 
 * returns 1 if (i,j,k) are the sides of any triangle
 * returns 2 if (i,j,k) are the sides of an isosceles triangle
 * returns 3 if (i,j,k) are the sides of an equilateral triangle
 * returns 4 if (i,j,k) are not the sides of a triangle
 * 
 
 * an error has been inserted line 31 in the assignment
 * when (i,j,k) = (1,2,1) returns 2 while it would return 4 (not a triangle)
 */

class TritypeV3 {

	/*@ requires 
          @ ((i ==1) && (j == 2) && (k ==1));
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
                                
				trityp = trityp + 1;   /* error in the assignment : trityp = trityp + 1 instead of trityp = trityp + 2 */
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
						if (trityp == 2 && (i+k) > j) {
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
  		TritypeV3 p = new TritypeV3();
  		System.out.println(p.caller(1, 2, 1));		
  	}
}
