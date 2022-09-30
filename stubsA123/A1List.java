// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List successor=this.next;
        A1List newNode= new A1List(address,size,key);

        this.next=newNode;
        newNode.next=successor;
        successor.prev=newNode;
        newNode.prev=this;

        return newNode;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List x,z,y=this;
        if(y==null||d==null)
            return false;
        while(y.next!=null)
        {
            if(y.key!=d.key)
            y=y.next;
            else
            {
                if(y.address==d.address && y.size==d.size)
                {
                    x=y.prev;
                    z=y.next; 
                    x.next=z;
                    z.prev=x;
                    return true;
                }
            }
        }

        y=this;
        while(y.prev!=null)
        {
            if(y.key!=d.key)
            y=y.prev;
            else
            {
                if(y.address==d.address && y.size==d.size)
                {
                    x=y.prev;
                    z=y.next;
                    x.next=z;
                    z.prev=x;
                    return true;
                }
            }
        }

        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List x,y,z=this;
        if(z==null)
            return null;
        if(exact)
        {
            //if(z.next!=null)
            while(z.next!=null)
            {
                if(z.key!=k)
                z=z.next;   
                else
                {
                    return z;
                }
            }
            z=this;
            //if(z.prev!=null)
            while(z.prev!=null)
            {
                if(z.key!=k)
                z=z.prev;   
                else
                {
                    return z;
                }
            }
            return null;
        }
        else
        {
            
            while(z.next!=null)
            {
                if(z.key<k)
                z=z.next;   
                else
                {
                    return z;
                }
            }
            z=this;
            //if(z.prev!=null)
            while(z.prev!=null)
            {
                if(z.key<k)
                z=z.prev;   
                else
                {
                    return z;
                }
            }
            return null;   
        }


        
    }

    public A1List getFirst()
    {
        A1List x=this;
        if(x==null)
            return null;
        //if only header and tailer then null
        if((x.next==null && x.prev.prev==null) || (x.prev==null && x.next.next==null))
            return null;

        while(x.prev.prev!=null)
        {
            x=x.prev;
        }
        return x;
        
    }
    

    public A1List getNext() 
    {
        if(this==null)
            return null;
        A1List x=this;
        if(x.next==null)
        return null;
        return  x.next;
    }

    public boolean sanity()
    {
        //cycle detection
        A1List slow,fast;
        slow=this.getFirst();
        fast=slow;
        while(fast!=null && slow !=null)
        {
            if(fast.next!=null)
            {
                fast=fast.next.next;
            }
            else break;
            slow=slow.next;
            if(fast==slow)
                return false;
        }

        //prev of head, next of tail
        A1List header,tailer;
        header=this;
        tailer=this;

        while(header.prev!=null)
        {
            header=header.prev;
        }
        while(tailer!=null)
        {
            tailer=tailer.next;
        }
        if(header.prev!=null)
            return false;
        if(tailer.next!=null)
            return false;

        //next.prev and prev.next
        if(this.next!=null && this.next.prev!=this)
            return false;
        if(this.prev!=null && this.prev.next!=this)
            return false;

        return true;
    }

}
