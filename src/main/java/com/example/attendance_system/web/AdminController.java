package com.example.attendance_system.web;

import com.example.attendance_system.user.dto.CreateLecturerRequest;
import com.example.attendance_system.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/lecturers/new")
    public String showCreateLecturerForm(Model model) {
        model.addAttribute("lecturerRequest", new CreateLecturerRequest());
        return "admin/new-lecturer";
    }

    @PostMapping("/lecturers")
    public String createLecturer(
            @Valid @ModelAttribute("lecturerRequest")
            CreateLecturerRequest request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "admin/new-lecturer";
        }

        try {
            userService.createLecturer(request);
        } catch (IllegalArgumentException exception) {
            model.addAttribute("creationError", exception.getMessage());
            return "admin/new-lecturer";
        }

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Lecturer account created successfully."
        );

        return "redirect:/admin";
    }
}
