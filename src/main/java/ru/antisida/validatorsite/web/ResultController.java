package ru.antisida.validatorsite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antisida.validatorsite.model.SimpleWay;
import ru.antisida.validatorsite.repo.ResultRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/")
    public String getWays(Model model) {
        List<SimpleWay> simpleWays = resultRepository.getAll();
        model.addAttribute("ways", simpleWays);
        return "way";
    }
}

