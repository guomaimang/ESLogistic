package tech.hirsun.eslogistic.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.hirsun.eslogistic.pojo.po.DBPack;
import tech.hirsun.eslogistic.result.ErrorMessage;
import tech.hirsun.eslogistic.result.Result;
import tech.hirsun.eslogistic.service.PackService;
import tech.hirsun.eslogistic.service.WorkNodeService;

@Slf4j
@RestController
@RequestMapping("/pack")
public class PackController {

    @Autowired
    PackService packService;

    @Autowired
    private WorkNodeService workNodeService;

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
    public Result create(@RequestBody DBPack dbPack) {
        packService.create(dbPack);

        if (dbPack.getSenderName() == null || dbPack.getSenderName().equals("")) {
            return Result.error(new ErrorMessage(60001, "Sender name is required"));
        }else if (dbPack.getSenderPhone() == null || dbPack.getSenderPhone().equals("")) {
            return Result.error(new ErrorMessage(60002, "Sender phone is required"));}
        else if (dbPack.getReceiverName() == null || dbPack.getReceiverName().equals("")) {
            return Result.error(new ErrorMessage(60003, "Receiver name is required"));
        }else if (dbPack.getReceiverPhone() == null || dbPack.getReceiverPhone().equals("")) {
            return Result.error(new ErrorMessage(60004, "Receiver phone is required"));
        }

        if (dbPack.getPackType() != 1 && dbPack.getPackType() != 2) {
            return Result.error(new ErrorMessage(60005, "Pack type is required"));
        }

        // check if the sender work node exists in the worknode map keys
        if (!workNodeService.getWorkNodesMap().containsKey(dbPack.getSenderWorkNodeId())) {
            return Result.error(new ErrorMessage(60006, "Sender work node is not correct"));
        }else if (!workNodeService.getWorkNodesMap().containsKey(dbPack.getReceiverWorkNodeId())) {
            return Result.error(new ErrorMessage(60007, "Receiver work node is not correct"));
        }

        return Result.success();
    }

    @GetMapping("/count")
    public Result count() {
        return Result.success(packService.count());
    }
}
