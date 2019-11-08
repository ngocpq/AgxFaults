public class RunTCAS {
	
	static int cur_Vertical_Sep = 890;
	static boolean high_Confidence = true;
	static boolean two_of_Three_Reports_Valid = false;
	static int own_Tracked_Alt = 0;
	static int own_Tracked_Alt_Rate = 577;
	static int other_Tracked_Alt = 622;
	static int alt_Layer_Value = 0;
	static int up_Separation = 0;
	static int down_Separation = 284;
	static int other_RAC = 0;
	static int other_Capability = 2;
	static int climb_Inhibit = 1;

	
	public static void main(String[] argv) {
		int argc = argv.length + 1;
		if (argc < 13) {
			System.out.println("Error: Command line arguments are");
			System.out.println("Cur_Vertical_Sep, High_Confidence, Two_of_Three_Reports_Valid");
			System.out.println("Own_Tracked_Alt, Own_Tracked_Alt_Rate, Other_Tracked_Alt");
			System.out.println("Alt_Layer_Value, Up_Separation, Down_Separation");
			System.out.println("Other_RAC, Other_Capability, Climb_Inhibit");
			System.exit(1);
		}
		cur_Vertical_Sep = Integer.parseInt(argv[0] + "");
		if (Integer.parseInt(argv[1] + "") == 0) {
			high_Confidence = false;
		} else {
			high_Confidence = true;
		}
		if (Integer.parseInt(argv[2] + "") == 0) {
			two_of_Three_Reports_Valid = false;
		} else {
			two_of_Three_Reports_Valid = true;
		}
		own_Tracked_Alt = Integer.parseInt(argv[3] + "");
		own_Tracked_Alt_Rate = Integer.parseInt(argv[4] + "");
		other_Tracked_Alt = Integer.parseInt(argv[5] + "");
		alt_Layer_Value = Integer.parseInt(argv[6] + "");
		up_Separation = Integer.parseInt(argv[7] + "");
		down_Separation = Integer.parseInt(argv[8] + "");
		other_RAC = Integer.parseInt(argv[9] + "");
		other_Capability = Integer.parseInt(argv[10] + "");
		climb_Inhibit = Integer.parseInt(argv[11] + "");
		
		System.out.println(runtcas_agx());
	}

	public static int runtcas(int cur_Vertical_Sep, boolean high_Confidence, boolean two_of_Three_Reports_Valid,
			int own_Tracked_Alt, int own_Tracked_Alt_Rate, int other_Tracked_Alt, int alt_Layer_Value, int up_Separation,
			int down_Separation, int other_RAC, int other_Capability, int climb_Inhibit) {

		tcas p = new tcas();
		p.initialize();
		p.Cur_Vertical_Sep = cur_Vertical_Sep;
		p.High_Confidence = high_Confidence;
		p.Two_of_Three_Reports_Valid = two_of_Three_Reports_Valid;
		p.Own_Tracked_Alt = own_Tracked_Alt;
		p.Own_Tracked_Alt_Rate = own_Tracked_Alt_Rate;
		p.Other_Tracked_Alt = other_Tracked_Alt;
		p.Alt_Layer_Value = alt_Layer_Value;
		p.Up_Separation = up_Separation;
		p.Down_Separation = down_Separation;
		p.Other_RAC = other_RAC;
		p.Other_Capability = other_Capability;
		p.Climb_Inhibit = climb_Inhibit;
		int out = p.alt_sep_test();
		checkAssert(out);
		return out;

	}

	private static void checkAssert(int out) {
		assert out == expectedOut;
	}
	private static int expectedOut;

	public static int runtcas_agx() {
		expectedOut = run_goldtcas();
		int out = runtcas(cur_Vertical_Sep, high_Confidence, two_of_Three_Reports_Valid, own_Tracked_Alt,
				own_Tracked_Alt_Rate, other_Tracked_Alt, alt_Layer_Value, up_Separation, down_Separation, other_RAC,
				other_Capability, climb_Inhibit);
		return out;
	}

	static int run_goldtcas() {
		tcas_gold p = new tcas_gold();
		p.initialize();
		p.Cur_Vertical_Sep = cur_Vertical_Sep;
		p.High_Confidence = high_Confidence;
		p.Two_of_Three_Reports_Valid = two_of_Three_Reports_Valid;
		p.Own_Tracked_Alt = own_Tracked_Alt;
		p.Own_Tracked_Alt_Rate = own_Tracked_Alt_Rate;
		p.Other_Tracked_Alt = other_Tracked_Alt;
		p.Alt_Layer_Value = alt_Layer_Value;
		p.Up_Separation = up_Separation;
		p.Down_Separation = down_Separation;
		p.Other_RAC = other_RAC;
		p.Other_Capability = other_Capability;
		p.Climb_Inhibit = climb_Inhibit;

		return p.alt_sep_test();
	}
}

class tcas_gold {
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
	public void initialize()
	{
	    Positive_RA_Alt_Thresh_0= 400;
	    Positive_RA_Alt_Thresh_1= 500;
	    Positive_RA_Alt_Thresh_2= 640;
	    Positive_RA_Alt_Thresh_3= 740;
	}
	int ALIM ()
	{
		if(Alt_Layer_Value==0) 
			return Positive_RA_Alt_Thresh_0;
		else if(Alt_Layer_Value==1) 
			return Positive_RA_Alt_Thresh_1;
		else if(Alt_Layer_Value==2) 
			return Positive_RA_Alt_Thresh_2;
		else 
			return Positive_RA_Alt_Thresh_3;
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

	 public int alt_sep_test()
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

}