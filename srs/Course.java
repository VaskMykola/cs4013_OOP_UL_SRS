//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Course {
//    private String courseCode;
//    private String courseName;
//    private String courseDescription;
//    private int semester;
//    List<Module> modules;
//
//    public Course(String courseCode, String courseName, String courseDescription, int semester) {
//        this.courseCode = courseCode;
//        this.courseName = courseName;
//        this.courseDescription = "";
//        this.semester = semester;
//        this.modules = new ArrayList<>();
//    }
//
//    public void editDescription(String description) {
//        this.courseDescription = description;
//    }
//
//    public void editSemester(int semester) {
//        this.semester = semester;
//    }
//
//    public void readModulesFromFile(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] moduleInfo = line.split(",");
//                String moduleCode = moduleInfo[0].trim();
//                String moduleName = moduleInfo[1].trim();
//                int moduleSemester = Integer.parseInt(moduleInfo[2].trim());
//                if (moduleSemester == 1 || moduleSemester == 2) {
//                    Module module = new Module(moduleCode, moduleName, moduleSemester);
//                    modules.add(module);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addModule(Module module) {
//            modules.add(module);
//    }
//
//    public void removeModule(String moduleCode) {
//        modules.removeIf(module -> module.getModuleCode().equals(moduleCode));
//    }
//
//}
//
