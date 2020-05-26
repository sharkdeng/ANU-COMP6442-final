
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




public class StudentGrades {

	static Map<Student, Grades> stu = new HashMap<>();

	// path of the outline.json
	public static String path = "/Users/dph/Documents/6Study/s2-6442-SoftwareConstruction/final/2018/exam2018s2/StudentGrades/outline.json";



	public static void main(String[] args) {



		Scanner x = new Scanner(System.in);
		try {
			System.out.println("Press \"a\" to add a new student or \"r\"  to report final grades:");
			while (x.nextLine().equals("a")) {

				System.out.println("Enter the students id:");

				// new student
				Student ns = new Student();
				Grades ng = new Grades(path);
				String s_id = x.nextLine();
				ns.setId(s_id);

				// set grades
				for (String k : ng.results.keySet() ) {
					System.out.println("Please enter the " + k + " mark out of " + ng.results.get(k) + ":");

					// check validity of mark
					int mark = Integer.valueOf(x.nextLine());
					if (mark >=0 && mark <= ng.results.get(k)) {
						Grades.putMark(k, mark);
					} else {
						throw new Exception("Invalid mark");
					}

				}


				// add to map
				stu.put(ns, ng);

				x.nextLine();
				System.out.println("Press \"a\" to add a new student or \"r\"  to report final grades:");
			}


			System.out.println("Grade Report:");

			// iterate each student
			for (Student st: stu.keySet()) {
				int score = 0;

				// get the grades of the student
				Grades sg = stu.get(st);
				for (String k: sg.results.keySet()) {
					score += sg.results.get(k);
				}


				if (score < 50) {
					System.out.println(st.getId() + " " + score + " N");
				} else if (score < 60) {
					System.out.println(st.getId() + " " + score + " P");
				} else if (score < 70) {
					System.out.println(st.getId()+ " " + score + " C");
				} else if (score < 80) {
					System.out.println(st.getId() + " " + score + " D");
				} else  {
					System.out.println(st.getId() + " " + score + " HD");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		x.close();
	}



}
