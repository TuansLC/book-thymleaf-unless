package com.fpt.controller.admin;

import com.fpt.config.ProjectConfig;
import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.service.admin.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS)
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            Model model) {
        Page<Author> authorPage = authorService.findAll(PageRequest.of(page - 1, limit));
        model.addAttribute("authors", authorPage.getContent());
        model.addAttribute("currentPage", authorPage.getPageable().getPageNumber() + 1);
        model.addAttribute("limit", authorPage.getPageable().getPageSize());
        model.addAttribute("totalPage", authorPage.getTotalPages());
        return "admin/author/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable long id, Model model) {
        Author author = authorService.getById(id);
        if (author == null) {
            return "404";
        }
        model.addAttribute("author", author);
        return "admin/author/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        return "admin/author/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(@Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/author/create";
        }
        authorService.save(author);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (StringUtils.isEmpty(keyword)) {
            return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
        }
        model.addAttribute("authors", authorService.search((keyword)));
        return "admin/author/list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) long id, RedirectAttributes redirectAttributes) {
        authorService.delete(id);
        redirectAttributes.addFlashAttribute("Success!", "Deleted contact successfully!");
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update/{id}")
    public String updateAuthor(@PathVariable long id, Model model) {
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "admin/author/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public String update(@PathVariable long id, Author author) {
        authorService.update(id, author);
        return "redirect:" + ProjectConfig.PREFIX_ADMIN + ProjectConfig.PREFIX_ADMIN_AUTHORS;
    }
}
