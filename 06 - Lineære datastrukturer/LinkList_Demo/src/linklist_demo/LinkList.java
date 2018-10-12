package linklist_demo;
// Link is the newest guy in the town 
// and the newest guy in the town he know is firstLink
public class LinkList {
    
    // firstlink = newest guy in the town
    public Link firstLink;
    
    LinkList() {
        // First link always starts with the null value when creating a link List
        // Default value is null anyway
        firstLink = null;
    }
    
    public boolean isEmpty() {
        return (firstLink == null );
    }
    
    public void insertFirstLink(String bookName, int millionsSold) {
       
        Link newLink = new Link(bookName, millionsSold);
        
        newLink.next = firstLink;
        
        // FirstLink is the last guy moved to the town
        firstLink = newLink;
    }
    
    public Link removeFirst() {
        Link linkReference = firstLink;
        if(!isEmpty()) {
            firstLink = firstLink.next;
        } else {
            System.out.println("Empty LinkedList");
        }
        return linkReference;
    }
    
    public void display() {
        Link theLink = firstLink; 
        while(theLink != null) {
            theLink.display();
            System.out.println("Next Link: " + theLink.next);
            
            theLink = theLink.next;
            
            System.out.println();
        }
    }
    
    public Link find(String bookName) {
        Link theLink = firstLink;
        
        if(!isEmpty()) {
            while(theLink.bookName != bookName) {
                if(theLink.next == null) {
                    return null;
                } else {
                    theLink = theLink.next;
                }
            }
        } else {
            System.out.println("Empty LinkedList");
        }
        return theLink;
    } 
    
    public Link removeLink(String bookName) {
        Link currentLink = firstLink;
        Link previousLink = firstLink;
        
        while(currentLink.bookName != bookName) {
            
            if(currentLink.next == null) {
                return null;
            } else {
                previousLink = currentLink;
                currentLink = currentLink.next;
            }
            
            if(currentLink == firstLink) {
                firstLink = firstLink.next;
            } else {
                previousLink.next = currentLink.next;
            }
        }
        return currentLink;
    }
}
