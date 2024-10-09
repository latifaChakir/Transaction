package com.example.gestionTask.servelet;

import com.example.gestionTask.entity.Account;
import com.example.gestionTask.service.AccountService;
import jakarta.faces.component.html.HtmlBody;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/accounts")
public class AccountServelet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            AccountService accountService = new AccountService();
            Long accountId = Long.parseLong(req.getParameter("id"));
            Account account = accountService.getAccountById(accountId);
            req.setAttribute("account", account);
            req.getRequestDispatcher("editAccount.jsp").forward(req, resp);
        }
    }

    AccountService accountService=new AccountService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if ("create".equals(action)) {
            String accountNumber=req.getParameter("accountNumber");
            BigDecimal balance=BigDecimal.valueOf(Long.parseLong(req.getParameter("balance")));
            Account account=new Account(0L, accountNumber,balance);
            account.setAccountNumber(accountNumber);
            accountService.addAccount(account);
            resp.sendRedirect("accountList.jsp");
        } else if ("update".equals("action")) {
            Long accountId=Long.valueOf(req.getParameter("id"));
            String accountNumber=req.getParameter("accountNumber");
            BigDecimal balance=BigDecimal.valueOf(Long.parseLong(req.getParameter("balance")));
            Account account=new Account(accountId, accountNumber,balance);
            account.setId(accountId);
            account.setAccountNumber(accountNumber);
            account.setBalance(balance);
            accountService.updateAccount(account);
            resp.sendRedirect("accountList.jsp");

        }
    }
}
