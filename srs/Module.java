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
        return "Module{" +
                "moduleCode='" + moduleCode + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", semester=" + semester +
                '}';
    }
}