package tech.hirsun.eslogistic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.hirsun.eslogistic.result.Result;
import tech.hirsun.eslogistic.service.WorkNodeService;

@Slf4j
@RestController
@RequestMapping("/worknode")
public class WorkNodeController {

    @Autowired
    private WorkNodeService workNodeService;

    @GetMapping("/list")
    public Result list(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                       @RequestParam(name = "id", required = false) String id) {
        return Result.success(workNodeService.list(id, pageNum, pageSize));
    }

    @GetMapping("/info")
    public Result info(@RequestParam String id) {
        return Result.success(workNodeService.info(id));
    }

    @GetMapping("/count")
    public Result count() {
        return Result.success(workNodeService.count());
    }

}
