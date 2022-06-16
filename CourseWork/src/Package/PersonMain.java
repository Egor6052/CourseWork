package Package;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonMain {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        /**
         * Таблица информмационной базы в виде динамического списка объектов;
         */
        final List<Person> personList = new ArrayList<>();

        personList.add(0, new Person("Ivanov", "Ivan", "Ivanovich", 1, "Prosthetics", 150, new  Date(2022, 6, 20), "No", 340));
        personList.add(1, new Person("Lapin", "Maksim", "Sergeevich", 2, "Plombirovanie", 200, new Date(2022, 7, 10), "Yes", 0));
        personList.add(2, new Person("Tkachuk", "Nicholas", "Aleksandrovich", 3, "Inplontacia", 105, new Date(2022, 7, 16), "No", 550));
        personList.add(3, new Person("Smirnov", "Oleg", "Vadimovich", 4, "Restoration", 350, new Date(2022, 7, 20), "Yes", 0));
        personList.add(4, new Person("Nikonov", "Jaromir", "Vladimirovich", 5, "gum treatment", 220, new Date(2022, 8, 6), "Yes", 0));
        personList.add(5, new Person("Afanasiev", "Raphael", "Danilovich", 6, "Dental treatment", 170, new Date(2022, 8, 25), "No", 100));
        personList.add(6, new Person("Bykov", "Ilya", "Bogdanovich", 7, "Whitening", 468, new Date(2022, 8, 29), "No", 220));
        personList.add(7, new Person("Seleznev", "Khariton", "Yaroslavovich", 8, "Orthodontics", 460, new Date(2022, 9, 1), "Yes", 0));
        personList.add(8, new Person("Bondarenko", "Arthur", "Stanislavovich", 9, "Restoration", 220, new Date(2022, 9, 12), "Yes", 0));
        personList.add(9, new Person("Dorofeev", "Michael", "Viktorovich", 10, "Restoration", 470, new Date(2022, 9, 29), "Yes", 0));
        personList.add(10, new Person("Yakovlev", "Spartacus", "Viktorovich", 11, "Plombirovanie", 155, new Date(2022, 10, 5), "No", 350));
        personList.add(11, new Person("Bobylev", "Orestes", "Alexeyevich", 12, "Plombirovanie", 210, new Date(2022, 10, 25), "No", 100));
        personList.add(12, new Person("Kaskiv", "Taras", "Leonidovich", 13, "Whitening", 300, new Date(2022, 10, 30), "Yes", 0));
        personList.add(13, new Person("Gaychuk", "Svyatoslav", "Danilovich", 14, "Restoration", 155, new Date(2022, 11, 5), "Yes", 0));
        personList.add(14, new Person("Ryabov", "Stepan", "Bogdanovich", 15, "Plombirovanie", 330, new Date(2022, 11, 15), "Yes", 0));
        personList.add(15, new Person("Shashkov", "Ignatius", "Stanislavovich", 16, "Prosthetics", 125, new Date(2022, 11, 25), "No", 450));
        personList.add(16, new Person("Kuznetsov", "Cicero", "Alexeyevich", 17, "Inplontacia", 400, new Date(2023, 1, 10), "Yes", 0));
        personList.add(17, new Person("Skoropadsky", "Novel", "Borisovich", 18, "Plombirovanie", 285, new Date(2023, 1, 23), "Yes", 0));
        personList.add(18, new Person("Andreev", "Nikita", "Alexeyevich", 19, "Prosthetics", 600, new Date(2023, 2, 12), "No", 120));
        personList.add(19, new Person("Muravyov", "Sigmund", "Leonidovich", 20, "Whitening", 450, new Date(2023, 2, 8), "Yes", 0));

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
