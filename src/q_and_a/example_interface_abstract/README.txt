########################################################
## Which to choose?  interface or abstract?
########################################################

I will give you an example first:

public interface LoginAuth{
   public String encryptPassword(String pass);
   public void checkDBforUser();
}
########################################################

########################################################
## Now suppose you have 3 databases in your application. 
## Then each and every implementation for that database needs 
## to define the above 2 methods:
########################################################
------------------------------------------------------------------------------------------------------------------------
public class DBMySQL implements LoginAuth{
          // Needs to implement both methods
}
public class DBOracle implements LoginAuth{
          // Needs to implement both methods
}
public class DBAbc implements LoginAuth{
          // Needs to implement both methods
}
------------------------------------------------------------------------------------------------------------------------

########################################################
## But what if encryptPassword() is not database dependent,
## and it's the same for each class?
## Then the above would not be a good approach.
########################################################

########################################################
## Instead, consider this approach:
########################################################
public abstract class LoginAuth{
   public String encryptPassword(String pass){
            // Implement the same default behavior here
            // that is shared by all subclasses.
   }

   // Each subclass needs to provide their own implementation of this only:
   public abstract void checkDBforUser();
}


// note:   Abstract allows partial implementation as described above.
/