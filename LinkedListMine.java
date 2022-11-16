package Project3;

import java.security.InvalidParameterException;

public class LinkedListMine<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    //////Constructors///////
    public LinkedListMine(int size){
        this.size = size;
        head = new Node<E>();
        for(int i = 0; i < size; i++){
            add(null);
        }
    }
    public LinkedListMine(){
        this(0);
    }

    //////Getters and Setters///////
    public int getSize(){
        return this.size;
    }

    public E getData(int index){
        Node<E> resultNode = searchIter(index);
        return resultNode.data;
    }

    public void setData(int index, E data){
        searchIter(index).data = data;
    }

    //////Methods///////
    public void addFront(E data){
        Node<E> newNode = new Node<E>(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }
    public void addEnd(E data){
        Node<E> newNode = new Node<E>(data);
        if(head == null){
            head = newNode;
        }
        else{
            newNode.previous = tail;
            tail.next = newNode; //set previous tail pointer to new tail
        }
        tail = newNode; //set linked-list property tail to new node as tail
        size++;
    }
    public void addIndex(E data, int index){
        if (index == 0){
            addFront(data);
            return;
        }
        Node<E> newNode = new Node<E>(data);
        Node<E> previousNode = searchIter(index-1);
        newNode.next = previousNode.next;
        newNode.previous = previousNode;
        previousNode.next.previous = newNode;
        previousNode.next = newNode;
        size++;
    }
    public void add(E data){
        addEnd(data);
    }

    public E remove(int index){
        if(index == 0){
            E data = head.data;
            head = head.next;
            size--;
            return data;
        }
        Node<E> previousNode = searchIter(index-1);
        E data = previousNode.next.data;
        previousNode.next.next.previous = previousNode;
        previousNode.next = previousNode.next.next;
        size--;
        return data;
    }

    public void removeAll(){
        head = null;
        tail = null;
        size = 0;
    }

    public E[] toArray(){
        Object[] arr = new Object[size];
        Node<E> currentNode = head;
        for(int i = 0; i < size; i++){
            arr[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return (E[]) arr;
    }
//    public String toString(){
//        String str = "";
//        Node<E> current = head;
//
//        return null;
//    }


    ///////////Iterative Search Methods//////////
    private Node<E> searchIter(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if(index > (size/2)){
            return searchBwd(index);
        }
        return searchFwd(index);
    }

    private Node<E> searchIter(E data){
        return searchFwd(data);
    }

    private Node<E> searchFwd(int index){
        if(index >= this.size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = head;
        int counter = 0;
        while(counter != index){ //while loop is cleaner than for loop
            currentNode = currentNode.next;
            counter++;
        }
        return currentNode;

    }
    private Node<E> searchFwd(E data){
        Node<E> currentNode = head;
        int counter = 0;
        while(counter != this.size && currentNode.data != data){ //while loop is cleaner than for loop
            currentNode = currentNode.next;
            counter++;
        }
        if(counter == this.size){
            throw new InvalidParameterException("Data not found");
        }
        return currentNode;
    }
    private Node<E> searchBwd(int index){
        if(index >= this.size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = tail;
        int counter = size;
        while(counter != index){ //while loop is cleaner than for loop
            currentNode = currentNode.previous;
            counter--;
        }
        return currentNode;
    }
    private Node<E> searchBwd(E data){
        Node<E> currentNode = tail;
        int counter = 0;
        while(counter != this.size && currentNode.data != data){ //while loop is cleaner than for loop
            currentNode = currentNode.previous;
            counter++;
        }
        if(counter == this.size){
            throw new InvalidParameterException("Data not found");
        }
        return currentNode;
    }


    ///////////recursive Search Methods///////////////
    private Node<E> searchRecurse(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if (index > (size/2)){
            return searchBwd(tail, index, 0);
        }
        else{
            return searchFwd(tail, index, 0);
        }
    }
    private Node<E> searchRecurse(E data){
        return searchFwd(head, data, 0);
    }

    private Node<E> searchFwd(Node<E> currentNode, int index, int counter){
        if (index == counter){
            return currentNode;
        }
        if (counter == this.size){
            throw new InvalidParameterException();
        }
        return searchFwd(currentNode.next, index, counter+1);
    }

    private Node<E> searchFwd(Node<E> currentNode, E data, int counter){
        if (currentNode.data.equals(data)){
            return currentNode;
        }
        if (counter == this.size){
            throw new InvalidParameterException();
        }
        return searchFwd(currentNode.next, data, counter + 1);
    }

    private Node<E> searchBwd(Node<E> currentNode, int index, int counter){
        if ((this.size - index) == counter){
            return currentNode;
        }
        if (counter == this.size){
            throw new InvalidParameterException();
        }
        return searchBwd(currentNode.previous, index, counter+1);
    }

    private Node<E> searchBwd(Node<E> currentNode, E data, int counter){
        if (currentNode.data.equals(data)){
            return currentNode;
        }
        if (counter == this.size){
            throw new InvalidParameterException();
        }
        return searchBwd(currentNode.previous, data, counter + 1);
    }

    private class Node<E>{
        //Using public so that parent linklist class has access. This DOES NOT open it to anyone using linked-list though.
        public E data;
        public Node<E> next;
        public Node<E> previous;

        public Node(E data){
            this.data = data;
        }
        public Node(){
            this.data = null;
        }
    }
}
