public class tcas {
	public static int OLEV=600;
	public static int MAXALTDIFF=600;
	public static int MINSEP=300;
	public static int NOZCROSS=100;
	

	public static int Cur_Vertical_Sep;
	public static boolean High_Confidence;
	public static boolean Two_of_Three_Reports_Valid;

	public static int Own_Tracked_Alt;
	public static int Own_Tracked_Alt_Rate;
	public  static int Other_Tracked_Alt;

	public static int Alt_Layer_Value;		/* 0, 1, 2, 3 */
	//public static int[] Positive_RA_Alt_Thresh;
	static int Positive_RA_Alt_Thresh_0;
	static int Positive_RA_Alt_Thresh_1;
	static int Positive_RA_Alt_Thresh_2;
	static int Positive_RA_Alt_Thresh_3;

	public static int Up_Separation;
	public  static int Down_Separation;
	

	/* state variables */
	public  static int Other_RAC;			/* NO_INTENT, DO_NOT_CLIMB, DO_NOT_DESCEND */
	public static int NO_INTENT=0;
	public static int DO_NOT_CLIMB= 1;
	public static int DO_NOT_DESCEND=2;
	
	public static int Other_Capability;		/* TCAS_TA, OTHER */
	public static int TCAS_TA=1;
	public static int OTHER=2;
	
	public static int Climb_Inhibit;		/* true/false */
	
	public static int UNRESOLVED=0;
	public static int UPWARD_RA=1;
	public static int DOWNWARD_RA=2;
	void initialize()
	{
	    Positive_RA_Alt_Thresh_0= 400;
	    Positive_RA_Alt_Thresh_1= 500;
	    Positive_RA_Alt_Thresh_2= 640;
	    Positive_RA_Alt_Thresh_3= 740;
	}
	int ALIM ()
	{
		
			return Positive_RA_Alt_Thresh_0; //error: 
		
	}

	 int  Inhibit_Biased_Climb ()
	{
		if(Climb_Inhibit >0)
			return Up_Separation + NOZCROSS;
		else 
			return Up_Separation;
	    //return (Climb_Inhibit ? Up_Separation + NOZCROSS : Up_Separation);
	}

	 boolean Non_Crossing_Biased_Climb()
	{
	    int upward_preferred;
	    int upward_crossing_situation;
	    boolean result;
	    if(Inhibit_Biased_Climb() > Down_Separation){
	    	upward_preferred=1;
	    }
	    else{
	    	upward_preferred=0;
	    }
	    if (upward_preferred!=0)
	    {
	    	
		result = !(Own_Below_Threat()) || ((Own_Below_Threat()) && (!(Down_Separation >= ALIM()))); 
	    }
	    else
	    {	
		result = Own_Above_Threat() && (Cur_Vertical_Sep >= MINSEP) && (Up_Separation >= ALIM());
	    }
	    return result;
	}

	boolean Non_Crossing_Biased_Descend()
	{
	    int upward_preferred;
	    int upward_crossing_situation;
	    boolean result;
	    if(Inhibit_Biased_Climb() > Down_Separation){
	    	upward_preferred=1;
	    }
	    else{
	    	upward_preferred=0;
	    }
	    //upward_preferred = Inhibit_Biased_Climb() > Down_Separation;
	    if (upward_preferred!=0)
	    {
		result = Own_Below_Threat() && (Cur_Vertical_Sep >= MINSEP) && (Down_Separation >= ALIM());
	    }
	    else
	    {
		result = !(Own_Above_Threat()) || ((Own_Above_Threat()) && (Up_Separation >= ALIM()));
	    }
	    return result;
	}

	 boolean Own_Below_Threat()
	{
	    return (Own_Tracked_Alt < Other_Tracked_Alt);
	}

	 boolean Own_Above_Threat()
	{
	    return (Other_Tracked_Alt < Own_Tracked_Alt);
	}

	 int alt_sep_test()
	{
	    boolean enabled, tcas_equipped, intent_not_known;
	    boolean need_upward_RA, need_downward_RA;
	    int alt_sep;

	    enabled = High_Confidence && (Own_Tracked_Alt_Rate <= OLEV) && (Cur_Vertical_Sep > MAXALTDIFF);
	    tcas_equipped = Other_Capability == TCAS_TA;
	    intent_not_known = Two_of_Three_Reports_Valid && Other_RAC == NO_INTENT;
	    
	    alt_sep = UNRESOLVED;
	    
	    if (enabled && ((tcas_equipped && intent_not_known) || !tcas_equipped))
	    {
		need_upward_RA = Non_Crossing_Biased_Climb() && Own_Below_Threat();
		need_downward_RA = Non_Crossing_Biased_Descend() && Own_Above_Threat();
		if (need_upward_RA && need_downward_RA)
	        /* unreachable: requires Own_Below_Threat and Own_Above_Threat
	           to both be true - that requires Own_Tracked_Alt < Other_Tracked_Alt
	           and Other_Tracked_Alt < Own_Tracked_Alt, which isn't possible */
		    alt_sep = UNRESOLVED;
		else if (need_upward_RA)
		    alt_sep = UPWARD_RA;
		else if (need_downward_RA)
		    alt_sep = DOWNWARD_RA;
		else
		    alt_sep = UNRESOLVED;
	    }
	    
	    return alt_sep;
	}

        public static void main(String[] argv) {
            //int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0;
            int argc=argv.length+1;
	    if(argc<13){
		System.out.println("Error: Command line arguments are");
		System.out.println("Cur_Vertical_Sep, High_Confidence, Two_of_Three_Reports_Valid");
		System.out.println("Own_Tracked_Alt, Own_Tracked_Alt_Rate, Other_Tracked_Alt");
		System.out.println("Alt_Layer_Value, Up_Separation, Down_Separation");
		System.out.println("Other_RAC, Other_Capability, Climb_Inhibit");
		System.exit(1);
	    }
            tcas newtcas=new tcas();
            newtcas.initialize();
            newtcas.Cur_Vertical_Sep =Integer.parseInt(argv[0]+"");
	    if(Integer.parseInt(argv[1]+"")==0){
	    	newtcas.High_Confidence=false;
	    }
	    else{
	    	newtcas.High_Confidence=true;
	    }
		if(Integer.parseInt(argv[2]+"")==0){
	    	newtcas.Two_of_Three_Reports_Valid=false;
	    }
	    else{
	    	newtcas.Two_of_Three_Reports_Valid=true;
	    }
	    newtcas.Own_Tracked_Alt = Integer.parseInt(argv[3]+"");
	    newtcas.Own_Tracked_Alt_Rate = Integer.parseInt(argv[4]+"");
	    newtcas.Other_Tracked_Alt = Integer.parseInt(argv[5]+"");
	    newtcas.Alt_Layer_Value = Integer.parseInt(argv[6]+"");
	    newtcas.Up_Separation = Integer.parseInt(argv[7]+"");
	    newtcas.Down_Separation = Integer.parseInt(argv[8]+"");
	    newtcas.Other_RAC = Integer.parseInt(argv[9]+"");
	    newtcas.Other_Capability = Integer.parseInt(argv[10]+"");
	    newtcas.Climb_Inhibit = Integer.parseInt(argv[11]+"");
            //System.out.println("begin");
	    System.out.println(newtcas.alt_sep_test());
	}

}

