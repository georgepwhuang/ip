package blarb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends a task to the tasklist file.
     *
     * @param task blarb.Task to be added.
     * @throws IOException Issues with writing into the file.
     */
    public void file(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.encode());
        fw.write("\n");
        fw.close();
    }

    /**
     * Rewrites the entire tasklist file.
     *
     * @throws IOException Issues with writing into the file.
     */
    public void refile(Tasklist list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(task.encode());
            fw.write("\n");
        }
        fw.close();
    }

    public List<Task> load() throws IOException {
        List<Task> list = new ArrayList<>(100);
        File file = new File(filePath);
        File parent = file.getParentFile();
        parent.mkdirs();
        file.createNewFile();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] tokens = str.split(" / ");
            Task task;
            switch (tokens[0]) {
            case "T":
                task = new ToDo(tokens[2]);
                if (Integer.parseInt(tokens[1]) == 1) {
                    task.markAsDone();
                }
                break;
            case "D":
                task = new Deadline(tokens[2], tokens[3]);
                if (Integer.parseInt(tokens[1]) == 1) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = new Event(tokens[2], tokens[3]);
                if (Integer.parseInt(tokens[1]) == 1) {
                    task.markAsDone();
                }
                break;
            default:
                throw new InputMismatchException();
            }
            list.add(task);
        }
        return list;
    }
}
