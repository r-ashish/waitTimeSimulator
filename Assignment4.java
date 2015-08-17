import java.util.*;
public class Assignment4 
{
    public static void main (String[] argv)
    {
	Scanner in = new Scanner(System.in);
	System.out.print("\nEnter number of billing counters (k) : ");
	int k = in.nextInt();
	System.out.print("Enter the number of customers (N) : ");
	int N = in.nextInt();
	System.out.print("Enter the arrival rate (R) : ");
	double r = in.nextDouble();
	System.out.print("Enter mean of the service time (μ) : ");
	double mu = in.nextDouble();
	System.out.print("Enter std. devn of the service time (σ) : ");
	double sig = in.nextDouble();
	
        System.out.println("Simulation started,please wait for simulation to complete....\n");
        simulator e = new simulator(N,k,r,mu,sig);
        double avg = 0;
	int iter = 5000;
        for(int i = 0;i < iter ; i++)
        {
            e.simulate();
            avg += e.getAvg();
            System.out.print(">>"+(i+1)*100/iter+"% complete\r");
        }
	System.out.println ("Simulation results:" + "\navg Wait time:    "+avg/iter+"\n");
    }
}
