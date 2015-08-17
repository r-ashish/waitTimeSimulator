import static java.lang.Math.*;
public class heap3
{
    private event[] array;
    private int len = 0;
    heap3(int k)
    {
        array = new event[k];
    }
    public boolean isEmpty()
    {
        return len == 0;
    }
    public event get(int i)
    {
        return array[i];
    }
    public event pop()
    {
        event temp = array[0];
        delete(array[0]);
        return temp;
    }
    
    private void percolateUp(int init)
    {
        int i = init;
        while(i > 0 && array[i].time < array[(i-1)/2].time)
        {
            event tempNode = array[i];
            array[i] = array[(i-1)/2];
            array[(i-1)/2] = tempNode;
            i = (i-1)/2;
        }
    }
    public void insert(event node)
    {
        array[len] = node;
        percolateUp(len);
        len += 1;
    }
    public int search(event node)
    {
        for(int i = 0; i < len; i++)
        {
            if(array[i].time==node.time) return i;
        }
        return 0;
    }
    public void delete(event node)
    {
        int i = search(node);
        array[i] = array[len-1];
        array[len-1] = null;
        len -= 1;
        event current = array[i];
        while(2*i+1 < len-1 && current.time > min(array[2*i+1].time,array[2*i+2].time))
        {
            if(array[2*i+1].time <= array[2*i+2].time)
            {
                array[i] = array[2*i+1];
                array[2*i+1] = current;
                current = array[2*i+1];
                i = 2*i+1;
            }
            else
            {
                array[i] = array[2*i+2];
                array[2*i+2] = current;
                current = array[2*i+2];
                i = 2*i+2;
            }
        }
        if(2*i+1 == len-1)
        {
            array[i] = array[2*i+1];
            array[2*i+1] = current;
            i = 2*i + 1;
        }
        percolateUp(i);
    }
    void print()
    {
        for(int i = 0; i < len;i++)
        {
            System.out.println(i+":"+array[i].time);
        }
    }
    event minm()
    {
        return array[0];
    }
}
