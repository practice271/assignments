package hw07;


import java.lang.reflect.Array;

/**
 * Created by Jinx on 31.10.2015.
 */

public class OrderedArray<E extends Comparable<? super E>> extends AbstractOrderedList<E>  {

    private E[] result;
    private int size;
    private Class<?> typeHandler;

    public OrderedArray(int size, Comparable<E> first){
        typeHandler = first.getClass();
        result =(E[]) Array.newInstance(typeHandler, size);
        for (int i = 0; i < size; i++){
            result[i] = (E) first;
        }
        this.size = size;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public E get(int i){
        return result[i];
    }

    @Override
    public void add(E elem){
        this.size++;
        E[] temp = (E[]) Array.newInstance(typeHandler, size);
        int place = placeFinder(elem);
        temp [place] = elem;
        System.arraycopy(result,0,temp,0,place);
        System.arraycopy(result,place,temp,place + 1,size - place - 1);
        result = temp;
    }
    private int placeFinder(E elem){
        int i = 0;
        while (elem.compareTo(result[i]) > 0){
         i++;
            if (i > size - 2) return size - 1;
        }
        return i;
    }
}
