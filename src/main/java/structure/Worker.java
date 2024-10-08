package structure;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Worker extends User{
        private String supLogin;
        private int numberOfContracts;

        public Worker (){}
        public Worker (String login, String password, String fio, String supLogin, int numberOfContracts){
                super(login, password, fio);
                this.supLogin = supLogin;
                this.numberOfContracts = numberOfContracts;
        }

        @Override
        public String getLogin() {
                return super.getLogin();
        }
        @Override
        public void setLogin(String login) {
                super.setLogin(login);
        }
        @Override
        public String getPassword() {
                return super.getPassword();
        }
        @Override
        public void setPassword(String password) {
                super.setPassword(password);
        }
        @Override
        public String getFio() {
                return super.getFio();
        }
        @Override
        public void setFio(String fio) {
                super.setFio(fio);
        }
        public void setSupLogin(String supLogin) {
                this.supLogin = supLogin;
        }
        public String getSupLogin() {
                return supLogin;
        }

        public int getNumberOfContracts() {
                return numberOfContracts;
        }

        public void setNumberOfContracts(int numberOfContracts) {
                this.numberOfContracts = numberOfContracts;
        }
}
