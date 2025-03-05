package basicFile;

import java.util.*;

class AxineLinkedList <E> implements Iterable{
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
        Node(E element) {
            this.item = element;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (Node<E> x = first; x != null; x = x.next){
            if(x.item.equals(o))
                return true;
        }
        return false;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    public boolean add(E e) {
        if(first == null){
            last = new Node<>(e);
            first = last;
        }else {
            last = last.next = new Node<>(e);
        }
        size++;
        return false;
    }

    public boolean remove(Object o) {
        if (first == null) return false;
        if (first.item.equals(o)) {
            first = first.next;
            size--;
            return true;
        }
        else if (last.item.equals(o)) {
            int i = 0;
            for (Node<E> x = first; x != null; x = x.next) {
                if (i == size - 2) {
                    last = x;
                }
                i++;
            }
        }
        Node<E> prev = first;
        while (prev.next != null) {
            if (prev.next.item.equals(o)) {
                prev.next = prev.next.next;
                size--;
                return true;
            }
            prev = prev.next;
        }
        return false;
    }

    public void clear() {
        first = last = null;
        size = 0;
    }

    public E get(int index) {
        Node<E> element = first;
        for (int i = 0; i < index; i++){
            element = element.next;
        }
        return element.item;
    }

    public E set(int index, E element) {
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            if(i == index){
                x.item = element;
                return x.item;
            }
            i++;
        }
        return null;
    }

    public E remove(int index) {
        remove(get(index));
        return null;
    }

    public int indexOf(Object o) {
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item.equals(o))
                return i;
            i++;
        }
        return -1;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> curr = first;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                E value = curr.item;
                curr = curr.next;
                return value;
            }
        };
    }


    /**
     * Реализация рекурсивного реверса элементов<br>
     * со вспомогательными методами
     *
     * @author  AxineIT
     * */
    public void reverseAll() {
        reverseAllHelper(0, size-1);
    }

    /**
    * Реализация реверса элементов по индексу
     * @param index1 индекс первого элемента
     * @param index2 индекс второго элемента
    * */
    public void reverse(int index1, int index2) {
        Node<E> num1Iteam = null, num2Iteam = null;
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            if (num1Iteam != null && num2Iteam != null)
                break;
            if (i == index1)
                num1Iteam = x;
            if (i == index2)
                num2Iteam = x;
            i++;
        }
        reverseItem(num1Iteam, num2Iteam);
    }
    /**
     * Реверс значений
     * */
    private void reverseItem(Node<E> num1, Node<E> num2){
        E currentIteam = num1.item;

        num1.item = num2.item;
        num2.item = currentIteam;
    }
    /**
     * Вспомогательный метод, для рекурсивного реверса
     * */
    private void reverseAllHelper(int index1, int index2){
        reverse(index1, index2);
        if (index2 > size/2){
            reverseAllHelper(index1+1, index2-1);
        }
    }
}
