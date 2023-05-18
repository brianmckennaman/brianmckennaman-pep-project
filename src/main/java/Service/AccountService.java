package Service;

import Model.Account;
import DAO.AccountDAO;

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
        return newAccount;
    }

    public Account userLogin(Account account) {
        return accountDAO.userLogin(account);
    }

    public Account getAccountByUsername(Account account){
        return accountDAO.getAccountByUsername(account.getUsername());
    }

    
}
