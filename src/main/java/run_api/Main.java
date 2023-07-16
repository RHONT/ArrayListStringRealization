package run_api;

import servises.api.StringList;
import servises.api_impl.StringListImpl;

public class Main {
    public static void main(String[] args) {
        StringList list=new StringListImpl();
        list.add("2");
        list.add("10");

        list.remove(5);
        System.out.println(list);
    }
}
