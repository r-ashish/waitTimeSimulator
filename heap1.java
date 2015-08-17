import static java.lang.Math.*;
public class heap1
{
    private heapNode1[] array1;
    private int maxLen;
    public heap1(int m)
    {
        maxLen = m;
        array1 = new heapNode1[m];
        for(int i = 1;i <= m; i++)
        {
            array1[i-1] = new heapNode1(0,i);
        }
    }
    heapNode1 get(int i)
    {
        return array1[i];
    }
    heapNode1 pop()
    {
        heapNode1 temp = array1[0];
        delete(0);
        return temp;
    }
    int find(int x)
    {
        for(int i = 0; i < maxLen; i++)
        {
            if(array1[i].queueNo==x) 
            {
                return array1[i].customers;
            }
        }
        return 0;
    }
    void remove(int q)
    {
        for(int i = 0; i < maxLen; i++)
        {
            if(array1[i].queueNo==q)
            {
                decreaseValue(i,1);
                break;
            }
        }
    }
    double insert(double t,heap2 h,double in)
    {
        double w;
        int q = array1[0].queueNo;
        //System.out.print(array1[0].time+" , "+in+" , ");
        if(array1[0].customers > 0) 
        {
            h.insert(new heapNode2(q,in,array1[0].time+t));
            w = array1[0].time - in;
            array1[0].time += t;
        }
        else
        {
            h.insert(new heapNode2(q,in,in+t));
            array1[0].time = t + in;
            w = 0;
        } 
        //System.out.println("service time : "+t+" , "+" waitTime : "+w);
        //System.out.println(w+" ,currentCust : "+array1[0].customers);
        simulator.avgCust += array1[0].customers;
        array1[0].customers += 1;
        percolateDown(0);
        return w;
    }
    
    public int increaseValue(int node,int value,double t)
    {
        int i = node;
        int v = array1[i].queueNo;
        array1[i].customers += value;
        array1[i].time = t;
        percolateDown(i);
        return v;
    }
    public void decreaseValue(int node,int value)
    {
        int i = node;
        array1[i].customers -= value;
        percolateUp(i);
    }
    private void percolateUp(int init)
    {
        int i = init;
        while(i > 0 && array1[i].customers < array1[(i-1)/2].customers)
        {
            heapNode1 tempNode = array1[i];
            array1[i] = array1[(i-1)/2];
            array1[(i-1)/2] = tempNode;
            i = (i-1)/2;
        }
    }
    private int percolateDown(int i)
    {
        heapNode1 current = array1[i];
        while(2*i+1 < maxLen-1 && current.customers > min(array1[2*i+1].customers,array1[2*i+2].customers))
        {
            if(array1[2*i+1].customers <= array1[2*i+2].customers)
            {
                array1[i] = array1[2*i+1];
                array1[2*i+1] = current;
                current = array1[2*i+1];
                i = 2*i+1;
            }
            else
            {
                array1[i] = array1[2*i+2];
                array1[2*i+2] = current;current = array1[2*i+1];
                current = array1[2*i+2];
                i = 2*i+2;
            }
        }
        if(2*i+1 == maxLen-1)
        {
            array1[i] = array1[2*i+1];
            array1[2*i+1] = current;
            i = 2*i + 1;
        }
        return i;
    }
    
    public int search(heapNode1 node)
    {
        for(int i = 0; i < maxLen; i++)
        {
            if(array1[i].customers==node.customers) return i;
        }
        return 0;
    }
    int size()
    {
        return maxLen;
    }
    public void delete(int i)
    {
        int q = array1[i].queueNo;
        array1[i].customers = array1[maxLen-1].customers;
        array1[i].time = array1[maxLen-1].time;
        int x = array1[maxLen-1].queueNo;
        array1[i].queueNo = x;
        array1[maxLen-1].customers = 0;
        array1[maxLen-1].queueNo = q;
        maxLen -= 1;
        percolateDown(i);
        percolateUp(i);
    }
    void print()
    {
        for(int i = 0; i < maxLen;i++)
        {
            System.out.println(i+":"+array1[i].customers+","+array1[i].queueNo);
        }
    }
    heapNode1 minm()
    {
        return array1[0];
    }
}
