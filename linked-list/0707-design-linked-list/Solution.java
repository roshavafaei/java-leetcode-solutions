class MyLinkedList {
    
    private class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public int get(int index) {
      if (index < 0 || index >= size)
            return -1;

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.val;  
    }
    
    public void addAtHead(int val) {
        Node node = new Node(val);

        if (head == null)
            head = tail = node;

        else {
            node.next = head;
            head = node;
        }
        size++;
    }
    
    public void addAtTail(int val) {
        Node node = new Node(val);

        if (head == null)
            head = tail = node;

        else {
            tail.next = node;
            tail = node;
        }
        size++;
    }
    
    public void addAtIndex(int index, int val) {
      Node node = new Node(val);
      if (index < 0 || index > size)
          throw new IllegalArgumentException();

      if (index == 0)
          addAtHead(val);

      else if (index == size) {
          addAtTail(val);
      }

      else {
          Node current = head;

          for (int i = 0; i < index - 1; i++) {
              current = current.next;
          }

          node.next = current.next;
          current.next = node;
          size++;
      }  
    }
    
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;
        
        if (head == null)
            throw new IllegalStateException();
        
        if (index == 0) {
            head = head.next;
            size--;
            
            if (size == 0)
                tail = null;
            
            return;
        }
        
        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        
        if (index == size -1)
            tail = current;
        
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
