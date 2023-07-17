package servises.api_impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servises.api.IntegerList;
import servises.api.StringList;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    IntegerList list;

    @BeforeEach
    void init() {
        list = new IntegerListImpl();
    }

    @Test
    void add() {
        list.add(1);
        list.add(2);
        assertEquals("1 | 2 | ", list.toString());
    }

    @Test
        // Расширяется массив каждый раз на 10 ячеек.
        // Исходный сделал с емкостью 2
        // При добавлении вышел за пределы, + 10. Итого теперь 12 ячеек в массиве.

        // Объект создал напрямую через класс реализации, так как мне был нужен метод getSize() (Истинный размер массива),
        // который не прописан в интерфейсе StringList
    void add_With_Extends() {
        IntegerListImpl list=new IntegerListImpl(2);
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(12, list.getSize());
    }


    @Test
    void testAdd_Two_Parameters() {
        list.add(1);
        list.add(2);

        list.add(0, 5);
        assertEquals("5 | 1 | 2 | ", list.toString());

        list.add(2, 5);
        assertEquals("5 | 1 | 5 | 2 | ", list.toString());
    }

    @Test
    void testAdd_Two_Parameters_Throw_ArrayIndexOutOfBoundsException() {
        list.add(1);
        list.add(2);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.add(-1, 5));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.add(2, 5));


    }

    @Test
    void set() {
        list.add(1);
        list.add(2);
        list.set(0, 5);
        assertEquals("5 | 2 | ", list.toString());

    }

    @Test
    void set_Throw_ArrayIndexOutOfBoundsException() {
        list.add(1);
        list.add(2);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(-1, 5));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(2, 5));

    }

    @Test
    void remove() {
        list.add(1);
        list.add(2);

        list.remove(1);
        assertEquals("1 | ", list.toString());

        list.remove(0);
        assertEquals("", list.toString());
    }

    @Test
    void remove_ThrowsNoSuchElementException() {
        list.add(1);
        list.add(2);
        list.remove(1);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(2));
    }

    @Test
    void testRemove() {
        list.add(1);
        list.add(2);
        list.remove(1);
        assertEquals("1 | ", list.toString());


    }

    @Test
    void testRemove_Throw_ArrayIndexOutOfBoundsException() {
        list.add(1);
        list.add(2);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(10));


    }

    @Test
    void contains_Return_True() {
        list.add(600);
        list.add(100);
        list.add(40);


        assertTrue(list.contains(40));
    }

    @Test
    void contains_Return_False() {
        list.add(40);
        list.add(100);
        list.add(600);
        assertFalse(list.contains(10));
    }

    @Test
    void indexOf_Return_Positive() {
        list.add(1);
        list.add(2);
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
    }

    @Test
    void indexOf_Return_Negative() {
        list.add(1);
        list.add(2);
        assertEquals(-1, list.indexOf(5));
    }

    @Test
    void lastIndexOf_Positive() {
        list.add(1);
        list.add(2);
        assertEquals(0, list.lastIndexOf(1));
        assertEquals(1, list.lastIndexOf(2));
    }

    @Test
    void lastIndexOf_Negative() {
        list.add(1);
        list.add(2);
        assertEquals(-1, list.lastIndexOf(5));
    }

    @Test
    void get_Positive() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void get_Negative() {
        list.add(1);
        list.add(2);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    void testEquals_Positive() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        IntegerList listForCompare = new IntegerListImpl();

        listForCompare.add(1);
        listForCompare.add(2);
        listForCompare.add(3);
        listForCompare.add(4);

        assertTrue(list.equals(listForCompare));


    }

    @Test
    void testEquals_Negative() {
        list.add(2);
        list.add(3);
        list.add(4);

        IntegerList listForCompare = new IntegerListImpl();

        listForCompare.add(1);
        listForCompare.add(2);
        listForCompare.add(3);

        assertFalse(list.equals(listForCompare));

        listForCompare.add(4);

        assertFalse(list.equals(listForCompare));
    }

    @Test
    void size() {
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(3, list.size());
    }

    @Test
    void isEmpty_Positive() {
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmpty_Negative() {
        list.add(2);
        assertFalse(list.isEmpty());
    }

    @Test
    void clear() {
        list.add(2);
        list.add(3);
        list.add(4);
        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    void toArray() {
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] newArr = new Integer[3];
        newArr[0] = 1;
        newArr[1] = 2;
        newArr[2] = 3;

        assertArrayEquals(newArr, list.toArray());
    }
}