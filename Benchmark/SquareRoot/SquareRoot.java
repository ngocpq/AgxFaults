class SquareRoot{
  /*@ ensures((res*res <= val) && (res+1)*(res+1) > val); 
    @*/
  static int SquareRoot(int val)
    {     
        int i = 1;
        int v = 0;
        int res = 0;
        while (v < val){
             v = v + 2*i + 1;
             i = i + 1; 
        }
        res = i; /*error: the instruction should be res = i - 1 */

        return res;
   }
}
