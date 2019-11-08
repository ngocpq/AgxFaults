

/*
 * Find the maximum and minimum of six values.
 */
public class Maxmin6varKO5{
    
    /*@ 
      @ ensures ( (max >= a) && (max >= b) && (max >= c) && (max >= d) && (max >= e) && (max >= f) && (min <= a) && (min <= b) && (min <= c) && (min <= d) && (min <= e) && (min <= f) );
      @*/
    public void maxmin (int a, int b, int c, int d, int e, int f) {
	int max;
        int min;
	if ((a>b) && (a>c) && (a>d) && (a>e) && (a>f)){
           max=a;
           
           if ((b<c) && (b<d) && (b<e) && (b<f)){
             min=b;
           }
           else{
              if ((c<d) && (c<e) && (c<f)){
                 min=c;
              }
              else{
                  if ((d<e) && (d<f)){
                     min=d;
                  }
                  else{
                     if (e<f){  
                         min=e;
                     }
                     else{
                         min=f;
                     }
                  }
              }  
           }
           
	}else{ 
	    if ((b>c) && (b<d) && (b>e) && (b>f)){ // error, the instruction should be (b>d)
	       max=b;
        
               if ((a<c) && (a<d) && (a<e) && (a<f)){
                 min=a;
               }else{
                   if ((c<d) && (c<e) && (c<f)){
                     min=c;
                   }
                   else{
                      if ((d<e) && (d<f)){
                         min=d; 
                      }
                      else{
                         if (e<f){
                            min=e;
                         }
                         else{
                            min=f;
                         }
                      }
                   }  
               }
               
            }    
	    else{
		if ((c>d) && (c>e) && (c>f)){ 
	           max=c;
        
                   if ((a<b) && (a<d) && (a<e) && (a<f)){
                      min=a;
                   }else{
                       if ((b<d) && (b<e) && (b<f)){
                         min=b;
                       }
                       else{
                          if ((d<e) && (d<f)){
                            min=d; 
                          }
                          else{
                             if (e<f){
                               min=e;
                             }
                             else{
                               min=f;
                             }
                          }
                       }  
                   }
           
		}
		else{
		    if ((d>e) && (d>f)){
		       max=d;
        
                       if ((a<b) && (a<c) && (a<e) && (a<f)){
                          min=a;
                       }else{
                           if ((b<c) && (b<e) && (b<f)){
                             min=b;
                           }
                           else{
                              if ((c<e) && (c<f)){
                                min=c; 
                              }
                              else{
                                 if (e<f){
                                   min=e;
                                 }
                                 else{
                                   min=f;
                                 }
                              }
                           }  
                        }
		    }
		    else{
			if (e>f){
			    max=e;
        
                            if ((a<b) && (a<c) && (a<d) && (a<f)){
                               min=a;
                            }else{
                                if ((b<c) && (b<d) && (b<f)){
                                   min=b;
                                }
                                else{
                                   if ((c<d) && (c<f)){
                                      min=c; 
                                   }
                                   else{
                                      if (d<f){
                                         min=d;
                                      }
                                      else{
                                         min=f;
                                      }
                                   }
                                }  
                             }       
			}
			else{
			    max=f;
        
                            if ((a<b) && (a<c) && (a<d) && (a<e)){
                               min=a;
                            }else{
                                if ((b<c) && (b<d) && (b<e)){
                                   min=b;
                                }
                                else{
                                   if ((c<d) && (c<e)){
                                      min=c; 
                                   }
                                   else{
                                      if (d<e){
                                         min=d;
                                      }else{
                                         min=e;
                                      }
                                   }
                                }  
                             } 
			}
		    }
		}
	    }
        }             
			//System.out.println("min: "+min+", max: "+max);
    }
    public static void main(String[] args) {
    	Maxmin6varKO5 p = new Maxmin6varKO5();
  		p.maxmin(-3,4,-4,-2,-1,-2);
  	}
}
