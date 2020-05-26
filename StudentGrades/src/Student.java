import java.util.List;

public class Student {
   private String id;

   public String getId() {
       return id;
   }

   public void setId(String id) throws Exception {
       // check if id is correct format
       if (!id.startsWith("u")) { throw new InvalidIDException("Invalid id, without u"); }

       if (id.length() == 8) {
           for (int i=1; i<8; i++) {
               if (!Character.isDigit(id.charAt(i))) {
                   throw new InvalidIDException("Invalid id, length is incorrect");
               }
           }
           this.id = id;
       } else {
           throw new InvalidIDException("Invalid id");
       }

   }


}
