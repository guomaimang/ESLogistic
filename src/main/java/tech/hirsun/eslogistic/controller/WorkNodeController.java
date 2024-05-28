package tech.hirsun.eslogistic.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/worknode")
public class WorkNodeController {

    @GetMapping("/list")
    public Result list() {

    }

    @GetMapping("/info")
    public Result info() {

    }

    @GetMapping("/count")
    public Result count() {

    }

}
