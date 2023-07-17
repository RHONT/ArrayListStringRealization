package servises.api_impl;

import servises.api.IntegerList;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private final int DEFAULT_SIZE = 10;
    private int size = DEFAULT_SIZE;
    private Integer[] integers;
    private int length = 0;


    public IntegerListImpl(int size) {
        integers = new Integer[size];
        this.size = size;
    }

    public IntegerListImpl() {
        integers = new Integer[DEFAULT_SIZE];
    }

    @Override
    public Integer add(Integer item) {
        if (item==null) {
            throw new NullPointerException("Объект null или пустой");
        }

        // динамическое расширения массива, если он заполнен
        if (length==size) {
            Integer[] newArr=new Integer[size+ DEFAULT_SIZE];
            System.arraycopy(integers,0,newArr,0,length);
            integers =newArr;
            size= newArr.length;
        }

        for (int i = length; i < size; i++) {
            if (integers[i] == null) {
                integers[i] = item;
                length++;
                break;
            }
        }
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {

        AvailabilityInRange();
        checkIndexOfRange(index);

        for (int i = length; i > index; i--) {
            integers[i] = integers[i - 1];
        }
        integers[index] = item;
        length++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndexOfRange(index);

        integers[index] = item;

        return item;
    }


    @Override
    public Integer remove(Integer item) {
        for (int i = 0; i < length; i++) {
            if (integers[i].equals(item)) {
                for (int j = i; j < length - 1; j++) {
                    integers[j] = integers[j + 1];
                }
                length--;
                return item;
            }
        }

        throw new NoSuchElementException("Элемент не найден");
    }

    @Override
    public Integer remove(int index) {
        checkIndexOfRange(index);
        Integer result = integers[index];

        for (int i = index; i < length - 1; i++) {
            integers[i] = integers[i + 1];
        }
        length--;
        return result;
    }


    @Override
    public boolean contains(Integer item) {
        sort();
        int result= Arrays.binarySearch(toArray(),item);

        return result>=0;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < length; i++) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {

        for (int i = length - 1; i >= 0; i--) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {

        checkIndexOfRange(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullPointerException("Передана объект null!");
        }

        if (size() != otherList.size()) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Objects.equals(integers[i], otherList.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return integers[0] == null;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = length - 1; i >= 0; i--) {
                integers[i] = null;
            }
        }
    }

    @Override
    public Integer[] toArray() {
        Integer[] newStrings = new Integer[length];

        for (int i = 0; i < length; i++) {
            newStrings[i]= integers[i];
        }
        return newStrings;
    }

    @Override
    public void sort() {

            for (int i = 1; i < length; i++) {
                int temp = integers[i];
                int j = i;
                while (j > 0 && integers[j - 1] >= temp) {
                    integers[j] = integers[j - 1];
                    j--;
                }
                integers[j] = temp;
            }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(integers[i]);
            sb.append(" | ");
        }
        return sb.toString();
    }

    public int getSize() {
        return size;
    }

    // Проверка на допустимость вхождения в существующий диапазон индексов.
    private void checkIndexOfRange(int index) {
        if (index < 0 || index > length - 1) {
            throw new ArrayIndexOutOfBoundsException("Индекс не входит в требуемый диапазон. Макcимальный индекс = " + (length - 1));
        }
    }

    // Проверка для тех методов, которые предполагают запись в новый индекс. Для записи место быть должно.
    // Если нет, то выбрасывается исклюение.
    private void AvailabilityInRange() {
        if (size == length) {
            throw new ArrayIndexOutOfBoundsException("Массив переполнен!");
        }
    }
}
