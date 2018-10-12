package linklist_demo;

public class LinkList_Demo {
    
   

    public static void main(String[] args) {
        LinkList theLinkedList = new LinkList();
        
        theLinkedList.insertFirstLink("Don Quixote", 500);
        theLinkedList.insertFirstLink("A Tale of Two Cities", 200);
        theLinkedList.insertFirstLink("The Lord of the Rings", 150);
        theLinkedList.insertFirstLink("Harry Potter and the Sorcrerer", 300);
        
        theLinkedList.removeFirst();
        
        theLinkedList.display();
        
        theLinkedList.removeLink("The Lord of the Rings");  
        
        System.out.println(theLinkedList.find("The Lord of the Rings").bookName + " Was Found");
        
           
      
    }
    
}
