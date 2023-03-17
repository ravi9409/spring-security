package com.springsecurity.implement;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @GetMapping("/")
    public String showIndexPage(Model model, HttpSession session) {
        System.out.println("** showIndexPage **");
        List<String> myroles=getUserRoles();
        session.setAttribute("MyRoles", myroles);
        return "index";
    }
    @GetMapping("/viewBooks")
    public String showViewBooksPage(Model model) {
        System.out.println("** showViewBooksPage **");
        return "viewBooks";
    }
    @GetMapping("/addBook")
    public String showAddBookPage(Model model) {
        System.out.println("** showAddBookPage **");
        return "addBook";
    }
    @GetMapping("/editBook")
    public String showEditBookPage(Model model) {
        System.out.println("** showEditBookPage **");
        return "editBook";
    }
    @GetMapping("/deleteBook")
    public String showDeleteBookPage(Model model) {
        System.out.println("** showDeleteBookPage **");
        return "deleteBook";
    }
    @GetMapping("/placeOrder")
    public String showPlaceOrderPage(Model model) {
        System.out.println("** showPlaceOrderPage **");
        return "placeOrder";
    }
    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,HttpSession session) {
        System.out.println("** loginPage **");
        return "mylogin";
    }
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse
            response) {
        System.out.println("** logoutPage **");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    private List<String> getUserRoles() {
        List<String> myroles = new ArrayList<String>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<GrantedAuthority> col = (Collection<GrantedAuthority>) auth.getAuthorities();
            for (GrantedAuthority gauth : col)
                myroles.add(gauth.getAuthority());
        }
        return myroles;
    }
}