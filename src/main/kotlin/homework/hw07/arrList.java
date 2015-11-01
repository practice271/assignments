package homework.hw07;

public class arrList<T extends Comparable<T>> extends abstractList<T> {
    private int size    =  2;
    private int pointer = -1;
    protected T[] list;
    private int limit = 2147483647;

    private void createArr(){
       if (list == null) {
           list = (T[]) new Comparable[size];
       }
        else if (list.length-1 == pointer) {
           T[] list2 = (T[]) new Comparable[size]; // 'size' already changed to new value
           System.arraycopy(list, 0, list2, 0, size/2);
           list = list2;
       }
    }

    @Override
    public boolean isEmpty(){
        return (pointer==0);
    }

    @Override
    public int size(){
        return pointer;
    }

    @Override
    public T get(int index){
        if (index<=pointer && index>=0) return list[index];
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public void add(T elem){
        if (pointer<size-1){
            createArr();
            pointer++;
            list[pointer] = elem;
        }
        else {
            size = size * 2;
            createArr();
            pointer++;
            list[pointer] = elem;
        }
    }

    public void print(){
        for (int i = 0; i <= pointer; i++)
            System.out.print(list[i] + " ");
    }

    @Override
    public void removeAt(int index){
        if (index>=0 && index<=pointer){
            System.arraycopy(list, index+1, list, index, pointer - index);
            pointer--;
        }
        else throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int hashCode(){
        final int number = 17;
        int result = 0;

        for (int i = 0; i <= pointer; i++){
            result += (list[i].hashCode() * number) % limit;
        }
        return result;
    }

    @Override
    public boolean equals(Object arg) {
        if (!(arg instanceof arrList)) {
            return false;
        }
        arrList argList = (arrList) arg;
        if (argList.size() != pointer) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (list[i] != argList.get(i)) {
                return false;
            }
        }
        return true;
    }
}
