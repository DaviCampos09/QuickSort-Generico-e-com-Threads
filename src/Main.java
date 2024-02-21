public class Main {
    public static void main(String[] args) throws Exception {
        String[] arrayOfStrings = { "Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara",
                "Nelda", "Blair", "Ernestine", "Chara", "Kareen", "Monty", "Rene", "Cami", "Winifred", "Tara",
                "Demetrice", "Azucena" };
        Integer[] arrayInt = {5, 9, 3, 7, 2, 8, 6, 1, 4, 23, 1, 13, 99, 101, 242, 72};        

        QuickSort<Integer> ordena = new QuickSort<>(arrayInt, 0, arrayInt.length-1);

        ordena.start();

        try {
            ordena.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(java.util.Arrays.toString(arrayInt));
    }
}
