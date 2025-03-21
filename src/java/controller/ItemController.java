package controller;

import constant.MessageConstant;
import model.Item;
import service.ItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ItemController", urlPatterns = {"/item-manager"})
public class ItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        ItemService itemService = new ItemService();

        switch (action != null ? action : "") {
            case "LIST":
                // List all items
                List<Item> itemList = itemService.getAllItems();
                request.setAttribute("ListItem", itemList);
                request.getRequestDispatcher(UrlConstant.ITEM_MANGAGER_URL).forward(request, response);
                break;
            case "UPDATE":
                // Get the item details to update
                int updateItemId = Integer.parseInt(request.getParameter("itemId"));
                Item item = itemService.getItemById(updateItemId);
                request.setAttribute("item", item);
                request.getRequestDispatcher(UrlConstant.ITEM_EDIT_URL).forward(request, response);
                break;
            case "DELETE":
                // Delete the item by ID
                int deleteItemId = Integer.parseInt(request.getParameter("itemId"));
                boolean isDeleted = itemService.deleteItem(deleteItemId);
                if (isDeleted) {
                    response.sendRedirect("item-manager?action=LIST");
                } else {
                    response.getWriter().println("Cannot delete the item.");
                }
                break;
            default:
                response.getWriter().println("Invalid action.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String itemId = request.getParameter("itemId");

        switch (action != null ? action : "") {
            case "ADD":
                // Handle adding new item
                postAdd(request, response);
                break;
            case "UPDATE":
                // Handle updating existing item
                postUpdate(request, response, itemId);
                break;
            default:
                response.getWriter().println("Invalid action.");
        }
    }

    // Add new item
    protected void postAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameItem = request.getParameter("nameItem");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String createdBy = request.getParameter("createdBy");

        if (nameItem == null || price == null || description == null || createdBy == null) {
            request.setAttribute("error", MessageConstant.NULL_ALARM);
            request.getRequestDispatcher(UrlConstant.ITEM_ADD_URL).forward(request, response);
            return;
        }

        double itemPrice = Double.parseDouble(price);
        Item item = new Item(nameItem, itemPrice, description, createdBy);

        ItemService itemService = new ItemService();
        boolean isAdded = itemService.addItem(item);

        if (isAdded) {
            request.setAttribute("message", MessageConstant.ADDED_SUCCESS);
        } else {
            request.setAttribute("message", MessageConstant.ADDED_FAILED);
        }

        request.getRequestDispatcher("addItem.jsp").forward(request, response);
    }

    // Update item
    protected void postUpdate(HttpServletRequest request, HttpServletResponse response, String itemId)
            throws ServletException, IOException {
        String nameItem = request.getParameter("nameItem");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String createdBy = request.getParameter("createdBy");

        double itemPrice = Double.parseDouble(price);

        Item item = new Item(Integer.parseInt(itemId), nameItem, itemPrice, description, createdBy);
        ItemService itemService = new ItemService();
        boolean isUpdated = itemService.updateItem(item);

        if (isUpdated) {
            response.sendRedirect("item-manager?action=LIST");
        } else {
            request.setAttribute("message", MessageConstant.UPDATED_FAILED);
        }

//        request.getRequestDispatcher("itemManager.jsp").forward(request, response);
    }

    // Delete item
    protected void postDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        ItemService itemService = new ItemService();
        boolean isDeleted = itemService.deleteItem(Integer.parseInt(itemId));

        if (isDeleted) {
            request.setAttribute("message", MessageConstant.DELETED_SUCCESS);
        } else {
            request.setAttribute("message", MessageConstant.DELETED_FAILED);
        }
        request.getRequestDispatcher(UrlConstant.ITEM_MANGAGER_URL).forward(request, response);
    }
}
