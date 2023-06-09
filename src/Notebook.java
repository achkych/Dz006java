import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Notebook {
    private String brand;
    private int ramSize;
    private int hddSize;
    private int os;
    private String color;
    private int videoMemorySize;

 public Notebook(String brand, int ramSize, int hddSize, int os, String color, int videoMemorySize) {
        this.brand = brand;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
        this.os = os;
        this.color = color;
        this.videoMemorySize = videoMemorySize;
    }

    public String getBrand() {
        return brand;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getHddSize() {
        return hddSize;
    }

    public int getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public int getVideoMemorySize() {
        return videoMemorySize;
    }


    @Override
    public String toString() {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", ramSize=" + ramSize +
                ", hddSize=" + hddSize +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Notebook notebook1 = new Notebook("Lenovo", 8, 500, 10, "Black", 2);
        Notebook notebook2 = new Notebook("Apple", 16, 512, 12, "Silver", 4);
        Notebook notebook3 = new Notebook("Asus", 8, 256, 10, "Red", 2);
        Notebook notebook4 = new Notebook("HP", 12, 1000, 11, "White", 6);
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(notebook1);
        notebooks.add(notebook2);
        notebooks.add(notebook3);
        notebooks.add(notebook4);

        Map<Integer, String> filterMap = new HashMap<>();
        filterMap.put(1, "ramSize");
        filterMap.put(2, "hddSize");
        filterMap.put(3, "os");
        filterMap.put(4, "color");
        filterMap.put(5, "videoMemorySize");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Пункты фильтрации:");
        System.out.println("1 - Размер оперативной памяти");
        System.out.println("2 - Размер жесткого диска");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("5 - Размер видеопамяти");
        System.out.println("Введите номера критериев фильтрации через запятую: ");
        String[] criteria = scanner.nextLine().split(",");
        Map<String, Integer> filterParameters = new HashMap<>();
        for (String c : criteria) {
            System.out.println("Введите минимальное значение для " + filterMap.get(Integer.parseInt(c.trim())) + ":");
            String s = scanner.nextLine();
            try{
                int value = Integer.parseInt(s);
                filterParameters.put(filterMap.get(Integer.parseInt(c.trim())), value);
            }
            catch(Exception e){
                filterParameters.put(filterMap.get(Integer.parseInt(c.trim())), 0);
            }
        }
        scanner.close();

        List<Notebook> filteredNotebooks = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            boolean passedFilter = true;
            for (String key : filterParameters.keySet()) {
                if (key.equals("ramSize")) {
                    if (notebook.getRamSize() < filterParameters.get(key)) {
                        passedFilter = false;
                        break;
                    }
                } else if (key.equals("hddSize")) {
                    if (notebook.getHddSize() < filterParameters.get(key)) {
                        passedFilter = false;
                        break;
                    }
                } else if (key.equals("os")) {
                    if (notebook.getOs() < filterParameters.get(key)) {
                        passedFilter = false;
                        break;
                    }
                } else if (key.equals("color")) {
                    if (!notebook.getColor().equals(filterParameters.get(key).toString())) {
                        passedFilter = false;
                        break;
                    }
                } else if (key.equals("videoMemorySize")) {
                    if (notebook.getVideoMemorySize() < filterParameters.get(key)) {
                        passedFilter = false;
                        break;
                    }
                }
            }
            if (passedFilter) {
                filteredNotebooks.add(notebook);
            }
        }
        System.out.println(filteredNotebooks);
    }
}