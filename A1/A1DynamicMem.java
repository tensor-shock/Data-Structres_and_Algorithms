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

        if(ans==null||blockSize<=0)
        return -1;

        int x=ans.address,y=ans.key;
        this.freeBlk.Delete(ans);
        if(y!=blockSize)
        this.freeBlk.Insert(x+blockSize,y-blockSize,y-blockSize);
        this.allocBlk.Insert(x,blockSize,x);
        return x;
    } 
    
    public int Free(int startAddr) {
        
        if(startAddr<0)
            return -1;
        Dictionary ans=this.allocBlk.Find(startAddr,true);

        if(ans==null)
            return -1;
        int x=ans.address,y=ans.size;
        this.allocBlk.Delete(ans);
        this.freeBlk.Insert(x,y,y);
        return 0;
    }
}