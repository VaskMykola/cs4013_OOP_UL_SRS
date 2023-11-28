import java.util.*;

public class CourseSorter {

    public static void arraySort(ArrayList<String> courses) {
        quickSort(courses, 0, courses.size() - 1);
    }

    private static void quickSort(ArrayList<String> courses, int low, int high) {
        if (low < high) {
            int pi = partition(courses, low, high);

            quickSort(courses, low, pi - 1);
            quickSort(courses, pi + 1, high);
        }
    }

    private static int partition(ArrayList<String> courses, int low, int high) {
        String pivot = courses.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (compareCourses(courses.get(j), pivot) <= 0) {
                i++;
                Collections.swap(courses, i, j);
            }
        }

        Collections.swap(courses, i + 1, high);
        return i + 1;
    }

    // Custom comparator for course strings
    private static int compareCourses(String course1, String course2) {
        String[] split1 = course1.split(",");
        String[] split2 = course2.split(",");

        // Compare year, semester, and course code in that order
        int yearCompare = Integer.compare(Integer.parseInt(split1[1]), Integer.parseInt(split2[1]));
        if (yearCompare != 0) return yearCompare;

        int semesterCompare = Integer.compare(Integer.parseInt(split1[2]), Integer.parseInt(split2[2]));
        if (semesterCompare != 0) return semesterCompare;

        return split1[3].compareTo(split2[3]);
    }

    public static void main(String[] args) {
        ArrayList<String> courses = new ArrayList<>(Arrays.asList(
                "22310975,2023,1,CS4013",
                "22310975,2024,1,MA4011",
                "22310975,2023,1,CS4416",
                "22310975,2023,1,CS4004",
                "22310975,2023,2,CS4141",
                "22310975,2023,2,ET4023",
                "22310975,2023,2,MA4011"

        ));

        arraySort(courses);

        for (String course : courses) {
            System.out.println(course);
        }
    }
}
