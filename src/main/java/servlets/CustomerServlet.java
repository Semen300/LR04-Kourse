package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import services.ContractService;
import services.ItemService;
import services.TaskService;
import structure.Contract;
import structure.Item;
import structure.Task;

public class CustomerServlet extends  HttpServlet{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action!=null){
            switch (action) {
                case "add" -> {
                    ItemService itemService = new ItemService();
                    List<Item> items = itemService.getItems();
                    req.setAttribute("items", items);
                    req.getRequestDispatcher("pages/customer/add.jsp").forward(req, resp);
                }
                case "delete" -> {
                    int id = Integer.parseInt(req.getParameter("id"));
                    ContractService contractService = new ContractService();
                    Contract contract = contractService.getContractById(id);
                    contractService.deleteContract(contract);
                    resp.sendRedirect(req.getContextPath() + "/customer");
                }
                default -> {
                    ContractService contractService = new ContractService();
                    List<Contract> contracts = contractService.getContractsByCustomer(req.getSession().getAttribute("userLogin").toString());
                    req.setAttribute("contracts", contracts);
                    req.getRequestDispatcher("/pages/customer/main.jsp").forward(req, resp);
                }
            }
        }
        else{
            ContractService contractService = new ContractService();
            List<Contract> Contracts = contractService.getContractsByCustomer(req.getSession().getAttribute("userLogin").toString());
            req.setAttribute("contracts", Contracts);
            req.getRequestDispatcher("/pages/customer/main.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ContractService contractService = new ContractService();
        ItemService itemService = new ItemService();
        List<Task> listOfTasks = new ArrayList<>();
        int totalAmount = 0;
        for(int i=1; i<=itemService.getIDOfLastItem(); i++){
            if(req.getParameter("nameOf"+i)!=null && Integer.parseInt(req.getParameter("numberOf" + i))!=0) {
                Task task = new Task(-1,  req.getParameter("name")+ "-" + req.getParameter("nameOf"+i), -1, new Item(i,req.getParameter("nameOf"+i)), Integer.parseInt(req.getParameter("numberOf" + i)), false);
                listOfTasks.add(task);
                totalAmount += Integer.parseInt(req.getParameter("numberOf" + i));
            }
        }
        Contract contract = new Contract(
            contractService.getLastID()+1,
            req.getParameter("name"),
            Date.valueOf(req.getParameter("deadline")),
            null,
            req.getParameter("consLogin"),
            0,
            1
        );
        contract.setTasks(listOfTasks);
        if(totalAmount==0){
            showError(req, resp, contract, "Добавьте в заказ хотя бы одно изделие");
        }
        else if(contract.getDeadline().getTime()<System.currentTimeMillis()){
            showError(req, resp, contract, "Дедлайн не может быть меньше текущей даты");
        }
        else {
            contractService.saveContract(contract);
            resp.sendRedirect(req.getContextPath() + "/customer?action=show");
        }
    }

    private void showError(HttpServletRequest req,HttpServletResponse resp, Contract contract, String errorMessage) throws ServletException, IOException{
        ItemService itemService = new ItemService();
        List<Item> items = itemService.getItems();
        req.setAttribute("errorText", errorMessage);
        req.setAttribute("name", contract.getName());
        req.setAttribute("deadline", contract.getDeadline());
        req.setAttribute("items", items);
        req.getRequestDispatcher("pages/customer/add.jsp").forward(req,resp);
    }
}
