import static java.lang.Math.*;
import java.util.Random;

public class simulator
{
    double arrivalRate;
    double serviceRate;
    double std;
    int k;
    
    static int maxCustomers;
    heap1 counterList;
    heap2 customers;
    heap3 eventList;
    double clock;

    double totalWaitTime;
    
    simulator(int n,int K,double r,double mu,double sig)
    {
        maxCustomers = n;
	arrivalRate = r;
	serviceRate = mu;
	std = sig;
	k = K;
    }

    void init ()
    {
        counterList = new heap1(k);
        eventList = new heap3(maxCustomers);
        customers = new heap2(maxCustomers);
        clock = 0.0;
        totalWaitTime = 0;
        double t = 0;
        for(int i = 0; i < maxCustomers; i++)
        {
            t += interArrivalTime();
            eventList.insert(new event(t));
        }
    }
    void simulate ()
    {
        init();
        while (!eventList.isEmpty() || !customers.isEmpty()) 
        {
            if(eventList.isEmpty() || (!customers.isEmpty() && customers.get(0).outTime < eventList.get(0).time))
            {
                clock = customers.get(0).outTime;
                handleDeparture ();
            }
            else
            {
                event e = eventList.pop();
                clock = e.time;
                handleArrival(); 
            }
        }
    }
    
    void handleArrival ()
    {
	double waitTime = counterList.insert(randomServiceTime(),customers,clock);  
        totalWaitTime += waitTime;
    }
    void handleDeparture ()
    {
	heapNode2 c = customers.pop();
        counterList.remove(c.queueNo);
    }
    double avgInter = 0;
    double interArrivalTime()
    {
        Random r = new Random();
        double a = r.nextDouble();
        double ret = (-1.0/arrivalRate)*(log(1-a));
        avgInter += ret;
        return (ret);
    }
    double avgServ = 0;
    static double avgCust = 0;
    double randomServiceTime ()
    {
        Random r = new Random();
        double ret =  1.0/(abs(r.nextGaussian()*std + serviceRate));
        avgServ += ret;
        return ret;
    }
    double getAvg()
    {
        return totalWaitTime / maxCustomers ;
    }
    public String toString ()
    {
        String results = "Simulation results:";
        double avgWaitTime = totalWaitTime/maxCustomers ;
        results += "\n  avgInter : "+avgInter/maxCustomers;
        results += "\n  avgServ : "+avgServ/maxCustomers;
        results += "\n  avgCust : "+avgCust/maxCustomers;
        results += "\n  avgWait : " + avgWaitTime;
        results += "\n  avgWait/avgCust: " + avgWaitTime/(avgCust/maxCustomers);
        return results;
    }
}
