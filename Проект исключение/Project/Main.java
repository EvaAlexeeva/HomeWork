package Project;

import Project.Program.Program;
import Project.Console.Console;
import Project.Console.ConsOn;

public class Main {
    public static void main(String[] args) {
        Program<ConsOn> prog = new Program<ConsOn>(new Console());
        prog.start();
    }
}

