public class Admin extends Account{

    Admin(String username, String password) {
        super(username, password);
      }

      void useAdminPage() {
        new AdminPage();
      }
}