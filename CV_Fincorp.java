import java.util.*;
import java.time.LocalDate;

interface Variables
{
	static Scanner sc=new Scanner(System.in);
    static ArrayList<User_details> details = new ArrayList<User_details>();
    static ArrayList<User_details2> details2 = new ArrayList<User_details2>();
	public static final String RESET = "\033[0m";  // Reset
    public static final String ANSI_HIDDEN = "\033[8m";
    public static final String RED = "\033[0;31m";  // Red
    public static final String GREEN = "\033[0;32m";  // Green
    public static final String YELLOW = "\033[0;33m";  // Yellow
	public static final String BRIGHT_PINK = "\033[38;5;205m"; // Bright pink
	public static final String LIGHT_PINK = "\033[38;5;218m"; // Light pink
    public static final String PURPLE = "\033[0;35m";  // Purple
    public static final String CYAN = "\033[0;36m";  // Cyan
    public static final String ORG = "\033[38;5;208m";  // Orange
    public static final String BLINK = "\033[5m";  //Blink
    public static final String GRAY = "\033[38;5;243m"; // Gray
    public static final String DARK_GRAY = "\033[38;5;239m"; // Dark Gray
    public static final String LIGHT_GRAY = "\033[38;5;250m"; // Light Gray
    public static final String ANSI_ITALIC = "\u001b[3m";
   
   
   
    public static String bold="\u001B[1m"; // 1 for bold
    public static String under="\u001B[21m"; // underline
    public static String backblue="\u001B[104m";   // 101 - 107 background colors
    public static String backcyan="\u001B[105m";
    public static String backpurple="\u001B[106m";

}

abstract class UserLoan implements Variables
{
	abstract double calculateSI(int p, float t, float r);
	abstract double calculateCI(int p, float t, float r);
	abstract double calculateEMI(int p, float t, float r);
	abstract double equate(LocalDate date, int p, int t, float r);
	abstract String equate(int in, LocalDate date, int p, int t, float r);
	private String password="3467kjhv";
	private String loanID = "120820248808@cvf";

	void set_Password(String password)
	{
		this.password = password;
	}
	String get_Password()
	{
		return password;
	}

	void setLoanID(String loanID)
	{
		this.loanID = loanID;
	}
	String getLoanID()
	{
		return loanID;
	}
}

class Loan extends UserLoan 
{
	static Scanner sc = new Scanner(System.in);
	double calculateSI(int p, float t, float r) 
	{
		System.out.println(ORG+"  Rate of Interest for your loan is "+r+"% per annum"+RESET);
		double amt = 0.0;
		double interest = ((p * t * r)/100);
		System.out.print(ORG);
		System.out.printf("  Total Interest need to be paid is Rs.%.2f/-",interest);
		System.out.print(RESET);
		System.out.println();
		amt = p + interest;
		return amt;
	}
	double calculateCI(int p, float t, float r)
	{
		System.out.println(ORG+"  Rate of Interest for your loan is "+r+"% per annum"+RESET);
		double amt = 0.0;
		double interest = p * (Math.pow((1 + r/100), t) - 1);
		System.out.print(ORG);
		System.out.printf("  Total Interest need to be paid is Rs.%.2f/-",interest);
		System.out.print(RESET);
		System.out.println();
		amt = p + interest;
		return amt;
	}
	double calculateEMI(int p, float t, float r)
	{
		float emi;
		r = r / (12 * 100); // one month interest
		t = t * 12; // one month period
		emi = (p * r * (float)Math.pow(1 + r, t)) / (float)(Math.pow(1 + r, t) - 1);
		return emi;
	}
	double equate(LocalDate date, int p, int t, float r)
	{
		int temp = p;
		System.out.println(ORG+"  Time in months to clear your loan is "+t+" months"+RESET);
		System.out.println(ORG+"  Rate of Interest is "+r+"% per month"+RESET);
		
		System.out.println(PURPLE+"  INo."+"	   "+"Date"+" 	 "+"Installment"+"	"+"Interest"+"	"+"Amount"+RESET);
		int install = 0;
		if(p % t == 0)
		{
			install = p/t;
		}
		else
		{
			install = 1 + p/t;
		}
		int ti = 0;
		int ta = 0;
		int pr = 0;
		for(int i = 1; i <= t; i++)
		{
			date = date.plusDays(30);
			int interest = (int)((r * p)/100);
			if(i == t)
			{
				install = temp - pr;
			}
			int amount = install + interest;
			System.out.println(" "+i+"	"+date+"	   "+install+"		  "+interest+"	         "+amount);	
			pr += install;
			p = p - install;
			ti += interest;
			ta += amount;
		}
		System.out.println(YELLOW+"  Total Principle amount need to be paid: Rs."+RESET+pr+"/-");
		System.out.println(YELLOW+"  Total Interest need to be paid: Rs."+RESET+ti+"/-");
		System.out.println(YELLOW+"  Total Amount need to be paid: Rs."+RESET+ta+"/-");
		return ta;
	}
	String equate(int in, LocalDate date, int p, int t, float r)
	{
		System.out.println("I.No."+"	"+" Date "+"    "+"Installment"+"	"+"Interest"+"	"+"Amount");
		int install = p/t;
		int ri = 0;
		int ra = 0;
		int rp = 0;
		for(int i = 1; i <= t; i++)
		{
			date = date.plusDays(30);
			int interest = (int)((r * p)/100);
			int amount = install + interest;
			if(i == in)
			{
				System.out.println(i+"	 "+date+"     "+install+"		"+interest+"		"+amount);
			}
			if(i >= in)
			{
				rp += install;
				ri += interest;
				ra += amount;
			}
			p = p - install;
		}
		System.out.println("Remaining Principle amount need to be paid: Rs."+rp+"/-");
		System.out.println("Remaining Interest need to be paid: Rs."+ri+"/-");
		System.out.println("Remaining Amount need to be paid: Rs."+ra+"/-");
		return "Thank You";
	}
}
class User extends Loan 
{
	void loanDetailsDisplay(int p, UserLoan ul)
	{
		System.out.println();
		System.out.println(PURPLE+"  Please verify your Loan details"+RESET);
		System.out.println();
		System.out.println(ORG+"  Your Principle Loan amount is Rs."+p+"/-"+RESET);
	}
}

class CV_Fincorp extends User
{
	static Random rndm_method = new Random();
	static CV_Fincorp obj = new CV_Fincorp();
	static UserLoan ul = (CV_Fincorp)obj;

	// static int generateCIBILScore()
	// {
	// 	int cbs = rndm_method.nextInt(210);
	// 	cbs += 690;
	// 	return cbs;
	// }

	static int declarePrinciple(int cbs)
	{
		if(cbs < 700)
		{
			return 0;
		}
		else if(cbs <= 725)
		{
			return 50000;
		}
		else if(cbs <= 750)
		{
			return 100000;
		}
		else if(cbs <= 775)
		{
			return 150000;
		}
		else if(cbs <= 800)
		{
			return 200000;
		}
		else if(cbs <= 825)
		{
			return 250000;
		}
		else if(cbs <= 850)
		{
			return 300000;
		}
		else if(cbs <= 875)
		{
			return 500000;
		}
		else
		{
			return 2000000;
		}
	}
	static float decideInterest(int p, int o)
	{
		float rate = 0.0f;
		if((p >= 1000 && p <= 100000) && (o == 1))
		{
			rate = 12.0f;
		}
		else if((p > 100000 && p <= 200000) && (o == 1))
		{
			rate = 11.5f;
		}
		else if((p > 200000 && p <= 300000) && (o == 1))
		{
			rate = 11.0f;
		}
		else if((p > 300000 && p <= 500000) && (o == 1))
		{
			rate = 10.5f;
		}
		else if((p > 500000 && p <= 2000000) && (o == 1))
		{
			rate = 10.25f;
		}
		if((p >= 1000 && p <= 100000) && (o == 2))
		{
			rate = 11.0f;
		}
		else if((p > 100000 && p <= 200000) && (o == 2))
		{
			rate = 10.5f;
		}
		else if((p > 200000 && p <= 300000) && (o == 2))
		{
			rate = 10.0f;
		}
		else if((p > 300000 && p <= 500000) && (o == 2))
		{
			rate = 9.5f;
		}
		else if((p > 500000 && p <= 2000000) && (o == 2))
		{
			rate = 9.0f;
		}
		if((p >= 1000 && p <= 100000) && (o == 3))
		{
			rate = 16.0f;
		}
		else if((p > 100000 && p <= 200000) && (o == 3))
		{
			rate = 15.0f;
		}
		else if((p > 200000 && p <= 300000) && (o == 3))
		{
			rate = 14.0f;
		}
		else if((p > 300000 && p <= 500000) && (o == 3))
		{
			rate = 13.0f;
		}
		else if((p > 500000 && p <= 2000000) && (o == 3))
		{
			rate = 12.0f;
		}
		if((p >= 1000 && p <= 100000) && (o == 4))
		{
			rate = 2.5f;
		}
		else if((p > 100000 && p <= 200000) && (o == 4))
		{
			rate = 2.25f;
		}
		else if((p > 200000 && p <= 300000) && (o == 4))
		{
			rate = 2.00f;
		}
		else if((p > 300000 && p <= 500000) && (o == 4))
		{
			rate = 1.75f;
		}
		else if((p > 500000 && p <= 2000000) && (o == 4))
		{
			rate = 1.50f;
		}
		return rate;
	}
	static float decideFDInterest(int p, float t)
	{
		float rate = 0.0f;
		if((p >= 1000 && p <= 100000) && t == 0.5)
		{
			rate = 3.00f;
		}
		else if((p <= 200000) && t == 0.5)
		{
			rate = 3.25f;
		}
		else if((p <= 300000) && t == 0.5)
		{
			rate = 3.50f;
		}
		else if((p <= 500000) && t == 0.5)
		{
			rate = 3.75f;
		}
		else if((p <= 1000000) && t == 0.5)
		{
			rate = 4.00f;
		}
		else if((p <= 1500000) && t == 0.5)
		{
			rate = 4.25f;
		}
		else if((p <= 2000000) && t == 0.5)
		{
			rate = 4.50f;
		}
		if((p >= 1000 && p <= 100000) && t == 1.0)
		{
			rate = 3.25f;
		}
		else if((p <= 200000) && t == 1.0)
		{
			rate = 3.50f;
		}
		else if((p <= 300000) && t == 1.0)
		{
			rate = 3.75f;
		}
		else if((p <= 500000) && t == 1.0)
		{
			rate = 4.00f;
		}
		else if((p <= 1000000) && t == 1.0)
		{
			rate = 4.25f;
		}
		else if((p <= 1500000) && t == 1.0)
		{
			rate = 4.50f;
		}
		else if((p <= 2000000) && t == 1.0)
		{
			rate = 4.75f;
		}
		if((p >= 1000 && p <= 100000) && t == 1.5)
		{
			rate = 3.50f;
		}
		else if((p <= 200000) && t == 1.5)
		{
			rate = 3.75f;
		}
		else if((p <= 300000) && t == 1.5)
		{
			rate = 4.00f;
		}
		else if((p <= 500000) && t == 1.5)
		{
			rate = 4.25f;
		}
		else if((p <= 1000000) && t == 1.5)
		{
			rate = 4.50f;
		}
		else if((p <= 1500000) && t == 1.5)
		{
			rate = 4.75f;
		}
		else if((p <= 2000000) && t == 1.5)
		{
			rate = 5.00f;
		}
		if((p >= 1000 && p <= 100000) && t == 2.0)
		{
			rate = 4.00f;
		}
		else if((p <= 200000) && t == 2.0)
		{
			rate = 4.20f;
		}
		else if((p <= 300000) && t == 2.0)
		{
			rate = 4.40f;
		}
		else if((p <= 500000) && t == 2.0)
		{
			rate = 4.50f;
		}
		else if((p <= 1000000) && t == 2.0)
		{
			rate = 4.75f;
		}
		else if((p <= 1500000) && t == 2.0)
		{
			rate = 5.00f;
		}
		else if((p <= 2000000) && t == 2.0)
		{
			rate = 5.25f;
		}
		if((p >= 1000 && p <= 100000) && t == 3.0)
		{
			rate = 4.20f;
		}
		else if((p <= 200000) && t == 3.0)
		{
			rate = 4.40f;
		}
		else if((p <= 300000) && t == 3.0)
		{
			rate = 4.60f;
		}
		else if((p <= 500000) && t == 3.0)
		{
			rate = 4.80f;
		}
		else if((p <= 1000000) && t == 3.0)
		{
			rate = 5.00f;
		}
		else if((p <= 1500000) && t == 3.0)
		{
			rate = 5.20f;
		}
		else if((p <= 2000000) && t == 3.0)
		{
			rate = 5.25f;
		}
		if((p >= 1000 && p <= 100000) && t == 5.0)
		{
			rate = 4.25f;
		}
		else if((p <= 200000) && t == 5.0)
		{
			rate = 4.40f;
		}
		else if((p <= 300000) && t == 5.0)
		{
			rate = 4.50f;
		}
		else if((p <= 500000) && t == 5.0)
		{
			rate = 4.75f;
		}
		else if((p <= 1000000) && t == 5.0)
		{
			rate = 4.90f;
		}
		else if((p <= 1500000) && t == 5.0)
		{
			rate = 5.00f;
		}
		else if((p <= 2000000) && t == 5.0)
		{
			rate = 5.25f;
		}
		if((p >= 1000 && p <= 100000) && t == 10.0)
		{
			rate = 4.50f;
		}
		else if((p <= 200000) && t == 10.0)
		{
			rate = 4.60f;
		}
		else if((p <= 300000) && t == 10.0)
		{
			rate = 4.75f;
		}
		else if((p <= 500000) && t == 10.0)
		{
			rate = 5.00f;
		}
		else if((p <= 1000000) && t == 10.0)
		{
			rate = 5.25f;
		}
		else if((p <= 1500000) && t == 10.0)
		{
			rate = 5.50f;
		}
		else if((p <= 2000000) && t == 10.0)
		{
			rate = 5.75f;
		}
		return rate;
	}

	static void collectLoanDetails(int principle,int index)
	{
		System.out.println(ORG+"  CV Fincorp application can provide you Loan upto Rs."+principle+".00/-");
		System.out.println();
		System.out.print(CYAN + "  If you want to continue the loan taking process , please specify Yes/no: " + RESET);
		String loan_specify = Validations.option_valid2();
		details.get(index).loan_holder=loan_specify;
		if (loan_specify.equals("yes") || loan_specify.equals("Yes")) {
			System.out.println(PURPLE + "	Please enter your Loan details	" + RESET);
			System.out.print(CYAN + "  Enter your Principle Loan amount: " + RESET);
			int p = Validations.principle_valid();
			if (p <= principle) {
				details.get(index).principle_amount = p;
				System.out.println(
						GREEN + "  Yes, we as a CV Fincorp can sanction you a loan of Rs." + p + ".00/-" + RESET);
				System.out.println();
				System.out.println(PURPLE + "  Which type of Interest Loan do you need:-" + RESET);
				System.out.println(YELLOW + "  1. Simple Interest");
				System.out.println("  2. Compound Interest");
				System.out.println("  3. Equated Monthly Installment(EMI)");
				System.out.println("  4. Compound Descending Interest Equated Monthly Installment(CDI EMI)" + RESET);
				System.out.print("  ");
				int o = Validations.input();
				details.get(index).loan_type = o;
				float r = decideInterest(p, o);
				details.get(index).interest = r;
				System.out.printf("  Your rate of interest is %.2f", r);
				if (o == 1 || o == 2 || o == 3) {
					System.out.print("% per year");
				} else if (o == 4) {
					System.out.print("% per month");
				}
				System.out.println();
				LocalDate date = LocalDate.now();
				System.out.println("  Loan was Sanctioned or Issued on " + date);
				switch (o) {
					case 1: {
						System.out.print(CYAN + "  Enter Time in months: " + RESET);
						int ti = Validations.months_valid();
						details.get(index).time = ti;
						System.out.println(YELLOW + "  You have selected Simple Interest for your Loan" + RESET);
						obj.loanDetailsDisplay(p, ul);
						float t = ((float) ti) / 12;
						System.out.println(ORG + "  Time in months to clear your loan is " + ti + " months" + RESET);
						double amount = ul.calculateSI(p, t, r);
						details.get(index).total_amount = amount;
						System.out.print(ORG);
						System.out.printf("  Total amount need to be paid is Rs.%.2f/-", amount);
						System.out.print(RESET);
						System.out.println();
						System.out.println();
						System.out.print(CYAN + "  If you want me to sanction the loan, please specify Yes/no: " + RESET);
						loan_specify = Validations.option_valid2();
						details.get(index).loan_holder = loan_specify;
						if (loan_specify.equals("yes") || loan_specify.equals("Yes")) {
							String otp = Validations.generateOTP(4);
							int lfd = (int) (details.get(index).aadhar % 10000);
							String loanID = otp + lfd + "@cvf";
							ul.setLoanID(loanID);
							System.out.print(CYAN + "  Your LoanID is " + RESET);
							System.out.print(ul.getLoanID());
							System.out.println();
							details.get(index).loanID = ul.getLoanID();
							System.out.println(GREEN + "         Your Loan is sanctioned." + RESET);
							System.out.println(YELLOW + "		Thank You for visiting CV Fincorp");
							System.out.println("			Have a Nice Day");
							System.out.println("		      Please Visit Again" + RESET);
							System.out.println();
						}
						else {
							collectLoanDetails(principle,index);
						}
						break;
					}
					case 2: {
						System.out.print(CYAN + "  Enter Time in months: " + RESET);
						int ti = Validations.months_valid();
						details.get(index).time = ti;
						System.out.println(YELLOW + "  You have selected Compound Interest for your Loan" + RESET);
						obj.loanDetailsDisplay(p, ul);
						float t = ((float) ti) / 12;
						System.out.println(ORG + "  Time in months to clear your loan is " + ti + " months" + RESET);
						double amount = ul.calculateCI(p, t, r);
						details.get(index).total_amount = amount;
						System.out.print(ORG);
						System.out.printf("  Total amount need to be paid is Rs.%.2f/-", amount);
						System.out.print(RESET);
						System.out.println();
						System.out.println();
						System.out
								.print(CYAN + "  If you want me to sanction the loan, please specify Yes/no: " + RESET);
						loan_specify = Validations.option_valid2();
						details.get(index).loan_holder = loan_specify;
						if (loan_specify.equals("yes") || loan_specify.equals("Yes")) {
							String otp = Validations.generateOTP(4);
							int lfd = (int) (details.get(index).aadhar % 10000);
							String loanID = otp + lfd + "@cvf";
							ul.setLoanID(loanID);
							System.out.print("  Your LoanID is ");
							System.out.print(ul.getLoanID());
							System.out.println();
							details.get(index).loanID = ul.getLoanID();
							System.out.println(GREEN + "      Your Loan is sanctioned." + RESET);
							System.out.println(YELLOW + "		Thank You for visiting CV Fincorp");
							System.out.println("			Have a Nice Day");
							System.out.println("		      Please Visit Again" + RESET);
							System.out.println();
						}
						else {
							collectLoanDetails(principle,index);
						}
						break;
					}
					case 3: {
						System.out.print(CYAN + "  Enter Time in months: " + RESET);
						int ti = Validations.months_valid();
						details.get(index).time = ti;
						System.out.println(
								YELLOW + "  You have selected Equated Monthly Installment(EMI) for your Loan" + RESET);
						obj.loanDetailsDisplay(p, ul);
						float t = ((float) ti) / 12;
						double emi = ul.calculateEMI(p, t, r);
						details.get(index).total_amount = emi;
						System.out.printf("  Equated Monthly Installment(EMI) per month is Rs.%.2f/-", emi);
						System.out.println();
						System.out.println();
						System.out
								.print(CYAN + "  If you want me to sanction the loan, please specify Yes/no: " + RESET);
						loan_specify = Validations.option_valid2();
						details.get(index).loan_holder = loan_specify;
						if (loan_specify.equals("yes") || loan_specify.equals("Yes")) {
							String otp = Validations.generateOTP(4);
							int lfd = (int) (details.get(index).aadhar % 10000);
							String loanID = otp + lfd + "@cvf";
							ul.setLoanID(loanID);
							System.out.print(CYAN + "  Your LoanID is " + RESET);
							System.out.print(ul.getLoanID());
							System.out.println();
							details.get(index).loanID = ul.getLoanID();
							System.out.println(GREEN + "        Your Loan is sanctioned." + RESET);
							System.out.println(YELLOW + "		Thank You for visiting CV Fincorp");
							System.out.println("			Have a Nice Day");
							System.out.println("		      Please Visit Again" + RESET);
							System.out.println();
						}
						else {
							collectLoanDetails(principle,index);
						}
						break;
					}
					case 4: {
						System.out.print(CYAN + "  Enter Time in months: " + RESET);
						int t = Validations.months_valid();
						details.get(index).time = t;
						System.out.println(YELLOW + "  You have selected Compound Descending Interest Equated Monthly Installment(EMI) for your Loan" + RESET);
						obj.loanDetailsDisplay(p, ul);
						double response = ul.equate(date, p, t, r);
						details.get(index).total_amount = response;
						System.out.print(CYAN + "  If you want me to sanction the loan, please specify Yes/no: " + RESET);
						loan_specify = Validations.option_valid2();
						details.get(index).loan_holder = loan_specify;
						if (loan_specify.equals("yes") || loan_specify.equals("Yes")) {
							String otp = Validations.generateOTP(4);
							int lfd = (int) (details.get(index).aadhar % 10000);
							String loanID = otp + lfd + "@cvf";
							ul.setLoanID(loanID);
							System.out.print("  Your LoanID is ");
							System.out.print(ul.getLoanID());
							System.out.println();
							details.get(index).loanID = ul.getLoanID();
							System.out.println(GREEN + "  Your Loan is sanctioned." + RESET);
							System.out.println(
									CYAN + "  Do you need CDI EMI for any particular month? please specify yes/no: "
											+ RESET);
							String option = Validations.option_valid2();
							if (option.equals("yes") || loan_specify.equals("Yes")) {
								System.out.print(CYAN + "  Enter particular Installment no. " + RESET);
								int in = Validations.installment_valid(t);
								String res = ul.equate(in, date, p, t, r);
								System.out.println();
								System.out.println(PURPLE + "	 	" + res + " for visiting CV Fincorp		" + RESET);
								System.out.println(YELLOW + "			Have a Nice Day		");
								System.out.println("	   	      Please Visit Again	" + RESET);
								System.out.println();
							} else {
								System.out.println();
								System.out.println(YELLOW + "	 	Thank you for visiting CV Fincorp	");
								System.out.println("			Have a Nice Day		");
								System.out.println("	  	     Please Visit Again		" + RESET);
								System.out.println();
							}
						}
						else {
							collectLoanDetails(principle,index);
						}
						break;
					}
				}
			} 
			else {
				System.out.println(RED + "  You have exceeded your Loan expectations! Please try again" + RESET);
				System.out.println();
				collectLoanDetails(principle, index);
			}
		}
	}

    public static void main(String[]args)
	{
		details.add(new User_details());
		details2.add(new User_details2());
		
		System.out.println("\n\n");
		System.out.println(CYAN+"                       ********************"+RESET);
		System.out.print(CYAN+"                       *"+RESET);
		System.out.print(bold+ORG+BLINK+"    CV FINCORP    "+RESET);
		System.out.println(CYAN+"*"+RESET);
		System.out.println(CYAN+"                       ********************"+RESET);
		System.out.println(YELLOW+BLINK+"		       Welcome to CV Fincorp	            "+RESET);
		System.out.println(YELLOW+BLINK+ANSI_ITALIC+"		 Thank You for visiting CV Fincorp 	         "+RESET);
		System.out.println();
		boolean b=true;
		while(b)
		{
			System.out.println(ORG+"  Do you want to:- "+RESET);
			System.out.println( "  1. Login");
			System.out.println( "  2. Register");
			System.out.println("  3. Exit");
			System.out.print("  ");
			String option = Validations.option_valid();
			if(option.equals("2"))   //registration
			{
				System.out.println();
				System.out.print(ORG+"  Are you looking for \n"+RESET);
				System.out.println("  1. Loan");
				System.out.println("  2. Fixed Deposit");
				System.out.println("  3. Back");
				System.out.print("  ");
				String option1 = Validations.option_valid();
				if(option1.equals("1"))  //loan register
				{
					System.out.println();
					System.out.print(BRIGHT_PINK+"	Welcome to CV Fincorp Loan Registration Process	\n"+RESET);
					System.out.println();
					System.out.print(BRIGHT_PINK + "		Please enter your details	\n" + RESET);
					User_details obj = new User_details();
					obj.collectDetails();
					details.add(obj);
					//details.add(new User_details().User_details.collectDetails());
					System.out.println(GREEN + "  		Your Loan Registration is Successful." + RESET);
				}
                else if(option1.equals("2"))  //fd register
				{
			
					System.out.println("  You are eligible for Registration Process.");
					System.out.print(PURPLE + "	Welcome to CV Fincorp FD Registration Process	\n" + RESET);
					System.out.println();
					System.out.print(BRIGHT_PINK + "	Please enter all the details	\n" + RESET);
					User_details2 obj2 = new User_details2();
					obj2.collectDetails2(obj2);
					details2.add(obj2);
					//details2.add(new User_details2().User_details2.collectDetails2());
					System.out.println(GREEN + "  			Your Fixed Deposit Registration is Successful." + RESET);
				}
				else if(option1.equals("3")){
					continue ;
				}
			}
			else if(option.equals("1"))   //login
			{
				System.out.print(BRIGHT_PINK+"	Welcome to CV Fincorp Login Process	\n"+RESET);
				System.out.print(ORG+"  Choose the option which you want to login \n"+RESET);
				System.out.println("  1. Loan");
				System.out.println("  2. Fixed Deposit");
				System.out.println("  3. Back ");
				System.out.print("  ");
				String option1 = Validations.option_valid();
				if(option1.equals("1")) //Loan Login
				{
					System.out.println();
					System.out.print(BRIGHT_PINK+"	 Welcome to CV Fincorp Loan Login Process	\n"+RESET);
                    System.out.println();
                    System.out.print(YELLOW+"  Please enter your phone number for login: "+RESET);
                    String phone_no=Validations.PHNOvalid();
					int c=0;
					for(int i=0;i<=details.size()-1;i++)
					{
						if(details.get(i).phno.equals(phone_no))
						{
							int index=i;
							System.out.println(GREEN+"  			You are a Registered person."+RESET);
							System.out.println(PURPLE+"           Welcome "+details.get(i).name+RESET);
							String otp = Validations.generateOTP(6);
							System.out.println();
							System.out.println(CYAN + "	Your OTP is    " + RESET + otp);
							System.out.println();
							System.out.println(CYAN + "  Enter your One Time Password(OTP) sent to your mobile number ******"+phone_no.charAt(6)+phone_no.charAt(7)+phone_no.charAt(8)+phone_no.charAt(9)+" to proceed: " + RESET);
							System.out.print("  ");
							String yourotp = new Validations().OTP_valid(otp);
							if(otp.equals(yourotp))
							{
								System.out.println();
								System.out.println(BRIGHT_PINK+"	Welcome to CV Fincorp Loan Queries Window	"+RESET);
								
								if(details.get(i).loan_holder.equals("no")||details.get(i).loan_holder.equals("No"))
								{
									int cibilScore = details.get(i).cbs;
									System.out.println(ORG+"  Your CIBIL Score is "+RESET+cibilScore);
									int principle = declarePrinciple(cibilScore);
									if(principle == 0)
									{
										System.out.println(RED+"  Sorry, We as a CV Fincorp cannot sanction you a loan as your CIBIL Score is Poor"+RESET);
										System.out.println();
									}
									else
									{
										collectLoanDetails(principle,index);
									}
								}
								else 
								{
									System.out.println(BRIGHT_PINK+"  Welcome "+RESET+details.get(i).name);
									System.out.println("  The principal loan amount is " + details.get(i).principle_amount+ ".00/-");
									System.out.println("  Rate of interest is " + details.get(i).interest + "%");
									System.out.printf("  Total interest amount need to be paid is Rs.%.2f/-",details.get(i).total_amount-details.get(i).principle_amount);
									System.out.println();
									System.out.printf("  Total amount need to be paid is Rs.%.2f/-",details.get(i).total_amount);
									System.out.println();
								}
							}
							c=1;
						}
					}
					if(c==0)
					{
						System.out.println(RED+"  You are not a registered person."+RESET);
					}
				}
                else if(option1.equals("2")) //FD Login
				{
					System.out.println();
					System.out.print(BRIGHT_PINK + "	 Welcome to CV Fincorp Deposit Login Process  \n" + RESET);
					System.out.println();
					System.out.print(CYAN + "  Please enter your UserID for login: " + RESET);
					String id = Validations.getuserID();
					int c = 0;
					for (int i = 0; i <= details2.size() - 1; i++) {
						if (details2.get(i).userID.equals(id)) {
							System.out.println(GREEN + "  			You are a Registered person." + RESET);
							System.out.println("  Welcome " + details2.get(i).name);
							System.out.print(CYAN + "  Enter your Password: " + RESET);
							String pass_word = Validations.getpassword();
							if (details2.get(i).password.equals(pass_word)) {
								System.out.println(GREEN + BLINK + "  			Login Successful." + RESET);
								System.out.print("  If you want to change the password, please specify yes/no: ");
								String password_change = Validations.option_valid2();
								if (password_change.equals("yes") || password_change.equals("Yes")) {
									System.out.print(CYAN + "  Enter your new password: " + RESET);
									String pass_word1 = Validations.getpassword();
									ul.set_Password(pass_word1);
									System.out.print("  Your new password is " + ul.get_Password());
									details2.get(i).password = ul.get_Password();
									System.out.println();
								}
								c = 1;
								System.out.println(BRIGHT_PINK+ "	Welcome to CV Fincorp Fixed Deposit (FD) Queries Window	" + RESET);
								if (details2.get(i).deposit_holder.equals("no")|| details2.get(i).deposit_holder.equals("No")) {
									System.out.print(CYAN + "  Enter your FD amount: " + RESET);
									int p = Validations.principle_valid2();
									details2.get(i).principle_amount = p;
									System.out.println();
									System.out.print("  CV Fincorp only allow Fixed Deposits for\n  6 months  or\n  12 months or\n  18 months or\n  24 months or\n  36 months or\n  48 months or\n  60 months or\n  120 months");
									System.out.print(CYAN + "  Enter time in months: " + RESET);
									int t = Validations.months_valid();
									float time = (float) t / 12;
									details2.get(i).time = time;
									float fdr = decideFDInterest(p, time);
									details2.get(i).interest = fdr;
									System.out.println("  For your FD amount Rs." + p + ".00/-" + " and for a period of "+ (int) t + " months");
									System.out.printf("  We as a CV Fincorp will provide you %.2f", fdr);
									System.out.println("% interest per year");
									float in = (float) ((p * time * fdr) / 100);
									details2.get(i).interest_amount = in;
									float amt = p + in;
									details2.get(i).epsum_amount = amt;
									System.out.println("  Your Fixed Deposit amount is Rs."+p+".00/-");
									System.out.println("  Your FD will be matured after " + t + " months");
									System.out.printf("  Then, You will receive an interest of Rs.%.2f/-", in);
									System.out.println();
									System.out.printf("  Then, You will receive epsum amount of Rs.%.2f/-", amt);
									System.out.println();
									System.out.print(CYAN
											+ "  Do you want to Deposit your amount in CV Fincorp please specify yes/no?"
											+ RESET);
									String depositor_specify = Validations.option_valid2();
									details2.get(i).deposit_holder = depositor_specify;
									if (depositor_specify.equals("yes") || depositor_specify.equals("Yes")) {
										System.out.println(YELLOW + BLINK
												+ "  			Your Amount is successfully deposited in CV Fincorp. " + RESET);
										System.out.println(GREEN + "		Thank You for visiting CV Fincorp");
										System.out.println("			Have a Nice Day");
										System.out.println("		      Please Visit Again" + RESET);
										System.out.println();
									}
								} else {
									System.out.println("  Welcome" + details2.get(i).name);
									if (details2.get(i).time == 0.5) {
										System.out.println(
												"  Your FD amount is Rs." + details2.get(i).principle_amount + ".00/-");
										System.out.println("  Time Period is 6 months");
									} else if (details2.get(i).time == 1.0) {
										System.out.println(
												"  For your FD amount Rs." + details2.get(i).principle_amount + ".00/-");
										System.out.println("  Time period is 12 months");
									} else {
										System.out.println(
												"  For your FD amount Rs." + details2.get(i).principle_amount + ".00/-");
										System.out.println("   Time period is " + ((int)details2.get(i).time * 12) + " months");
									}
									System.out.printf("  The interest is %.2f", details2.get(i).interest);
									System.out.println("%");
									System.out.printf("  The interest amount is Rs.%.2f/-",details2.get(i).interest_amount);
									System.out.println();
									System.out.printf("  The epsum amount you will receive is of Rs.%.2f/-",details2.get(i).epsum_amount);
									System.out.println();
								}
							}
						}
					}
					if (c == 0) {
						System.out.println(RED + "  You are not a registered person." + RESET);
					}
				}
				else  if(option1.equals("3")){
					continue;
				}
			}
            else
			{
                b=false;
            }
		}
	}
}

class User_details extends CV_Fincorp
{
	int generateCIBILScore()
	{
		int cbs = (income / 100000) * 8;
		cbs += 690;
		return cbs;
	}

    String name ="Ramanji";
	String age = "25";
	String g = "male";
	String phno = "7418529635";
	String emailID = "ramanji@gmail.com";
	long aadhar=654987321654l;
	String pan="RTYUI6789L";
	String loanID="56781654@cvf";
	int principle_amount=10000;
	int income = 96000;
	float time=1.0f;
	float interest=2.5f;
	float interest_amount=20000;
	double total_amount=15000;
	int cbs=750;
	int loan_type=2;
	String loan_holder="yes";
    void collectDetails()
	{
		System.out.print(CYAN+"  Please enter your Full Name: "+RESET);
		String name = Validations.name_validation();
		this.name=name;
		System.out.print(CYAN+"  Enter your age(18-72): "+RESET);
		String age = Validations.getageinput();
        this.age=age;
		System.out.print(CYAN+"  Enter your gender(Female-F/f, Male-M/m, Other-O/o): "+RESET);
		String g = Validations.gender_validation();
        this.g=g;
		System.out.print(CYAN+"  Enter your Mobile Number: "+RESET);
		String phno = Validations.PHNOvalid();
		System.out.print(CYAN+"  Enter your Email ID: "+RESET);
		String emailID = Validations.verify_email();
		System.out.print(CYAN+"  Enter your Aadhar Number: "+RESET);
		long aadhar = Validations.aadhar_valid();
		System.out.print(CYAN+"  Enter your PAN card Number: "+RESET);
		String pan = Validations.getpaninput();
		boolean unique = true;
		for(int i=0;i<=details.size()-1;i++)
		{
			if (details.get(i).phno.equals(phno) || details.get(i).aadhar == aadhar
					|| details.get(i).pan.equals(pan) || details.get(i).emailID.equals(emailID)) {
				System.out.println("You are already registered person in Loan process.");

				unique = false;
				break;
			}
		}
		if (unique == true) {

			this.phno = phno;
			this.emailID = emailID;
			this.aadhar = aadhar;
			this.pan = pan;
			System.out.print(CYAN + "  Enter your Annual Income: " + RESET);
			int income = Validations.income_valid();
			this.income = income;
			this.loanID = "";
			int cbs = this.generateCIBILScore();
			this.cbs = cbs;
			this.time = 0.0f;
			this.interest = 0.0f;
			this.principle_amount = 0;
			this.loan_type = 0;
			this.loan_holder = "No";
			System.out.println();
			this.userDetailsDisplay();
			System.out.print(CYAN + "  If your details are correct then say yes/no: " + RESET);
			System.out.print("  ");
			String opt = Validations.option_valid2();
			if (opt.equals("Yes") || opt.equals("yes")) {
			} else {
				this.collectDetails();
			}
		}
		else {
			System.out.println(RED+
				"Atleast one of the four details namely aadhar, pan, phone. no.,email address are already registered person in Loan process.Try with other details"+RESET);
				this.collectDetails();
		}
    }

    void userDetailsDisplay()
	{
		System.out.println();
		System.out.println(PURPLE + "    				Welcome " + this.name);
		System.out.println("");
		System.out.println("  		Please verify your details"+RESET);
		System.out.println();
		System.out.println(YELLOW+"  Your Age is "+this.age+" years");
        System.out.println("  Your Gender is "+this.g);
		System.out.println("  Your Mobile Number is "+this.phno);
		System.out.println("  Your Email ID is "+this.emailID);
		System.out.println("  Your Aadhar Number is "+this.aadhar);
		System.out.println("  Your PAN Card Number is "+this.pan+RESET);
		System.out.println();
	}
}


class User_details2 extends CV_Fincorp
{
    String name="Sruthi";
	String age="24";
	String g = "female";
	String phno = "9418529635";
	String emailID = "sruthi@gmail.in";
	long aadhar=852741987654l;
	String pan="UJMYH6789R";
	int principle_amount=400000;
	float time=2.0f;
	float interest=1f;
	float interest_amount=20000;
	String userID="1234567adb@cvf";
	float epsum_amount=500000f;
	String password = "Sruthi@12";
	String deposit_holder="yes";
    void collectDetails2(User_details2 obj)
	{
		System.out.print(CYAN+"  Please enter your Full Name: "+RESET);
		String name = Validations.name_validation();
		this.name=name;
		System.out.print(CYAN+"  Enter your age(18-72): "+RESET);
		String age = Validations.getageinput();
        this.age=age;
		System.out.print(CYAN+"  Enter your gender(Female-F/f, Male-M/m, Other-O/o): "+RESET);
		String g = Validations.gender_validation();
        this.g=g;
		System.out.print(CYAN+"  Enter your Mobile Number: "+RESET);
		String phno = Validations.PHNOvalid();
		System.out.print(CYAN+"  Enter your Email ID: "+RESET);
		String emailID = Validations.verify_email();
		System.out.print(CYAN+"  Enter your Aadhar Number: "+RESET);
		long aadhar = Validations.aadhar_valid();
		System.out.print(CYAN+"  Enter your PAN card Number: "+RESET);
		String pan = Validations.getpaninput();
		boolean unique = true;
		for(int i=0;i<=details2.size()-1;i++)
		{
			if (details2.get(i).phno.equals(phno) || details2.get(i).aadhar == aadhar
					|| details2.get(i).pan.equals(pan) || details2.get(i).emailID.equals(emailID)) {
				System.out.println("You are already registered person in Loan process.");

				unique = false;
				break;
			}
		}
		if (unique == true) {
			this.phno = phno;
			this.emailID = emailID;
			this.aadhar = aadhar;
			this.pan = pan;
			this.time = 0.0f;
			this.interest = 0.0f;
			this.principle_amount = 0;
			this.deposit_holder = "No";
			System.out.println();
			userDetailsDisplay2();
			UserLoan obj1 = (User_details2) obj;
			System.out.print(CYAN + "  If your details are correct then say yes/no: " + RESET);
			System.out.print("  ");
			String opt = Validations.option_valid2();
			if (opt.equals("Yes") || opt.equals("yes")) {
				String capital = Validations.generate_capital(3);
				String num = Validations.generateOTP(8);
				String userID = num + capital + "@cvf";
				System.out.println(CYAN + "  Your UserID is: " + RESET + userID);
				this.userID = userID;
				System.out.print(CYAN + "  Please set your Password: " + RESET);
				String password = Validations.getpassword();
				System.out.print(CYAN + "  Please confirm your Password: " + RESET);
				String password2 = Validations.confirm_password(password);
				obj1.set_Password(password2);
				this.password = obj1.get_Password();
			} else {
				this.collectDetails2(obj);
			}
		}
		else {
			System.out.println(
				"Atleast one of the four details namely aadhar, pan, phone. no.,email address are already registered person in Loan process.Try with other details");
				this.collectDetails2(obj);
		}
    }

    void userDetailsDisplay2()
	{
		System.out.println();
		System.out.println(PURPLE+"    Welcome "+this.name);
		System.out.println("  Please verify your details"+RESET);
		System.out.println();
		System.out.println(YELLOW+"  Your Age is "+this.age+" years");
        System.out.println("  Your Gender is "+this.g);
		System.out.println("  Your Mobile Number is "+this.phno);
		System.out.println("  Your Email ID is "+this.emailID);
		System.out.println("  Your Aadhar Number is "+this.aadhar);
		System.out.println("  Your PAN Card Number is "+this.pan+RESET);
	}
}

class Validations implements Variables
{

	static String generateOTP(int length) 
	{  
    	String numbers = "0123456789";  
    	Random rndm_method = new Random();  
   	 	char[] otp = new char[length];  
    	for (int i = 0; i < length; i++) 
    	{  
        	otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));  
    	}  
    	return new String(otp);  
	}

	static String generate_capital(int length) 
	{
    	String numbers = "abcdefghijklmnopqrstuvwxyz"; 
    	Random rndm_method = new Random();  
   	 	char[] otp = new char[length];  
    	for (int i = 0; i < length; i++) 
    	{  
        	otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length())); 
    	}  
    	return new String(otp);  
	}

	boolean OTP_verify(String num,String otp)
	{
        int c=1;
        char ch;
        for(int i=0;i<num.length()-1;i++)
        {
    		ch=num.charAt(i);
    		if(ch>='0'&&ch<='9')
			{
        		c++;
    		}
        }	
    	if(c==6&&num.equals(otp))
    	{
    		return true;
    	}
        else
        {
    		return false;
    	}
	}

	String OTP_valid(String otp1)
	{
		String otp=sc.nextLine();
		boolean b=true;
		while(b)
		{
			b=OTP_verify(otp,otp1);
			if(b)
			{
				b=false;
			}
			else
			{
				System.out.println(RED + "  Invalid OTP...\n Please enter valid OTP: " + RESET);
				System.out.print("  ");
				otp=sc.nextLine();
				b=true;
			}
		}
		return otp;
	}

	static boolean principle_verify(String principle)
	{
		if(principle.length()>=5 && principle.length()<=7)
		{
			for (int i = 0; i < principle.length(); i++){
				char ch = principle.charAt(i);
				if (!(ch >= '0' && ch <= '9')) {
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	static int principle_valid()
	{
		String principle=sc.nextLine();
		boolean b=true;
		while(b)
		{
			b=principle_verify(principle);
			if(b)
			{
				b=false;
			}
			else
			{
				b=true;
				System.out.println(RED + "  Enter valid principle amount: " + RESET);
				System.out.print("  ");
				principle=sc.nextLine();
				b=true;
			}
		}
		return Integer.parseInt(principle);
	}

	static int principle_valid2()
	{
		String principle = sc.nextLine();
		boolean b = true;
		while (b) {
			b = principle_verify(principle);
			if (b && Integer.parseInt(principle) >= 1000 && Integer.parseInt(principle) <= 2000000) {
				b = false;
			} else {
				b = true;
				System.out.println(RED + "  Enter valid principle amount(1000-2000000): " + RESET);
				System.out.print("  ");
				principle = sc.nextLine();
				b = true;
			}
		}
		return Integer.parseInt(principle);
	}
	static boolean income_verify(String income)
	{
		if(income.length()>=5 && income.length()<=7)
		{
			for (int i = 0; i < income.length(); i++){
				char ch = income.charAt(i);
				if (!(ch >= '0' && ch <= '9')) {
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	static int income_valid()
	{
		String income=sc.nextLine();
		boolean b=true;
		while(b)
		{
			b=income_verify(income);
			if(b)
			{
				b=false;
			}
			else
			{
				b=true;
				System.out.println(RED + "  Enter valid income amount: " + RESET);
				System.out.print("  ");
				income=sc.nextLine();
				b=true;
			}
		}
		return Integer.parseInt(income);
	}

	static boolean option(String opt)
	{
		if(opt.length()!=1)
			return false ;
		char ch = opt.charAt(0) ;
		if(!(ch>='1' && ch<='4'))
		{
			return false;
		}
		return true;
	}
	static int input()
	{
		String opt;
		while(true)
		{
			opt=sc.nextLine();
			if(option(opt))
			{
				break;
			}
			else
			{
				System.out.println(RED + "  Select the option listed above only..." + RESET);
				System.out.print("  ");
			}
		}
		return Integer.parseInt(opt);
	}

	static boolean months_verify(String years)
	{
		boolean b=true;
		int c=0;
		for(int i=0;i<=years.length()-1;i++)
		{
			char ch=years.charAt(i);
			if((ch>='0' && ch<='9'))
			{
				b=true;
				c++;
			}
			else
			{
				return false;
			}
		}
		if(c==years.length()&& years.length()>0 && years.length()<=3)
		{
			b=true;
		}
		else
		{
			b=false;
		}
		return b;
	}

	static int months_valid()
	{
		String years=sc.nextLine();
		boolean b=true;
		while(b)
		{
			b=months_verify(years);
			if(b&&(Integer.parseInt(years)>=6 && Integer.parseInt(years)<=120))
			{
				b=false;
			}
			else
			{
				System.out.println(CYAN + "  Enter valid Months(6-120): " + RESET);
				System.out.print("  ");
				years=sc.nextLine();
				b=true;
			}
		}
		return Integer.parseInt(years);
	}

	static boolean years_verify2(String years)
	{
		boolean b=true;
		int c=0;
		for(int i=0;i<=years.length()-1;i++)
		{
			char ch=years.charAt(i);
			if((ch>='0' && ch<='9')||ch=='.')
			{
				b=true;
				c++;
			}
			else
			{
				return false;
			}
		}
		if(c==years.length() && years.length()>0 && years.length()<=3)
		{
			b=true;
		}
		else
		{
			b=false;
		}
		return b;
	}

	static float years_valid2()
	{
		String years=sc.nextLine();
		boolean b=true;
		while(b)
		{
			b=years_verify2(years);
			if(b)
			{
				float f = Float.parseFloat(years);
				if(f==0.5f || f==1 || f==1.5f || f==2f || f==3f || f==5f || f==10f)
				{
					b = false;
				}
				else
				{
					System.out.println(PURPLE+"  CV Fincorp only allow Fixed Deposits for"+RESET);
					System.out.println(CYAN+"      0.5 year --> 6 months"+RESET);
					System.out.println(CYAN+"      1 year --> 12 months"+RESET);
					System.out.println(CYAN+"      1.5 year --> 18 months"+RESET);
					System.out.println(CYAN+"      2 years --> 24 months"+RESET);
					System.out.println(CYAN+"      3 years --> 36 months"+RESET);
					System.out.println(CYAN+"      5 years --> 60 months"+RESET);
					System.out.println(CYAN+"      10 years --> 120 months"+RESET);
					System.out.println(YELLOW + "  Enter time in months: " + RESET);
					System.out.print("  ");
					years = sc.nextLine();
					b = true;
				}
			}
			else
			{
				System.out.println(PURPLE+"CV Fincorp only allow Fixed Deposits for"+RESET);
				System.out.println(CYAN+"      0.5 year --> 6 months");
				System.out.println("      1 year --> 12 months");
				System.out.println("      1.5 year --> 18 months");
				System.out.println("      2 years --> 24 months");
				System.out.println("      3 years --> 36 months");
				System.out.println("      5 years --> 60 months");
				System.out.println("      10 years --> 120 months"+RESET);
				System.out.println(YELLOW + "Enter time in months: " + RESET);
				System.out.print("  ");
				years=sc.nextLine();
				b=true;
			}
		}
		return Float.parseFloat(years);
	}


	static boolean installment_verify(String months,String m2)
	{
		boolean b=true;
		int c=0;
		for(int i=0;i<=months.length()-1;i++)
		{
			char ch=months.charAt(i);
			if((ch>='0' && ch<='9'))
			{
				b=true;
				c++;
			}
			else
			{
				return false;
			}
		}
		if(c<=m2.length())
		{
			b=true;
		}
		else
		{
			b=false;
		}
		return b;
	}

	static int installment_valid(int m)
	{
		String months=sc.nextLine();
		String m2=""+m;
		boolean b=true;
		while(b)
		{
			b=installment_verify(months,m2);
			if(b&&(Integer.parseInt(months)<=m))
			{
				b=false;
			}
			else
			{
				System.out.println(RED + "  Enter valid month(1-" + m + "):" + RESET);
				System.out.print("  ");
				months=sc.nextLine();
				b=true;
			}
		}
		return Integer.parseInt(months);
	}


    static boolean option_verify(String option)
	{
		if(option.length()==1)
		{
			char ch=option.charAt(0);
			if(ch=='1'||ch=='2'||ch=='3')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	static String option_valid()
	{
		String opt = sc.nextLine();
		boolean b = true;
		while (b) {
			b = option_verify(opt);
			if (b) {
				b = false;
			} else {
				b = true;
				System.out.println(RED + "  Invalid Choice...\n Please enter valid choice(1 or 2 or 3): " + RESET);
				System.out.print("  ");
				opt = sc.nextLine();
				b = true;
			}
		}
		return opt;
	}

	static boolean option_verify2(String opt)
	{
		if(opt.equals("Yes")||opt.equals("yes")||opt.equals("No")||opt.equals("no"))
        {
            return true;
        }
        else
        {
            return false;
        }
	}

	static String option_valid2()
	{
		String opt = sc.nextLine();
		boolean b = true;
		while (b) {
			b = option_verify2(opt);
			if (b) {
				b = false;
			} else {
				b = true;
				System.out.println(RED + "  Invalid option...\n Please enter valid option(yes/no): " + RESET);
				System.out.print("  ");
				opt = sc.nextLine();
				b = true;
			}
		}
		return opt;
	}
	
	static String name_validation()
	{
		String name;
        while (true) 
		{
            name = sc.nextLine();
			boolean isValid = true;
			char ch ='b';
            for (int i = 0; i < name.length(); i++) 
			{
                ch = name.charAt(i);
                if (!Character.isLetter(ch) && ch != ' ') 
				{
                    isValid = false;
                    break;
                }
            }
            if(name.length()>0){
                ch = name.charAt(0) ;
            }
            if (isValid && name.length() > 0 && !(ch == ' ')) 
			{
                break; 
            } 
            else 
			{
				System.out.println(RED + "  Please enter a valid name containing letters and spaces only: " + RESET);
				System.out.print("  ");
            }
        }
        return name;
	}

	static boolean agevalid(String age)
	{
		if(age.length() != 2)
		{
			return false;
		}	
		for(int i=0;i<=age.length()-1;i++)
		{
			char ch=age.charAt(i);
			if(!(ch>='0' && ch<='9'))
			{
				return false;
			}
			else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
			{
				return false;
			}
		}
		return true;
	}	
	static String getageinput()
	{
		String age ;
		while(true)
		{
			age=sc.nextLine();
			if(agevalid(age))
			{
				int age1=Integer.parseInt(age);
				if(age1<18){
					System.out.println(RED + "  Your age is less than 18..please enter again: " + RESET);
					System.out.print("  ");
				}
				else if(age1>72){
					System.out.println(RED + "  Your age is greater than 72..please enter again: " + RESET);
					System.out.print("  ");
				}
				else{
					break ;
				}
			}
			else
			{
				System.out.println(RED + "  Please Enter a valid age!" + RESET);
				System.out.print("  ");
			}
		}
		
		return age;
	}

	static boolean gender_verification(String gender)
	{
		if(gender.equals("F")||gender.equals("M")||gender.equals("O")||gender.equals("f")||gender.equals("m")||gender.equals("o")||gender.equals("Female")||gender.equals("female")||gender.equals("Male")||gender.equals("male")||gender.equals("other")||gender.equals("Other"))
		{
			return true;
		}
		else{
			return false;
		}
	}

	static String gender_validation()
	{
		boolean b=true;
		String gender=sc.nextLine();
		while(b)
		{
			b=gender_verification(gender);
			if(b)
			{
				b = false;
				if (gender.equals("F") || gender.equals("f")) {
					gender = "Female";
				}
				else if (gender.equals("M") || gender.equals("m")) {
					gender = "Male";
				}
				else {
					gender = "Other";
				}
			}
			
			else
			{
				b=true;
				System.out.println(RED + "  Please enter valid gender: " + RESET);
				System.out.print("  ");
				gender=sc.nextLine();
			}
		}
		return gender;
	}

	static boolean PHNOverify(String num)
	{
        int c=0;
    	for(int i=0;i<=num.length()-1;i++)
		{
            char ch=num.charAt(i);
        	if(ch>='0'&&ch<='9')
			{
               	c++;
            }
        }
        if(c==10&&num.length()==10&&(num.charAt(0)>='6'&&num.charAt(0)<='9'))
		{
            return true;
        }
        else
		{
        	return false;
        }
    }
	static String PHNOvalid()
	{
		String phno=sc.nextLine();
        	boolean b=true;
		while(b)
		{
			b=PHNOverify(phno);
			if(b)
			{
            	b=false;
			}
			else
			{
            	b=true;
				System.out.println(RED + "  Entered phone number is invalid!\n  Please enter valid phone number: " + RESET);
				System.out.print("  ");
				phno=sc.nextLine();
			}
		}
		return phno;
	}

	static boolean validateEmail1(String s)
	{
		int n = s.length();
		String s2 = "";
		String s1="";
		if(n==0)
			return false;
		char ch=s.charAt(0);
		if(!((ch>=65 && ch<=90) || (ch>=97 && ch<=122))){ 
			return false;
		}
        for(int i=0;i<n;i++)
		{
			if(i<n-10)
			{
            	char a=s.charAt(i);
       			if((a>=48 && a<=57) || (a>=65 && a<=90) || (a>=97 && a<=122))
				{
					s1=s1+a;
				}
    			else
				{
					return false;
				}
        	}
			else
			{ 
				s2+=s.charAt(i);
			}
    	}
        if(s2.equals("@gmail.com") && s.length()>=13&&s1.length()>=3)
		{
			return true;
        }
    	else
		{ 
			return false;
		}
    }

	static boolean validateEmail2(String s)
	{
    	int n=s.length();
		String s1 = "";
		String s2="";
		if(n==0)
			return false;
		
		char ch=s.charAt(0);
		if(!((ch>=65 && ch<=90) || (ch>=97 && ch<=122))){ 
			return false;
		}
		for(int i=0;i<n;i++)
		{
			if(i<n-9)
			{
				char a=s.charAt(i);
				if((a>=48 && a<=57) || (a>=65 && a<=90) || (a>=97 && a<=122))
				{
					s2=s2+a;
				}
                else
				{
					return false;
				}
            }
        	else
			{ 
				s1+=s.charAt(i);
			}
        }
    	if(s1.equals("@gmail.in") && s.length()>=12&&s2.length()>=3)
		{
            return true;
        }
    	else
		{ 
			return false;
		}
    }

	static String verify_email()
	{
		boolean b=true,b1=true;
		String email=sc.nextLine();
		while(b||b1)
		{
			b=validateEmail1(email);
			b1=validateEmail2(email);
			if(b||b1)
			{
            	b=false;
				b1=false;
        	}
			else
			{
				System.out.println(RED + "  Enter valid email address: " + RESET);
				System.out.print("  ");
				email=sc.nextLine();
            	b= true;
				b1=true;
			}
		}
		return email;
	}

	static boolean aadhar_verify(String aadhar)
	{
        int c=0;
    	for(int i=0;i<=aadhar.length()-1;i++)
		{
    		char ch=aadhar.charAt(i);
    		if(ch>='0'&&ch<='9')
			{
	        	c++;
            }
        }
        if(c==12&&aadhar.length()==12)
		{
            return true;
        }
    	else
		{
        	return false;
    	}
	}
	static long aadhar_valid()
	{
		String aadhar=sc.nextLine();
        boolean b=true;;
		while(b)
		{
			b=aadhar_verify(aadhar);
			if(b)
			{
            	b=false;
			}
			else
			{
            	b=true;
				System.out.println(RED + "  Entered aadhar number is invalid!\nPlease enter valid aadhar number: " + RESET);
				System.out.print("  ");
				aadhar=sc.nextLine(); 
			}
		}
		return Long.parseLong(aadhar);
	}

	static boolean isvalidpan(String pan)
	{
		if(pan.length()!=10)
		{
			return false ;
		}
		for(int i=0;i<=pan.length()-1;i++)
		{          
			char ch=pan.charAt(i);
			if(i<5 || i==9)
			{
 				if(!(ch>='A'&&ch<='Z'))
				{
					return false;
 				}
			}
			else if(i<9)
			{
 				if(!(ch>='0'&&ch<='9'))
				{
  					return false;
				}
			}
		}
		return true;
	}
	static String getpaninput()
	{
  		String pan;
  		while(true)
		{  
  			pan=sc.nextLine(); 
  			if(isvalidpan(pan))
			{
   				break;
  			}
  			else
			{
				System.out.println(RED + "  Invalid PAN Number....\nplease enter a valid PAN number: " + RESET);
				System.out.print("  ");
  			}
 		}
		return pan;
	}

	static boolean userID_valid(String id)
	{
		for(int i=0;i<=id.length()-1;i++)
		{          
			char ch=id.charAt(i);
 			if(!((ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>='0'&&ch<='9')||ch=='@'))
			{
				return false;
 			}
		}
		return true;
	}

	static String getuserID()
	{
		System.out.println("  You have only three attempts.");
  		String UserID;
		int c=0;
		String count="";
  		while(true)
		{  
  			UserID=sc.nextLine(); 
  			if(userID_valid(UserID))
			{
   				break;
  			}
  			else
			{
				if(c==3){
					System.out.print("  You are reached maximum attempts");
					count+=c;
					return count;
				}
				else{
					System.out.println(RED + "  Invalid UserID....\nplease enter a valid UserID: " + RESET);
					System.out.print("  ");
					c++;
				}
  			}
 		}
		return UserID;
	}

	static boolean password_valid(String id)
	{
		int cap = 0;
		int small = 0;
		int digit = 0;
		int special = 0;
		for(int i=0;i<=id.length()-1;i++)
		{
			char ch = id.charAt(i);
			if (ch >= (char) 33 && ch <= (char) 126) {
				if (ch >= (char) 48 && ch <= (char) 57) {
					digit++;
				} else if (ch <= (char) 65 && ch <= (char) 90) {
					cap++;
				} else if (ch >= (char) 97 && ch <= (char) 122) {
					small++;
				} else {
					special++;
				}
			} else {
				return false;
			}
		}
		if (id.length() >= 6 && id.length() <= 10 && cap >= 1 && small >= 1 && digit >= 1 && special >= 1) {
			return true;
		}
		else {
			return false;
		}
	}

	static String getpassword()
	{
  		String password;
  		while(true)
		{  
  			password=sc.nextLine();
  			if(password_valid(password))
			{
   				break;
  			}
  			else
			{
				System.out.println(RED + "  Invalid Password....\n  please enter a valid Password: " + RESET);
				System.out.print("  ");
				System.out.println(RED +"Atleast one capital letter(A-Z)\n  Atleast one small letter(a-z)\n  Atleast one digit letter(0-9)\n  Atleast one special character(like @,#,$...)"+ RESET);
				System.out.println();
  			}
 		}
		return password;
	}

	static String confirm_password(String p)
	{
  		String password;
  		while(true)
		{  
            System.out.print(ANSI_HIDDEN);
  			password=sc.nextLine();
            System.out.print(RESET);
  			if(confirm_password_valid(password)&&password.equals(p))
			{
   				break;
  			}
  			else
			{
				System.out.println(RED + "  Invalid Password....\n  please enter a valid Password: " + RESET);
				System.out.print("  ");
				System.out.println(RED +"  Password length should be 6-10 characters\n  Atleast one capital letter(A-Z)\n  Atleast one small letter(a-z)\n  Atleast one digit letter(0-9)\n  Atleast one special character(like @,#,$...)"+ RESET);
				System.out.println();
  			}
 		}
		return password;
	}

	static boolean confirm_password_valid(String id)
	{
		int cap = 0;
		int small = 0;
		int digit = 0;
		int special = 0;
		for(int i=0;i<=id.length()-1;i++)
		{
			char ch = id.charAt(i);
			if (ch >= (char) 33 && ch <= (char) 126) {
				if (ch >= (char) 48 && ch <= (char) 57) {
					digit++;
				} else if (ch <= (char) 65 && ch <= (char) 90) {
					cap++;
				} else if (ch >= (char) 97 && ch <= (char) 122) {
					small++;
				} else {
					special++;
				}
			} else {
				return false;
			}
		}
		if (id.length() >= 6 && id.length() <= 10 && cap >= 1 && small >= 1 && digit >= 1 && special >= 1) {
			return true;
		}
		else {
			return false;
		}
	}
}

