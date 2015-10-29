package hw07.javaClasses;

/* OrderedArray made by Guzel Garifullina
   Estimated time  1 hour
   real time       2 hours
*/
import java.lang.reflect.Array;

public class OrderedArray<E extends Comparable<? super E>>
        extends AbstractOrderedList< E >{
    private int size_basic = 100;
    private int size = 0;
    private E[] arr;

    public OrderedArray(int size, E defaultElem) {
        size_basic = Math.max(size_basic, size);
        arr =  (E[]) Array.newInstance(defaultElem.getClass(), size_basic);
        for (int i = 0 ; i < size; i++){
            arr[i] =  defaultElem;
        }
        this.size = size;
    }
    @Override
    public  int	size(){
        return size;
    }
    @Override
    public void add (E elem){
        if (size < size_basic){
            arr[size] = elem;
            size ++;
        }
        else {
            size_basic *= 2;
            E[] arr2 = (E[]) Array.newInstance(arr[0].getClass(), size_basic);
            for (int i = 0 ; i < size; i++){
                arr2[i] = arr[i];
            }
            arr2[size] = elem;
            size ++;
            arr = arr2;
        }
    }
    @Override
    public  E get(int index){
        if ((index < size) && (0 <= index)){
            return arr[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }
     @Override
     public  void set(int index, E element){
         if ((index < size) && (0 <= index)){
             arr[index] = element;
             return;
         }
         throw new ArrayIndexOutOfBoundsException();
     }
    @Override
    public void removeAt(int index){
        if ((index >= size) && (0 > index)){
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i ++){
            arr[i] = arr [i + 1];
        }
        size --;
    }
    @Override
    public void	clear() {
        size = 0;
    }
    @Override
    public void	reverse() {
        E[] arr2 = (E[]) Array.newInstance(arr[0].getClass(), size_basic);
        for (int i = 0 ; i < size; i++){
            arr2[i] = arr[size - i - 1];
        }
        arr = arr2;
    }
}
