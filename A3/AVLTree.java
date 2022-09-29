// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    

    
    public AVLTree rebalance(AVLTree[] xyz, AVLTree[] arr, int numarr) {
    
        AVLTree a,b,c;
        a=xyz[0];
        b=xyz[1];
        c=xyz[2];
        b.right=c;
        c.parent=b;
        b.left=a;
        a.parent=b;
        
        a.left=null;
        c.left=null;
        a.right=null;
        c.right=null;
        int m=0,n=0,o=0,p=0;
        
        
        int i=0;
        while(i<numarr)
        {
            if(arr[i].key==a.key)
            {
                a.left=arr[i];
                m=arr[i].height;
                arr[i].parent=a;
            }
            else if(arr[i].key>c.key)
            {
                c.right=arr[i];
                p=arr[i].height;
                arr[i].parent=c;   
            }
            else if(arr[i].key>b.key)
            {
                c.left=arr[i];
                o=arr[i].height;
                arr[i].parent=c;
            }
            else if(arr[i].key<b.key)
            {
                a.right=arr[i];
                n=arr[i].height;
                arr[i].parent=a;
            }
            i++;
        }
        
        if(m>n)
        {
            a.height=m+1;
            m++;
        }
        else
        {
            a.height=n+1;
            m=n+1;
        }
        
        
        
        if(o>p)
        {
            c.height=o+1;
            o++;
        }
        else
        {
            c.height=p+1;
            o=p+1;
        }
        
        
        
        if(m>o)
        {
            b.height=m+1;    
        }
        else
        {
            b.height=o+1;
        }
        
        return b;

    }

    public int getNewHeight()
    {
        if(this==null)
            return 1;
        if(this.left==null && this.right==null)
            return 1;
        if(this.left==null)
            return this.right.height+1;
        if(this.right==null)
            return this.left.height+1;
        if(this.left.height>this.right.height)
            return this.left.height+1;
        return this.right.height+1;
    }

    public boolean checkAVLHeight()
    {
        if(this==null)
            return true;
        if(this.left==null && this.right==null)
            return true;
        if(this.left==null)
            if(this.right.height>1)
                return false;
        if(this.right==null)
            if(this.left.height>1)
                return false;
        if(this.left.height>this.right.height)
            if(this.left.height-this.right.height>1)
                return false;
        if(this.left.height<this.right.height)
            if(this.left.height-this.right.height<-1)
                return false;   
        return true;
    }

    public void printTree()
    {
        AVLTree x,y,z;
        x=this.getFirst();
        while(x!=null)
        {
            System.out.printf("%d %d %d\n",x.address,x.size,x.key);
            x=x.getNext();
        }
    }

    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree x,y,z,p;
        x=this;
        y=new AVLTree(address,size,key);
        y.height=1;
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
            //this.printTree();
            return y;
        }
        boolean done=false;
        while(!done) 
        {
            p=x;
            //p.height++;
            if(x.key>y.key)
            {
                x=x.left;
                if(x==null)
                {
                    p.left=y;
                    y.parent=p;
                    done =true;
                }
            }
            else if(x.key<y.key)
            {
                x=x.right;
                if(x==null)
                {
                    p.right=y;
                    y.parent=p;
                    done =true;
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
                        done =true;
                    }
                }
                else
                {
                    x=x.right;
                    if(x==null)
                    {
                        p.right=y;
                        y.parent=p;
                        done =true;
                      }
                }
            }
        }
        
        x=y;
        
        while(x.parent!= null && x.parent.parent!=null)
        {
            AVLTree s;
            z=x.parent;
            p=z.parent;
            if(p.parent==null)
            {
                z.height=z.getNewHeight();
                break;
            }
            int lol;
            s=p.parent;
            if(s.left==p)
                lol=0;
            else lol=1;
            z.height=z.getNewHeight();
            p.height=p.getNewHeight();
            int a,b;
            if(p.left==null)
                a=0;
            else a=p.left.height;
            
            if(p.right==null)
                b=0;
            else b=p.right.height;
            
            if(a-b>1 || b-a>1)
            {
                AVLTree arr[]=new AVLTree[3];
                
                arr[0]=x;
                arr[1]=z;
                arr[2]=p;
                for(a=0;a<3;a++)
                {
                    for(b=a+1;b<3;b++)
                    {
                        AVLTree t,r,e;
                        t=arr[a];
                        r=arr[b];
                        if(t.key>r.key)
                        {
                            e=r;
                            arr[b]=t;
                            arr[a]=e;
                        }
                        else if(t.key==r.key)
                        {
                            if(t.address>r.address)
                            {
                                e=r;
                            arr[b]=t;
                            arr[a]=e;
                            }
                        }
                    }
                }

                int num=0;
                AVLTree ba[]=new AVLTree[4];
                
                if(p.left==z && p.right!=null)
                {
                    ba[num]=p.right;
                    num++;
                }
                else if(p.right==z && p.left!=null)
                {
                    ba[num]=p.left;
                    num++;
                }
                if(z.left==x && z.right!=null)
                {
                    ba[num]=z.right;
                    num++;
                }
                else if(z.right==x && z.left!=null)
                {
                    ba[num]=z.left;
                    num++;
                }
                if(x.left!=null)
                {
                    ba[num]=x.left;
                    num++;
                }
                if(x.right!=null)
                {
                    ba[num]=x.right;
                    num++;
                }    
                
                for(a=0;a<num;a++)
                {
                    for(b=a+1;b<num;b++)
                    {
                        AVLTree t,r,e;
                        t=ba[a];
                        r=ba[b];
                        if(t.key>r.key)
                        {
                            e=t;
                            ba[a]=r;
                            ba[b]=e;
                        }
                        else if(t.key==r.key)
                        {
                            if(t.address>r.address)
                            {
                                e=t;
                                ba[a]=r;
                                ba[b]=e;
                            }
                        }
                    }
                }
                
                p=p.rebalance(arr,ba,num);
                if(lol==1)
                {
                    s.right=p;
                }
                else
                    s.left=p;
                p.parent=s;
            }
            x=x.parent;
        }
        //this.printTree();
        return y;  
        
    }

    public boolean Delete(Dictionary e)
    {
        
        AVLTree p,x,y,z=this;
        boolean found=false;
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
            if(z==e)
            {
                found=true;
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
                    int u,i,o;
                    u=x.address;
                    i=x.size;
                    o=x.key;
                    y=x.parent;
                    x.Delete(x);
                    z.address=u;
                    z.size=i;
                    z.key=o;
                }

                p=y;
                if(p!=null && p.parent!=null)
                while(p.parent.parent!=null)
                {
                    p.height=p.getNewHeight();

                    AVLTree s;
                    int lol;
                    s=p.parent;
                    if(s.left==p)
                        lol=0;
                    else lol=1;
                    int a,b;
                    if(p.left==null)
                        a=0;
                    else a=p.left.height;
                    
                    if(p.right==null)
                        b=0;
                    else b=p.right.height;
                    
                    if(a-b>1 || b-a>1)
                    {
                        AVLTree arr[]=new AVLTree[3];
                        
                        int c,d;

                        if(p.left!=null)
                            c=p.left.height;
                        else c=0;

                        if(p.right!=null)
                            d=p.right.height;
                        else d=0;

                        if(c>d)
                            z=p.left;
                        else z=p.right;


                        if(z.left!=null)
                            c=z.left.height;
                        else c=0;

                        if(z.right!=null)
                            d=z.right.height;
                        else d=0;

                        if(c>d)
                            x=z.left;
                        else x=z.right;



                        arr[0]=x;
                        arr[1]=z;
                        arr[2]=p;
                        for(a=0;a<3;a++)
                        {
                            for(b=a+1;b<3;b++)
                            {
                                AVLTree t,r,f;
                                t=arr[a];
                                r=arr[b];
                                if(t.key>r.key)
                                {
                                    f=t;
                                    arr[a]=r;
                                    arr[b]=f;
                                }
                                else if(t.key==r.key)
                                {
                                    if(t.address>r.address)
                                    {
                                        f=t;
                                        arr[a]=r;
                                        arr[b]=f;
                                    }
                                }
                            }
                        }

                        int num=0;
                        AVLTree ba[]=new AVLTree[4];
                        
                        if(p.left==z && p.right!=null)
                        {
                            ba[num]=p.right;
                            num++;
                        }
                        else if(p.right==z && p.left!=null)
                        {
                            ba[num]=p.left;
                            num++;
                        }
                        if(z.left==x && z.right!=null)
                        {
                            ba[num]=z.right;
                            num++;
                        }
                        else if(z.right==x && z.left!=null)
                        {
                            ba[num]=z.left;
                            num++;
                        }
                        if(x.left!=null)
                        {
                            ba[num]=x.left;
                            num++;
                        }
                        if(x.right!=null)
                        {
                            ba[num]=x.right;
                            num++;
                        }    
                        
                        for(a=0;a<num;a++)
                        {
                            for(b=a+1;b<num;b++)
                            {
                                AVLTree t,r,f;
                                t=ba[a];
                                r=ba[b];
                                if(t.key>r.key)
                                {
                                    f=t;
                                    ba[a]=r;
                                    ba[b]=f;
                                }
                                else if(t.key==r.key)
                                {
                                    if(t.address>r.address)
                                    {
                                        f=t;
                                        ba[a]=r;
                                        ba[b]=f;
                                    }
                                }
                            }
                        }

                        p=p.rebalance(arr,ba,num);//check
                        
                        if(lol==0)
                        s.left=p;
                        else
                            s.right=p;
                        p.parent=s;


                    }

                    p=p.parent;                        
                }
                //this.printTree();
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


        //this.printTree();
        return false;
    }
    
    public AVLTree Find(int k, boolean exact)
    { 
        AVLTree x,y,z;
        z=this;
        if(z==null)
          {
            
            return null;
          }  
        while(z.parent!=null)
        {
            z=z.parent;
        }
        z=z.right;
        if(z==null)
            {
                
                return null;
            }
        if(exact)
        {
            while(z!=null && z.key!=k)
            {
                if(z.key>k)
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
                if(z.key>=k)
                {
                    return z;
                }
                z=z.getNext();
            }
        }
        return null;
    }

    public AVLTree getFirst()
    { 
        AVLTree x,y,z=this;
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

    public AVLTree getNext()
    {
        AVLTree x,y,z=this;
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
        //avl height difference condition
        AVLTree x=this.getFirst();
        while(x!=null)
        {
            if(x.left!=null && x.left.parent!=x)
                return false;
            if(x.right!=null && x.right.parent!=x)
                return false;
            if(!x.checkAVLHeight())
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


