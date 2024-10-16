import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Используем HashSet<Laptop>, чтобы хранить объекты типа Laptop
        HashSet<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop(8, 512, "Windows 10", "Black"));
        laptops.add(new Laptop(16, 1024, "Windows 11", "Silver"));
        laptops.add(new Laptop(8, 256, "Ubuntu", "Black"));
        laptops.add(new Laptop(32, 1024, "macOS", "Gray"));
        laptops.add(new Laptop(16, 512, "Windows 10", "White"));

        // Критерии для фильтрации
        Map<Integer, String> criteria = new HashMap<>();
        criteria.put(1, "ОЗУ");
        criteria.put(2, "Объем ЖД");
        criteria.put(3, "Операционная система");
        criteria.put(4, "Цвет");

        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();
        boolean continueFiltering = true;

        while (continueFiltering) {
            // Печатаем доступные критерии фильтрации
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            for (Map.Entry<Integer, String> entry : criteria.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }

            // Ввод критерия от пользователя
            int criterion = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер

            // Обрабатываем ввод критерия и запрашиваем значения для фильтра
            switch (criterion) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ (в ГБ): ");
                    filters.put("ram", scanner.nextInt());
                    scanner.nextLine(); // Очищаем буфер
                    break;
                case 2:
                    System.out.print("Введите минимальное значение объема ЖД (в ГБ): ");
                    filters.put("hdd", scanner.nextInt());
                    scanner.nextLine(); // Очищаем буфер
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    filters.put("os", scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    filters.put("color", scanner.nextLine());
                    break;
                default:
                    System.out.println("Некорректный критерий.");
            }

            // Спрашиваем, хочет ли пользователь добавить еще один критерий
            System.out.print("Хотите добавить еще один критерий? (да/нет): ");
            String response = scanner.nextLine();
            continueFiltering = response.equalsIgnoreCase("да");
        }

        // Фильтруем ноутбуки
        filterLaptops(laptops, filters);
    }

    // Метод для фильтрации ноутбуков
    public static void filterLaptops(HashSet<Laptop> laptops, Map<String, Object> filters) {
        // Проходим по каждому ноутбуку и проверяем, соответствует ли он фильтрам
        for (Laptop laptop : laptops) {
            boolean matches = true;

            // Проверяем фильтр по ОЗУ
            if (filters.containsKey("ram") && laptop.getRam() < (int) filters.get("ram")) {
                matches = false;
            }

            // Проверяем фильтр по ЖД
            if (filters.containsKey("hdd") && laptop.getHdd() < (int) filters.get("hdd")) {
                matches = false;
            }

            // Проверяем фильтр по операционной системе
            if (filters.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) filters.get("os"))) {
                matches = false;
            }

            // Проверяем фильтр по цвету
            if (filters.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) filters.get("color"))) {
                matches = false;
            }

            // Если ноутбук соответствует всем критериям, выводим его на экран
            if (matches) {
                System.out.println(laptop);
            }
        }
    }
}

