package homework6;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WorkingWithFiles {

    /**
     * Задание из видео:
     * 1. Создать 2 текстовых файла, примерно 50-100 символов в каждом.
     * 2. Написать программу, склеивающую эти файлы, то есть вначале идет текст из первого файла,
     * потом текст из второго.
     * 3.* Написать программу, которая проверяет, присутствует ли указанное пользователем слово в файле.
     * 4.** Написать метод, проверяющий, есть ли указанное слово в папке.
     */

    public static void main(String[] args) {
        createFiles();
        gluingFiles();
        checkWordInFile("text1.txt", "химический");
        System.out.println("В названии файла или папки есть указанное слово ? " + checkWordInFolder("C:/Users/volox/IdeaProjects/homework/", "home"));
    }

    /**
     * Метод создает 2 файла "text1.txt и text2.txt" и записывает в них текст.
     */

    private static void createFiles() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("text1.txt"), StandardCharsets.UTF_8));
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("text2.txt"), StandardCharsets.UTF_8));

            bufferedWriter.write("Алюминий — химический элемент 13-й группы (по устаревшей классификации главной подгруппы третьей группы) ");
            bufferedWriter.close();
            bufferedWriter2.write("третьего периода периодической таблицы химических элементов Д. И. Менделеева, с атомным номером 13.");
            bufferedWriter2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод склеивает содержимое файла text1, а после содержимое файла text2 в файл text3.
     */

    private static void gluingFiles() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("text1.txt"), StandardCharsets.UTF_8));
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("text2.txt"), StandardCharsets.UTF_8));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("text3.txt"), StandardCharsets.UTF_8));

            int singleCharFile;
            while ((singleCharFile = bufferedReader.read()) != -1) {
                bufferedWriter.write((char) singleCharFile);
            }
            bufferedReader.close();

            while ((singleCharFile = bufferedReader2.read()) != -1) {
                bufferedWriter.write((char) singleCharFile);
            }
            bufferedReader2.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверяет, присутствует ли указанное пользователем слово в файле.
     *
     * @param fileName - Имя файла.
     * @param word     - указанное слово.
     */

    private static void checkWordInFile(String fileName, String word) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
            int singleCharFile;
            while ((singleCharFile = bufferedReader.read()) != -1) {
                text.append((char) singleCharFile);
            }
            bufferedReader.close();
            System.out.printf("В файле %s присутствует слово: %s - %s\n", fileName, word, text.toString().contains(word));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkWordInFolder(String dir, String findText) {
        try {
            File path = new File(dir);
            File[] arrFiles = path.listFiles();
            if (path.listFiles() == null) {
                throw new IOException("Директория не существует или пустая");
            }
            for (int i = 0; i < arrFiles.length; i++) {
                if (arrFiles[i].getName().contains(findText)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
