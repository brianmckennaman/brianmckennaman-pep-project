package Service;

import Model.Account;
import DAO.AccountDAO;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account) {
        Account newAccount = accountDAO.userRegistration(account);
        if(newAccount.username == account.username){
            return null;
        }
        if(newAccount.username == ""){
            return null;
        } 
        return newAccount;
    }

    
}
