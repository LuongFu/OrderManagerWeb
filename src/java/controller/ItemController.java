/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import constant.MessageConstant;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import model.Item;
import service.ItemService;

/**
 *
 * @author Fu
 */
@WebServlet(name = "ItemController", urlPatterns = {"/item-manager"})
public class ItemController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemService itemService = new ItemService();
        List<Item> listItem = (List<Item>) itemService.getAllItems();

        HttpSession session = request.getSession();
        session.setAttribute("ListItem", listItem);
        request.getRequestDispatcher(UrlConstant.ITEM_MANGAGER_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idItem = request.getParameter("idItem");

        System.out.println("DEBUG: Action is: " + action);

        if (action == null) {
            System.out.println("DEBUG: Action is null");
        }

        // Add null checks for parameters
        String nameItem = request.getParameter("nameItem");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String createdBy = request.getParameter("createdBy");
        String image = request.getParameter("image");

        // Log parameters to check for null
        System.out.println("DEBUG: nameItem: " + nameItem);
        System.out.println("DEBUG: price: " + price);
        System.out.println("DEBUG: description: " + description);
        System.out.println("DEBUG: createdBy: " + createdBy);
        System.out.println("DEBUG: image: " + image);

        if (nameItem == null || price == null || description == null || createdBy == null || image == null) {
            System.out.println("DEBUG: One or more parameters are null");
        }

        // Check for action and call the respective method
        switch (action) {
            case "edit":
                postEdit(request, response, idItem);
                break;
            case "delete":
                postDelete(request, response);
                break;
            case "add":
                postAdd(request, response);
                break;
            case "update":
                postUpdate(request, response, idItem);
        }
    }

    protected void postUpdate(HttpServletRequest request, HttpServletResponse response, String idItem)
            throws ServletException, IOException {

    }

    protected void postEdit(HttpServletRequest request, HttpServletResponse response, String idItem)
            throws ServletException, IOException {
        // upload item needs to upload for request
        ItemService itemService = new ItemService();
        Item item = itemService.getItemById(Integer.parseInt(idItem));
        request.setAttribute("item", item);
        request.getRequestDispatcher(UrlConstant.ITEM_EDIT_URL).forward(request, response);
    }

    protected void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idItem = request.getParameter("idItem");
        ItemService itemService = new ItemService();
        boolean isDeleted = itemService.deleteItem(Integer.parseInt(idItem));

        if (isDeleted) {
            request.setAttribute("message", MessageConstant.DELETED_SUCCESS);
        } else {
            request.setAttribute("message", MessageConstant.MESSAGE_FAILED);
        }
        request.getRequestDispatcher(UrlConstant.ITEM_MANGAGER_URL).forward(request, response);
    }

    protected void postAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve data directly from the request
        String nameItem = request.getParameter("nameItem");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String createdBy = request.getParameter("createdBy");
        Integer itemCreatedBy = Integer.parseInt(createdBy);

        if (nameItem == null || price == null || description == null || createdBy == null) {
            request.setAttribute("error", "All fields must be filled out.");
            request.getRequestDispatcher(UrlConstant.ITEM_MANGAGER_URL).forward(request, response);
            return;
        }

        BigDecimal itemPrice = new BigDecimal(price);

        // Create a new item object
        Item item = new Item(nameItem, itemPrice, description, itemCreatedBy);

        // Add the item using ItemService
        ItemService itemService = new ItemService();
        boolean isAdded = itemService.addItem(item);

        if (isAdded) {
            request.setAttribute("message", MessageConstant.ADDED_SUCCESS);
        } else {
            request.setAttribute("message", MessageConstant.MESSAGE_FAILED);
        }

        // Forward to item-manager.jsp to display message
        request.getRequestDispatcher(UrlConstant.ITEM_MANGAGER_URL).forward(request, response);
    }

    protected void postUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve data from the form
        String nameItem = request.getParameter("nameItem");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String createdBy = request.getParameter("createdBy");
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        BigDecimal itemPrice = new BigDecimal(price);

        // Create the updated item object
        Item item = new Item(itemId, nameItem, itemPrice, description, Integer.parseInt(createdBy));

        // Update the item using ItemService
        ItemService itemService = new ItemService();
        boolean isUpdated = itemService.updateItem(item);

        if (isUpdated) {
            request.setAttribute("message", MessageConstant.ADDED_SUCCESS);
        } else {
            request.setAttribute("message", MessageConstant.MESSAGE_FAILED);
        }

        // Forward to item-manager.jsp to display message
        request.getRequestDispatcher(UrlConstant.ITEM_MANGAGER_URL).forward(request, response);
    }
    
}
