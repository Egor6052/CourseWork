package Package;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonMain {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        /**
         * Таблица информмационной базы в виде динамического списка объектов;
         */
        final List<Person> personList = new ArrayList<>();

        personList.add(0, new Person("Иванов", "Иван", "Ивановичь", 1, "Протезирование", 150, LocalDate.of(2022, 6, 13), "No", 340));
        personList.add(1, new Person("Лапин", "Максим", "Сергеевичь", 2, "Пломбирование", 200, LocalDate.of(2022, 7, 10), "Yes", 0));
        personList.add(2, new Person("Ткачук", "Николай", "Александровичь", 3, "Инплантация", 105, LocalDate.of(2022, 7, 16), "No", 550));
        personList.add(3, new Person("Смирнов", "Олег", "Вадимовичь", 4, "Реставрация", 350, LocalDate.of(2022, 7, 20), "Yes", 0));
        personList.add(4, new Person("Никонов", "Яромир", "Владимирович", 5, "Лечение десны", 220, LocalDate.of(2022, 8, 6), "Yes", 0));
        personList.add(5, new Person("Афанасьев", "Рафаил", "Данилович", 6, "Лечение зубов", 170, LocalDate.of(2022, 8, 25), "No", 100));
        personList.add(6, new Person("Быков", "Илья", "Богданович", 7, "Отбеливание", 468, LocalDate.of(2022, 8, 29), "No", 220));
        personList.add(7, new Person("Селезнёв", "Харитон", "Ярославович", 8, "Ортодонтия", 460, LocalDate.of(2022, 9, 1), "Yes", 0));
        personList.add(8, new Person("Бондаренко", "Артур", "Станиславович", 9, "Красота зубов", 220, LocalDate.of(2022, 9, 12), "Yes", 0));
        personList.add(9, new Person("Дорофеев", "Михаил", "Викторович", 10, "Реставрация", 470, LocalDate.of(2022, 9, 29), "Yes", 0));
        personList.add(10, new Person("Яковлев", "Спартак", "Викторович", 11, "Пломбирование", 155, LocalDate.of(2022, 10, 5), "No", 350));
        personList.add(11, new Person("Бобылёв", "Орест", "Алексеевич", 12, "Пломбирование", 210, LocalDate.of(2022, 10, 25), "No", 100));
        personList.add(12, new Person("Каськив", "Тарас", "Леонидович", 13, "Отбеливание", 300, LocalDate.of(2022, 10, 30), "Yes", 0));
        personList.add(13, new Person("Гайчук", "Святослав", "Данилович", 14, "Реставрация", 155, LocalDate.of(2022, 11, 5), "Yes", 0));
        personList.add(14, new Person("Рябов", "Степан", "Богданович", 15, "Пломбирование", 330, LocalDate.of(2022, 11, 15), "Yes", 0));
        personList.add(15, new Person("Шашков", "Игнатий", "Станиславович", 16, "Протезирование", 125, LocalDate.of(2022, 11, 25), "No", 450));
        personList.add(16, new Person("Кузнецов", "Цицерон", "Алексеевич", 17, "Инплантация", 400, LocalDate.of(2023, 1, 10), "Yes", 0));
        personList.add(17, new Person("Скоропадский", "Роман", "Борисович", 18, "Пломбирование", 285, LocalDate.of(2023, 1, 23), "Yes", 0));
        personList.add(18, new Person("Андреев", "Никита", "Алексеевич", 19, "Протезирование", 600, LocalDate.of(2023, 2, 12), "No", 120));
        personList.add(19, new Person("Муравьёв", "Зигмунд", "Леонидович", 20, "Отбеливание", 450, LocalDate.of(2023, 2, 8), "Yes", 0));

        //Вывод в консоль;
        for (int i = 0; i < personList.size(); i++) {
            System.out.println(personList.get(i));
        }

        /**
         * Загрузка базы данных во внешний файл (сериализация);
         */
        try {
            FileOutputStream fos = new FileOutputStream("src/Package/Database.json");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < personList.size(); i++) {
                oos.writeObject(personList.get(i));
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Выгрза базы данных из внешнего файла (десериализация);
         */

        try {
            FileInputStream fis = new FileInputStream("src/Package/Database.json");
            ObjectInputStream ois = new ObjectInputStream(fis);
            for (int i = 0; i < personList.size(); i++) {
                Person person = (Person) ois.readObject();
                System.out.println(person);
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        /**
         * Формирую Json;
         */
        System.out.println("--------------------------------Json---------------------------------");

        StringBuilder jsonString = new StringBuilder("");
        try {
            FileOutputStream fos = new FileOutputStream("src/Package/Database.json");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < personList.size(); i++) {
                oos.writeObject(personList.get(i));
            }
            jsonString.append(gson.toJson(personList));
            try (PrintWriter out = new PrintWriter("src/Package/Database.txt")){
                out.println(jsonString.toString());
            }
            oos.close();
            System.out.println(jsonString);
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
        //Вызов функции для запуска сервера;
                simplestServerExample();
    }

    /**
     * Запуск сервера;
     */
    private static void simplestServerExample() throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/back", new HttpHandlerServer());
        server.start();
        System.out.println(System.lineSeparator() + "\033[1;32mServer started at:\tlocalhost:8001\u001B[0m");
    }

}