package softwarearchitektur.userverwaltung;

import DaoJPA.DaoClasses.User_DaoJpa;
import DaoJPA.DaoJPA;
import DaoJPA.EntityClasses.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NutzerDataHandler {
    private static DaoJPA<User> nutzerDao;

    public NutzerDataHandler(){
        nutzerDao = new User_DaoJpa();
    }


    public User userByUsernameAndEMail(String username, String email){
        List<User> userList = nutzerDao.getAll();

        for(User user : userList){
            if (user.getName().equals(username) && user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
}


