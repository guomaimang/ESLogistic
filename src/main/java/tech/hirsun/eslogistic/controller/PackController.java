package tech.hirsun.eslogistic.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.hirsun.eslogistic.result.Result;
import tech.hirsun.eslogistic.service.PackService;

@Slf4j
@RestController
@RequestMapping("/pack")
public class PackController {

    @Autowired
    PackService packService;

    @GetMapping("/list")
    public Result list(){

    }

    @GetMapping("/info")
    public Result info() {

    }

    @PostMapping("/create")
    public Result create() {

    }

    @GetMapping("/count")
    public Result count() {

    }
}
