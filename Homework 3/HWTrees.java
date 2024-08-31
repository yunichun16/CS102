//This code was adopted from Data Structures and Algorithms in Java / Edition 2 by Robert Lafore
// tree.java
// demonstrates binary search tree

//HW3 QUESTION: provide the implementation of the methods below + TEST all your methods in the main by using the menu in the main (see main method)
//Make sure your code works (either compiled in command line (terminal) or in Eclipse. 

import java.io.*;
import java.util.*;               // for Stack class if needed
////////////////////////////////////////////////////////////////
class Node
   {
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   }  // end class Node  
////////////////////////////////////////////////////////////////
class Tree
   {
   private Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
      {                  
         Node current = root;  // Start at the root
         while (current != null && current.iData != key) {  // Continue searching while there are nodes and the key doesn't match
             if (key < current.iData)  // Go left 
                 current = current.leftChild;
             else  // Go right 
                 current = current.rightChild;
         }
         return current;    // found it        
      }  // end find()
// -------------------------------------------------------------
   public void insert(int id, double dd) //this method inserts a node of (id and dd) into the tree. (We are consider a BINARY SEARCH TREE by iData)
      {
         Node newNode = new Node();
         newNode.iData = id;
         newNode.dData = dd;
     
         if (root == null)
             root = newNode;
         else {
             Node current = root;
             Node parent;
             while (true) {
                 parent = current;
                 if (id < current.iData) {  // Go left?
                     current = current.leftChild;
                     if (current == null) {
                         parent.leftChild = newNode;
                         return;
                     }
                 }
                 else {  // Or go right?
                     current = current.rightChild;
                     if (current == null) {
                         parent.rightChild = newNode;
                         return;
                     }
                 }
             }
         }
      }  // end insert()
//////////////////////////////////////////////////////

   public void traverse(int traverseType) //this method is full implemented see below 
      {
       switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot) //implement preOrder traversal
      {
         if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }

      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot) //implement in Order traversal
      {
         if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }

      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot) //implement postOrder traversal
      {
         if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }

      }

///////////////////////////////////////////////////////////////
    public void isBST() //this method will take a tree as an input and will PRINT to the screen if the tree is a BST or NOT.
    {
      if (isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
         System.out.println("The tree is a binary search tree.");
     } else {
         System.out.println("The tree is not a binary search tree.");
     }
   }
 
 private boolean isBSTHelper(Node node, int min, int max) {
     if (node == null) {
         return true;
     }
     if (node.iData <= min || node.iData >= max) {
         return false;
     }
     // Recursively check the subtrees, updating the min and max values
     return isBSTHelper(node.leftChild, min, node.iData) && isBSTHelper(node.rightChild, node.iData, max);
    }

// -------------------------------------------------------------
   public boolean delete(int key) // delete node with given key (iData) (if there are multiple nodes match key with iData you have to delete all of them.
      {                           // (assumes non-empty list)
         Node parent = root;
         Node current = root;
         boolean isLeftChild = true;
     
         while (current.iData != key) {
             parent = current;
             if (key < current.iData) {
                 isLeftChild = true;
                 current = current.leftChild;
             } else {
                 isLeftChild = false;
                 current = current.rightChild;
             }
             if (current == null) {
                 return false;
             }
         }
     
         // Case 1: Node has no children
         if (current.leftChild == null && current.rightChild == null) {
             if (current == root)
                 root = null;
             else if (isLeftChild)
                 parent.leftChild = null;
             else
                 parent.rightChild = null;
         }
         // Case 2: Node has one child
         else if (current.rightChild == null) {
             if (current == root)
                 root = current.leftChild;
             else if (isLeftChild)
                 parent.leftChild = current.leftChild;
             else
                 parent.rightChild = current.leftChild;
         }
         else if (current.leftChild == null) {
             if (current == root)
                 root = current.rightChild;
             else if (isLeftChild)
                 parent.leftChild = current.rightChild;
             else
                 parent.rightChild = current.leftChild;
         }
         return true;

      }  // end delete()

// -------------------------------------------------------------
   public void displayTreeLevels() // this method will display the nodes at each level in the tree. (The method should print the nodes (id) as: Level1:.... - Level2:... 
      {    
         if (root == null) return;
         Queue<Node> queue = new LinkedList<>();
         queue.add(root);
         int level = 0;
     
         while (!queue.isEmpty()) {
             int size = queue.size();
             System.out.print("Level " + ++level + ": ");
             for (int i = 0; i < size; i++) {
                 Node tempNode = queue.poll();
                 System.out.print(tempNode.iData + " ");
                 if (tempNode.leftChild != null) queue.add(tempNode.leftChild);
                 if (tempNode.rightChild != null) queue.add(tempNode.rightChild);
             }
             System.out.println();
         }

      }  // end displayTreeLevels()

// -------------------------------------------------------------

  public void displaymyChilds(int id, double dd) //given a node who idata is id and dd is ddata display it childen in the following way:
  {
   Node current = find(id); // Reuse the find method to locate the node
   if (current == null || current.dData != dd) {
       System.out.println("Node not found.");     //if the node does not have children you display message that the nodes Do not have children. 
       return;
   }

   System.out.println("Node with iData: " + id + " and dData: " + dd);
   if (current.leftChild != null) {
       System.out.println("Left child: iData: " + current.leftChild.iData + ", dData: " + current.leftChild.dData);  //Left child: idata:  dData: 
   } else {
       System.out.println("No left child.");
   }

   if (current.rightChild != null) {
       System.out.println("Right child: iData: " + current.rightChild.iData + ", dData: " + current.rightChild.dData); //Right child: idata: dData:
   } else {
       System.out.println("No right child.");     // or if one of the child is null, then you display a message stating that. 
   }
  }
// -------------------------------------------------------------

public void displayLeaves() //this method will display all the leaves (iData and dData) of all the leaves)
   {
      displayLeavesHelper(root);
   }

private void displayLeavesHelper(Node localRoot) {
    if (localRoot != null) {
        if (localRoot.leftChild == null && localRoot.rightChild == null) {
            System.out.println("Leaf: iData: " + localRoot.iData + ", dData: " + localRoot.dData);
        } else {
            displayLeavesHelper(localRoot.leftChild);
            displayLeavesHelper(localRoot.rightChild);
        }
    }
   
  }

// -------------------------------------------------------------


}  // end class Tree


////////////////////////////////////////////////////////////////

class HWTrees {
   public static void main(String[] args) throws IOException {
       Scanner scanner = new Scanner(System.in);
       // Add nodes to the tree
       Tree theTree = new Tree();

       //... you change these inputs to build the tree, and/or can add other inputs to test the program. 
      //The tree is ordered by iData.  

      theTree.insert(50, 1.5);
      theTree.insert(25, 1.2);
      theTree.insert(75, 1.7);
      theTree.insert(12, 1.5);
      theTree.insert(37, 1.2);
      theTree.insert(43, 1.7);
      theTree.insert(30, 1.5);
      theTree.insert(33, 1.2);
      theTree.insert(87, 1.7);
      theTree.insert(93, 1.5);
      theTree.insert(97, 1.5);

       int choice;
       do {
           System.out.println("\nMenu:");
           System.out.println("1. Traverse");
           System.out.println("2. Check if BST");
           System.out.println("3. Delete");
           System.out.println("4. Display Tree by Levels");
           System.out.println("5. Display My Childs");
           System.out.println("6. Insert a New Node");
           System.out.println("7. Display All the Leaves");
           System.out.println("8. Exit");

           System.out.print("Enter your choice: ");
           choice = scanner.nextInt();

           switch (choice) {
               case 1:
                   System.out.print("Enter type (1=preorder, 2=inorder, 3=postorder): ");
                   int type = scanner.nextInt();
                   theTree.traverse(type);
                   break;
               case 2:
                   theTree.isBST();
                   break;
               case 3:
                   System.out.print("Enter value to delete: ");
                   int value = scanner.nextInt();
                   theTree.delete(value);
                   break;
               case 4:
                   theTree.displayTreeLevels();
                   break;
               case 5:
                   System.out.print("Enter iData of the node to find its children: ");
                   int idata = scanner.nextInt();
                   System.out.print("Enter dData of the node to find its children: ");
                   double ddata = scanner.nextDouble();
                   theTree.displaymyChilds(idata, ddata);
                   break;
               case 6:
                   System.out.print("Enter iData of the new node: ");
                   int newId = scanner.nextInt();
                   System.out.print("Enter dData of the new node: ");
                   double newDd = scanner.nextDouble();
                   theTree.insert(newId, newDd);
                   break;
               case 7:
                   theTree.displayLeaves();
                   break;
               case 8:
                   System.out.println("Exiting...");
                   break;
               default:
                   System.out.println("Invalid choice. Please enter a number between 1 and 8.");
           }
       } while (choice != 8);

       scanner.close();
   }

}  // end class TreeApp

// -------------------------------------------------------------