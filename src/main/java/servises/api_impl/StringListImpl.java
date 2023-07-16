package servises.api_impl;

import servises.api.StringList;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class StringListImpl implements StringList {
    private final int DEFOULT_SIZE = 10;
    private int size = DEFOULT_SIZE;
    private String[] strings;
    private int length = 0;


    public StringListImpl(int size) {
        strings = new String[size];
        this.size = size;
    }

    public StringListImpl() {
        strings = new String[DEFOULT_SIZE];

    }

    @Override
    public String add(String item) {
        AvailabilityInRange();

        for (int i = length; i < size; i++) {
            if (strings[i] == null) {
                strings[i] = item;
                length++;
                break;
            }
        }

        return item;
    }

    @Override
    public String add(int index, String item) {

        AvailabilityInRange();

        checkIndexOfRange(index);

        for (int i = length; i > index; i--) {
            strings[i] = strings[i - 1];
        }
        strings[index] = item;
        length++;

        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndexOfRange(index);
        strings[index] = item;

        return item;
    }


    @Override
    public String remove(String item) {
        for (int i = 0; i < length; i++) {
            if (strings[i].equals(item)) {
                for (int j = i; j < length - 1; j++) {
                    strings[j] = strings[j + 1];
                }
                length--;
                return item;
            }
        }

        throw new NoSuchElementException("Элемент не найден");
    }

    @Override
    public String remove(int index) {
        checkIndexOfRange(index);
        String result = strings[index];

        for (int i = index; i < length - 1; i++) {
            strings[i] = strings[i + 1];
        }
        length--;
        return result;
    }


    @Override
    public boolean contains(String item) {
        for (int i = 0; i < length; i++) {
            if (strings[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < length; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(String item) {

        for (int i = length - 1; i >= 0; i--) {
            if (strings[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String get(int index) {
        checkIndexOfRange(index);

        return strings[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullPointerException("Передана объект null!");
        }

        if (size() != otherList.size()) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Objects.equals(strings[i], otherList.get(i))) {
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
        return strings[0] == null;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = length - 1; i >= 0; i--) {
                strings[i] = null;

            }
        }

    }

    @Override
    public String[] toArray() {
        String[] newStrings = new String[length];

        for (int i = 0; i < length; i++) {
            newStrings[i]=strings[i];
        }
        return newStrings;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(strings[i]);
            sb.append(" | ");
        }
        return sb.toString();

    }

}
