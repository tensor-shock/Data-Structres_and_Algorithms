// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree x,y,p;
        x=this;
        y=new BSTree(address,size,key);
        while(x.parent!=null)
        {
            x=x.parent;
        }
        p=x;
        x=x.right;
        if(x==null)
        {
            p.right=y;
            y.parent=p;
            return y;
        }
        while(x!=null)
        {
            p=x;
            if(x.key>y.key)
            {
                x=x.left;
                if(x==null)
                {
                    p.left=y;
                    y.parent=p;
                }
            }
            else if(x.key<y.key)
            {
                x=x.right;
                if(x==null)
                {
                    p.right=y;
                    y.parent=p;
                }
            }
            else
            {
                if(y.address<x.address)
                {
                    x=x.left;
                    if(x==null)
                    {
                        p.left=y;
                        y.parent=p;
                    }
                }
                else
                {
                    x=x.right;
                    if(x==null)
                    {
                        p.right=y;
                        y.parent=p;
                    }
                }
            }
        }
        
        return y;
    }

    public boolean Delete(Dictionary e)
    { 
        BSTree x,y,z=this;

        if(z==null)
        {
            return false;
        }
        while(z.parent!=null)
        {
            z=z.parent;
        }
        z=z.right;
        while(z!=null)
        {
            y=z.parent;
            if(z.key==e.key && z.address==e.address && z.size==e.size)
            {
                if(z.left==null && z.right==null)
                {
                    if(y.left==z)
                        y.left=null;
                    else
                        y.right=null;
                }
                else if(z.left==null)
                {
                    x=z.right;
                    x.parent=y;
                    if(y.left==z)
                        y.left=x;
                    else
                        y.right=x;
                }

                else if(z.right==null)
                {
                    x=z.left;
                    x.parent=y;
                    if(y.left==z)
                        y.left=x;
                    else
                        y.right=x;
                }
                else
                {

                    x=z.getNext();
                    z=x;
                    x.Delete(x);

                }
                return true;
            }
            else
            {
                if(e.key<z.key)
                {
                    z=z.left;
                }   
                else if(e.key>z.key)
                {
                    z=z.right;
                }
                else
                {
                    if(e.address<z.address)
                    {
                        z=z.left;
                    }
                    else
                        z=z.right;
                }
            }
        }

        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        BSTree x,y,z;
        z=this;
        if(z==null)
            return null;
        while(z.parent!=null)
        {
            z=z.parent;
        }
        z=z.right;
        if(z==null)
            return null;
        if(exact)
        {
            while(z!=null && z.key!=key)
            {
                if(z.key>key)
                    z=z.left;
                else z=z.right;
            }
            return z;
        }
        else
        {
            //z=this;
            z=z.getFirst();
            while(z!=null)
            {
                if(z.key>=key)
                {
                    return z;
                }
                z=z.getNext();
            }
        }
        return null;
    }

    public BSTree getFirst()
    { 
        BSTree x,y,z=this;
        if(z==null)
            return null;
        while(z.parent!=null)
        {
            z=z.parent;
        }
        z=z.right;
        if(z==null)
            return null;
        while(z.left!=null)
        {
            z=z.left;
        }
        return z;
    }

    public BSTree getNext()
    { 
        BSTree x,y,z=this;
        if(z.right!=null)
        {
            z=z.right;
            while(z.left!=null)
            {
                z=z.left;

            }
            return z;
        }
        else
        {
            while(z.parent.parent!=null)
            {
                //z=z.right;
                y=z.parent;
                if(y.left==z)
                {
                    return y;
                }
                else
                    z=y;
            }
        }
        return null;
    }

    public boolean sanity()
    { 
        //this.child.parent==this
        
        BSTree x=this.getFirst();
        while(x!=null)
        {
            if(x.left!=null && x.left.parent!=x)
                return false;
            if(x.right!=null && x.right.parent!=x)
                return false;
            x=x.getNext();
        }
        if(this.left.parent!=this || this.right.parent!=this)
            return false;
        
        //left of sentinel is null
        x=this;
        while(x.parent!=null)
        {
            x=x.parent;
        }
        if(x.left!=null)
            return false;
        
        //value of sentinel
        if(x.address!=-1 || x.size!=-1 || x.key!=-1)
        return false;

        return true;
    }

}


 


