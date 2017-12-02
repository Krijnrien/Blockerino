package blockerino.auth;

import java.util.Random;

public class Auth {

    public boolean authenticateUser(String _password, String _username) {
        //TODO validate authentication over HTTP
        //TODO hash password first
        if (true) {
            //TODO Get userId int from server as response;
            int userId = new Random().nextInt();
            String username = _username;
            loginUser(userId, username);
            return true;
        } else {
            //TODO Authentication failed
            return false;
        }

    }

    public void loginUser(int _userId, String _username) {
        new User(_userId, _username);
    }
}
