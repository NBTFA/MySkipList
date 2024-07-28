public class test {
    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<>(5, 0.5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.print();
        System.out.println(list.search(3));
        list.remove(3);
        list.print();
        System.out.println(list.search(3));
    }
}
