//package in.DAA.PhotoPlaza.controllers;
//
//import in.DAA.PhotoPlaza.dto.BusinessAccountDto;
//import in.DAA.PhotoPlaza.entites.User;
//import in.DAA.PhotoPlaza.services.BusinessAccountService;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class BusinessAccountController {
//
//    private final BusinessAccountService businessAccountService;
//
//    public BusinessAccountController(BusinessAccountService businessAccountService) {
//        this.businessAccountService = businessAccountService;
//    }
//
//    @GetMapping("/businessAccount")
//    public String showForm(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("seller", new BusinessAccountDto());
//        return "business-account-form"; // Your HTML template name
//    }
//
//    @PostMapping("/businessAccount")
//    public String createBusinessAccount(
//            @AuthenticationPrincipal User user,
//            BusinessAccountDto businessAccountDto) {
//
//        businessAccountService.createBusinessAccount(businessAccountDto, user);
//        return "redirect:/dashboard";
//    }
//}