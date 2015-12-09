package stackoverflow;

/**
 * @author v.chibrikov
 */
public class DoublyLinkedList {
    Node head;
    int size = 0;

    public void insertionSort(int p) {
        Node n = new Node(p);
        Node curr = head;

        if (head == null) {
            head = n;
        } else {
            if (n.getProb() <= curr.getProb()) {
                n.setNext(curr);
                curr.setPrev(n);
                head = n;
            } else if (n.getProb() > curr.getProb()) {
                while (n.getProb() > curr.getProb() && curr.getNext() != null) {
                    curr = curr.getNext();
                }

                if (n.getProb() <= curr.getProb()) {
                    n.setNext(curr);
                    n.setPrev(curr.getPrev());
                    if (curr.getPrev() != null)
                        curr.getPrev().setNext(n);
                    curr.setPrev(n);
                } else if (n.getProb() > curr.getProb()) {
                    n.setNext(curr.getNext());
                    n.setPrev(curr);
                    if (curr.getNext() != null)
                        curr.getNext().setPrev(n);
                    curr.setNext(n);
                }
            }
        }
        size++;
    }

    public void printSortedList() {
        Node curr = head;

        while (curr != null) {
            System.out.println(curr.getProb());
            curr = curr.getNext();
        }
        System.out.println('\n');
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.insertionSort(10);
        list.insertionSort(5);
        list.insertionSort(5);
        list.insertionSort(60);
        list.insertionSort(5);
        list.insertionSort(10);
        list.insertionSort(15);
        list.insertionSort(8);

        list.printSortedList();
    }

    class Node {
        int p;
        Node next;
        Node prev;

        Node(int p) {
            this.p = p;
        }

        int getProb() {
            return p;
        }

        void setNext(Node next) {
            this.next = next;

        }

        void setPrev(Node prev) {
            this.prev = prev;

        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }
    }
}
