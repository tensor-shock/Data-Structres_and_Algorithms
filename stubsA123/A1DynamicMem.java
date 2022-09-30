// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }
    
    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        
        Dictionary ans=this.freeBlk.Find(blockSize,false);

        //System.out.println("Allocating\n");
        if(ans==null||blockSize<=0)
        return -1;

        int x=ans.address,y=ans.key;
        //System.out.println("freeBlk post deletion");   
        this.freeBlk.Delete(ans);
        
        if(y!=blockSize)
        {
            //System.out.println("freeBlk post insertion");   
            this.freeBlk.Insert(x+blockSize,y-blockSize,y-blockSize);
                
        }
        //System.out.println("allocBlk post insertion");   
        this.allocBlk.Insert(x,blockSize,x);
        return x;
    } 
    
    public int Free(int startAddr) {
        
        //System.out.println("Freeing");
        if(startAddr<0)
            return -1;
        Dictionary ans=this.allocBlk.Find(startAddr,true);

        if(ans==null)
        {
            
             return -1;
        }   
        int x=ans.address,y=ans.size;
        //System.out.println("allocBlk post deletion");   
        this.allocBlk.Delete(ans);
        //System.out.println("freeBlk post insertion");   
        this.freeBlk.Insert(x,y,y);
        return 0;
    }
}