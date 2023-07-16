package servises.api_impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servises.api.StringList;

import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringList list;

    @BeforeEach
    void init() {
        list = new StringListImpl();
    }

    @Test
    void add() {
        list.add("First");
        list.add("Second");
        assertEquals("First | Second | ", list.toString());
    }

    @Test
    void add_Thow_ArrayIndexOutOfBoundsException() {
        list = new StringListImpl(1);
        list.add("First");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.add("Second"));
    }


    @Test
    void testAdd_Two_Parameters() {
        list.add("First");
        list.add("Second");

        list.add(0, "!");
        assertEquals("! | First | Second | ", list.toString());

        list.add(2, "!");
        assertEquals("! | First | ! | Second | ", list.toString());
    }

    @Test
    void testAdd_Two_Parameters_Throw_ArrayIndexOutOfBoundsException() {
        list.add("First");
        list.add("Second");

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.add(-1, "!"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.add(2, "!"));


    }

    @Test
    void set() {
        list.add("First");
        list.add("Second");
        list.set(0, "!");
        assertEquals("! | Second | ", list.toString());

    }

    @Test
    void set_Throw_ArrayIndexOutOfBoundsException() {
        list.add("First");
        list.add("Second");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(-1, "!"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(2, "!"));

    }

    @Test
    void remove() {
        list.add("First");
        list.add("Second");

        list.remove("Second");
        assertEquals("First | ", list.toString());

        list.remove("First");
        assertEquals("", list.toString());
    }

    @Test
    void remove_ThrowsNoSuchElementException() {
        list.add("First");
        list.add("Second");
        list.remove("Second");
        assertThrows(NoSuchElementException.class, () -> list.remove("!"));
    }

    @Test
    void testRemove() {
        list.add("First");
        list.add("Second");
        list.remove(1);
        assertEquals("First | ", list.toString());


    }

    @Test
    void testRemove_Throw_ArrayIndexOutOfBoundsException() {
        list.add("First");
        list.add("Second");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(10));


    }

    @Test
    void contains_Return_True() {
        list.add("First");
        list.add("Second");
        assertTrue(list.contains("First"));
    }

    @Test
    void contains_Return_False() {
        assertFalse(list.contains("!"));
    }

    @Test
    void indexOf_Return_Positive() {
        list.add("First");
        list.add("Second");
        assertEquals(0, list.indexOf("First"));
        assertEquals(1, list.indexOf("Second"));
    }

    @Test
    void indexOf_Return_Negative() {
        list.add("First");
        list.add("Second");
        assertEquals(-1, list.indexOf("!"));
    }

    @Test
    void lastIndexOf_Positive() {
        list.add("First");
        list.add("Second");
        assertEquals(0, list.lastIndexOf("First"));
        assertEquals(1, list.lastIndexOf("Second"));
    }

    @Test
    void lastIndexOf_Negative() {
        list.add("First");
        list.add("Second");
        assertEquals(-1, list.lastIndexOf("!"));
    }

    @Test
    void get_Positive() {
        list.add("First");
        list.add("Second");
        assertEquals("First", list.get(0));
        assertEquals("Second", list.get(1));
    }

    @Test
    void get_Negative() {
        list.add("First");
        list.add("Second");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    void testEquals_Positive() {
        list.add("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");

        StringList listForCompare = new StringListImpl();

        listForCompare.add("First");
        listForCompare.add("Second");
        listForCompare.add("Third");
        listForCompare.add("Fourth");

        assertTrue(list.equals(listForCompare));


    }

    @Test
    void testEquals_Negative() {
        list.add("Second");
        list.add("Third");
        list.add("Fourth");

        StringList listForCompare = new StringListImpl();

        listForCompare.add("First");
        listForCompare.add("Second");
        listForCompare.add("Third");

        assertFalse(list.equals(listForCompare));

        listForCompare.add("fourth");

        assertFalse(list.equals(listForCompare));
    }

    @Test
    void size() {
        list.add("Second");
        list.add("Third");
        list.add("Fourth");

        assertEquals(3, list.size());
    }

    @Test
    void isEmpty_Positive() {
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmpty_Negative() {
        list.add("Second");
        assertFalse(list.isEmpty());
    }

    @Test
    void clear() {
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    void toArray() {
        list.add("First");
        list.add("Second");
        list.add("Third");

        String[] newArr = new String[3];
        newArr[0] = "First";
        newArr[1] = "Second";
        newArr[2] = "Third";

        assertArrayEquals(newArr, list.toArray());
    }
}