import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private String courseDescription;
    private int semester;
    List<Module> modules;

    public Course(String courseCode, String courseName, String courseDescription, int semester) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDescription = "";
        this.semester = semester;
        this.modules = new ArrayList<>();
    }

    public void editDescription(String description) {
        this.courseDescription = description;
    }

    public void editDuration(int duration) {
        this.semester = duration;
    }

    public void readModulesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] moduleInfo = line.split(",");
                String moduleCode = moduleInfo[0].trim();
                String moduleName = moduleInfo[1].trim();
                int moduleSemester = Integer.parseInt(moduleInfo[2].trim());
                if (moduleSemester == 1 || moduleSemester == 2) {
                    Module module = new Module(moduleCode, moduleName, moduleSemester);
                    modules.add(module);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addModule(Module module) {
            modules.add(module);
    }

    public void removeModule(String moduleCode) {
        modules.removeIf(module -> module.getModuleCode().equals(moduleCode));
    }
//TESTING
    public static void main(String[] args) {
        //Example
        Course course = new Course("LM051", "Computer Systems", "Bachelor of Science in Computer Systems", 2);

        //Reading CSV file
        course.readModulesFromFile("C:\\Users\\Admin\\IdeaProjects\\michaelEnglish\\src\\Modules.csv");

        //Adding a module
        Module newModule = new Module("CS4178", "SOFTWARE REQUIREMENTS AND MODELLING", 2);
        course.addModule(newModule);

        // Removing a module
        course.removeModule("CS4141");

        // Displaying remaining modules
        for (Module module : course.modules) {
            System.out.println(module);
        }
    }
}

class Module {
    private String moduleCode;
    private String moduleName;
    private int semester;

    public Module(String moduleCode, String moduleName, int semester) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.semester = semester;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    @Override
    public String toString() {
        return "Module | " +
                "code - " + moduleCode +
                " name - " + moduleName +
                " semester - " + semester;
    }
}
