import static java.lang.Math.*;
public class heap2 
{
    private heapNode2[] array2;
    private int len = 0;
    public heap2(int k)
    {
        array2 = new heapNode2[k];
    }
    public boolean isEmpty()
    {
        return len == 0;
    }
    heapNode2 find(int x)
    {
        for(int i = 0; i < len; i++)
        {
            if(array2[i].queueNo==x) return array2[i];
        }
        return null;
    }
    
    public heapNode2 get(int i)
    {
        return array2[i];
    }
    public heapNode2 pop()
    {
        heapNode2 temp = array2[0];
        delete(array2[0]);
        return temp;
    }
    
    private void percolateUp(int init)
    {
        int i = init;
        while(i > 0 && array2[i].outTime < array2[(i-1)/2].outTime)
        {
            heapNode2 tempNode = array2[i];
            array2[i] = array2[(i-1)/2];
            array2[(i-1)/2] = tempNode;
            i = (i-1)/2;
        }
    }
    public void insert(heapNode2 node)
    {
        array2[len] = node;
        percolateUp(len);
        len += 1;
    }
    public int search(heapNode2 node)
    {
        for(int i = 0; i < len; i++)
        {
            if(array2[i].outTime==node.outTime) return i;
        }
        return 0;
    }
    public void delete(heapNode2 node)
    {
        int i = search(node);
        array2[i] = array2[len-1];
        array2[len-1] = null;
        len -= 1;
        heapNode2 current = array2[i];
        while(2*i+1 < len-1 && current.outTime > min(array2[2*i+1].outTime,array2[2*i+2].outTime))
        {
            if(array2[2*i+1].outTime <= array2[2*i+2].outTime)
            {
                array2[i] = array2[2*i+1];
                array2[2*i+1] = current;
                current = array2[2*i+1];
                i = 2*i+1;
            }
            else
            {
                array2[i] = array2[2*i+2];
                array2[2*i+2] = current;
                current = array2[2*i+2];
                i = 2*i+2;
            }
        }
        if(2*i+1 == len-1)
        {
            array2[i] = array2[2*i+1];
            array2[2*i+1] = current;
            i = 2*i + 1;
        }
        percolateUp(i);
    }
    void print()
    {
        for(int i = 0; i < len;i++)
        {
            System.out.println(i+":"+array2[i].outTime);
        }
    }
    heapNode2 minm()
    {
        return array2[0];
    }
}
