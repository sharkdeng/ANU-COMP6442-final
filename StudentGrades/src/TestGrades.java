import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class TestGrades {

    String path = new File("").getAbsoluteFile() + "/outline.json";
    Grades g = new Grades(path);


    // test valid marks
    // 0
    @Test(expected = InvalidMarkException.class)
    public void testExam11() throws Exception {
        String type = new ArrayList<>(g.results.keySet()).get(0); // turn set to list
        Grades.putMark(type, -1);
    }


    @Test(expected = InvalidMarkException.class)
    public void testExam12() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(0);
        int range = g.results.get(type);
        Grades.putMark(type, range+1);
    }



    // 1
    @Test(expected = InvalidMarkException.class)
    public void testExam21() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(1);
        Grades.putMark(type, -1);
    }


    @Test(expected = InvalidMarkException.class)
    public void testExam22() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(1);
        int range = g.results.get(type);
        Grades.putMark(type, range+1);
    }


    // 2
    @Test(expected = InvalidMarkException.class)
    public void testExam31() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(2);
        int range = g.results.get(type);
        Grades.putMark(type, -1);
    }


    @Test(expected = InvalidMarkException.class)
    public void testExam32() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(2);
        int range = g.results.get(type);
        Grades.putMark(type, range+1);
    }



    // 3
    @Test(expected = InvalidMarkException.class)
    public void testExam41() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(3);
        Grades.putMark(type, -1);
    }

    @Test(expected = InvalidMarkException.class)
    public void testExam42() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(3);
        int range = g.results.get(type);
        Grades.putMark(type, range+1);
    }


    // 4
    @Test(expected = InvalidMarkException.class)
    public void testExam51() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(4);
        Grades.putMark(type, -1);
    }

    @Test(expected = InvalidMarkException.class)
    public void testExam52() throws Exception{
        String type = new ArrayList<>(g.results.keySet()).get(4);
        int range = g.results.get(type);
        Grades.putMark(type, range+1);
    }



    // student ids
    @Test(expected = InvalidIDException.class)
    public void testStudentIDWithoutU() throws Exception {
        Student s = new Student();
        s.setId("1234567");
    }

    @Test(expected = InvalidIDException.class)
    public void testStudentIDIncorrectLength1() throws Exception {
        Student s = new Student();
        s.setId("u123456");
    }

    @Test(expected = InvalidIDException.class)
    public void testStudentIDIncorrectLength2() throws Exception {
        Student s = new Student();
        s.setId("u123456678");
    }

    @Test(expected = InvalidIDException.class)
    public void testStudentIDInvalidCharacter() throws Exception {
        Student s = new Student();
        s.setId("uu123456");
    }

}
