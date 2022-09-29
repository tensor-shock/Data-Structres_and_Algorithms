// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        
        Dictionary x;
        if(type==2)
            x=new BSTree();
        else 
            x=new AVLTree();
        Dictionary y,z,p,q;
        y=freeBlk.getFirst();
        while(y!=null)
        {
            x.Insert(y.address,y.size,y.address);
            y=y.getNext();
        }   
        y=x.getFirst();
        while(y!=null)
        {
            z=y.getNext();
            if(z!=null)
            {
                if(y.size+y.address==z.address)
                {
                    int a=y.address;
                    int b=y.size+z.size;
                    freeBlk.Delete(y);
                    x.Delete(y);
                    freeBlk.Delete(z);
                    x.Delete(z);
                    freeBlk.Insert(a,b,b);
                    x.Insert(a,b,a);
                }
            }
            y=z;
        }
        return ;
    }
}