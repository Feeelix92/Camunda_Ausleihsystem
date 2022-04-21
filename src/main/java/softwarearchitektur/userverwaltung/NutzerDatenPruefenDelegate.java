package softwarearchitektur.userverwaltung;

import DaoJPA.EntityClasses.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NutzerDatenPruefenDelegate implements JavaDelegate {

    private NutzerDataHandler userHandler;
    private User user;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        init();
        pruefeNutzer    ((String) delegateExecution.getVariable("username"),
                        (String) delegateExecution.getVariable("email"),
                        delegateExecution);
    }

    private void init(){
        userHandler = new NutzerDataHandler();
    }

    private void pruefeNutzer(String username, String email, DelegateExecution delegateExecution){
        boolean exists;
        user = userHandler.userByUsernameAndEMail(username, email);

        if (user == null){
            exists = false;
        }else{
            exists = true;
            delegateExecution.setVariable("BenutzerId",user.getBenutzerID());
            System.out.println("BenutzerId: " + user.getBenutzerID());
        }
        delegateExecution.setVariable("NutzerExistiert",exists);
    }
}
