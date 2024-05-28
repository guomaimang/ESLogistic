package tech.hirsun.eslogistic.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.hirsun.eslogistic.pojo.bo.Pack;
import tech.hirsun.eslogistic.result.Result;
import tech.hirsun.eslogistic.service.PackService;

@Slf4j
@RestController
@RequestMapping("/pack")
public class PackController {

    @Autowired
    PackService packService;

    @GetMapping("/list")
    public Result list(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                       @RequestParam(name = "id", required = false) Long id,
                       @RequestParam(name = "keyword", required = false) String keyword,
                       @RequestParam(name = "status", required = false) Integer status) {
        return Result.success(packService.list(id, keyword, pageNum, pageSize, status));
    }

    @GetMapping("/info")
    public Result info(@RequestParam Long id) {
        return Result.success(packService.info(id));
    }

    @GetMapping("/records")
    public Result records(@RequestParam Long id,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize) {
        return Result.success(packService.getPackRecords(id, pageNum, pageSize));
    }

    @PostMapping("/create")
    public Result create(@RequestBody Pack pack) {
        packService.create(pack);
        return Result.success();
    }

    @GetMapping("/count")
    public Result count() {
        return Result.success(packService.count());
    }
}
