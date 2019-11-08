/* program that computes the square of the area of a triangle.
It uses the Heron formula as specification. It uses a specific formula to compute the area when the triangle is equilateral and uses some variations of the Heron formula when the triangle is isosceles.
We assume that the sum of the sides is even to guarantee that the expression (i+j+k)/2 used in Heron formula is an integer.

This example illustrates the case of a program with numerical computations
 */

class HeronV2 {

	/*@ requires 
          @ (i ==2 && j == 2 && k ==4);
	  @ ensures
	  @ (res == -1);
	  @*/

	static int caller (int i, int j, int k) {
		int trityp = 0;
		int res = 0;

		if (i == 0 || j == 0 || k == 0) {
			trityp = 4;
			res = 0;
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
				    trityp = 1; // any triangle
				    res = ((i+j+k)/2)*(((i+j+k)/2)-i)*(((i+j+k)/2)-j)*(((i+j+k)/2)-k);
				}			
			}
			else {
			    if (trityp > 3) { // equilateral
				trityp = 3;
				res = (3*i*i*i*i)/16;
				}
				else {
				    if (trityp == 1 && (i+j) > k) { // isosceles
						trityp = 2;
						// i==j
						res = ((i+j+k)/2)*(((i+j+k)/2)-i)*(((i+j+k)/2)-i)*(((i+j+k)/2)-k);

					}
					else {
						// error in the condition : trityp == 1 instead of trityp == 2
						if (trityp == 1 && (i+k) > j) {
							trityp = 2;
							// i==k
						        res = ((i+j+k)/2)*(((i+j+k)/2)-i)*(((i+j+k)/2)-j)*(((i+j+k)/2)-i); 
						}
						else {
							if (trityp == 3 && (j+k) > i) {
								trityp = 2;
								// j==k
								res = ((i+j+k)/2)*(((i+j+k)/2)-i)*(((i+j+k)/2)-j)*(((i+j+k)/2)-j);
							}
							else {
								trityp = 4;
								res = -1;

							}
						}
					}
				}
			}
		}
		return res;
	}

}
